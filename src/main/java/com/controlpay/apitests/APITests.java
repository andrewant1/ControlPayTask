package com.controlpay.apitests;

import com.controlpay.api.models.Login;
import com.controlpay.api.models.Token;
import com.controlpay.api.models.UserProfile;
import com.controlpay.configuration.ApiConfiguration;
import com.controlpay.data.Users;
import com.controlpay.data.UsersDataProvider;
import com.controlpay.helpers.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class APITests extends ApiConfiguration  {
    static Token tokenContainer;
    static UserProfile userProfileContainer;
    ObjectMapper mapper = new ObjectMapper();
    SoftAssert softAssert = new SoftAssert();
    private String token;
    private String tokenType;


    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class,priority = 1)
    public void loginTest(final Users userData){
        Login login = new Login(userData.getLogin(), userData.getPassword());
        String response = given(requestSpec)
                .header("Authorization", "Basic YWxhZGRpbjpvcGVuc2VzYW1l")
                .body(login)
                .when()
                .post("/token")
                .then()
                .statusCode(200).toString();
        try {
            tokenContainer = mapper.readValue(response, Token.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        token = tokenContainer.getAccessToken();
        tokenType = tokenContainer.getTokenType();

    }

    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class,priority = 2,dependsOnMethods = "loginTest")
    public void checkPermissions(final Users userData){
        String decodedToken = Utils.base64Decode(token);
        String permissions = Utils.getPermissionsFromToken(decodedToken);
        Assert.assertEquals(permissions,userData.getPermissions());

    }



    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class,priority = 3,dependsOnMethods = "loginTest")
    public void profileTest(final Users userData){
       String profileResponse =
        given(requestSpec)
        .header("Authorization", token+tokenType)
        .when()
        .get("/profile")
        .then().statusCode(200).toString();

        try {
            userProfileContainer = mapper.readValue(profileResponse, UserProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        softAssert.assertEquals(userData.getFirstname(), userProfileContainer.getFirstname());
        softAssert.assertEquals(userData.getLastname(), userProfileContainer.getLastname());
        softAssert.assertEquals(userData.getLogin(), userProfileContainer.getEmail());
        softAssert.assertEquals(userData.getAge(), userProfileContainer.getAge());
        softAssert.assertEquals(userData.getUserRole(), userProfileContainer.getUserRole());
        softAssert.assertAll();
    }
}

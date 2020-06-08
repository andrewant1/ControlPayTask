package com.controlpay.apitests;

import com.controlpay.api.controllers.UserController;
import com.controlpay.api.models.Login;
import com.controlpay.api.models.Token;
import com.controlpay.api.models.UserProfile;
import com.controlpay.data.Users;
import com.controlpay.data.UsersDataProvider;
import com.controlpay.helpers.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.controlpay.apitests.APITests.userProfileContainer;


public class ApiTestsModified {
    private String token;
    private String tokenType;
    SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class,priority = 1)
    public void loginTest(final Users userData) {
        Login testLogin = new Login(userData.getLogin(), userData.getPassword());
        Token tokenResponse  = new UserController(testLogin).getToken();
        token = tokenResponse.getAccessToken();
        tokenType = tokenResponse.getTokenType();
    }

    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class,priority = 2,dependsOnMethods = "loginTest")
    public void checkPermissions(final Users userData){
        String decodedToken = Utils.base64Decode(token);
        String permissions = Utils.getPermissionsFromToken(decodedToken);
        Assert.assertEquals(permissions,userData.getPermissions());

    }

    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class,priority = 3,dependsOnMethods = "loginTest")
    public void profileTest(final Users userData) {
        Login testLogin = new Login(userData.getLogin(), userData.getPassword());
        UserProfile userProfileResponse = new UserController(testLogin).getUserProfile(token,tokenType);

        softAssert.assertEquals(userData.getFirstname(), userProfileResponse.getFirstname());
        softAssert.assertEquals(userData.getLastname(), userProfileResponse.getLastname());
        softAssert.assertEquals(userData.getLogin(), userProfileResponse.getEmail());
        softAssert.assertEquals(userData.getAge(), userProfileResponse.getAge());
        softAssert.assertEquals(userData.getUserRole(), userProfileResponse.getUserRole());
        softAssert.assertAll();

    }
}

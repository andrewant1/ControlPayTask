package practise;

import com.controlpay.api.models.Login;
import com.controlpay.api.models.Token;
import com.controlpay.helpers.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static net.jadler.Jadler.*;

public class TestPermissions {
    ObjectMapper mapper = new ObjectMapper();
    Login loginUser = new Login("andrew","qwerty1");
    Token tokenUser = new Token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImouZG9lQHRlc3QuY29tIiwibmFtZSI6IkpvaG4gRG9lIiwicGVybWlzc2lvbiI6WyJSRUFEIiwiQ1JFQVRFIiwiREVMRVRFIl19.bRS_8EBE9Cc6EqZ00CprRvJC3Ik1VbqW63yxdZr50ak",3600,"bearer");

     private String tokenString;
     private String loginUserString="";
     private static Token tokenContainer;
     private String userToken = "";

    {

        try {
            tokenString = mapper.writeValueAsString(tokenUser);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {

        initJadlerListeningOn(8081);
    }

    @AfterClass
    public void tearDown() {
        closeJadler();
    }

    @Test
    public void getAccount() {
        onRequest()
               .havingMethodEqualTo("POST")
                // .havingPathEqualTo("/accounts/" + ID)
                .respond()
                .withBody(tokenString)
                .withContentType("application/json; charset=UTF-8")
                .withStatus(200);

        System.out.println(tokenString);

        String tokenResponse =
                given()
                        .contentType(ContentType.JSON)
                        .baseUri("http://localhost")
                        .port(8081)
                        .when()
                        .body(loginUser)
                        .log()
                        .all()
                        .post("/token")
                        .asString();

        System.out.println(tokenResponse);

        try {
            tokenContainer = mapper.readValue(tokenResponse, Token.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        userToken = tokenContainer.getAccessToken();
        System.out.println(userToken);
        String decodedToken = Utils.base64Decode(userToken);
        String permissions = Utils.getPermissionsFromToken(decodedToken);
        System.out.println(permissions);
        Assert.assertEquals(permissions,"\"READ\",\"CREATE\",\"DELETE\"");

    }
}

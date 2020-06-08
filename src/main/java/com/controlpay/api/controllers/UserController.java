package com.controlpay.api.controllers;

import com.controlpay.api.models.Login;
import com.controlpay.api.models.Token;
import com.controlpay.api.models.UserProfile;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UserController {


    private RequestSpecification requestSpecification;
    private Login login;


    public UserController(Login testLogin) {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://testapp.com")
                .setContentType(ContentType.JSON)
                .build();
        new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

        RestAssured.defaultParser = Parser.JSON;
    }

      public Token  getToken(){
          return given(requestSpecification)
                  .header("Authorization", "Basic YWxhZGRpbjpvcGVuc2VzYW1l")
                  .body(login)
                  .post("/token")
                  .then()
                  .statusCode(200)
                  .and()
                  .extract().response().as(Token.class);
      }

      public UserProfile getUserProfile(String token, String tokenType){
          return given(requestSpecification)
                  .header("Authorization", token+tokenType)
                  .get("/profile")
                  .then()
                  .statusCode(200)
                  .and()
                  .extract().response().as(UserProfile.class);
      }


}

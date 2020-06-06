package com.controlpay.configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfiguration {
    public RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://testapp.com")
            .setRelaxedHTTPSValidation()
            .build();
}



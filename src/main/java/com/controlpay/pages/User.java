package com.controlpay.pages;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class User {


    public void login(String username, String password) {
        $("#login").setValue(username);
        $("#password").setValue(password);
        $("#loginButton").click();
    }

    public void openMainPage() {
        open("/");
    }

}

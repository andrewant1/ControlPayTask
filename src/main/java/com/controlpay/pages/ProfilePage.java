package com.controlpay.pages;
import   com.codeborne.selenide.WebDriverRunner;
import java.io.File;
import static com.codeborne.selenide.Selenide.$;


public class ProfilePage {

    public String getProfilePageURL(){
      String  pageURL= WebDriverRunner.url();
      return  pageURL;
    }
    public void uploadImage(String pathname){
        $("#image").uploadFile(new File(pathname));
    }
}

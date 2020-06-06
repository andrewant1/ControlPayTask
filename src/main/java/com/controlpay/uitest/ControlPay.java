package com.controlpay.uitest;


import com.controlpay.configuration.BaseTest;
import com.controlpay.data.Users;
import com.controlpay.data.UsersDataProvider;
import com.controlpay.pages.ProfilePage;
import com.controlpay.pages.User;
import org.testng.Assert;
import org.testng.annotations.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;


public class ControlPay extends BaseTest {


    @Test(dataProvider = "getData",dataProviderClass = UsersDataProvider.class)

    public void e2etest(final Users userData){
       User user = new User();
       user.openMainPage();
       user.login(userData.getLogin() ,userData.getPassword());
       ProfilePage page =new ProfilePage();
        Assert.assertEquals(page.getProfilePageURL(),"Profile URL");
        page.uploadImage("profile.jpg");
         if  ($("#link").shouldHave(text("profile.jpg")).isDisplayed()) {
             assertTrue(true, "Profile picture is Uploaded");
        }
    }
}

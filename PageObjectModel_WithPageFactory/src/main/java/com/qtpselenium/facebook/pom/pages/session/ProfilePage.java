package com.qtpselenium.facebook.pom.pages.session;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {


    public ProfilePage(WebDriver driver, ExtentTest test){

        super(driver, test);
    }

    public void verifyProfile(){

        test.log(LogStatus.INFO, "Verifying Profile");
    }


}

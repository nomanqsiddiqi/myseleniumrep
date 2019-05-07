package com.qtpselenium.facebook.pom.pages;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LaunchPage extends BasePage {

   public LaunchPage (WebDriver driver, ExtentTest test){

       super(driver, test);
       //driver.get("https://www.google.com/gmail/about/");
   }

   public LoginPage gotoLoginPage(){
       test.log(LogStatus.INFO, "Opening the URL - " + FBConstants.HOMEPAGE_URL);
       driver.get(FBConstants.HOMEPAGE_URL);
       test.log(LogStatus.PASS, "URL opened" + FBConstants.HOMEPAGE_URL);
       LoginPage loginPage = new LoginPage(driver, test);
       PageFactory.initElements(driver, loginPage);
       return loginPage;
   }


}

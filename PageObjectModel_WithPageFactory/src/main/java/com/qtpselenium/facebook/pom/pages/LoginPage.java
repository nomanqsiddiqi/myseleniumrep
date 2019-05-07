package com.qtpselenium.facebook.pom.pages;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath= FBConstants.LOGIN_USERNAME)
    public WebElement email;

    @FindBy(xpath=FBConstants.LOGIN_PASSWORD)
    public WebElement password;

    @FindBy(xpath=FBConstants.LOGIN_BUTTON)
    public WebElement LogIn;

    public  LoginPage(WebDriver driver, ExtentTest test){

        super(driver, test);
    }


     public Object doLogin(String usName, String pWord) throws InterruptedException {

        test.log(LogStatus.INFO, "Logging  in " + usName + " / " + pWord);
        email.sendKeys(usName);
        password.sendKeys(pWord);
        LogIn.click();
         boolean loginSuccess;
        if(isElementPresent(FBConstants.ACCOUNT_SETTING))loginSuccess = true;
        else loginSuccess = false;

        if(loginSuccess) {
            test.log(LogStatus.INFO, "Login Success");
            LandingPage landingPage = new LandingPage(driver, test);
            PageFactory.initElements(driver, landingPage);
            return landingPage;
        }else {
            test.log(LogStatus.INFO, "Login Not Successful");
            LoginPage loginPage = new LoginPage(driver, test);
            PageFactory.initElements(driver, loginPage);
            return loginPage;
        }
    }

}


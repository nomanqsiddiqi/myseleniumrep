package com.qtpselenium.facebook.pom.pages.session;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    @FindBy(xpath = FBConstants.WELCOME_PAGE)
    public WebElement profile;

    public LandingPage(WebDriver driver, ExtentTest test){

        super(driver, test);
    }

    public ProfilePage gotoProfilePage(){
        test.log(LogStatus.INFO, "Going to profile page");
        profile.click();
        ProfilePage profilePage = new ProfilePage(driver,test);
        PageFactory.initElements(driver, profilePage);
        return profilePage;
    }

}

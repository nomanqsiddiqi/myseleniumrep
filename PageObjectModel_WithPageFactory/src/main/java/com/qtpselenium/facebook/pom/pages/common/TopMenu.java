package com.qtpselenium.facebook.pom.pages.common;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TopMenu extends BasePage {
    @FindBy(xpath = FBConstants.NAVIGATION_LABEL)
    public WebElement navigationLabel;

    @FindBy(xpath = FBConstants.SETTING_LINK)
    public WebElement settings;


    public WebDriver driver;
    ExtentTest test;
    public TopMenu(WebDriver driver, ExtentTest test){
        this.driver = driver;
        this.test = test;

    }

    public void logout(){

    }

    public void gotoSettings(){
        test.log(LogStatus.INFO, "Going to settings");
        navigationLabel.click();
        settings.click();
        test.log(LogStatus.INFO, "Setting page opened");
    }

    public void search(){

    }
}

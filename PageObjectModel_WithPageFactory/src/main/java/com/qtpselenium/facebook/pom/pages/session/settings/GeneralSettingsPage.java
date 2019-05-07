package com.qtpselenium.facebook.pom.pages.session.settings;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GeneralSettingsPage extends BasePage {

    public String testCaseName = "ChangePasswordTest";
    @FindBy(xpath = FBConstants.CHANGE_PASSWD)
    public WebElement pwdChange;

    @FindBy(xpath = FBConstants.SETTING_LINK)
    public WebElement securityLink;

    @FindBy(xpath = FBConstants.EDIT_PASSWORD)
    public WebElement editPassword;

    @FindBy(xpath = FBConstants.OLD_PASSWORD)
    public WebElement oldPassword;

    @FindBy(xpath = FBConstants.NEW_PASSWORD)
    public WebElement newPassword;

    @FindBy(xpath = FBConstants.CONFIRM_PASSWORD)
    public WebElement confirmPassword;

    @FindBy(xpath = FBConstants.CONTINUE_BTN)
    public WebElement continueButton;

    @FindBy(xpath = FBConstants.PASSWORD_CHANGE_POPUP)
    public WebElement passwdChangePopup;

    @FindBy(xpath = FBConstants.CHANGE_PASSWD_BUTTON)
    public WebElement changePasswdButton;

    public GeneralSettingsPage(WebDriver driver, ExtentTest test){
        super(driver,test);
    }

    public void gotoPasswordChange(Object page){
        test.log(LogStatus.INFO, "Clicking on password change link");

        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(FBConstants.SECURITY_LINK)));
        element.click();
        test.log(LogStatus.INFO, "Security and Login link opened");
        if(!isElementPresent(FBConstants.CHANGE_PASSWD)){
            reportFailure("Element not found "+ FBConstants.CHANGE_PASSWD, page);
        }
        editPassword.click();
        test.log(LogStatus.INFO, "On password change page");
    }


    public String doPasswordChange(String oPassword,String nPassword) {
        test.log(LogStatus.INFO, "Changing Password");
        WebElement element = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(FBConstants.OLD_PASSWORD)));
        element.sendKeys(oPassword);
        newPassword.sendKeys(nPassword);
        confirmPassword.sendKeys(nPassword);
        continueButton.click();

        if(!isElementPresent(FBConstants.PASSWORD_CHANGE_POPUP)) return "Unsuccessful";

        WebElement radio1 = driver.findElement(By.xpath(FBConstants.REVIEW_SESSION));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", radio1);
        WebElement radio2 = driver.findElement(By.xpath(FBConstants.KEEP_SESSION));
        JavascriptExecutor exe = (JavascriptExecutor)driver;
        exe.executeScript("arguments[0].click();", radio2);
        changePasswdButton.click();
        return "Success";

    }




}

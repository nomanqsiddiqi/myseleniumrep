package com.qtpselenium.facebook.pom.base;

import com.qtpselenium.facebook.pom.pages.common.TopMenu;
import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.ProfilePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class BasePage {

    public WebDriver driver;
    public TopMenu menu;

    public ExtentTest test;

    public BasePage(){}

    public BasePage(WebDriver driver, ExtentTest test){

        this.driver = driver;
        this.test = test;
        menu = new TopMenu(driver, test);
        PageFactory.initElements(driver, menu);

    }

    public String verifyTitle(String expTitle) {
        test.log(LogStatus.INFO, "verifying the title " + driver.getTitle());
        return "";
    }
    public String verifyText(String locator, String expText){

        return "";
    }
    public boolean isElementPresent(String locator){
        test.log(LogStatus.INFO, "Trying to find a element ---> " + locator);
        int count = driver.findElements(By.xpath(locator)).size();
        System.out.println(locator + " --> size -->  " + count);
        if(count > 0) {
            test.log(LogStatus.INFO, "Element found");
            return true;
        }else{
            test.log(LogStatus.INFO, "Element not found");
            return false;
        }

    }
    public TopMenu getoMenu(){
        return menu;
    }

    public void takeScreenShot(Object obj){
        // fileName of the screenshot
        Date d = new Date();
        String objname = null;
        String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
        // store screenshot in that file
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filePath = FBConstants.REPORTS_PATH+"//screenshots//"+screenshotFile;
        try {
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (IOException e) {
            // TODO Auto-generated catcsh block
            e.printStackTrace();
        }
        //put screenshot file in reports
        if(obj instanceof LaunchPage){
            objname = "Launch Page";
        }else if(obj instanceof LoginPage){
            objname = "Login Page";
        }else if(obj instanceof ProfilePage){
            objname = "Profile Page";
        }else{
            objname = "Page not found:";
        }
        test.log(LogStatus.INFO,objname + test.addScreenCapture(filePath));

    }

    public void reportFailure(String failureMessage, Object obj){
        test.log(LogStatus.FAIL, failureMessage);
        takeScreenShot(obj);
        Assert.fail(failureMessage);
    }

}

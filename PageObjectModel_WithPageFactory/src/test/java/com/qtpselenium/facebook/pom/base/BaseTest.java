package com.qtpselenium.facebook.pom.base;

import com.qtpselenium.facebook.pom.util.Xls_Reader;
import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.ProfilePage;
import com.qtpselenium.facebook.pom.util.ExtentManager;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extent = ExtentManager.getInstance();
    public ExtentTest test;
    public Xls_Reader xls = new Xls_Reader(FBConstants.DATA_PATH);
    public void init(String bType) {
        test.log(LogStatus.INFO, "Openeing browser" + bType);
        if (bType.equals("Mozilla")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("permissions.default.desktop-notification", 1);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            System.setProperty("webdriver.gecko.driver", "C:\\Tools\\August 2018\\WebDriver\\geckodriver.exe");
            driver = new FirefoxDriver(capabilities);
            driver.manage().deleteAllCookies();
        } else if (bType.equals("Chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
            driver =new ChromeDriver(options);
        }
        test.log(LogStatus.INFO, "Opened browser successfully" + bType);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

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

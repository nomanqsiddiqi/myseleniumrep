package com.qtpselenium.facebook.pom.testcases;

import com.qtpselenium.facebook.pom.pages.session.settings.GeneralSettingsPage;
import com.qtpselenium.facebook.pom.base.BaseTest;
import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.util.DataUtil;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class ChangePasswordTest extends BaseTest {
    String testCaseName = "ChangePasswordTest";

    @Test(dataProvider="getData")
    public void changePasswdTest(Hashtable<String,String> data) throws InterruptedException {
        test = extent.startTest(testCaseName);
        if(!DataUtil.isTestExecutable(xls, testCaseName)|| data.get(FBConstants.TESTCASE_RUNMODE).equals("N")){
            test.log(LogStatus.SKIP, "Skipping the test case as Runmode is N");
            throw new SkipException("Skipping the test case as runmode is N");
        }
        test.log(LogStatus.INFO, " Starting test");
        init(data.get("Browser"));
        LaunchPage launchPage = new LaunchPage(driver, test);
        PageFactory.initElements(driver, launchPage);
        Thread.sleep(3000);
        launchPage.takeScreenShot(launchPage);
        LoginPage loginPage = launchPage.gotoLoginPage();
        test.log(LogStatus.INFO, "Logging in");
        Object page = loginPage.doLogin(data.get("Username"), data.get("OldPassword"));
        if (page instanceof LoginPage) {
            takeScreenShot(page);
            reportFailure("Could not Login", page);
        }
        LandingPage landingPage = (LandingPage) page;
        landingPage.getoMenu().gotoSettings();
        GeneralSettingsPage settingsPage = new GeneralSettingsPage(driver, test);
        PageFactory.initElements(driver, settingsPage);
        settingsPage.gotoPasswordChange(settingsPage);
        String result = settingsPage.doPasswordChange(data.get("OldPassword"), data.get("NewPassword"));
    }

    @AfterMethod
    public void quit(){
        if(extent != null){
            extent.endTest(test);
            extent.flush();
        }
        if(driver!=null)
            driver.quit();
    }

    @DataProvider
    public Object[][] getData(){
        return DataUtil.getData(xls, testCaseName);
    }

}

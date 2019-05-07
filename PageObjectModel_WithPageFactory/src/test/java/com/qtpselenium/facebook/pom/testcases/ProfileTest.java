package com.qtpselenium.facebook.pom.testcases;

import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.pages.session.ProfilePage;
import com.qtpselenium.facebook.pom.base.BaseTest;
import com.qtpselenium.facebook.pom.util.DataUtil;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;


public class ProfileTest extends BaseTest {
    String testCaseName = "ProfileTest";

    @Test(dataProvider="getData")
    public void testProfile(Hashtable<String, String > data) throws InterruptedException {

        test = extent.startTest(testCaseName);
        if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(FBConstants.TESTCASE_RUNMODE).equals("N")){
            test.log(LogStatus.SKIP, "Skipping the test case as Runmode is N");
            throw new SkipException("Skipping the test case as runmode is N");
        }

        test.log(LogStatus.INFO, "Profile Test");
        test.log(LogStatus.INFO, "Starting Profile Test");
        init(data.get("Browser"));
        LaunchPage launchPage = new LaunchPage(driver, test);
        PageFactory.initElements(driver, launchPage);

        LoginPage loginPage = launchPage.gotoLoginPage();
        loginPage.verifyTitle("Facebook Login");

        Object page = loginPage.doLogin(data.get("Username"), data.get("Password"));
        if(page instanceof LoginPage) {
           Assert.fail("Login failed");
        }else if( page instanceof LandingPage){
           System.out.println("Logged in successfully");
        }

        LandingPage landingPage = (LandingPage) page;
        ProfilePage profilePage = landingPage.gotoProfilePage();
        profilePage.verifyProfile();
        test.log(LogStatus.PASS, "Test Passed");;
        profilePage.takeScreenShot(profilePage);

    }

    @AfterMethod
    public void quit() {
        if (extent != null) {
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

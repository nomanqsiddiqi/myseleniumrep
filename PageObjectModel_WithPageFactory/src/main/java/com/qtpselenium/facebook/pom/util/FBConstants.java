package com.qtpselenium.facebook.pom.util;

public class FBConstants {

    //path
    public static final String CHROME_DRIVER_EXE = "C:\\Tools\\August 2018\\WebDriver\\chromedriver.exe";
    public static final String REPORTS_PATH = "C:\\report\\";
    public static final String DATA_PATH="C:\\Users\\Noman\\IdeaProjects\\LearnTestNG\\PageObjectModel_WithPageFactory\\PageObjectModel_WithPageFactory\\data\\Data.xlsx";

    //locator
    public static final String LOGIN_USERNAME = "//input[@id='email']";
    public static final String LOGIN_PASSWORD = "//input[@id='pass']";
    public static final String LOGIN_BUTTON   = "//input[@value='Log In']";
    public static final String WELCOME_PAGE   = "//div[@data-click='home_icon']";
    public static final String HOMEPAGE_URL  = "http://facebook.com";
    public static final String ACCOUNT_SETTING = "//div[@id='userNavigationLabel']";

    //Account Settings
    public static final String NAVIGATION_LABEL = "//div[@id='userNavigationLabel']";
    public static final String SETTING_LINK = "//span[text()='Settings']";
    public static final String CHANGE_PASSWD = "//span[text()='Change password']";
    public static final String SECURITY_LINK = "//*[@id='navItem_security']/a/div[2]/div";
    //testcase
    public static final String TESTCASE_RUNMODE="Runmode";
    public static final String SHEET_NAME="TestData";
    public static final String SUITE_NAME="TestCases";

    public static final String EDIT_PASSWORD = "//div[@id='SettingsPage_Content']/div/div/div/div[3]/div[2]/table/tbody/tr/td[3]/button";
    public static final String OLD_PASSWORD = "//*[@id='password_old']";
    public static final String NEW_PASSWORD = "//*[@id='password_new']";
    public static final String CONFIRM_PASSWORD = "//*[@id='password_confirm']";
    public static final String CONTINUE_BTN = "//input[@value='Save Changes']";
    public static final String PASSWORD_CHANGE_POPUP = "//h3[text()='Password Changed']" ;
    public static final String REVIEW_SESSION = "//input[@value='review_sessions']";
    public static final String KEEP_SESSION = "//input[@value='keep_sessions']";
    public static final String CHANGE_PASSWD_BUTTON = "//button[text()='Continue']";
}

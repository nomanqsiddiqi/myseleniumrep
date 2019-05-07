package com.qtpselenium.facebook.pom.util;

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html


import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            Date d=new Date();
            String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
            String reportPath =FBConstants.REPORTS_PATH + fileName;

            extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);


            extent.loadConfig(new File("C:\\Users\\Noman\\IdeaProjects\\LearnTestNG\\PageObjectModel_WithPageFactory\\PageObjectModel_WithPageFactory\\ReportsConfig.xml"));
            // optional
            extent.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo(
                    "Environment", "Prod");
        }
        return extent;
    }
}

package com.mystore.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extent; //populate common info on the report

//    ExtentSparkReporter->  UI of the report

    public static ExtentReports getReporter(){
        if(extent == null){
            String reportPath = System.getProperty("user.dir")+ "/test-output/ExtentReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();
            extent.attachReporter(spark);

        }
        return extent;
    }


}

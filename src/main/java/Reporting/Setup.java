package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Setup implements ITestListener {

    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public void onStart(ITestContext context)
    {

        String fileName = ExtentReportManager.getReportNameWithTime();

        String fullPath = System.getProperty("user.dir") + "/reports/" + fileName;

        extentReports = ExtentReportManager.createInstances(fullPath,"Test API automation report","Test Execution Report");

    }

    public void onFinish(ITestContext context)
    {

        if(extentReports != null)
        {
            extentReports.flush();
        }
        else {
            System.out.println("extentReports is null");
        }


    }

    public void onTestStart(ITestResult result)
    {

        ExtentTest test = extentReports.createTest("Test name "+ result.getMethod().getMethodName());
        extentTest.set(test);
    }


}

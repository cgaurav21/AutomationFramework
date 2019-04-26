package Listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utility.TestBase;
import utility.Utils;

public class Retry implements IRetryAnalyzer{
	private int count = 0;
    private static int maxTry = 1; //Run the failed test 2 times
 
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) {                            //Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
                System.out.println("Retrying......" + maxTry);
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                try {
					extendReportsFailOperations(iTestResult); //ExtentReports fail operations
				} catch (Exception e) {
					e.printStackTrace();
				}    
                return true;                                 //Tells TestNG to re-run the test
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
 
    public void extendReportsFailOperations (ITestResult iTestResult) throws Exception {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((TestBase) testClass).getDriver();
//        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
//        ExtentTestManager.getTest().log(Status.FAIL,"Test Failed",
//                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        String temp = Utils.takeScreenshot(webDriver, TestListener.getTestMethodName(iTestResult));
        ExtentTestManager.getTest().fail(iTestResult.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        ExtentTestManager.getTest().log(Status.FAIL, "FAILED!! Retrying...");
    }

}

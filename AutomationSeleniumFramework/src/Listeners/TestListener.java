package Listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import utility.TestBase;
import utility.Utils;

public class TestListener extends TestBase implements ITestListener {
	 static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
	 
	    @Override
	    public void onStart(ITestContext iTestContext) {
	        System.out.println("I am in onStart method " + iTestContext.getName());
	        iTestContext.setAttribute("WebDriver", this.driver);
	    }
	 
	    @Override
	    public void onFinish(ITestContext iTestContext) {
	        System.out.println("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for extentreports reporting!
	        ExtentTestManager.endTest();
	        ExtentManager.getExtent().flush();
	    }
	 
	    @Override
	    public void onTestStart(ITestResult iTestResult) {
	        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
	        ExtentTestManager.createTest(iTestResult.getMethod().getMethodName(), null);
	       
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
	        //Extentreports log operation for passed tests.
	        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	    }
	 
	    @Override
	    public void onTestFailure(ITestResult iTestResult) {
	    	System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
	    	 Object testClass = iTestResult.getInstance();
	         WebDriver webDriver = ((TestBase) testClass).getDriver();
//	         String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
//	         ExtentTestManager.getTest().log(Status.FAIL,"Test Failed",
//	                 ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	         
	        String temp;
			try {
				temp = Utils.takeScreenshot(webDriver, getTestMethodName(iTestResult));
				 ExtentTestManager.getTest().fail(iTestResult.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				 ExtentTestManager.getTest().log(Status.FAIL, "FAILED!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	         	
	    
	    }
	       /* System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
	        
	        ITestContext context = iTestResult.getTestContext();
			WebDriver driver = (WebDriver) context.getAttribute("driver");

			String targetLocation = null;

			String testClassName = iTestResult.getMethod().getMethodName();
			
			//String timeStamp = Util.getCurrentTimeStamp(); // get timestamp

			String testMethodName = iTestResult.getName().toString().trim();
			//String screenShotName = testMethodName + timeStamp + ".png";
			String screenShotName = testMethodName + ".png";
			String fileSeperator = System.getProperty("file.separator");
			String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
					+ "screenshots";
			 ExtentTestManager.getTest().log(Status.INFO, "Screen shots reports path - " + reportsPath);
			try {
				File file = new File(reportsPath + fileSeperator + testClassName); // Set
																					// screenshots
																					// folder
				if (!file.exists()) {
					if (file.mkdirs()) {
						ExtentTestManager.getTest().log(Status.INFO, "Directory: " + file.getAbsolutePath() + " is created!");
						
					} else {
						ExtentTestManager.getTest().log(Status.INFO, "Failed to create directory: " + file.getAbsolutePath());
					}

				}

				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
																												// location
				File targetFile = new File(targetLocation);
				ExtentTestManager.getTest().log(Status.INFO, "Screen shot file location - " + screenshotFile.getAbsolutePath());
				ExtentTestManager.getTest().log(Status.INFO, "Target File location - " + targetFile.getAbsolutePath());
				FileHandler.copy(screenshotFile, targetFile);

			} catch (FileNotFoundException e) {
				ExtentTestManager.getTest().log(Status.INFO, "File not found exception occurred while taking screenshot " + e.getMessage());
			} catch (Exception e) {
				ExtentTestManager.getTest().log(Status.INFO, "An exception occurred while taking screenshot " + e.getCause());
			}

			// attach screenshots to report
			try {
				ExtentTestManager.getTest().fail("Screenshot",
						MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
			} catch (IOException e) {
				ExtentTestManager.getTest().log(Status.INFO, "An exception occured while taking screenshot " + e.getCause());
		
			}
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		}
		*/
	 
	 
	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
	        //Extentreports log operation for skipped tests.
	        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	    }
	 
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	    }

}

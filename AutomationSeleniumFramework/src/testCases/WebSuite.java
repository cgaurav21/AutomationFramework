package testCases;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Listeners.ExtentManager;
import utility.Log;
import utility.TestBase;
import utility.Utils;


public class WebSuite extends TestBase{
	
	//public WebDriver driver;
	private static String testName;
	ExtentReports extent;
	ExtentTest test; 
	
	@BeforeMethod
	public void beforeMethod(Method testMethod) throws Exception{
		DOMConfigurator.configure("log4j.xml");
		String testName = testMethod.getName();
		Log.startTestCase(testName);
		//driver = Utils.OpenBrowser("chrome");
		extent = ExtentManager.getExtent();
		
	}

	
	@Test (priority = 1, description= "Search name test case google", enabled = true)
	public void SEARCH_NAME_TC(Method testMethod) throws InterruptedException{
			System.out.println("Test Print");
			Utils utils = new Utils(driver);
			String title = utils.getPageTitle();
			System.out.println(title);
			//Assert.assertTrue(driver.getTitle().contains("Google"));			
			//new LoginPagePF(driver).searchSomething();
			//Boolean test = utils.isElementPresent(new LoginPagePF(driver).getSearch());
			//utils.verifyPresent(new LoginPagePF(driver).getSearch());
			//test = extent.createTest(testMethod.getName(), "Search name test case google");

			Assert.assertEquals(driver.getTitle(), "ABC");
			//test.log(Status.PASS, "Page Title is not Google");

	}
	
	@Test (priority = 2, description= "Search name test case google 2", enabled = true)
	public void SEARCH_NAME_2_TC(Method testMethod) throws InterruptedException{
			//test = extent.createTest(testMethod.getName(), "Search name test case google 2");

			Assert.assertEquals(driver.getTitle(), "Google");
			//test.log(Status.PASS, "Page Title is Google");
			

	}
	
//	@AfterMethod
//	public void afterMethod(ITestResult result) throws Exception {
//		Log.endTestCase(testName);
//		if(result.getStatus() == ITestResult.FAILURE) {
//			String temp = Utils.takeScreenshot(driver, testName);
//			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
//			}
//		extent.flush();
//	}
} 
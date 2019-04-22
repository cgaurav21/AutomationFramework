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

	
	@Test (enabled = true)
	public void SEARCH_NAME_TC() throws InterruptedException{
			System.out.println("Test Print");
			Utils utils = new Utils(driver);
			String title = utils.getPageTitle();
//			String title = getDriver().getTitle();
			System.out.println(title);
			//Assert.assertTrue(driver.getTitle().contains("Google"));			
			//new LoginPagePF(driver).searchSomething();
			//Boolean test = utils.isElementPresent(new LoginPagePF(driver).getSearch());
			//utils.verifyPresent(new LoginPagePF(driver).getSearch());
			test = extent.createTest("Search Name TC", "Test google");
//			if(driver.getTitle().contains("abc")) {
//				//test.pass(driver.getTitle() + "contain" + "Google");
//				test.log(Status.PASS, driver.getTitle() + "contain" + "Google");
//			}
//			else {
//				//test.log(Status.FAIL, driver.getTitle() + "doesnt contain" + "Google");
//				test.fail(driver.getTitle() + "doesnt contain" + "Google");
//			
//			}
			Assert.assertEquals(driver.getTitle(), "ABC");
//			
//			
//			System.out.println("TEST LAST");
//			
		
			//driver.get("https://www.youtube.com");
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		Log.endTestCase(testName);
		if(result.getStatus() == ITestResult.FAILURE) {
			String temp = Utils.takeScreenshot(driver, testName);
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			}
		extent.flush();
	}
} 
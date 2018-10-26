package testCases;

import java.lang.reflect.Method;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pages.LoginPagePF;
import utility.Log;
import utility.TestBase;
import utility.Utils;


public class WebSuite extends TestBase{
	
	//public WebDriver driver;
	private String testname;
	ExtentReports report;
	ExtentTest logger; 
	
	@BeforeMethod
	public void beforeMethod(Method testMethod) throws Exception{
		DOMConfigurator.configure("log4j.xml");
		String testname = testMethod.getName();
		Log.startTestCase(testname);
		//driver = Utils.OpenBrowser("chrome");
		
	}

	
	@Test (enabled = true, testName = "test google")
	public void SEARCH_NAME_TC() throws InterruptedException{
			System.out.println("Test Print");
			Utils utils = new Utils(driver);
			String title = utils.getPageTitle();
//			String title = getDriver().getTitle();
			System.out.println(title);
			
			//new LoginPagePF(driver).searchSomething();
			//Boolean test = utils.isElementPresent(new LoginPagePF(driver).getSearch());
			utils.waitForElementIsPresent(new LoginPagePF(driver).getSearch());
			
			System.out.println("TEST LAST");
			
			driver.get("https://www.youtube.com");
	}
	
	
	@AfterMethod
	public void afterMethod() {
		Log.endTestCase(testname);
	}
	
}
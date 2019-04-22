/*package testCases;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class VerifyTitle
{

	ExtentReports report;
	ExtentTest logger; 
	WebDriver driver;

	
	
	@BeforeTest
    public void startReport(){
		report=new ExtentReports("/Users/gauravchandwani/eclipse-workspace/SeleniumFramework/Reports/extentreport.html", true);
		report.loadConfig(new File("configs//extent-config.xml"));
    }
	

	@Test
	public void verifyBlogTitle()
	{
		

		logger=report.startTest("verifyBlogTitle");

		driver=new ChromeDriver();

		driver.manage().window().maximize();

		logger.log(LogStatus.INFO, "Browser started ");

		driver.get("http://www.google.com");

		logger.log(LogStatus.INFO, "Application is up and running");

		String title=driver.getTitle();

		Assert.assertTrue(title.contains("Google")); 

		logger.log(LogStatus.PASS, "Title verified");
		
		report.endTest(logger);
		report.flush();
	}


	


}*/
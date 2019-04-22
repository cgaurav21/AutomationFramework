package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import dataProviders.ConfigFileReader;


public class Utils {
	
	public WebDriver driver;
	//static ConfigFileReader configfilereader;
	ConfigFileReader configfilereader = new ConfigFileReader();
	
	public Utils(WebDriver driver) {
		this.driver = driver;
	}
	
//	public static WebDriver OpenBrowser(String browserName) throws Exception{
//		configfilereader = new ConfigFileReader();
//		try{
//		if(browserName.equalsIgnoreCase("firefox")){
//		
//			driver = new FirefoxDriver();
//			Log.info("New driver instantiated");
//		    driver.manage().timeouts().implicitlyWait(configfilereader.getImplicitlyWait(), TimeUnit.SECONDS);
//		    Log.info("Implicit wait applied on the driver for " + configfilereader.getImplicitlyWait() +  " seconds");
//		    navigateToURL(configfilereader.getApplicationUrl());
//		    Log.info("Web application launched successfully");
//		}
//		else if(browserName.equalsIgnoreCase("chrome")){
//			driver = new ChromeDriver();
//			Log.info("New driver instantiated");
//			driver.manage().timeouts().implicitlyWait(configfilereader.getImplicitlyWait(), TimeUnit.SECONDS);
//		    Log.info("Implicit wait applied on the driver for " + configfilereader.getImplicitlyWait() +  " seconds");
//		    navigateToURL(configfilereader.getApplicationUrl());
//		    Log.info("Web application launched successfully");
//			
//		}
//		   
//		}catch (Exception e){
//			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
//		}
//		return driver;
//	}
//	
//	public static void navigateToURL(String URL) {
//		System.out.println("Navigating to: " + URL);
//		try {
//		driver.get(URL);
//		}catch(Exception e){
//			System.out.println("URL did not load: " + URL);
//		}
//	}
	

	 public static String takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception{
		 try{
			 File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 String path = Constant.Path_ScreenShot + "/src/Screenshots/" + sTestCaseName + ".png";
			 System.out.println(path);
			 File destination = new File(path);
			 FileUtils.copyFile(src, destination);
			 
			 return path;
		 } catch (Exception e){
			 Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
			 throw new Exception();
		 }
		 
	 }

	 public String getPageTitle() {
		 return driver.getTitle();
	 }

	 public void moveToElement(WebElement element) {
		 Actions action = new Actions(driver);
		 action.moveToElement(element).perform();
		 action.click();
	 }

	 public boolean verifyPresent(WebElement element){
		 try {
			 if(element.isDisplayed() || element.isEnabled()) {
				 Log.info(element + "is Present");
				 Reporter.log(element + "is Present");
				 return true;
			 }
		 } catch (Exception e) {
			 Log.error(e + "does not exist");
			 Reporter.log(e + "does not exist");
			 throw new AssertionError(element + "does not exist", e);
		 }
		return false;
	 }
	 
	 
	 public void waitForElementIsPresent(WebElement element) {
		 
		 try {
			 (new WebDriverWait(driver, configfilereader.getTimeout()))
			 .until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Log.exceptionInfo(e);
		}
	 }
	 
	 public void waitForElementIsNotPresent(WebElement element) {
		 try {
			 (new WebDriverWait(driver, configfilereader.getTimeout()))
			 .until(ExpectedConditions.invisibilityOf(element));
		 } catch (NoSuchElementException e1) {
			 Log.exceptionInfo(e1);
		 }
		 catch (TimeoutException e2) {
			 Log.exceptionInfo(e2);
		 }
	}
	
	 
	}

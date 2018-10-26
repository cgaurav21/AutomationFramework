package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import dataProviders.ConfigFileReader;

public class TestBase {
	
	public WebDriver driver;
    static ConfigFileReader configfilereader;
 
//    public WebDriver getDriver() {
//        return driver;
//    }
 
	@BeforeClass
    public void setup () {
       
        configfilereader = new ConfigFileReader();
        String browserName = configfilereader.getBrowserName();
               
		try{
		if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			Log.info("New driver instantiated");
			driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(configfilereader.getImplicitlyWait(), TimeUnit.SECONDS);
		    Log.info("Implicit wait applied on the driver for " + configfilereader.getImplicitlyWait() +  " seconds");
		    navigateToURL(configfilereader.getApplicationUrl());
		    Log.info("Web application launched successfully");
		}
		else if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
			Log.info("New driver instantiated");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(configfilereader.getImplicitlyWait(), TimeUnit.SECONDS);
		    Log.info("Implicit wait applied on the driver for " + configfilereader.getImplicitlyWait() +  " seconds");
		    navigateToURL(configfilereader.getApplicationUrl());
		    Log.info("Web application launched successfully");
		}
		   
		}catch (Exception e){
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
    }
    
    public void navigateToURL(String URL) {
    	System.out.println("Navigating to: " + URL);
    	try {
    		driver.get(URL);
    	}catch(Exception e){
    		System.out.println("URL did not load: " + URL);
    	}
    }

}

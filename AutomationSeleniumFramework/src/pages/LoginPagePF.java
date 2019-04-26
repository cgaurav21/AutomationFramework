package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import locators.Locators.testlocators;
import utility.TestBase;
import utility.Utils;

public class LoginPagePF extends TestBase implements testlocators{
	
	//WebDriver driver;
	
	public LoginPagePF(WebDriver driver){
		//this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(css="input#username")
//	WebElement username;
	
	@FindBy(name = LOC_SEARCH)
	WebElement search;
	
	@FindBy(id = LOC_SCROLL_TEST)
	WebElement scrollTest;
	
	public WebElement getSearch() {
		return search;
	}
	
	public WebElement getScrollTest() {
		return scrollTest;
	}
	
	
	public void searchSomething() {
		getSearch().sendKeys("Gaurav");
		getSearch().sendKeys(Keys.RETURN);
		new Utils(driver).moveToElement(getScrollTest());
		
	}
	
}
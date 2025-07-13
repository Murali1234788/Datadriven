package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "\"//span[text()='My Account']\"")
	private WebElement Myaccountdropmenu;
	
	@FindBy(linkText = "Login")
	private WebElement clickonaccdropmenu;
	
	public void Myaccountdropmenu() {
		Myaccountdropmenu.click();
	}
	
	public void clickonaccdropmenu()
	{
		clickonaccdropmenu.click();
	}
}

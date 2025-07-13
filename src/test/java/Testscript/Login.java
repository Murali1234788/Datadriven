package Testscript;

import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Handler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Base;
import Utils.DataUtil;
import Utils.MyXLSReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.HomePage;

public class Login extends Base {

	WebDriver driver;
	MyXLSReader excelreader;
	
	@Test(dataProvider = "Datareader")
	public void testlogin(HashMap<String,String> hmap)
	{
		if(!DataUtil.isRunnable(excelreader, "LoginTest", "Testcases") || hmap.get("Runmode").equals("N"))
		{
			throw new SkipException("Run mode is set to N , hence not executed");
		}
		
		driver = openbrowser(hmap.get("Browser"));
		
		HomePage hppage=new HomePage(driver);
		hppage.Myaccountdropmenu();
		hppage.clickonaccdropmenu();
		driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(hmap.get("Username"));
		//Bondaurundai@gmail.com , //Biryaniurundai@gmail.com
		driver.findElement(By.id("input-password")).sendKeys(hmap.get("Password"));
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		 
		
		String expectedResult = hmap.get("ExpectedResult");
		
		boolean expectedconvertedresult = false;
		
		if(expectedResult.equalsIgnoreCase("Success"))
		{
			expectedconvertedresult=true;
		}else if (expectedResult.equalsIgnoreCase("Failure")) {
			expectedconvertedresult=false;
		} 
		boolean actualresult = false;

		if (expectedconvertedresult) {
		    try {
		        actualresult = driver.findElement(By.linkText("Edit your account information")).isDisplayed();
		    } catch (Exception e) {
		        actualresult = false;
		    }
		}
		
		Assert.assertEquals( actualresult, expectedconvertedresult);
		
	}
	
	@DataProvider
	public Object[][] Datareader() 
	{
		Object[][] testData = null;
		try
		{
		excelreader= new MyXLSReader("src\\test\\resources\\TutorialsNinja.xlsx");
	testData = DataUtil.getTestData(excelreader, "LoginTest", "Data");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
		
		
	}
	
	@AfterMethod
	public void teardown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}
}

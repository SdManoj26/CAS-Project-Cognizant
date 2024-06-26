package Global_IT;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.ExcelUtilities;

public class BaseClass{
 
	public WebDriver driver;
	ExcelUtilities exUtil;
	
	@BeforeClass
	@Parameters({"br", "url"})
	void setupDriver(String browser, String URL) throws IOException
	{
		switch(browser)
		{
		case("chrome"):
			driver = new ChromeDriver();
			break;
		case("edge"):
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid");
		}
		driver.get(URL);
		driver.manage().window().maximize();
		exUtil = new ExcelUtilities();
		exUtil.createExcelFile();
		
	}
	
	@AfterSuite
	void closeDriver()
	{
		driver.quit();
	}
}

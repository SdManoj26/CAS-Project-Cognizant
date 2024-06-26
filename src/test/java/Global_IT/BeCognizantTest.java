package Global_IT;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ExcelUtilities;
import Utilities.ScreenshotUtilities;

public class BeCognizantTest extends BaseClass{
	
	BeCognizantPage bc;
	GlobalITPage gp;

	ExcelUtilities excelUtil;
	ScreenshotUtilities takeShot;

	@Test(priority = 1)
	void validateProfile() throws Exception 
	{ 
		String[] userDetails = new String[2];
		
		bc = new BeCognizantPage(driver);
		gp = new GlobalITPage(driver);

		takeShot = new ScreenshotUtilities(driver);
		
		excelUtil = new ExcelUtilities();
		Thread.sleep(6000);
		bc.clickProfile();
		Thread.sleep(2000);
		takeShot.takeScreenshot("ProfileClick");
		userDetails = bc.getUserDetails();
		
		System.out.println(userDetails[0]);
		for(int i=0; i<userDetails.length; i++)
		{
			ExcelUtilities.updateExcel(userDetails[i], "user Profile", 1, i);
		}
		if(userDetails[0]=="" || userDetails[1]=="")
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
		
		
	}
	
	@Test(dependsOnMethods = "validateProfile" )
	void navigateGlobalITPage() throws InterruptedException, IOException
	{
		bc.navigateGlobalIT();
		takeShot.takeScreenshot("navigatePage");
		System.out.println("Test");
		Thread.sleep(5000);
		gp.scrollPage(10);
		//Assert.assertEquals(driver.getTitle(), "Global_IT");
	}
	
}

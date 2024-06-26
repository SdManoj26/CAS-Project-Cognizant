package Global_IT;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ScreenshotUtilities;

public class GlobalITTest extends BeCognizantTest{
	
	GlobalITPage gp;
	ScreenshotUtilities takeShot;
	
	@Test(priority = 3)
	void validateTools() throws Exception 
	{
		int toolCheck;
		gp = new GlobalITPage(driver);
		Thread.sleep(2000);
		takeShot = new ScreenshotUtilities(driver);
		Thread.sleep(3000);
		toolCheck = gp.checkITTools();
		if(toolCheck > 0)
		{
			Assert.fail();
		}
		else
		{
			Assert.assertTrue(true);
		}
		takeShot.takeScreenshot("ITTools");
		
		
	}
	
	@Test(priority = 4)
	void validateNews() throws Exception
	{
		gp.scrollPage(30);
		driver.navigate().back();
		Thread.sleep(2000);
		gp.scrollPage(30);
		int newsCount;
		newsCount = gp.validateNews();
		if(newsCount == 0)
		{
			Assert.fail();
		}
		else {
			Assert.assertTrue(true);
		}
		takeShot.takeScreenshot("ITNews");
		gp.scrollPage(20);
	}
	
	@Test(priority = 5)
	void validateAwards() throws Exception
	{
		int awardsCount;
		takeShot.takeScreenshot("ITAwards");
		awardsCount = gp.validateAwards();
		if(awardsCount == 0)
		{
			Assert.fail();
		}
		else
		{
			Assert.assertTrue(true);
		}
		
		
	}

}

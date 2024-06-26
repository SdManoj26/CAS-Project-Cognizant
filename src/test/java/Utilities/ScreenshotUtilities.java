package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Global_IT.BaseClass;

public class ScreenshotUtilities{
	
	WebDriver driver;
	
	public ScreenshotUtilities(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void takeScreenshot(String fileName) throws IOException
	{
		TakesScreenshot takeSS = (TakesScreenshot)driver;
		File src = takeSS.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir") + "\\Screenshot\\" +fileName+ ".png");
		FileUtils.copyFile(src, trg);
		
	}
	

}

package Global_IT;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ExcelUtilities;
import Utilities.ScreenshotUtilities;

public class GlobalITPage{
	
	WebDriver driver;
	WebDriverWait wait;
	Actions act;
	ExcelUtilities excelUtil;
	//ScreenshotUtilities takeSS;
	
	GlobalITPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		act = new Actions(driver);
		excelUtil = new ExcelUtilities();
		//takeSS = new ScreenshotUtilities();
	}
	
	
//	@FindBy(xpath="//div[@class='c_a_37591358 l_a_37591358 k_a_37591358']/div")
//	List<WebElement> tools;
	
	@FindBy(xpath="//div[@data-automation-id='HeroTitle']")
	List<WebElement> tools;
	
	@FindBy(xpath="//div[@data-automation-id='contentScrollRegion']")
	WebElement scrollBar;
	
	@FindBy(xpath="//div[@data-automation-id='gridNewsLayout']//a[@data-automation-id='newsItemTitle']")
	List<WebElement> news;
	
	@FindBy(xpath="//div[@data-automation-id='listNewsLayout']//a[@data-automation-id='newsItemTitle']")
	List<WebElement> award;
	
	
	
	int checkITTools() throws IOException 
	{
		int count=0;
		System.out.println(tools.size());
		wait.until(ExpectedConditions.titleContains("Global IT"));
		
		//takeScreenshot("ITTools");
		
		for(WebElement tool : tools)
		{
			if(tool.isDisplayed())
			{
				System.out.println(tool.getText() + " Tool is Present");
				
			
			}
			else
			{
				System.out.println("Tool not Present");
				count++;
			}
			
		}
		
		return count;
	}
	
	void scrollPage(int amt) throws InterruptedException
	{
		act.moveToElement(scrollBar).click().perform();
		for(int i =0; i<amt; i++)
		{
			act.sendKeys(Keys.ARROW_DOWN).perform();
		}
		Thread.sleep(5000);
	}
	
	int validateNews() throws IOException
	{
		System.out.println(news.size());
		//takeScreenshot("ITNews");
		for(int i = 0; i < news.size(); i++)
		{
			System.out.println((i+1)+"."+news.get(i).getText());
			excelUtil.updateExcel(news.get(i).getText(), "IT News", i+1, 0);
			
			System.out.println(news.get(i).getAttribute("title"));
			excelUtil.updateExcel(news.get(i).getAttribute("title"), "IT News", i+1, 1);
			
			if(news.get(i).getText().equals(news.get(i).getAttribute("title")))
			{
				System.out.println("News " + i+1 + "is verified");
				excelUtil.updateExcel("Passed", "IT News", i+1, 2);
				Assert.assertTrue(true);
			}
			else
			{
				System.out.println("News" + i+1 + "Title and tool tip mismatch");
				excelUtil.updateExcel("Failed", "IT News", i+1, 2);
				Assert.fail();
			}
			
		}
		
		return news.size();
	}
	
	int validateAwards()throws Exception
	{
		//takeScreenshot("ITAwards");
		Thread.sleep(5000);
		//awards.get(0).click();
		for(int i =0; i < award.size(); i++)
		{
			List<WebElement> awards = driver.findElements(By.xpath("//div[@data-automation-id='listNewsLayout']//a[@data-automation-id='newsItemTitle']"));
			String title = awards.get(i).getText();
			awards.get(i).click();
			wait.until(ExpectedConditions.titleContains(title));
			System.out.println(driver.findElement(By.xpath("//div[@id='title_text']")).getText());
			List<WebElement> desc = driver.findElements(By.xpath("//p//span[@class='fontSizeMediumPlus']"));
			for(int k = 0; k<desc.size(); k++)
			{
				System.out.println(desc.get(k).getText());
			}
			
			driver.navigate().back();
			wait.until(ExpectedConditions.titleContains("Global IT"));
		
			scrollPage(50);
			System.out.println("\n______________________________");
			Thread.sleep(3000);
		}
		return award.size();
	}
	

}

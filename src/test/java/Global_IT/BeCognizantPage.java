package Global_IT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.ScreenshotUtilities;

public class BeCognizantPage{
	
	WebDriver driver;
	WebDriverWait wait;
	Actions act;
	//ScreenshotUtilities takeSS;
	
	BeCognizantPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		act = new Actions(driver);
		//takeSS = new ScreenshotUtilities();
	}
	
	@FindBy(xpath=("//input[@type='search']"))
	WebElement searchBar;
	
	@FindBy(xpath=("//button[@id='O365_MainLink_Me']/div/div[@class='clgiLVKPzugZZns0LiPTqw== NK6YQ060InuEH1W1LbFJ4Q==']"))
	WebElement profileBtn;
	
	@FindBy(xpath=("//*[@id='mectrl_currentAccount_primary']"))
	WebElement nameField;
	
	@FindBy(xpath=("//*[@id='mectrl_currentAccount_secondary']"))
	WebElement mailField;
	
	@FindBy(xpath=("//button[@name='Corporate Functions']"))
	WebElement corpMenu;
	
	@FindBy(xpath=("//a[@name='Security and Technology']"))
	WebElement secMenu;
	
	@FindBy(xpath=("//a[@name='IT']"))
	WebElement itMenu;
	
	@FindBy(xpath=("//div[@class='c_a_37591358 l_a_37591358 k_a_37591358']/div"))
	List<WebElement> tools;
	
	
	void clickProfile() throws IOException
	{
		profileBtn.click();
	
	}
	
	String[] getUserDetails() throws Exception
	{
		String[] userDetails = new String[2];
		wait.until(ExpectedConditions.visibilityOf(nameField));;
		userDetails[0] = nameField.getText();
		userDetails[1] = mailField.getText();
		
		System.out.println(userDetails[0]);
		System.out.println(userDetails[1]);
		
		
		return userDetails;
		
	}
	
	void navigateGlobalIT() throws IOException
	{
		
		corpMenu.click();
		act.moveToElement(secMenu).perform();
		wait.until(ExpectedConditions.visibilityOf(itMenu));
		
		itMenu.click();
		
	}
	
	public void takeScreenshot(String fileName) throws IOException
	{
		TakesScreenshot takeSS = (TakesScreenshot)driver;
		File src = takeSS.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir") + "\\Screenshot\\" +fileName+ ".xlsx");
		FileUtils.copyFile(src, trg);
		
	}

}

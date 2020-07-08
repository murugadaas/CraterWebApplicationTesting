package demo.com.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import demo.com.qa.utilities.PageInteractions;

public class LoginPage {
	WebDriver driver;
	PageInteractions interactions;
	
	@FindBy(xpath="//*[@id=\"loginForm\"]/div[1]/div/input")
	public WebElement userName;
	
	@FindBy(name="password")
	public WebElement passWord;
	
	@FindBy(css ="#loginForm > button")
	public WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		interactions = new PageInteractions(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	public void loginCrater(String username, String password)
	{
		try {
			interactions.clear(userName);
			Thread.sleep(1000);
			interactions.enterInput(userName, username);
			Thread.sleep(2000);
			interactions.clear(passWord);
			Thread.sleep(1000);
			interactions.enterInput(passWord, password);
			Thread.sleep(2000);
			interactions.click(loginButton);
			Thread.sleep(5000);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	

}

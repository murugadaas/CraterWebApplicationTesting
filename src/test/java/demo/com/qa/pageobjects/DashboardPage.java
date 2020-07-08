package demo.com.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.com.qa.utilities.PageInteractions;

public class DashboardPage {
	WebDriver driver;
	PageInteractions interactions;
	
	@FindBy(xpath="//h6[text()='Sales & Expenses ']")
	public WebElement salesExpenses;
	
	@FindBy(xpath="//div[@class='toast toast-success']")
	public WebElement loginSuccessMessage;
	
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		interactions = new PageInteractions(driver);
		PageFactory.initElements(driver, this);
	}

	public void PageView() {
		try {
			Thread.sleep(2000);
			interactions.scrollDown();
			Thread.sleep(2000);
			interactions.scrollUp();
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
}

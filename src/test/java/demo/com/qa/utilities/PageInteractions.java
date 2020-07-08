package demo.com.qa.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageInteractions {
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;	
	
	public PageInteractions(WebDriver driver) {
		this.driver = driver;
		wait=new WebDriverWait(driver, 5);
		js = (JavascriptExecutor) driver;	
		
	}
	
	public void clear(WebElement webElement){
		wait.until(ExpectedConditions.visibilityOf(webElement));
		webElement.clear();
	}
	
	public void click(WebElement webElement){
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}
	
	public void enterInput(WebElement webElement, String string){
		wait.until(ExpectedConditions.visibilityOf(webElement));
		webElement.sendKeys(string);
	}
	
	public String getText(WebElement webElement){
		wait.until(ExpectedConditions.visibilityOf(webElement));
		return webElement.getText();
	}

	public void selectOptionByText(WebElement webElement,By by, String option) throws Exception{
		wait.until(ExpectedConditions.visibilityOf(webElement));
		click(webElement);
		Thread.sleep(1000);
        // Get all of the options
        List<WebElement> options = driver.findElements(by);
        System.out.println(options.size());
        // Loop through the options and select the one that matches
        for (WebElement opt : options) {
        	//System.out.println(opt+"    "+opt.getText());
            if (opt.getText().equals(option)) {
                opt.click();
                Thread.sleep(2000);
                return;
            }
        }
        throw new NoSuchElementException("Can't find " + option + " in dropdown");
		//Select select = new Select(webElement); 
		//select.selectByVisibleText(option);
	}
	
	public void scrollDownUntilElementVisibility(WebElement webElement)
	{
		 js.executeScript("arguments[0].scrollIntoView(true);", webElement);
	}
	
	public void scrollDown()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void scrollUp()
	{
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}
}

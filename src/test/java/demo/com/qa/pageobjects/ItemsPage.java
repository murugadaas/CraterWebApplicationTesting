package demo.com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.com.qa.utilities.PageInteractions;

public class ItemsPage {

	WebDriver driver;
	PageInteractions interactions;

	@FindBy(css = "#app > div.template-container > div.sidebar-left > div > div > div:nth-child(1) > a:nth-child(3)")
	public WebElement itemsPageNavigation;

	@FindBy(xpath = "//a[@class='col-xs-2']")
	public WebElement addItemsButton;

	@FindBy(xpath = "//button[@class='base-button btn btn-outline-primary btn-lg ']")
	public WebElement filterItems;

	@FindBy(xpath = "//input[@name='name']")
	public WebElement itemName;

	@FindBy(xpath = "//div[@class='base-input']/input[@class='v-money input-field']")
	public WebElement itemPrice;

	@FindBy(className = "multiselect__input")
	public WebElement itemUnits;
	
	public By selectedItemUnit = By.xpath("//div[@class='multiselect__select']//following::div[4]/ul/li/span/span");

	@FindBy(xpath = "//textarea[@name='description']")
	public WebElement itemDescrption;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement itemSaveButton;
	
	@FindBy(xpath = "//div[text()='Item updated successfully']")
	public WebElement itemAddedSuccessMessage;

	public ItemsPage(WebDriver driver) {
		this.driver = driver;
		interactions = new PageInteractions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void addItem(String itemname, String itemprice, String itemUnit, String itemdescription) throws Exception
	{
		interactions.click(itemsPageNavigation);
		Thread.sleep(2000);
		interactions.click(addItemsButton);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		interactions.enterInput(itemName, itemname);
		Thread.sleep(1000);
		interactions.enterInput(itemPrice, itemprice);
		Thread.sleep(1000);
		interactions.selectOptionByText(itemUnits,selectedItemUnit,itemUnit);
		Thread.sleep(1000);
		interactions.enterInput(itemDescrption, itemdescription);
		Thread.sleep(1000);
		interactions.click(itemSaveButton);
		Thread.sleep(3000);
	}

}

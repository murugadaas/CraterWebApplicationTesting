package demo.com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.com.qa.utilities.PageInteractions;

public class InvoicePage {
	WebDriver driver;
	PageInteractions interactions;

	@FindBy(xpath = "//span[contains(text(),'Invoices')]")
	public WebElement invoicePageNavigation;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	public WebElement newInvoiceButton;

	@FindBy(xpath = "//button[@class=\"base-button btn btn-outline-primary btn-lg \"]/parent::div[@class=\"col-xs-2 mr-4\"")
	public WebElement filterInvoices;

	@FindBy(className = "add-customer-action")
	public WebElement newCustomer;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement newCustomerSearch;

	@FindBy(xpath = "(//label[@class='title'])[1]")
	public WebElement newSearchedCustomer;

	public By listOfAllExistingCustomer = By.xpath("(//div[@class='list'])//div/div/label[@class='title']");

	@FindBy(xpath = "(//input[@readonly='readonly'])[1]")
	public WebElement invoiceDate;

	@FindBy(xpath = "(//input[@class='date-field'])[2]")
	public WebElement invoiceDueDate;

	@FindBy(className= "prefix-input-field")
	public WebElement invoiceNumber;

	@FindBy(xpath = "//input[@class='input-field input-field-left-icon']")
	public WebElement invoiceRefNumber;

	@FindBy(className = "multiselect__input")
	public WebElement selectItems;
	
	public By listOfAllAddedItems = By.xpath("//div[@class='multiselect__tags']//input[1]/following::div/ul/li/span/span");

	@FindBy(xpath = "//input[@class='input-field small-input']")
	public WebElement invoiceQuantity;

	@FindBy(xpath = "//input[@class='v-money input-field']")
	public WebElement invoicePrice;

	@FindBy(xpath = "(//div[@class='item-amount']//div)[1]")
	public WebElement invoiceAmount;

	@FindBy(xpath = "//div[text()[normalize-space()='Add an Item']]")
	public WebElement invoiceAddItems;

	@FindBy(xpath = "//textarea[@class='text-area-field base-text-area']")
	public WebElement invoiceNotes;

	@FindBy(xpath = "//label[text()='Discount']/following::input")
	public WebElement invoiceDiscountPrice;

	@FindBy(xpath = "//button[contains(@class,'btn item-dropdown')]")
	public WebElement invoiceDiscountPriceUnit;

	@FindBy(className = "float-right")
	public WebElement invoiceAddTax;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement invoiceAddTaxSearch;
	
	@FindBy(xpath = "//div[@class='search-bar']/following-sibling::div[1]")
	public WebElement invoiceAddTaxSearchFirstResult;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	public WebElement invoiceTemplate;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement saveInvoiceButton;

	@FindBy(xpath = "//div[text()='Invoice created successfully']")
	public WebElement invoiceAddedSuccessMessage;


	public InvoicePage(WebDriver driver) {
		this.driver = driver;
		interactions = new PageInteractions(driver);
		PageFactory.initElements(driver, this);
	}

	public void addInvoice(String existingCustomer, String invoiceNo, String invoiceRefNo, String item, String itemQuantity, String invoicenotes, String tax) throws Exception {

		interactions.click(invoicePageNavigation);
		Thread.sleep(2000);
		interactions.click(newInvoiceButton);
		Thread.sleep(2000);
		interactions.click(newCustomer);
		Thread.sleep(1000);
		interactions.enterInput(newCustomerSearch, existingCustomer);
		Thread.sleep(1000);
		interactions.click(newSearchedCustomer);
		Thread.sleep(1000);
		
		interactions.clear(invoiceNumber);
		Thread.sleep(1000);
		interactions.enterInput(invoiceNumber, invoiceNo);
		Thread.sleep(1000);
		interactions.enterInput(invoiceRefNumber, invoiceRefNo);
		Thread.sleep(1000);
		interactions.selectOptionByText(selectItems,listOfAllAddedItems,item);
		Thread.sleep(1000);
		
		interactions.scrollDown();
		
		interactions.enterInput(invoiceNotes, invoicenotes);
		Thread.sleep(1000);
		interactions.click(invoiceAddTax);
		Thread.sleep(1000);
		interactions.enterInput(invoiceAddTaxSearch, tax);
		Thread.sleep(1000);
		interactions.click(invoiceAddTaxSearchFirstResult);
		Thread.sleep(1000);
		
		interactions.scrollUp();
		
		Thread.sleep(1000);
		interactions.click(saveInvoiceButton);
		Thread.sleep(1000);
	}

}

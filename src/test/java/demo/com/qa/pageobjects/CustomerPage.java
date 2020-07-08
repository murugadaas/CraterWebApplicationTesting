package demo.com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.com.qa.utilities.PageInteractions;

public class CustomerPage {
	WebDriver driver;
	PageInteractions interactions;

	@FindBy(xpath="//span[text()='Customers']")
	public WebElement customerPageNavigation;

	@FindBy(xpath="//button[text()[normalize-space()='New Customer']]")
	public WebElement addCustomerButton;

	@FindBy(name="name")
	public WebElement customerDisplayNameField;

	@FindBy(xpath="//div[@label='Contact Name']//input[1]")
	public WebElement customerPrimaryNameField;

	@FindBy(name="email")
	public WebElement customerEmailField;

	@FindBy(xpath="(//input[@name='phone'])[1]")
	public WebElement customerPhoneField;

	@FindBy(xpath="(//div[@class='multiselect__select'])[1]")
	public WebElement currencySelection;

	public By currencyOptions = By.xpath("(//div[contains(@class,'base-select multiselect')])[1]//following::div[4]/ul/li");

	@FindBy(xpath="(//input[@name='address_name'])[1]")
	public WebElement billingNameField;

	@FindBy(xpath="(//div[@class='multiselect__select'])[2]")
	public WebElement billingCountrySelection;

	public By countryOptions = By.xpath("(//div[contains(@class,'base-select multiselect')])[2]//following::div[4]/ul/li");

	@FindBy(name="billing.state")
	public WebElement billingStateField;

	@FindBy(name="billing.city")
	public WebElement billingCityField;

	@FindBy(name="billing_street1")
	public WebElement billingAddressStret1Field;

	@FindBy(name="billing_street2")
	public WebElement billingAddressStret2Field;

	@FindBy(xpath="(//input[@name='phone'])[2]")
	public WebElement billingPhoneField;

	@FindBy(xpath="(//input[@name='zip'])[1]")
	public WebElement billingZipField;

	@FindBy(xpath="(//input[@name='zip'])[2]")
	public WebElement shipingZipField;

	@FindBy(xpath="//button[contains(@class,'btn-sm base-button')]")
	public WebElement copyBillingDetailsButton;

	@FindBy(xpath="(//button[@type='submit'])[1]")
	public WebElement saveNewCustomerButton;

	@FindBy(xpath="//div[text()='Customer created successfully']")
	public WebElement customerAddedSuccessMessage;

	public CustomerPage(WebDriver driver) {
		this.driver = driver;
		interactions = new PageInteractions(driver);
		PageFactory.initElements(driver, this);
	}

	public void addCustomer(String dName,String pName,String eMail,String cPhone,String currency,String custName,String country,String state,String city,String address1,String address2,String phone,String zip) throws Exception {
		try {
			interactions.click(customerPageNavigation);
			Thread.sleep(2000);
			interactions.click(addCustomerButton);
			Thread.sleep(2000);
			interactions.enterInput(customerDisplayNameField, dName);
			Thread.sleep(1000);
			interactions.enterInput(customerPrimaryNameField, pName);
			Thread.sleep(1000);
			interactions.enterInput(customerEmailField, eMail);
			Thread.sleep(1000);
			interactions.enterInput(customerPhoneField, cPhone);
			Thread.sleep(1000);
			interactions.selectOptionByText(currencySelection,currencyOptions,currency);
			Thread.sleep(1000);
			interactions.enterInput(billingNameField, custName);
			Thread.sleep(1000);
			interactions.selectOptionByText(billingCountrySelection,countryOptions,country);
			Thread.sleep(1000);
			interactions.enterInput(billingStateField, state);
			Thread.sleep(1000);
			interactions.enterInput(billingCityField, city);
			Thread.sleep(1000);
			interactions.enterInput(billingAddressStret1Field, address1);
			Thread.sleep(1000);
			interactions.enterInput(billingAddressStret2Field, address2);
			Thread.sleep(1000);
			interactions.enterInput(billingPhoneField, phone);
			Thread.sleep(1000);
			interactions.scrollDownUntilElementVisibility(shipingZipField);
			Thread.sleep(1000);
			interactions.enterInput(billingZipField, zip);
			Thread.sleep(1000);
			interactions.click(copyBillingDetailsButton);
			Thread.sleep(3000);
			interactions.scrollUp();
			Thread.sleep(2000);
			interactions.click(saveNewCustomerButton);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

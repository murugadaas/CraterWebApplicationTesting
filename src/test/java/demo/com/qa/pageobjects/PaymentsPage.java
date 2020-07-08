package demo.com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import demo.com.qa.utilities.PageInteractions;

public class PaymentsPage {

	WebDriver driver;
	PageInteractions interactions;

	@FindBy(xpath = "//span[contains(text(),'Payments')]")
	public WebElement paymentsPageNavigation;

	@FindBy(xpath = "//button[@class=\"base-button btn btn-primary btn-lg \"]")
	public WebElement addPaymentButton;

	@FindBy(xpath = "//button[@class=\"base-button btn btn-outline-primary btn-lg \"]")
	public WebElement filterPayment;

	@FindBy(xpath="//input[@class='date-field']")
	public WebElement paymentDate;

	@FindBy(xpath="//input[@class='prefix-input-field']")
	public WebElement paymentNumber;

	@FindBy(xpath="(//div[@role='combobox']//div)[1]")
	public WebElement selectCustomerList;

	public By customerListOptions = By.xpath("(//div[@class='multiselect__select'])[1]//following::div[4]/ul/li");

	@FindBy(xpath="//input[@placeholder='Select a customer']")
	public WebElement selectCustomerSearch;

	@FindBy(xpath="(//span[contains(@class,'multiselect__option multiselect__option--highlight')])[1]")
	public WebElement selectedCustomer;

	@FindBy(xpath="(//div[@class='multiselect__select'])[2]")
	public WebElement selectInvoicelist;

	public By invoiceListOptions = By.xpath("(//div[@class='multiselect__select'])[2]//following::div[4]/ul/li");

	@FindBy(xpath="(//div[@class='multiselect__select'])[2]//following::div[4]/ul/li[1]")
	public WebElement selectedInvoice;

	@FindBy(xpath="//input[@class='v-money input-field']")
	public WebElement enterAmount;

	@FindBy(xpath = "(//div[@class='multiselect__select'])[3]")
	public WebElement paymentModeList;

	public By paymentModeOptions = By.xpath("(//div[contains(@class,'base-select multiselect')])[3]//following::div[4]/ul/li");

	@FindBy(xpath= "//textarea[@class='text-area-field base-text-area']")
	public WebElement notesPaymentPage;

	@FindBy(xpath= "	(//button[@type='submit'])[1]")
	public WebElement savePaymentButton;

	@FindBy(xpath = "//div[text()='Payment created successfully']")
	public WebElement paymentsDoneSuccessMessage;

	public PaymentsPage(WebDriver driver) {
		this.driver = driver;
		interactions = new PageInteractions(driver);
		PageFactory.initElements(driver, this);
	}

	public void newPayment(String customer, String payMode, String notes) throws Exception {
		interactions.click(paymentsPageNavigation);
		Thread.sleep(2000);
		interactions.click(addPaymentButton);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		interactions.selectOptionByText(selectCustomerList, customerListOptions, customer);
		Thread.sleep(1000);
		interactions.click(selectInvoicelist);
		Thread.sleep(1000);
		interactions.click(selectedInvoice);
		Thread.sleep(3000);
		interactions.selectOptionByText(paymentModeList, paymentModeOptions, payMode);
		Thread.sleep(1000);
		interactions.enterInput(notesPaymentPage, notes);
		Thread.sleep(1000);
		interactions.click(savePaymentButton);
		Thread.sleep(1000);	
	}

}

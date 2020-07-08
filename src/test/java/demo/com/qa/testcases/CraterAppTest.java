package demo.com.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.com.qa.pageobjects.CustomerPage;
import demo.com.qa.pageobjects.DashboardPage;
import demo.com.qa.pageobjects.InvoicePage;
import demo.com.qa.pageobjects.ItemsPage;
import demo.com.qa.pageobjects.LoginPage;
import demo.com.qa.pageobjects.PaymentsPage;
import demo.com.qa.utilities.ExcelUtils;
import demo.com.qa.utilities.PageInteractions;

public class CraterAppTest {

	WebDriver driver;
	SoftAssert softAssert;
	PageInteractions interactions;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	CustomerPage customerPage;
	ItemsPage itemsPage;
	InvoicePage invoicesPage;
	PaymentsPage paymentPage;

	public static String dataPath = "C:\\Users\\vino\\Desktop\\New folder\\com.qa.crater\\src\\test\\CraterData.xlsx";
	public static String resultPath = "C:\\Users\\vino\\Desktop\\New folder\\com.qa.crater\\src\\test\\CraterResults.xlsx";


	@Parameters({ "browser" })
	@BeforeTest(
			description = "Browser setup and App Navigation",
			alwaysRun = true)
	public void openBrowser(String browser) throws Exception {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.firefox.driver", "C:/Users/vino/Downloads/firefoxdriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:/Users/vino/Downloads/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "C:/Users/vino/Downloads/iedriver.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(ExcelUtils.getCellData("Common", 1, 1));
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(05, TimeUnit.SECONDS);

			interactions = new PageInteractions(driver);
			softAssert = new SoftAssert();
			loginPage = new LoginPage(driver);
			dashboardPage =  new DashboardPage(driver);
			customerPage = new CustomerPage(driver);
			itemsPage= new ItemsPage(driver);
			invoicesPage = new InvoicePage(driver);
			paymentPage = new PaymentsPage(driver);

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 0,
			testName = "100",
			description = "Crater App login test",
			groups = { "smoke", "regression" }
			)
	public void craterLogin() throws Exception{
		try {
			loginPage.loginCrater(ExcelUtils.getCellData("Login", 1, 0),ExcelUtils.getCellData("Login", 1, 1));
			softAssert.assertEquals(ExcelUtils.getCellData("Content", 1, 1),interactions.getText(dashboardPage.loginSuccessMessage));
			ExcelUtils.setCellData("Pass", 1, 2);
			Reporter.log("Crater App Logged-In successfully");
		} catch (Exception e) {
			Reporter.log("Crater App Login failed");
			ExcelUtils.setCellData("Fail", 1, 2);
			e.getMessage();
		}
	}

	@Test(priority = 1, 
			testName = "101",
			description = "Dashboard Page test",
			groups = { "regression" },
			dependsOnMethods = { "craterLogin" }
			)
	public void dashBoardPageVerification() throws Exception {
		try {
			dashboardPage.PageView();
			softAssert.assertEquals(ExcelUtils.getCellData("Content", 2, 1).trim(),interactions.getText(dashboardPage.salesExpenses).trim());
			ExcelUtils.setCellData("Pass", 2, 2);
			Reporter.log("Dashboard page verified successfully");
		} catch (Exception e) {
			Reporter.log("Dashboard page not verified");
			ExcelUtils.setCellData("Fail", 1, 2);
			e.getMessage();
		}	
	}

	@Test(priority = 2, 
			testName = "102",
			description = "Add New Customer test",
			groups = { "smoke","regression" },
			dependsOnMethods = { "craterLogin" }
			)
	public void newCustomerAddition() throws Exception {
		try {	
			customerPage.addCustomer(ExcelUtils.getCellData("Customers", 1, 0),ExcelUtils.getCellData("Customers", 1, 1),ExcelUtils.getCellData("Customers", 1, 2),ExcelUtils.getCellData("Customers", 1, 3),ExcelUtils.getCellData("Customers", 1, 4),ExcelUtils.getCellData("Customers", 1, 5),ExcelUtils.getCellData("Customers", 1, 6),ExcelUtils.getCellData("Customers", 1, 7),ExcelUtils.getCellData("Customers", 1, 8),ExcelUtils.getCellData("Customers", 1, 9),ExcelUtils.getCellData("Customers", 1, 10),ExcelUtils.getCellData("Customers", 1, 11),ExcelUtils.getCellData("Customers", 1, 12));
			softAssert.assertEquals(ExcelUtils.getCellData("Content", 3, 1).trim(),interactions.getText(customerPage.customerAddedSuccessMessage).trim());
			ExcelUtils.setCellData("Pass", 3, 2);
			Reporter.log("New Customer added successfully");
		} catch (Exception e) {
			Reporter.log("Unable to add New Customer");
			ExcelUtils.setCellData("Fail", 3, 2);
			e.printStackTrace();
		}	
	}

	@Test(priority = 3, 
			testName = "103",
			description = "Add New Item test",
			groups = {"smoke","regression" },
			dependsOnMethods = { "craterLogin" }
			)
	public void newItemAddition() throws Exception {
		try {	
			itemsPage.addItem(ExcelUtils.getCellData("Items", 1, 0),ExcelUtils.getCellData("Items", 1, 1),ExcelUtils.getCellData("Items", 1, 2),ExcelUtils.getCellData("Items", 1, 3));
			softAssert.assertEquals(ExcelUtils.getCellData("Content", 4, 1).trim(),interactions.getText(itemsPage.itemAddedSuccessMessage).trim());
			ExcelUtils.setCellData("Pass", 4, 2);
			Reporter.log("New Item added successfully");
		} catch (Exception e) {
			Reporter.log("Unable to add New Item");
			ExcelUtils.setCellData("Fail", 4, 2);
			e.printStackTrace();
		}	
	}

	@Test(priority = 4, 
			testName = "104",
			description = "Add New Invoice test",
			groups = { "smoke","regression" },
			dependsOnMethods = { "craterLogin" }
			)
	public void newInvoiceAddition() throws Exception {
		try {
			invoicesPage.addInvoice(ExcelUtils.getCellData("Invoices", 1, 0),ExcelUtils.getCellData("Invoices", 1, 1),ExcelUtils.getCellData("Invoices", 1, 2),ExcelUtils.getCellData("Invoices", 1, 3),ExcelUtils.getCellData("Invoices", 1, 4),ExcelUtils.getCellData("Invoices", 1, 5),ExcelUtils.getCellData("Invoices", 1, 6));
			softAssert.assertEquals(ExcelUtils.getCellData("Content", 5, 1).trim(),interactions.getText(invoicesPage.invoiceAddedSuccessMessage).trim());
			ExcelUtils.setCellData("Pass", 5, 2);
			Reporter.log("New Invoice added successfully");
		} catch (Exception e) {
			Reporter.log("Unable to add New Invoice");
			ExcelUtils.setCellData("Fail", 5, 2);
			e.printStackTrace();
		}	
	}

	@Test(priority = 5, 
			testName = "105",
			description = "New Payment test",
			groups = { "smoke","regression" },
			dependsOnMethods = { "craterLogin" }
			)
	public void newPayment() throws Exception {
		try {	
			paymentPage.newPayment(ExcelUtils.getCellData("Payments", 1, 0),ExcelUtils.getCellData("Payments", 1, 1),ExcelUtils.getCellData("Payments", 1, 2));
			softAssert.assertEquals(ExcelUtils.getCellData("Content", 6, 1).trim(),interactions.getText(paymentPage.paymentsDoneSuccessMessage).trim());
			ExcelUtils.setCellData("Pass", 6, 2);
			Reporter.log("New Payment done successfully");
			softAssert.assertAll();
		} catch (Exception e) {
			Reporter.log("Unable to proceed payment");
			ExcelUtils.setCellData("Fail", 6, 2);
			e.printStackTrace();
		}	
	}

	@AfterClass(alwaysRun = true)
	public void afterTest() {
		Reporter.log("Crater App Test completed successfully");
		System.out.println("Completed");
		driver.quit();
	}
}

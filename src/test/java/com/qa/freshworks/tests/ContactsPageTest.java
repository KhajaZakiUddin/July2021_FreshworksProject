package com.qa.freshworks.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.freshworks.base.BasePage;
import com.qa.freshworks.pages.ContactsPage;
import com.qa.freshworks.pages.HomePage;
import com.qa.freshworks.pages.LoginPage;
import com.qa.freshworks.pojo.Contacts;
import com.qa.freshworks.pojo.Credentials;
import com.qa.freshworks.util.AppConstants;
import com.qa.freshworks.util.ExcelUtil;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Credentials credentials;
	Contacts contacts;

	@BeforeTest
	public void setUp() {
		basePage=new BasePage();
		prop = basePage.init_Prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		credentials=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(credentials);
		contactsPage=homePage.goToContactsPage();
		

	}

	@Test(priority = 1)
	public void verifyContactsPageTitle() {
		String contactsPageTitle = contactsPage.getContactsPageTitle();
		System.out.println("Contacts page Tite is : " + contactsPageTitle);
		Assert.assertEquals(contactsPageTitle, AppConstants.CONTACTS_PAGE_TITLE);
	}

	
	@DataProvider
	public Object[][] getTestDataFromExcel(){
		Object data[][]=ExcelUtil.getTestData(AppConstants.CONTACTS_SHEET_NAME);
		return data;
	}
			
	@Test(priority = 2, dataProvider="getTestDataFromExcel")
	public void verifyCreateContactsTest(String FirstName, String LastName, String EmailId) {
		contacts=new Contacts(FirstName, LastName, EmailId);
		contactsPage.createContact(contacts);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

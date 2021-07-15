package com.qa.freshworks.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.freshworks.base.BasePage;
import com.qa.freshworks.pages.HomePage;
import com.qa.freshworks.pages.LoginPage;
import com.qa.freshworks.pojo.Credentials;
import com.qa.freshworks.util.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("EPIC-101 : define Login Feature for Freshworks Application")
@Feature("Feature 1 : Defines the Login feature of the Freshworks Application")


public class LoginPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credentials;
	
	
	
	@BeforeTest
	public void setUp(){
		basePage=new BasePage();
		prop=basePage.init_Prop();
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
		credentials=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=2)
	@Description("Verifying the Login Page Title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest(){
		String loginPageTitle=loginPage.verifyLoginPageTitle();
		System.out.println("LogInpage Title is : " +loginPageTitle);
		Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Test(priority=1)
	public void verifyLoginPageLogoTest(){
		Assert.assertTrue(loginPage.verifyLoginPageLogo());
	}
	
	@Test(priority=3)
	public void verifyTermsLinkTest(){
		Assert.assertTrue(loginPage.verifyTermsLink());
	}
	
	
	@Test(priority=4)
	@Description("Verifying the Login Feature of the Freshworks Application")
	@Severity(SeverityLevel.BLOCKER)
	public void doLoginTest(){
		homePage=loginPage.doLogin(credentials);
		String loggedInUser=homePage.getLoggedInUserName();
		Assert.assertEquals(loggedInUser, "Test Animation");
	}
	
	
	@DataProvider
	public Object[][] logInData(){
		Object data[][]={
				{"test@gamil.com","test123456"},
				{"","12345685"},
				{"tset@gamil.com",""},
				{"",""},
				{"test@outlook.com","invalid"},
		
				
		};
		return data;
	}
		
//	@Test(priority=5, dataProvider="logInData", enabled=false)
//	public void doLoginTestInvalid(String username, String password){
//		homePage=loginPage.doLogin(username,password);
//		String loggedInUser=homePage.getLoggedInUserName();
//		Assert.assertEquals(loggedInUser, "Test Animation");
//	}
//	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

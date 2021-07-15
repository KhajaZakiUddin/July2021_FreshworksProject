package com.qa.freshworks.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.freshworks.base.BasePage;
import com.qa.freshworks.pages.HomePage;
import com.qa.freshworks.pages.LoginPage;
import com.qa.freshworks.pojo.Credentials;
import com.qa.freshworks.util.AppConstants;

public class HomePageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage logInPage;
	HomePage homePage;
	Credentials credentials;
	
	@BeforeTest
	public void setUp(){
		basePage=new BasePage();
		prop=basePage.init_Prop();
		driver=basePage.init_driver(prop);
		logInPage=new LoginPage(driver);
		homePage=new HomePage(driver);
		credentials=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage=logInPage.doLogin(credentials);
	}
	
	
	@Test(priority=2)
	public void verifyHomePageTitleTest(){
		String homePageTitle=homePage.getHomePageTitle();
		System.out.println("Home Page Title is : " +homePageTitle);
		Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=1)
	public void verifyHomePageHeaderTest(){
		String homePageHeader=homePage.getHomePageHeader();
		System.out.println("Home Page header is : " +homePageHeader);
		Assert.assertEquals(homePageHeader, AppConstants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserTest(){
		String loggedInUserName=homePage.getLoggedInUserName();
		System.out.println("Logged in user is : "+loggedInUserName);
		Assert.assertEquals(loggedInUserName, prop.getProperty("accountName"));
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

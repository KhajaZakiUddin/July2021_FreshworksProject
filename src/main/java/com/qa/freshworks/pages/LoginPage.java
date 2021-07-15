package com.qa.freshworks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.freshworks.base.BasePage;
import com.qa.freshworks.pojo.Credentials;
import com.qa.freshworks.util.AppConstants;
import com.qa.freshworks.util.ElementActions;

import io.qameta.allure.Step;

public class LoginPage extends BasePage{

	
WebDriver driver;
ElementActions elementAction;
	
	//1. Defining the Page Objects/By locators of the LoginPage
	By username=By.id("username");
	By password=By.id("password");
	By logInButton=By.xpath("//button[text()='Sign in']");
	By freshworksLogo=By.xpath("//img[@alt='Organization Logo']");
	By termsLink=By.xpath("//div[@class='css-5g2dcw']//a[text()='Terms']");
	
	//2. Creating the constructor of the LoginPage Class to initialize the driver at global level
	public LoginPage(WebDriver driver){
		this.driver=driver;
		elementAction=new ElementActions(this.driver);
	}
	
	
	//3.Defining the Page Actions below for the above page objects
	
	public String verifyLoginPageTitle(){
		return elementAction.doGetTitle(AppConstants.LOGIN_PAGE_TITLE);
	}
		
	
	@Step("Verify the Login Page Logo")
	public boolean verifyLoginPageLogo(){
		elementAction.waitForElementPresent(freshworksLogo);
		return elementAction.doIsDisplayed(freshworksLogo);
	}
	
	
	@Step("Verify the Terms Link")
	public boolean verifyTermsLink(){
		return elementAction.doIsDisplayed(termsLink);
	}
	
	
	@Step("login with username: {0} and Password: {1}")
	public HomePage doLogin(Credentials credentials){
		System.out.println("Credentials are : username " +credentials.getEmailId()+ " and Password : " +credentials.getPassword());
		elementAction.waitForElementPresent(username);
		elementAction.doSendKeys(username, credentials.getEmailId());
		elementAction.doSendKeys(password, credentials.getPassword());
		elementAction.doClick(logInButton);
		
		return new HomePage(driver);
	}
	
	
	
	
}

package com.qa.freshworks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.freshworks.base.BasePage;
import com.qa.freshworks.util.AppConstants;
import com.qa.freshworks.util.ElementActions;
import com.qa.freshworks.util.JavaScriptUtil;

public class HomePage extends BasePage{

	WebDriver driver;
	ElementActions elementAction;
	JavaScriptUtil js;
	
	//1. Page Objects
	By homePageHeader=By.xpath("//h5[text()='Neo Admin Center']");
	By homePageUser=By.xpath("//h4[text()='Test Animation']");
	By freshworksContactLink=By.xpath("//a[@class='css-1dxkr9r']");
	
	
	//2. Constructor to initialize the driver
	public HomePage(WebDriver driver){
		this.driver=driver;
		elementAction=new ElementActions(this.driver);
		js=new JavaScriptUtil(this.driver);
		
	}
	
	
	//3. Page Actions
	
	public String getHomePageTitle(){
		return elementAction.doGetTitle(AppConstants.HOME_PAGE_TITLE);
	}
	
	public String getHomePageHeader(){
		elementAction.waitForElementPresent(homePageHeader);
		if(elementAction.doIsDisplayed(homePageHeader)){
			return elementAction.doGetText(homePageHeader);
		}
		return null;
	}
	
	public String getLoggedInUserName(){
		elementAction.waitForElementPresent(homePageUser);
		if(elementAction.doIsDisplayed(homePageUser)){
			return elementAction.doGetText(homePageUser);
		}
		return null;
	}
	
	public ContactsPage goToContactsPage(){
		elementAction.waitForElementPresent(freshworksContactLink);
		js.clickElementByJS(elementAction.getElement(freshworksContactLink));
		return new ContactsPage(driver);
	}

}

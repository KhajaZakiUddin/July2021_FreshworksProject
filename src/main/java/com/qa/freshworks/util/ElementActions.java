package com.qa.freshworks.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	
	public ElementActions(WebDriver driver){
		this.driver=driver;
		wait=new WebDriverWait(this.driver,AppConstants.DEFAULT_EXPLICIT_TIMEOUT);
		action=new Actions(this.driver);
	}
	
	public WebElement getElement(By locator){
		WebElement element=null;
		try{
			element=driver.findElement(locator);
		}catch(Exception e){
			System.out.println("Please Provide the correct locator. Element Not found at location : "+locator);
		}
		return element;
	}
	
	
	/**
	 * This Method is used to s
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value){
		getElement(locator).sendKeys(value);
	}
	
	public void doActionsClick(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		action.click(getElement(locator)).build().perform();
	}
	
	public void doActionsSendkeys(By locator, String value){
		action.sendKeys(getElement(locator), value).build().perform();
	}
	
	public void doMoveToElement(By locator){
		action.moveToElement(getElement(locator)).build().perform();
	}
	
	public void doClick(By locator){
		getElement(locator).click();
	}
	
	public String doGetText(By locator){
		return getElement(locator).getText();
	}
	
	public boolean doIsDisplayed(By locator){
		return getElement(locator).isDisplayed();
	}
	
	public void waitForElementPresent(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForElementVisible(By locator){
		WebElement ele=getElement(locator);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public String doGetTitle(String title){
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	
	
	
	
	
	
}

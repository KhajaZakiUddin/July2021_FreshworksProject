package com.qa.freshworks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.freshworks.pojo.Contacts;
import com.qa.freshworks.util.AppConstants;
import com.qa.freshworks.util.ElementActions;

public class ContactsPage {

	WebDriver driver;
	ElementActions elementAction;
	
	
	//1. Defining the Page Locators
	
	By contactsIcon=By.cssSelector("span.navbar-personal-link");
	By addContactsButton=By.xpath("//a[@id='top-nav-add-contact']");
	
	By Firstname=By.xpath("//input[@name='contact[firstName]']");
	By Lastname=By.xpath("//input[@name='contact[lastName]']");
	By emailId=By.xpath("//input[@name='fragments/email-address[value]']");
	By saveContactButton=By.xpath("//span[text()='Save']");
	
	
	//2. Creating the Constructor of the Contacts Page Class
	public ContactsPage(WebDriver driver){
		this.driver=driver;
		elementAction=new ElementActions(this.driver);
	}
	
	
	//3. Creating the page Actions
	
	public String getContactsPageTitle(){
		return elementAction.doGetTitle(AppConstants.CONTACTS_PAGE_TITLE);
	}
	
	
	public void createContact(Contacts contacts){
		
		elementAction.waitForElementPresent(contactsIcon);
		elementAction.doClick(contactsIcon);
		
		elementAction.waitForElementPresent(addContactsButton);
		elementAction.doActionsClick(addContactsButton);
		
		elementAction.doActionsSendkeys(this.Firstname, contacts.getFirstName());
		elementAction.doActionsSendkeys(this.Lastname, contacts.getLastName());
		elementAction.doActionsSendkeys(this.emailId, contacts.getEmailId());
		elementAction.doClick(saveContactButton);
		
//		elementAction.waitForElementPresent(contactsIcon);
//		elementAction.doClick(contactsIcon);
		
		
	}
	

	
}

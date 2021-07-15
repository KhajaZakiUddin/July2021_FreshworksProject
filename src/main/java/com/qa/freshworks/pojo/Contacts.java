package com.qa.freshworks.pojo;

public class Contacts {

	
	String FirstName;
	String LastName;
	String EmailId;
	
	
	public Contacts(String firstName, String lastName, String emailId) {
		
		this.FirstName = firstName;
		this.LastName = lastName;
		this.EmailId = emailId;
	}


	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		this.LastName = lastName;
	}


	public String getEmailId() {
		return EmailId;
	}


	public void setEmailId(String emailId) {
		this.EmailId = emailId;
	}
	
	
	
	
	
	
	
	
}

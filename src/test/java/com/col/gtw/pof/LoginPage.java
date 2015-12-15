package com.col.gtw.pof;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



import com.col.gtw.core.CoreBase;


public class LoginPage extends CoreBase{


	
	@FindBy(id="emailAddress")
	public WebElement username;

	@FindBy(id="password")
	public WebElement password;

	@FindBy(id="submit")
	public WebElement signIn;
	
	@FindBy(id="credentials.errors")
	public WebElement credentailsError;
	
	@FindBy(id="emailAddress.errors")
	public WebElement emailError;
	
	@FindBy(xpath="//a[contains(@href,'pwdrecovery')]")
	public WebElement FYP;
	
	public void setUserName(String userName) {
		username.clear();
		username.sendKeys(userName);
	}
	
	public void setPassword(String passwd) {
		password.clear();
		password.sendKeys(passwd);
	}
	
	public void clickSignIn() {
		signIn.click();
	}
	
	public boolean isLoginPageVisible() {
		return signIn.isDisplayed();
	}
	
	public String getFailedLoginError() {
		return credentailsError.getText();
	}
	
	public String getInvalidEmailError() {
		return emailError.getText();
	}
	
	public void logIn(String userName, String passwd) {
		setUserName(userName);
		setPassword(passwd);
		clickSignIn();
		
	}
	
	public boolean isLoginSuccessful() {
		try {
			
		
			if(credentailsError.isDisplayed() || emailError.isDisplayed()) { 
				return false;
			} else {
				return true;
			}
		} catch (NoSuchElementException e) {
			return true;
		}
	}
	
	public String getFYPtext()
	{
		return FYP.getText();
	}
}


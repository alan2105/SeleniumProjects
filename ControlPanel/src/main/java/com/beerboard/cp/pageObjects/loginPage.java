package com.beerboard.cp.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class loginPage {
	
	WebDriver driver;
	
	public loginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@type='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath="//button[@id='btnLogin']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@class='toast toast-error']")
	WebElement loginError;
	
	public void enterEmailAddress(String emailAddress)
	{
		this.username.sendKeys(emailAddress);
	}
	
	public void  enterPassword(String password)
	{
		this.password.sendKeys(password);
	}
	public void clickOnLoginbutton()
	{
		loginButton.click();
	}
	
	public WebElement errorToaster()
	{
		return loginError;
	}
}

package com.beerboard.cp.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dasboardPage {
	WebDriver driver;
	public dasboardPage(WebDriver driver)

	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@ng-if='isLoggedIn']//a[@id='dropdownSettingsMenu']")
	WebElement settingsDropdown;
	
	@FindBy(xpath="//p[contains(text(),'Logout')]")
	WebElement logoutLink;
	
	public void clickOnLogout()
	{
		settingsDropdown.click();
		logoutLink.click();
	}
	
	public WebElement userName()
	{
		return settingsDropdown;
	}
	
}

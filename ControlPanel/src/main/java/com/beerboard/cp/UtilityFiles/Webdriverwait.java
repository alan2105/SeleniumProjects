package com.beerboard.cp.UtilityFiles;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Webdriverwait {
	private WebDriver driver;
	
	public Webdriverwait(WebDriver driver)
	{
		this.driver = driver;
		
	}
	public void setImplicitWait(long timeout)
	{
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
	}
	public void setPageLoadTimeout(long timeout)
	{
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}
	
	public void waitForElementVisible(WebElement locator, WebDriver driver, long time)
	
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	public WebElement waitForElementToClickable(WebElement elemnet, WebDriver driver, long time)
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.elementToBeClickable(elemnet));
	}

}

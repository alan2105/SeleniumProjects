package com.beerboard.cp;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.beerboard.cp.UtilityFiles.Webdriverwait;
import com.beerboard.cp.baseFiles.TestBase;
import com.beerboard.cp.pageObjects.dasboardPage;
import com.beerboard.cp.pageObjects.loginPage;

public class LoginpageTest extends TestBase {

	loginPage lp ;
	dasboardPage dp ;
	Webdriverwait wp ;
	
	@Test(description ="Login with Valid credentials")
	public void TC_001() throws Exception
	{
		lp = new loginPage(driver);
		dp = new dasboardPage(driver);
		wp = new Webdriverwait(driver);
		lp.enterEmailAddress("admin@beerboard.com");
		lp.enterPassword("Power@1234");
		lp.clickOnLoginbutton();
		wp.waitForElementVisible(dp.userName(), driver, 10);
		String currentUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl1, "http://cp.stg.beerboard.com/dashboard");
	}
	@Test(description="Logged out")
	public void TC_002()
	{
	dp= new dasboardPage(driver);
		dp.clickOnLogout();	
		String currentUrl2 = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl2.contains("dashboard"));
	}
@Test(description ="Login with Invalid credentials")
	public void TC_003()
	{
		lp = new loginPage(driver);
		lp.enterEmailAddress("admin1234@beerboard.com");
		lp.enterPassword("Power1234");
		lp.clickOnLoginbutton();
		Assert.assertTrue(lp.errorToaster().isDisplayed(), "Not Throwing Error Toaster");
	}
	
	
}
 

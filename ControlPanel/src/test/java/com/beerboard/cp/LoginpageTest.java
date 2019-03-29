package com.beerboard.cp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.beerboard.cp.UtilityFiles.Webdriverwait;
import com.beerboard.cp.baseFiles.TestBase;
import com.beerboard.cp.pageObjects.dasboardPage;
import com.beerboard.cp.pageObjects.loginPage;

public class LoginpageTest extends TestBase {
	
	public static Logger log = LogManager.getLogger(LoginpageTest.class.getName());
	loginPage lp ;
	dasboardPage dp ;
	Webdriverwait wp ;
	
	@BeforeMethod
	public void test()
	{
		lp = new loginPage(driver);
		dp = new dasboardPage(driver);
		wp = new Webdriverwait(driver);
	}
	
	@Test(description ="Login with Valid credentials")
	public void TC_001() throws Exception
	{
		
		lp.enterEmailAddress("admin@beerboard.com");
		lp.enterPassword("Power@1234");
		log.debug("Valid credentials are typed");
		lp.clickOnLoginbutton();
		log.debug("Clicking Login button");
		wp.waitForElementVisible(dp.userName(), driver, 10);
		String currentUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl1, "http://cp.stg.beerboard.com/dashboard");
		log.info("Logged in Successfully and the current URL is:"+currentUrl1);
		
	}
		
	@Test(description="Logged out")
	public void TC_002()
	{
	
		dp.clickOnLogout();	
		String currentUrl2 = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl2.contains("dashboard"));
	}
@Test(description ="Login with Invalid credentials")
	public void TC_003()
	{
		
		lp.enterEmailAddress("admin1234@beerboard.com");
		lp.enterPassword("Power1234");
		lp.clickOnLoginbutton();
		Assert.assertTrue(lp.errorToaster().isDisplayed(), "Not Throwing Error Toaster");
		log.info("Error Message Status:"+lp.errorToaster().isDisplayed());
	}
	
	
}
 

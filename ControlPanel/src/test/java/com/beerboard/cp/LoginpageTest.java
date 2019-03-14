package com.beerboard.cp;

import org.testng.annotations.Test;

import com.beerboard.cp.baseFiles.TestBase;
import com.beerboard.cp.pageObjects.loginPage;

public class LoginpageTest extends TestBase {
	
	@Test
	public void VaildLogin() throws Exception
	{
		
	loginPage lp = new loginPage(driver);
		lp.enterEmailAddress("admin@beerboard.com");
		lp.enterPassword("Power@1234");
		lp.clickOnLoginbutton();
	}
	
}


package com.beerboard.cp.baseFiles;


import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public  Properties propfile;
	public  FileInputStream fis;
	public   File f1;
	
	
	
	public WebDriver browserSetup() throws IOException
	{ 
		
		String browserName = propfile.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
		
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	
	
	@BeforeClass
	public void getApplicationUrl() throws Exception
	{
		loadPropertiesFile("config.porperties");
		driver=browserSetup(); 
		driver.get(propfile.getProperty("url"));
		System.out.println(propfile.getProperty("url"));
	
	}
	

	public  String loadPropertiesFile(String fileName) throws IOException
	{
		propfile = new Properties();
		String filePath = System.getProperty("user.dir")+ "/src/main/java/com/beerboard/cp/configFiles/" + fileName;
		f1 = new File(filePath);
		fis = new FileInputStream(f1); 
		propfile.load(fis); 
		
		
		return filePath;
	}
	
	public void getFailureScreenshot(String fileName) throws IOException
	{
		DateFormat dateformat = new  SimpleDateFormat("dd-MM-yyyy h-m-s");
		Date currentdate = new Date();
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File(System.getProperty("user.dir")+"/src/main/java/resources/FailureScreenshots/"+ fileName+"_"+dateformat.format(currentdate)));
		
	}
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	public static void main(String[] args) throws Exception {
		TestBase tb = new TestBase();
		tb.loadPropertiesFile("config.porperties");	
		//tb.getApplicationUrl();
	}

}

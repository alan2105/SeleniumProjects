package com.beerboard.cp;

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

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public  Properties propfile;
	public  FileInputStream fis ;
	public   File f1;
	
	
	
	public WebDriver browserSetup() throws IOException 
	{
		
		String browserName = propfile.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
		
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("ie")) 
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	
	
	
	void getApplicationUrl() throws Exception
	{
		
		driver=browserSetup();
		driver.get(propfile.getProperty("url"));
		System.out.println(propfile.getProperty("url"));
		System.out.println(getMethodName());
	}
	public  String loadPropertiesFile(String fileName) throws IOException
	{
		propfile = new Properties();
		String filePath = System.getProperty("user.dir")+ "/src/main/java/resources/" + fileName;
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
		FileUtils.copyFileToDirectory(src, new File(System.getProperty("user.dir")+"/FailureScreenshots/"+ fileName+"_"+dateformat.format(currentdate)));
	}
	
	public String  getMethodName()
	{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		return  methodName;
	}
	
	
	public static void main(String[] args) throws Exception {
		TestBase tb = new TestBase();
		tb.loadPropertiesFile("config.porperties");
		tb.getApplicationUrl();
		tb.getFailureScreenshot("Fail");
		
	}

}

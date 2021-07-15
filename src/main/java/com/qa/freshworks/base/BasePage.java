package com.qa.freshworks.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	public OptionsManager optionsManager;

	//Thread local has two methods, get and set.Thread-Local is used to paralley execute the tests with different threads
	
	public static synchronized WebDriver getDriver(){
		return tlDriver.get();
	}
	
	
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		optionsManager=new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} 
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		} 
		
		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
		} 
		else {
			System.out.println(browserName + " not found. Please provide a valid browsername");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return getDriver();

	}

	/**
	 * This method is used to initialize the properties from config.properties
	 * file
	 * 
	 * @return this method will return the Properties -prop reference to get the
	 *         properties of config.roperties file
	 */
	public Properties init_Prop() {
		prop = new Properties();
		FileInputStream ip;
		String env=null;
		String path=null;
		
		try {
			env=System.getProperty("env");
			
			if(env==null)
			{
				path="./src/main/java/com/qa/freshworks/config/config.properties";
			}
			else{
				switch(env){
					
				case "qa":
					path="./src/main/java/com/qa/freshworks/config/config.qa.properties";
					break;
				
				case "stg":
					path="./src/main/java/com/qa/freshworks/config/config.stg.properties";
					break;
				
				case "prod":
					path="./src/main/java/com/qa/freshworks/config/config.prod.properties";
					break;
				
				default:
					System.out.println("Please Provide a valid environment value.");
					break;
				
				}
			}
			
			ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found at the Specified location. Please provide the correct file path.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	//The below method is for taking the screenshot
	public String getScreenshot(){
		File src= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination= new File(path);
		
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
		
	}

	
	
	
	
	
	
	

}

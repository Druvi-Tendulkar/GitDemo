package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	public WebDriver driver; 
	
	public WebDriver WebDriverManager() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\global.properties");
//		System.out.println("The paths is "+(System.getProperty("user.dir")+"\\src\\test\\resources\\global.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("QAUrl");
		String browser_properties = prop.getProperty("browser");
		String browser_maven = prop.getProperty("browser");
		
		//result = testCondition ? value1 : value2
		
		String browser = browser_maven!=null ? browser_maven : browser_properties;
		
		if(driver==null) 
		{
			if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","chromedriver1.exe");
				driver = new ChromeDriver(); //driver gets life
			}
			if(prop.getProperty("browser") == "firefox")
			{
				//code for firefox
				System.setProperty("webdriver.gecko.driver", "//Users//rahulshetty//downloads//gecko.");
				//install gecko driver and  add path
				driver = new FirefoxDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
			driver.get(url);
			
		}
		return driver;
	}
}

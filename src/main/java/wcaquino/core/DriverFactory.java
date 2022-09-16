package wcaquino.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	public static WebDriver driver;
	
	private DriverFactory() {}
	
	public static WebDriver getDriver() {
		if(driver == null) {
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();		
			driver.manage().window().setSize(new Dimension(1200, 765));
		}
		return driver;
	}
	
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}		
	}
}

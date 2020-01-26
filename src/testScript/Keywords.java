package testScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Keywords {

	static WebDriver driver;
	static Properties prop;
	static FileInputStream fis;

	public void tearDown() {
		driver.quit();
	}

	public void sleep(int secs) throws InterruptedException {
		Thread.sleep(secs);
	}

	@SuppressWarnings("deprecation")
	public void openBrowser(String browser) throws IOException {

		if (browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.contains("firefox")) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		    FirefoxOptions options = new FirefoxOptions();
		    capabilities.merge(options);
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\ashok\\eclipse-workspace-1\\Selenium_Pract\\Resources\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver(capabilities);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		prop = new Properties();
		fis = new FileInputStream(
				"C:\\Users\\ashok\\eclipse-workspace-1\\SeleniumDD\\src\\objectRepository\\objectRepository.properties");
		prop.load(fis);

	}

	public void navigate(String testData) {

		driver.get(testData);
	}

	public void inputUserName(String testData, String objectName) throws InterruptedException {
		sleep(3);
		WebElement usernameField = driver.findElement(By.id(prop.getProperty(objectName)));
		usernameField.sendKeys(testData);
	}

	public void inputPassword(String testData, String objectName) throws InterruptedException {
		sleep(3);
		driver.findElement(By.id(prop.getProperty(objectName))).sendKeys(testData);
	}

	public void clickLogin(String objectName) throws InterruptedException {
		sleep(3);
		driver.findElement(By.id(prop.getProperty(objectName))).click();
	}

}

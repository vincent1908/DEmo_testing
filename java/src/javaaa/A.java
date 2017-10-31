package javaaa;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class A {
	static WebDriver driver;
	static Logger log;

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\gregory\\Selenium\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com");

		// mouse over
		// WebElement element =
		// driver.findElement(By.xpath(".//*[@id='container']/div/header/div[2]/div/ul/li[1]/a"));
		// Actions action = new Actions(driver);
		// Thread.sleep(10000);
		// action.moveToElement(element).build().perform();
		mouseOverPerform(".//*[@id='container']/div/header/div[2]/div/ul/li[1]/a");
		System.err.println("pass");
		Thread.sleep(2000);
		for (int i = 0; i < 3; i++) {
			driver.findElement(By.xpath(".//*[@id='container']/div/header/div[2]/div/ul/li[1]/a"))
					.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);
		}
	}

	public static void mouseOverPerform(String locator) throws Exception {
		log = Logger.getLogger("A");
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.moveToElement(element).build().perform();
		System.out.println("mouseover done");
		log.info("Element clicked: " + locator);
	}

}

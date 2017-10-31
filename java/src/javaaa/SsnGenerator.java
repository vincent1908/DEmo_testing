package javaaa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SsnGenerator {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\gregory\\Selenium\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.fakeaddressgenerator.com/All_World_Address/get_se_address");
		Thread.sleep(3000);
		for (int i = 0; i <= 100; i++) {
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);

//			WebElement e1 = driver.findElement(By.xpath("//input[@type='submit']"));

			if (driver.findElement(By.xpath("//input[@type='submit']")).isDisplayed()) {
				driver.quit();
			} else {
				String ssnValue = driver
						.findElement(By
								.xpath("//html/body/div[1]/div[3]/div[1]/div/div[4]/div[2]/div[6]/div[2]/strong/input"))
						.getAttribute("value");
				Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
				Matcher match = pt.matcher(ssnValue);
				while (match.find()) {
					String s = match.group();
					ssnValue = ssnValue.replaceAll("\\" + s, "");
				}
				System.out.println(ssnValue);
				// System.out.println("done");
			}
			driver.quit();
		}
	}

}

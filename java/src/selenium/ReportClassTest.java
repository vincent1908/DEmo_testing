package selenium;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ReportClassTest extends ReportingHtml {

	// public static void main (String args[]) {
	//
	//// WebDriver driver = new FirefoxDriver();
	// Reporter.initialize();
	// //driver.get("http://automationpractice.com/");
	//
	// for (int i = 1; i <=5; i++) {
	// //WebElement el = driver.findElement(By.id("textfield" +
	// Integer.toString(i)));
	// Result obj = new Result();
	// //obj.test();
	// Reporter.report("TestLogin", "Text field " + "pass");
	// }
	//
	// Reporter.writeResults("test");
	//// driver.close();
	// }

	@Test
	public void testMethodName() throws Exception {
		// get method / function name
		 Boolean testCaseStatus = true;
		// System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		 testCaseStatus = false;
//		System.out.println(this.getClass().getSimpleName());
		updateResult(1, Thread.currentThread().getStackTrace()[1].getMethodName(), testCaseStatus, this.getClass().getSimpleName());
	}
	@Test
	public void testMethodName1() throws Exception {
		// get method / function name
		 Boolean testCaseStatus = true;
		// System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		 testCaseStatus = false;
//		System.out.println(this.getClass().getSimpleName());
		updateResult(1, Thread.currentThread().getStackTrace()[1].getMethodName(), testCaseStatus, this.getClass().getSimpleName());
	}
	@Test
	public void testMethodName2() throws Exception {
		// get method / function name
		 Boolean testCaseStatus = true;
		// System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		 testCaseStatus = false;
//		System.out.println(this.getClass().getSimpleName());
		updateResult(1, Thread.currentThread().getStackTrace()[1].getMethodName(), testCaseStatus, this.getClass().getSimpleName());
	}
}

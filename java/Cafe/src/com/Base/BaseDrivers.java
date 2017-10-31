package com.Base;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.capgemini.driver.CreateDriver;

/**
 * Please Do not Modify any code
 * 
 * @author PRAVIRAN
 *
 */

public class BaseDrivers {

	public static CreateDriver driver = null;
	public static RemoteWebDriver webDriver = null;

	public static CreateDriver getDriver() {
		if (driver == null) {
			driver = new CreateDriver();
		}
		return driver;
	}

	public static void setDriver(CreateDriver driver) {
		BaseDrivers.driver = driver;
	}

	public static RemoteWebDriver getWebDriver() {

		if (webDriver == null) {
			CreateDriver driver = getDriver();
			webDriver = driver.getWebDriver();
		}
		return webDriver;

	}

	public static void setWebDriver(RemoteWebDriver webDriver) {
		BaseDrivers.webDriver = webDriver;
	}

}

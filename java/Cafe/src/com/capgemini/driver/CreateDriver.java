package com.capgemini.driver;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.capgemini.executor.Executioner;
import com.capgemini.utilities.Utilities;

//import org.openqa.selenium.SeleneseCommandExecutor;



public class CreateDriver {

	
	RemoteWebDriver internetExplorerDriver = null;
	
	RemoteWebDriver firefoxDriver = null;
	RemoteWebDriver iPhoneDriver = null;
	RemoteWebDriver androidDriver = null;
	RemoteWebDriver safariDriver = null;
	RemoteWebDriver chromeDriver = null;
	Utilities util = new Utilities();
	Executioner exe = new Executioner();

	/**
	 * After successful execution of this method an object of RemoteWebDriver
	 * gets created-*-
	 * 
	 * @param No
	 *            param
	 * @return RemoteWebDriver object.
	 */

	public RemoteWebDriver getWebDriver(String host, String browser) {
		String strBrowserType = null;

		strBrowserType = browser;

		try {
			if (strBrowserType.equalsIgnoreCase("IE")
					|| strBrowserType.equalsIgnoreCase("Internet Explorer")) {

				System.setProperty("webdriver.ie.driver",
						"./data/IEDriverServer.exe");

				DesiredCapabilities cap = DesiredCapabilities
						.internetExplorer();

				cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,
						true);
				cap.setCapability(InternetExplorerDriver.IE_SWITCHES, true);
				cap.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				cap.setCapability(
						InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, true);
				cap.setCapability(
						InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,
						true);

				if (host.isEmpty()) {
					return new InternetExplorerDriver(cap);
				} else {
					return new RemoteWebDriver(new URL(host), cap);
				}

			} else if (strBrowserType.equalsIgnoreCase("FF")
					|| strBrowserType.equalsIgnoreCase("Firefox")) {

				DesiredCapabilities cap = DesiredCapabilities.firefox();
				if (host.isEmpty()) {
					return new FirefoxDriver(cap);
				} else {
					return new RemoteWebDriver(new URL(host), cap);
				}

			} else if (strBrowserType.equalsIgnoreCase("IPhone")
					|| strBrowserType.equalsIgnoreCase("IOS")) {
				//DesiredCapabilities cap = DesiredCapabilities.iphone();
				 return new RemoteWebDriver(new URL(host), DesiredCapabilities.iphone());
			} else if (strBrowserType.equalsIgnoreCase("Safari")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("safari");

				// CommandExecutor executor = new SeleneseCommandExecutor(new
				// URL("http://localhost:4444/"), new URL(strAppURL),
				// capabilities);
				// safariDriver = new RemoteWebDriver(executor, capabilities);
				return safariDriver;
			} else if (strBrowserType.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"./data/chromedriver.exe");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				if (host.isEmpty()) {
					return new ChromeDriver(cap);
				} else {
					return new RemoteWebDriver(new URL(host), cap);
				}

			} else if (strBrowserType.equalsIgnoreCase("android")) {
				
				return new RemoteWebDriver(new URL(host), DesiredCapabilities.android());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public RemoteWebDriver getWebDriver() {
		String strBrowserType = null;

		strBrowserType = exe.strExecutionBrowser; //using browser name from executioner

		try {
			if (strBrowserType.equalsIgnoreCase("IE")
					|| strBrowserType.equalsIgnoreCase("Internet Explorer")) {
				
				System.setProperty("webdriver.ie.driver",
						"./data/IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities
						.internetExplorer();
				cap.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

				internetExplorerDriver = new InternetExplorerDriver(cap);
				return internetExplorerDriver;

			} else if (strBrowserType.equalsIgnoreCase("FF")
					|| strBrowserType.equalsIgnoreCase("Firefox")) {
				firefoxDriver = new FirefoxDriver();

				return firefoxDriver;
			} else if (strBrowserType.equalsIgnoreCase("Safari")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("safari");

				return safariDriver;
			} else if (strBrowserType.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						"./data/chromedriver.exe");
				chromeDriver = new ChromeDriver();
				return chromeDriver;
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

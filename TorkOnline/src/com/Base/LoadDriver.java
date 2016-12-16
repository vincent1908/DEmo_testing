package com.Base;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class LoadDriver {
    public static WebDriver driver;
    public static int timeOut=2000;
    public static int sleepTime=1000;
	public static void Wait() throws Exception{
		Thread.sleep(sleepTime);
	}
	
	public static void Wait(int time) throws Exception{
		Thread.sleep(time);
	}

	public static int getSleepTime() {
		return sleepTime;
	}

	public static void setSleepTime(int sleepTime) {
		LoadDriver.sleepTime = sleepTime;
	}

	public static int getTimeOut() {
		return timeOut;
	}
	public static void setTimeOut(int timeOut) throws InterruptedException {
		LoadDriver.timeOut = timeOut;
		driver.wait(timeOut);
	}
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static boolean loadDriver(){
		try{
			System.setProperty("webdriver.ie.driver", "D:/Users/Praviran/Documents/My Received Files/Selenium Drivers/Selenium Drivers/ie.exe");
		
			driver= new InternetExplorerDriver();
			//driver.wait(timeOut);
		}catch(Exception e){
			return false;
		}
		
		return true;
	}
}

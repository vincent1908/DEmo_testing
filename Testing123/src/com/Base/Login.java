package com.Base;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.utilities.Reporter;

import org.junit.*;


public class Login extends BasicOperation{
	public Login(Reporter reporter) {
		super(reporter);
		// TODO Auto-generated constructor stub
	}

	private static boolean login=false;
	public static URL default_url;
	private static String host;
	private static String protocol;
	private static int port;
	public static String getProtocol() {
		
		return protocol;
	}

	public static String getHost() {
		return host;
	}
	


	

	public static Boolean createLogin(URL url, String username,String password) throws InterruptedException, MalformedURLException{
		 default_url=new URL("http://10.114.185.74:8180/SWAppWeb/dummylogin.jsf");
			
		if(url.toString().length()>0){
			default_url=url;
		}
		host=default_url.getHost();
		protocol=default_url.getProtocol();
		System.out.println("[Info] Protocol:"+protocol);
		System.out.println("[Info] Host:"+host);
		port=default_url.getPort();
		
		
		
		if(loadDriver()){
			
			WebDriver driver=getDriver();
			driver.get(default_url.toString());
			try{
				(new WebDriverWait(driver, 120)).until(ExpectedConditions.presenceOfElementLocated(By.id("j_idt25:loginField")));

			}catch(Exception b){}
			driver.findElement(By.id("j_idt25:loginField")).clear();
			//fill("j_idt25:loginField",username,driver);
			
		//	driver.findElement(By.id("j_idt25:loginField")).sendKeys(username);
			//Thread.sleep(2000);
			driver.findElement(By.id("j_idt25:passwordField")).clear();
		
			//fill("j_idt25:passwordField",password,driver);
			//driver.findElement(By.id("j_idt25:passwordField")).sendKeys(password);
			
		//	driver.findElement(By.id("j_idt25:loginButton")).click();
		
			click("j_idt25:loginButton", driver);
			
			login=true;
		}
		else{
			System.err.println("[Info] Driver Error!");
		}
		
		return login;
	}
	
	
	public static URL getDefault_url() {
		return default_url;
	}

	public static int getPort() {
		return port;
	}

	public static boolean isLogin() throws MalformedURLException, InterruptedException{
		if(!login){
			createLogin(default_url,"ifdssu","testpassword");
		}
		return login;
	}
	
	
	
	
	
	
}

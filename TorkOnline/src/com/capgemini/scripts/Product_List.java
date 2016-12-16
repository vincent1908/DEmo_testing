package com.capgemini.scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.Base.*;

import jxl.read.biff.BiffException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.driver.CreateDriver;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.driver.StepExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.executor.WriteMaster;
import com.capgemini.utilities.ReadExcel;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Product_List {
	
	public String TestCase="Product_List";
	MainController main1 = new MainController();
	DesiredCapabilities capabilities = new DesiredCapabilities();
	Reporter reporter = new Reporter(this.getClass().getSimpleName());
	CreateDriver driver = BaseDrivers.getDriver();
	RemoteWebDriver webDriver = null;
	private Utilities utils = new Utilities(reporter);
	private ScriptExecutor scriptExecutor = new ScriptExecutor();
	private ExecutionRowNumber executionRowNumber = new ExecutionRowNumber();
	// Object for calling verification functions
	private Verification verify = new Verification(reporter);
	WebDriverWait wait = null;
	private StepExecutor stepExecutor = new StepExecutor(reporter);
	private String StrExecutionStartTime = null;
	private long executionStartTime = 0;
	Map<String, String> DataMap = new HashMap();
	Map<String, String> objDataMap = new HashMap();
	Boolean sExecutionStatus;
	ReadExcel readExcel = new ReadExcel(reporter);
	ReadExcel readExcel1 = new ReadExcel(reporter);

	public String getExecutionStartTime() {
		return StrExecutionStartTime;
	}

	public String getStartTime() {
		return String.valueOf(executionStartTime);
	}
	public void executeTestcase(String browserName) throws Exception {
	int iNumberOfRows = 0;
	readExcel.setInputFile(System.getProperty("File"));
    readExcel1.setInputFile(System.getProperty("ObjFile"));
	readExcel.setSheetName(TestCase);
	readExcel1.setSheetName(TestCase);
	DataMap=readExcel.loadDataMap();
	iNumberOfRows = readExcel.getiNOfRows();
	
	reporter.start(reporter.calendar);
	StrExecutionStartTime = reporter.strStartTime;
	executionStartTime = reporter.startTime;
	
	reporter.ReportGenerator("Cafe#"+browserName);
	for (int i = 1; i <= iNumberOfRows; i++) {
		webDriver=BaseDrivers.getWebDriver();
		wait = new WebDriverWait(webDriver, 10);
		readExcel.setDataMap(DataMap);
		readExcel.readByIndex(i);
		System.out.println(capabilities.getBrowserName());
		reporter.setStrBrowser(capabilities.getBrowserName());
		reporter.addIterator(i);
		objDataMap = (Map<String, String>) readExcel.readPropertyFile();
		testcaseMain();	
//		if (!BasicOperation.FailCase) 
//		main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
//		else
//		main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");
		
		
		//NextFunctionCall
		//WriteMaster.updateNextURL(TestCase,webDriver.getCurrentUrl());
		reporter.closeIterator();
		System.out.println("\t \t \t \t \t Row number: " + i);
		webDriver.close();
	}
	String strStopTime = reporter.stop();
	reporter.strStopTime = strStopTime;
	float timeElapsed = reporter.getElapsedTime();
	reporter.timeElapsed = timeElapsed;
	reporter.CreateSummary("Cafe#"+browserName);
	main1.final_result(TestCase,reporter);
	}	



		
	/////////Product_List--TC_161,TC_162,TC_163,TC_173/////////////////////		
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);


		try {
			String testcase_id = DataMap.get("Testcase ID") ;
			if(testcase_id.equals("TC_161")||testcase_id.equals("TC_162")||testcase_id.equals("TC_163")){
				productSelection();
			}
			else if(testcase_id.equals("TC_173"))
			{
				TC_Demo();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	        Created By - Pratibha Bhosale
	        Cretaed On - 11/16/2016 
	        Test case covered - Product_List--TC_161
	        Objective - On adding products to list and navigating to Product list page, by default all the check boxes should be selected.
		    Test case covered - Product_List--TC_162
	        Objective - I, as an end user, should be able to view product description along with the Product name and article number.
		    Test case covered - Product_List--TC_163
	        Objective - Product description and product image on product list should be same as that in generated PDF and product detail page.
	        
	 */		
	private void productSelection() throws IOException, InterruptedException {	

		try{
			BasicOperation.waitForPageToLoad(webDriver);	
			SCACommon.verifyProductList(webDriver, objDataMap, verify, "Verification for Products");	
			BasicOperation.waitFor(5000);
			BasicOperation.click("ProductTab", webDriver);	
			BasicOperation.waitForExpectedElement("Xpath",objDataMap.get("ShoppingList"),webDriver );	
			System.out.println("Home page window:"+webDriver.getWindowHandle());
			BasicOperation.clickLink("ShoppingList", webDriver, "Click on Shopping List");		
			BasicOperation.waitForPageToLoad(webDriver);
			Thread.sleep(5000);			
			
			Set<String> windowIterator = webDriver.getWindowHandles();
			for(String window:windowIterator){	
				//BasicOperation.childframeswitch(webDriver, window);
				webDriver.switchTo().window(window);
				System.out.println("Product page window:"+webDriver.getWindowHandle());
				System.out.println("On product window");
			}			
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.waitFor(5000);
			BasicOperation.waitForExpectedElement("xpath", objDataMap.get("ProductListLink"), webDriver);
			reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size(), "Pass", "Product list count is"+webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size(), true, webDriver);
			
			if(DataMap.get("Testcase ID").equals("TC_163")){
				BasicOperation.clickButton("DownloadButton", webDriver, "Download as PDF");
				BasicOperation.waitForPageToLoad(webDriver);
				Thread.sleep(4000);
				Robot rb = new Robot();
				rb.keyPress(KeyEvent.VK_ENTER);
				rb.keyRelease(KeyEvent.VK_ENTER);
				
			}else{
				BasicOperation.waitFor(5000);
				if(DataMap.get("Testcase ID").equals("TC_161")){					
					verify.verifyCheckboxStatus(webDriver, objDataMap.get("CheckBox"), "xpath", "true");							
				}			
			}
		}
		catch(Exception e){
			e.printStackTrace();
			reporter.writeStepResult("Testcase execution failed", "Testcase execution failed", "Testcase execution failed", "Fail", "Testcase execution failed", true, webDriver);
		}

	}

	
	private void TC_Demo() throws IOException, InterruptedException {	

		try{
			JavascriptExecutor jse = (JavascriptExecutor)webDriver;
			BasicOperation.waitForPageToLoad(webDriver);	
			SCACommon.verifyProductList(webDriver, objDataMap, verify, "Verification for Products");	
			BasicOperation.waitFor(5000);
			BasicOperation.click("ProductTab", webDriver);	
			BasicOperation.waitForExpectedElement("Xpath",objDataMap.get("ShoppingList"),webDriver );	
			System.out.println("Home page window:"+webDriver.getWindowHandle());
			BasicOperation.clickLink("ShoppingList", webDriver, "Click on Shopping List");		
			BasicOperation.waitForPageToLoad(webDriver);
			Thread.sleep(5000);			
			
			Set<String> windowIterator = webDriver.getWindowHandles();
			for(String window:windowIterator){	
				//BasicOperation.childframeswitch(webDriver, window);
				webDriver.switchTo().window(window);
				System.out.println("Product page window:"+webDriver.getWindowHandle());
				System.out.println("On product window");
			}			
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.waitFor(5000);
			BasicOperation.waitForExpectedElement("xpath", objDataMap.get("ProductListLink"), webDriver);
			try{
				if(webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size() > 0){
					reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size(), "Pass", "Product list count is"+webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size(), true, webDriver);
				}
				else{
					reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+" 0", "Pass", "Product list count is"+" 0", true, webDriver);
				}
				
			}
			catch(Exception e){
				reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+" 0", "Pass", "Product list count is"+" 0", true, webDriver);
			}
//			jse.executeScript("window.scrollBy(0,500)", "");
//			BasicOperation.clickButton("DownloadButton", webDriver, "Download as PDF");
//			BasicOperation.waitForPageToLoad(webDriver);
//			Thread.sleep(4000);
//			Robot rb = new Robot();
//			rb.keyPress(KeyEvent.VK_ENTER);
//			rb.keyRelease(KeyEvent.VK_ENTER);
//			//String home = System.getProperty("user.home");		
//			//SCACommon.verifyDownloadedPDFHasContent(webDriver, home+"/Downloads/" + "Torkproductlist" + ".pdf");
//			SCACommon.verifyDownloadedPDFHasContent(webDriver, "D:\\Users\\skahar\\Downloads\\Torkproductlist.pdf");
//			BasicOperation.waitFor(3000);
//			System.out.println("Window before delete:"+webDriver.getWindowHandle());
//			webDriver.navigate().refresh();
			/*Alert alt = webDriver.switchTo().alert();
			alt.accept();*/
			//BasicOperation.click("CheckBox", webDriver);
//			BasicOperation.clickButton(objDataMap.get("CheckBox"), webDriver, "Click on Select all checkbox");
//			BasicOperation.waitFor(2000);
//			//BasicOperation.click("ProductChkBox1", webDriver);
//			BasicOperation.clickButton(objDataMap.get("ProductChkBox1"), webDriver, "Click on Select one product checkbox");
//			//BasicOperation.click("DeleteProduct", webDriver);
			
			jse.executeScript("window.scrollBy(0,500)", "");
			System.out.println("Window before delete:"+webDriver.getWindowHandle());
			
			BasicOperation.clickButton("DeleteProduct", webDriver, "Click on Delete Button");
			System.out.println("Product deleted...");
			//BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.waitFor(6000);
			try{
				if(webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size() > 0){
					reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size(), "Pass", "Product list count is"+webDriver.findElementsByXPath(".//div[@class='productImageContainer']").size(), true, webDriver);
				}
				else{
					reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+" 0", "Pass", "Product list count is"+" 0", true, webDriver);
				}
				
			}
			catch(Exception e){
				reporter.writeStepResult("Product list count", "Get Product list count", "Product list count is"+" 0", "Pass", "Product list count is"+" 0", true, webDriver);
			}
			
			
//			/////////////////Adding products/////////
//			BasicOperation.click("TorkLogo", webDriver);
//			BasicOperation.waitFor(4000);
//			SCACommon.verifyProductList(webDriver, objDataMap, verify, "Verification for Products");	
//			BasicOperation.waitFor(5000);
//			BasicOperation.click("ProductTab", webDriver);	
//			BasicOperation.waitForExpectedElement("Xpath",objDataMap.get("ShoppingList"),webDriver );	
//			System.out.println("Home page window:"+webDriver.getWindowHandle());
//			BasicOperation.clickLink("ShoppingList", webDriver, "Click on Shopping List");		
//			BasicOperation.waitForPageToLoad(webDriver);
//			Thread.sleep(5000);			
//			
//			Set<String> windowIterator1 = webDriver.getWindowHandles();
//			for(String window:windowIterator1){	
//				//BasicOperation.childframeswitch(webDriver, window);
//				webDriver.switchTo().window(window);
//				System.out.println("Product page window:"+webDriver.getWindowHandle());
//				System.out.println("On product window");
//			}			
//			BasicOperation.waitForPageToLoad(webDriver);
//			BasicOperation.waitFor(5000);
//			BasicOperation.waitForExpectedElement("xpath", objDataMap.get("ProductListLink"), webDriver);
//			////////////////////////////////////////
//			BasicOperation.clickButton("DownloadButton", webDriver, "Download as PDF");
//			BasicOperation.waitForPageToLoad(webDriver);
//			Thread.sleep(4000);
//			Robot rb = new Robot();
//			rb.keyPress(KeyEvent.VK_ENTER);
//			rb.keyRelease(KeyEvent.VK_ENTER);
//			String home = System.getProperty("user.home");		
//			SCACommon.verifyDownloadedPDFHasContent(webDriver, home+"/Downloads/" + "Torkproductlist" + ".pdf");
		}
		catch(Exception e){
			e.printStackTrace();
			reporter.writeStepResult("Testcase execution failed", "Testcase execution failed", "Testcase execution failed", "Fail", "Testcase execution failed", true, webDriver);
		}
	}
	
}

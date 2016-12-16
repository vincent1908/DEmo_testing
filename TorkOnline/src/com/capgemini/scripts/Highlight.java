package com.capgemini.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;

import javax.swing.text.html.HTMLDocument.Iterator;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.GetText;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BaseDrivers;
import com.Base.BasicOperation;
import com.Base.MainController;
import com.capgemini.driver.CreateDriver;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.driver.StepExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.utilities.ReadExcel;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

/** 
        
        Created By - Madhura Katkar 
        Cretaed On - 11/22/2016 
        Test case covered - Highlight Module 
        Objective - To check the working of Highlight Module located on Home page 
        Parameters - <mention if reusable methods defined> 
     
   
        */

public class Highlight
{
	
	public String TestCase="Highlight";
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
	
	static String WindowHandle=null;
	private static String[] links = null;
	private static String[] linksText = null;
	private static int linksCount = 0;
	private static int linksTextCount = 0;

	public String getExecutionStartTime() {
		return StrExecutionStartTime;
	}

	public String getStartTime() {
		return String.valueOf(executionStartTime);
	}
	@SuppressWarnings("unchecked")
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
		//if (!BasicOperation.FailCase) 
	//	main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
	//	else
	//	main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");
		
		
		//NextFunctionCall
		//WriteMaster.updateNextURL(TestCase,webDriver.getCurrentUrl());
		reporter.closeIterator();
		System.out.println("\t \t \t \t \t Row number: " + i);
		//webDriver.close();
	}
	String strStopTime = reporter.stop();
	reporter.strStopTime = strStopTime;
	float timeElapsed = reporter.getElapsedTime();
	reporter.timeElapsed = timeElapsed;
	reporter.CreateSummary("Cafe#"+browserName);
	main1.final_result(TestCase,reporter);
	}	

	public void testcaseMain() throws InterruptedException, BiffException,
	Exception {

			BasicOperation.setDataMap(DataMap);
			BasicOperation.setReporter(TestCase, webDriver, reporter);
			BasicOperation.setPropertiesDataMap(objDataMap);
					
								
			stepExecutor.launchApplication("URL", DataMap, webDriver);
				
			
			ComponentVerify();
			ImagesPromoTitleVerify();
			TextClickableVerify();
			ReadmoreClickableVerify();

}


public void ComponentVerify() {

	//######Start of Highlight Module>>TC_06########//

	BasicOperation.waitFor(2000);
	try{
	boolean imgCheck = webDriver.findElementByXPath("//*[@id='highlightPromos']/div/div/div/div[1]/div[@class='promoItem']").isDisplayed();
	if (imgCheck == true){
		System.out.println("Expected Highlight module>>Tork Resources section is displayed");
	}
	else{
		System.out.println("Expected Highlight module>>Tork Resources section is not displayed");
	}
	} catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	List<WebElement> allhighlights =  webDriver.findElements(By.xpath("//*[@id='highlightPromos']/div/div/div/div[2]/div/div[@class='col-xs-12']"));
	
	System.out.println("Highlights module left container component size is " + allhighlights.size());
	
	for(WebElement highlight : allhighlights){
		if(highlight.isDisplayed()){
			System.out.println("Component  -- " + highlight.getText() + " is displayed");
		}
	}
				
	//######End of Highlight Module>>TC_06########//
	
}

public void ImagesPromoTitleVerify() {

//######Start of Highlight Module>>TC_07########//


//	BasicOperation.waitFor(2000);
	
	List<WebElement> allhighlightsPromoTitle =  webDriver.findElements(By.xpath("//div/h3[@class='promoItemTitle']/a"));
	
	System.out.println("Highlights module promo title count is " + allhighlightsPromoTitle.size());
	
	for(WebElement highlight : allhighlightsPromoTitle)
	{
		
		JavascriptExecutor jse = (JavascriptExecutor)webDriver;
		jse.executeScript("window.scrollBy(0,250)", "");
		//List<WebElement> allhighlightsPromo =  webDriver.findElements(By.xpath("//div/h3[@class='promoItemTitle']/a"));
		//for(WebElement highlightitem : allhighlightsPromo){
		if(highlight.isDisplayed()){
			System.out.println("Component" + highlight.getText() + "is displayed");
			
	      
	            try {
	            	highlight.click();
	            	BasicOperation.waitFor(4000);
		            System.out.println("Back to home page.");
		            BasicOperation.click("HomeLink", webDriver);
					BasicOperation.waitFor(4000);
	                break;
	            } catch(StaleElementReferenceException e) {
	            }
	          

		}
	}

	
	//######End of Highlight Module>>TC_13########//
}		

public void TextClickableVerify() {	

//######Start of Highlight Module>>TC_8########//

	BasicOperation.waitFor(3000);	
	List<WebElement> allhighlightsText =  webDriver.findElements(By.xpath("//div[@class='promoText threedots']"));
	
	System.out.println("Highlights module left container component size is " + allhighlightsText.size());
	
	for(WebElement Text : allhighlightsText){
		if(Text.isDisplayed()){
			BasicOperation.waitFor(3000);
			System.out.println("Component  -- " + Text.getText() + " is displayed");
			try{
				Text.click();
				System.out.println("Component text is clickable. Test case failed");
			}
			catch (Exception e) {
				System.out.println("Component text is not clickable. Test case passed");
			}
		}
	}
	

//######End of Highlight Module>>TC_8########//
	
}
public void ReadmoreClickableVerify() {	

	//######Start of Highlight Module>>TC_10########//

		BasicOperation.waitFor(3000);
		try{
		//Clicking Read More link for Tork Resources component
		
		System.out.println("Inside ReadmoreClickableVerify");	
		BasicOperation.click("TorkResourcesReadMore", webDriver);
		BasicOperation.waitFor(4000);
        System.out.println("Back to home page.");
        BasicOperation.click("HomeLink", webDriver);
		BasicOperation.waitFor(4000);
		}catch (Exception e){}
			
		
		try{
		//Clicking Read More link for Placement matters for hospital hand sanitizers component
		
		BasicOperation.click("SanitizersReadMore", webDriver);
		BasicOperation.waitFor(4000);
		System.out.println("Back to home page.");
	    BasicOperation.click("HomeLink", webDriver);
		BasicOperation.waitFor(4000);
		}catch (Exception e){}
		
		try{
			//Clicking Read More link for Sustainability component
			
			BasicOperation.click("SustainabilityReadMore", webDriver);
			BasicOperation.waitFor(4000);
			System.out.println("Back to home page.");
		    BasicOperation.click("HomeLink", webDriver);
			BasicOperation.waitFor(4000);
			}catch (Exception e){}
			
		try{
			//Clicking Read More link for AD-a-Glance component
			
			BasicOperation.click("ADGlance", webDriver);
			BasicOperation.waitFor(4000);
			System.out.println("Back to home page.");
		    BasicOperation.click("//div[1]/header/div/h1/a[1]", webDriver);
			BasicOperation.waitFor(4000);
			
			}catch (Exception e){}	
		
	
	//######End of Highlight Module>>TC_10########//
		
	}
}
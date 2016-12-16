package com.capgemini.scripts;

import java.io.IOException;
import java.util.*;

import com.Base.*;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
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

public class Header {
	
	public String TestCase="Header";
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
		
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);


		try {
			header_menu();
			accessCookie();
			torkHeaderLogoClick();
			verifyRecommendationsSubmenuSection();
			clickRecommendationBusinessPage();
			clickRecommendationFocusPage();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 
	        Created By - Pratibha Bhosale
	        Created On - 11/16/2016 
			Updated By - Siddhartha Haldar
			Updated on - 12/03/2016
	        Test case covered - Header--TC_22
	        Objective - Header - Utility Navigator_User Interface for main navigation menu and promo.
	        
	 */	
	
	private void header_menu() throws IOException, InterruptedException {
		
		boolean sFlag=false;
		try{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);			
			String menus = DataMap.get("Menu");
			List<WebElement> menu_header = BasicOperation.getElements("header_menu");
			String actualMenuHeader[]=new String[menu_header.size()];
			for(int i=0;i<menu_header.size();i++)
			{
				actualMenuHeader[i]=menu_header.get(i).getText();
				if(menus.contains(actualMenuHeader[i]))
				{
					sFlag=true;
				}
				else
				{
					sFlag=false;
				}	
			}
				
			if(sFlag){					
					reporter.writeStepResult("Verify Header Menu links","Expected Header Menu links should be present" ,"Expected Header Links:"+menus,"Pass","Actual Header Links:"+Arrays.toString(actualMenuHeader), true, webDriver);	
				}
				else{
					reporter.writeStepResult("Verify Header Menu links","Expected Header Menu links should be present" ,"Expected Header Links:"+menus,"Fail","Actual Header Links:"+Arrays.toString(actualMenuHeader), true, webDriver);
				}
			BasicOperation.clickLink("header_product", webDriver, "Click on Product Link");
			if(webDriver.findElementByXPath(objDataMap.get("header_product")).getAttribute("class").equals("active")){
				reporter.writeStepResult("Verify product active menu will be different from other menu items", "Expected: " + "Product menu should be active", "Product menu active", "Pass", "Product menu validated for their appearance", true, webDriver);
			}
			else
			{
				reporter.writeStepResult("Verify product active menu will be different from other menu items", "Expected: " + "Product menu should be active", "Product menu not active", "Fail", "Product menu validated for their appearance", true, webDriver);
			}
/*			Thread.sleep(3000);
			List<WebElement> menu = webDriver.findElements(By.xpath(objDataMap.get("header_menu")));
			System.out.println("Menu size: "+menu.size());
			for(int i=2;i<=3;i++){
				//String br = webDriver.findElementByXPath(".//*[@id='headerNav']/div/div/div[2]/a").getAttribute("class");
				String br = webDriver.findElementByXPath(".//*[@id='headerNav']/div/div/div["+i+"]/a").getAttribute("class");
				System.out.println("Menu Colour : "+br);
				reporter.writeStepResult("Header menu appearance validation", "Expected: " + "menu color should be faded", "Actual list: "+"Other menu items are faded", "Pass", "Header menu validated for their appearance", true, webDriver);
			}*/
			
		}
		catch(Exception e){
			e.printStackTrace();
			reporter.writeStepResult("Header menu validation", "Header menu validation", "Validation Failed", "Fail", "Header menu validation failed", true,webDriver);
		}

	}	

	/** 
	        Created By - Pratibha Bhosale
	        Created On - 11/15/2016 
			Updated By - Siddhartha Haldar
			Updated On - 12/03/2016
	        Test case covered - Header--TC_70
	        Objective - Header - Cookies message.
	        
	 */	
	
	private void accessCookie() throws IOException, InterruptedException 
	{		

		try
		{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			BasicOperation.clickLink("cookie_header", webDriver, "Click on Click Here Cookie Link");	
			BasicOperation.waitFor(5000);
			verify.verifyPageTitle(webDriver, "LegalInformationPageTitle", DataMap);	
			BasicOperation.clickLink("cookie_cross", webDriver, "Click on Close icon of the cookie message");
			BasicOperation.waitFor(5000);
			verify.verifyElementNotPresent(webDriver,objDataMap.get("cookie_header"), "xpath", "Check non presence of Cookie message in header");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());			
		}

	}	
	
	
	/** 
	        Created By - Pratibha Bhosale
			Updated By - Siddhartha Haldar
	        Updated On - 11/28/2016 
	        Test case covered - Header--TC_71
	        Objective - Header - Click on Tork logo
	        
	 */	
	
	private void torkHeaderLogoClick() throws IOException, InterruptedException {

		try{			
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			verify.verifyElementClickable(webDriver, objDataMap.get("uppertorklogo"), "xpath", "HomePageTitle",DataMap,"Homepage Tork Logo");		
		}
		catch(Exception e){
			
		}

	}
	
	/** 
	        Created By - Siddhartha Haldar
			Created On - 12/02/2016
	        Test case covered - Header--TC_79
	        Objective - Header - Check Recommendation submenu section
	        
	 */	
	
	private void verifyRecommendationsSubmenuSection() throws IOException, InterruptedException {

		boolean sFlag=false;
		boolean focusFlag=false;
		try{
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			BasicOperation.clickLink("recommendationmenulink", webDriver,"Click on Recommendation");
			BasicOperation.waitFor(4000);
			List<WebElement> business_section_links = BasicOperation.getElements("business_section_links");
			List<WebElement> focus_section_links = BasicOperation.getElements("focus_section_links");
			List<WebElement> teaser_section = BasicOperation.getElements("teaser_section_blocks");
			String actualBusinessSectionLinks[]=new String[business_section_links.size()];
			String actualFocusSectionLinks[]=new String[focus_section_links.size()];
			for(int i=0;i<business_section_links.size();i++)
			{				
				if(business_section_links.get(i).isDisplayed())
				{
					actualBusinessSectionLinks[i]=business_section_links.get(i).getText();
					sFlag=true;
				}
				else
				{
					sFlag=false;
				}	
			}
				
			for(int i=0;i<focus_section_links.size();i++)
			{				
				if(focus_section_links.get(i).isDisplayed())
				{
					actualFocusSectionLinks[i]=focus_section_links.get(i).getText();
					focusFlag=true;
				}
				else
				{
					focusFlag=false;
				}	
			}
			
			if(sFlag)
				{					
					reporter.writeStepResult("Verify Business section in Recommendations","Expected business section links should be present" ,"NA","Pass","Actual Business section Links:"+Arrays.toString(actualBusinessSectionLinks), true, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify Business section in Recommendations","Expected business section links should be present" ,"NA","Fail","Actual Business section Links:"+Arrays.toString(actualBusinessSectionLinks), true, webDriver);
				}
			
			if(focusFlag)
				{						
					reporter.writeStepResult("Verify Focus section in Recommendations","Expected focus section links should be present" ,"NA","Pass","Actual Focus section Links:"+Arrays.toString(actualFocusSectionLinks), true, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify Focus section in Recommendations","Expected focus section links should be present" ,"NA","Fail","Actual Focus section Links:"+Arrays.toString(actualFocusSectionLinks), true, webDriver);
				}
			
			if(teaser_section.size()==2)
			{
				reporter.writeStepResult("Verify number of teaser sections in Recommendations","Expected number of teaser sections:2" ,"NA","Pass","Actual No of teaser sections:"+teaser_section.size(), true, webDriver);
			}
			else
			{
				reporter.writeStepResult("Verify number of teaser sections in Recommendations","Expected number of teaser sections:2" ,"NA","Fail","Actual No of teaser sections:"+teaser_section.size(), true, webDriver);
			}	
			
		}
		catch(Exception e){
			e.printStackTrace();
			reporter.writeStepResult("Verify Business/Focus/Teaser section in Recommendations","Expected business/focus/teaser section links should be present" ,"NA","Fail","Unable to locate business/focus/teaser sections", true, webDriver);
		}

	}
	
	
	/** 
			Created By - Siddhartha Haldar
	        Created On - 11/28/2016 
	        Test case covered - Header--TC_106,TC_107	        
	 */	
	
	private void clickRecommendationBusinessPage() throws IOException, InterruptedException {

		try{			
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			BasicOperation.clickLink("recommendationmenulink", webDriver,"Click on Recommendation");
			BasicOperation.waitFor(1000);
			BasicOperation.clickLink("recommendation_industry_link", webDriver,"Click on Industry Business link");
			verify.verifyElementNotClickable(webDriver,objDataMap.get("recommendations_page_heading"), "xpath", "Industry_page_title", DataMap, "Industry Header Title");
			verify.verifyElementNotClickable(webDriver,objDataMap.get("recommendation_header_image"), "xpath", "Industry_page_title", DataMap, "Industry Header Image");
		}
		catch(Exception e){
			
		}

	}
	
	/** 
		Created By - Siddhartha Haldar
        Created On - 11/28/2016 
        Test case covered - Header--TC_109,TC_110,TC_111        
 	*/	

	private void clickRecommendationFocusPage() throws IOException, InterruptedException {

		try{			
			BasicOperation.gotoURL(DataMap,"URL", webDriver, "Go to homepage");
			BasicOperation.waitFor(5000);
			BasicOperation.clickLink("recommendationmenulink", webDriver,"Click on Recommendation");
			BasicOperation.waitFor(1000);
			BasicOperation.clickLink("recommendation_dinner_link", webDriver,"Click on Dining Focus link");
			verify.verifyElementNotClickable(webDriver,objDataMap.get("recommendations_page_heading"), "xpath", "Dining_page_title", DataMap, "Dining Header Title");
			verify.verifyElementNotClickable(webDriver,objDataMap.get("recommendations_page_text"), "xpath", "Dining_page_title", DataMap, "Dining body text");
			verify.verifyElementNotClickable(webDriver,objDataMap.get("recommendation_header_image"), "xpath", "Dining_page_title", DataMap, "Industry Header Image");
		}
		catch(Exception e){
			
		}

	}
	
}


package com.capgemini.scripts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Base.BaseDrivers;
import com.Base.BasicOperation;
import com.Base.MainController;
import com.Base.SCACommon;
import com.capgemini.driver.CreateDriver;
import com.capgemini.driver.ScriptExecutor;
import com.capgemini.driver.StepExecutor;
import com.capgemini.executor.ExecutionRowNumber;
import com.capgemini.utilities.ReadExcel;
import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;
import com.capgemini.utilities.Verification;

/** 
        
        Created By - Sumit Kahar
        Cretaed On - 11/30/2016 
        Test case covered - Why_tork Module
        Objective - To check the working of Why tork Module located on Home page 
        Parameters - <mention if reusable methods defined> 
        
        */
public class Why_tork {

	public String TestCase="Why_tork";
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
		BasicOperation.waitFor(5000);
		
		try{
			String testcase_sr = DataMap.get("Testcase ID");
			System.out.println("Test scriptsto be executed are :"+testcase_sr);
			
			if(testcase_sr.equals("TC_37")){			
				TC_37();
			}
			else if(testcase_sr.equals("TC_132")){			
				TC_130_TC_132();
			}
			else if(testcase_sr.equals("TC_133")){			
				TC_133();
			}	
			else if(testcase_sr.equals("TC_134")){			
				TC_134();
			}			
			else if(testcase_sr.equals("TC_136")){			
				TC_136();
			}
			else if(testcase_sr.equals("TC_31")){			
				TC_31();
			}
			else if(testcase_sr.equals("TC_33")){			
				TC_33();
			}
			else if(testcase_sr.equals("TC_59")){			
				TC_59();
			}
			else if(testcase_sr.equals("TC_38")){			
				TC_38();
			}
			else if(testcase_sr.equals("TC_51_TC_152")){			
				TC_51_TC_152();
			}
			else if(testcase_sr.equals("TC_32")){			
				TC_32();
			}
			else if(testcase_sr.equals("TC_30")){			
				TC_30();
			}
			else {	
				System.out.println("...No Test case will be executed .....");
			}		

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public void TC_130_TC_132() throws Exception	{
		
		try	{
			System.out.println("--Why_tork- Click on hero image and heading in listing page. --");
		
			/*SCACommon.scrollForTridion(webDriver,14,"pagedownsteps","Reached Bottom");
			BasicOperation.waitFor(6000);
		
			verify.verifyElementPresent(webDriver,objDataMap.get("Why_tork_lnk"),"xpath","Why_tork link is present");
			BasicOperation.waitFor(6000);		
			BasicOperation.click(objDataMap.get("Why_tork_lnk"), webDriver);		
			BasicOperation.waitFor(4000);*/
		
			//System.out.println("Why_tork link clicked...........");
				
			verify.verifyElementPresent(webDriver,objDataMap.get("Whats_new"),"xpath","What's_new link is present");
			BasicOperation.click(objDataMap.get("Whats_new"), webDriver);		
			BasicOperation.waitFor(4000);
			System.out.println("What's_new link clicked...........");
		
			String strhyperlink = webDriver.findElementByXPath(objDataMap.get("Whats_new_img")).getAttribute("href");	
			System.out.println("hyperlink is :"+strhyperlink);
			boolean sFlag = true;
			if (strhyperlink == null)
			{
				System.out.println("Image is not Clickable..");
				sFlag = true;
			}			
		
			if(sFlag)
			{
				reporter.writeStepResult("Image is not Clickable ",
						"Verify image is clickable",
						"Image is not Clickable", "Pass",
						"Image is present on the page", true, webDriver);
			}
			else
			{
				reporter.writeStepResult("Image is not Clickable",
						"Verify image is clickable ","Image is Clickable", "Fail",
						"Image clickable Failed", true,webDriver);
			}
		
			verify.clickable(webDriver, objDataMap.get("Heading_click"),"xpath", objDataMap.get("Whats_new"),objDataMap.get("Title"));
			BasicOperation.waitFor(5000);
		
			verify.imageClickable(webDriver, objDataMap.get("Img_click"),"xpath", 
					objDataMap.get("Img_hyperlink"), objDataMap.get("Whats_new"),objDataMap.get("Title"));
				
			System.out.println("------Test Case TC_130_TC_132 successfully completed------");
			BasicOperation.waitFor(5000);
		} catch (Exception e) {			
			e.printStackTrace();			
		}
	}
		
	public void TC_37() throws Exception	{
		
		try	{
		System.out.println("--Why_tork- Campaign Page_In text highlight module text is Non-clickable. --");
				
		BasicOperation.click(objDataMap.get("Hygiene_link"), webDriver);		
		BasicOperation.waitFor(5000);
		
		System.out.println("Hygiene link clicked...........");
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Hygiene_text"),"xpath","Hygiene content is present");
		BasicOperation.waitFor(3000);
		
		verify.headingClickable(webDriver, objDataMap.get("Hygiene_text"),"xpath");
		BasicOperation.waitFor(3000);
			
		System.out.println("------Test Case 37 successfully completed------");
		BasicOperation.waitFor(1000);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

	public void TC_133() throws Exception	{
		
		try	{
			System.out.println("--Why_tork- Click on heading on campaign page. --");
				
			BasicOperation.click(objDataMap.get("Whats_new"), webDriver);		
			BasicOperation.waitFor(5000);		
			System.out.println("Whats_new link clicked...........");
		
			SCACommon.scrollForTridion(webDriver,2,"pagedownsteps","Reached Bottom");
			BasicOperation.waitFor(6000);
		
			BasicOperation.click(objDataMap.get("Click_readmore_campaign"), webDriver);		
			BasicOperation.Wait(5000);		
			System.out.println(" Readmore campaign link clicked...........");

			verify.verifyElementPresent(webDriver,objDataMap.get("Click_heading_campaign"),"xpath","Campaign heading is present");
			BasicOperation.waitFor(5000);
		
			verify.headingClickable(webDriver, objDataMap.get("Click_heading_campaign"),"xpath");
			BasicOperation.waitFor(5000);
							
			verify.verifyElementPresent(webDriver,objDataMap.get("Click_hero_img_campaign"),"xpath","Campaign image is present");
			BasicOperation.waitFor(5000);
		
			String strhyperlink = webDriver.findElementByXPath(objDataMap.get("Click_hero_img_campaign")).getAttribute("href");	
			System.out.println("hyperlink is :"+strhyperlink);
			boolean sFlag = true;
			if (strhyperlink == null)
			{
				System.out.println("Image is not Clickable..");
				sFlag = true;
			}			
		
			if(sFlag)
			{
				reporter.writeStepResult("Image is not Clickable ",
						"Verify image is clickable",
						"Image is not Clickable", "Pass",
						"Image is present on the page", true, webDriver);
			}
			else
			{
				reporter.writeStepResult("Image is not Clickable",
						"Verify image is clickable ","Image is Clickable", "Fail",
						"Image clickable Failed", true,webDriver);
			}
			
			System.out.println("------Test Case TC_133 successfully completed------");
			BasicOperation.Wait(3000);
		} catch (Exception e) {			
			e.printStackTrace();
			
		}
	}
	public void TC_134() throws Exception	{
		
		try	{
			System.out.println("-- Click on subheading on campaign page. --");
				
			BasicOperation.click(objDataMap.get("Whats_new"), webDriver);		
			BasicOperation.waitFor(5000);		
			System.out.println("Whats_new link clicked...........");
		
			SCACommon.scrollForTridion(webDriver,2,"pagedownsteps","Reached Bottom");
			BasicOperation.waitFor(6000);
		
			BasicOperation.click(objDataMap.get("Click_readmore_campaign"), webDriver);		
			BasicOperation.Wait(5000);		
			System.out.println(" Readmore campaign link clicked...........");
		
			verify.verifyElementPresent(webDriver,objDataMap.get("Click_Subheading_campaign"),"xpath","Campaign Subheading is present");
			BasicOperation.waitFor(5000);
		
			verify.headingClickable(webDriver, objDataMap.get("Click_Subheading_campaign"),"xpath");
			BasicOperation.waitFor(5000);						
		
			System.out.println("------Test Case TC_134 successfully completed------");
			BasicOperation.Wait(3000);
		} catch (Exception e) {			
			e.printStackTrace();
			
		}
	}
	
public void TC_136() throws Exception	{
		
		try	{
			System.out.println("-- Click on back to top label on campaign page and Click on back to campaigns label on campaign page --");
				
			BasicOperation.click(objDataMap.get("Whats_new"), webDriver);		
			BasicOperation.waitFor(5000);		
			System.out.println("Whats_new link clicked...........");
		
			SCACommon.scrollForTridion(webDriver,2,"pagedownsteps","Reached Bottom");
			BasicOperation.waitFor(6000);
		
			BasicOperation.click(objDataMap.get("Click_readmore_campaign"), webDriver);		
			BasicOperation.Wait(5000);		
			System.out.println(" Readmore campaign link clicked...........");
			
			SCACommon.scrollForTridion(webDriver,4,"pagedownsteps","Reached Bottom");
			BasicOperation.waitFor(6000);
			
			verify.verifyElementPresent(webDriver,objDataMap.get("backtotop"),"xpath","Back to top is present");
			BasicOperation.waitFor(5000);
			
			BasicOperation.click(objDataMap.get("backtotop"), webDriver);		
			BasicOperation.Wait(5000);		
			System.out.println(" Backtotop link clicked...........");
			
			SCACommon.scrollForTridion(webDriver,4,"pagedownsteps","Reached Bottom");
			BasicOperation.waitFor(6000);
			
			verify.verifyElementPresent(webDriver,objDataMap.get("backtocampaign"),"xpath","Back to campaign is present");
			BasicOperation.waitFor(5000);
			
			BasicOperation.click(objDataMap.get("backtocampaign"), webDriver);		
			BasicOperation.Wait(5000);		
			System.out.println(" Backtotop link clicked...........");					
		
			System.out.println("------Test Case TC_136 successfully completed------");
			BasicOperation.Wait(3000);
		} catch (Exception e) {			
			e.printStackTrace();
			
		}
	}

public void TC_31() throws Exception	{
	
	try	{
		System.out.println("-- What`s News_ On clicking to Read more link news detail page is displayed and On clicking to back button on What`s News page is displayed. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("Whats_new"),"xpath","Whats_new is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Whats_new"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Whats_new link clicked...........");
	
		SCACommon.scrollForTridion(webDriver,2,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Click_readmore_campaign"),"xpath","Click readmore campaign is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Click_readmore_campaign"), webDriver);		
		BasicOperation.Wait(5000);		
		System.out.println(" Readmore campaign link clicked...........");
		
		SCACommon.scrollForTridion(webDriver,4,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("backtotop"),"xpath","Back to top is present");
		BasicOperation.waitFor(5000);
		
		BasicOperation.click(objDataMap.get("backtotop"), webDriver);		
		BasicOperation.Wait(5000);		
		System.out.println(" Backtotop link clicked...........");
			
		System.out.println("------Test Case TC_31 successfully completed------");
		BasicOperation.Wait(3000);
	} catch (Exception e) {			
		e.printStackTrace();
		
	}
}
public void TC_33() throws Exception	{
	
	try	{
		System.out.println("--Press & Release details_ Verify the tool box module "
				+ "display and functionality on  press  & Release detail article page. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("pressRelease"),"xpath","Press and Release is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("pressRelease"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Press and Release link clicked...........");
	
		SCACommon.scrollForTridion(webDriver,5,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("pressReadmore"),"xpath","Click readmore press and release  is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("pressReadmore"), webDriver);		
		BasicOperation.Wait(5000);		
		System.out.println("Readmore press and release link clicked...........");
		
		SCACommon.scrollForTridion(webDriver,6,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Print"),"xpath","Print button  is present");
		BasicOperation.waitFor(5000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("Download"),"xpath","Download button  is present");
		BasicOperation.waitFor(5000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("Share"),"xpath","Share button  is present");
		BasicOperation.waitFor(5000);
				
		System.out.println("------Test Case TC_33 successfully completed------");
		BasicOperation.Wait(3000);
	} catch (Exception e) {			
		e.printStackTrace();
		
	}
}
public void TC_59() throws Exception	{
	
	try	{
		System.out.println("--Sustainability_User Interface for editorial sustainability page. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("Sustainability"),"xpath","Sustainability is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Sustainability"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Sustainability link clicked...........");
	
		verify.verifyElementPresent(webDriver,objDataMap.get("Sustain_img"),"xpath","Sustainability image  is present");
		BasicOperation.waitFor(5000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Sustain_breadcrumbs"),"xpath","Sustainability breadcrumbs  is present and highlighted");
		BasicOperation.waitFor(5000);
				
		SCACommon.scrollForTridion(webDriver,1,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Sustain_heading"),"xpath","Sustainability heading  is present");
		BasicOperation.waitFor(5000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Sustain_text"),"xpath","Sustainability text  is present");
		BasicOperation.waitFor(5000);
	
		SCACommon.scrollForTridion(webDriver,9,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Print"),"xpath","Print button  is present");
		BasicOperation.waitFor(5000);
		
		System.out.println("------Test Case TC_59 successfully completed------");
		BasicOperation.Wait(3000);
		
	} catch (Exception e) {			
		e.printStackTrace();
		
	}
}

public void TC_38() throws Exception	{
	
	try	{
		System.out.println("--Campaign Page_Content can be expanded and collapsed in Expandable/collapsible module. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("Campaign_tork"),"xpath","Campaign  is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Campaign_tork"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Campaign_tork link clicked...........");
		
		SCACommon.scrollForTridion(webDriver,1,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("Campaign_tork_heading"),"xpath","Campaign heading is present");
		BasicOperation.waitFor(5000);
				
		System.out.println("------Test Case TC_38 successfully completed------");
		BasicOperation.Wait(3000);
		
	} catch (Exception e) {			
		e.printStackTrace();
		
	}
}

public void TC_51_TC_152() throws Exception	{
	
	try	{
		System.out.println("-- News Listing page_Check the display functionality on news listing page. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("Press_release"),"xpath","Press release  is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Press_release"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Press_release link clicked...........");
		
		SCACommon.scrollForTridion(webDriver,2,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("Press_rel_Title"),"xpath","Press release title is present");
		BasicOperation.waitFor(5000);
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Press_rel_Publish_dt"),"xpath","Press release publishing date  is present");
		BasicOperation.waitFor(5000);	
		
		verify.verifyElementPresent(webDriver,objDataMap.get("Press_rel_Desc"),"xpath","Description is present");
		BasicOperation.waitFor(5000);
		
		System.out.println("------Test Case TC_51 successfully completed------");
		BasicOperation.Wait(3000);
		
	} catch (Exception e) {			
		e.printStackTrace();
		
	}
}

public void TC_32() throws Exception	{
	
	try	{
		System.out.println("-- News & Press_ Latest news is displayed at top depends on the published date on news & press page. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("Press_release"),"xpath","Press release  is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Press_release"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Press_release link clicked...........");
		
		SCACommon.scrollForTridion(webDriver,2,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("News_date"),"xpath","News First Date  is present");
		BasicOperation.waitFor(5000);
		
		//------------------
		boolean boolExists_latest = false;
		BasicOperation.waitFor(5000);
		
		String strLatesttime=webDriver.findElementByXPath(".//*[@id='divdata']/article[1]/div/div[2]/div[1]").getText();
		System.out.println(strLatesttime);
		
		List<WebElement> lst= webDriver.findElementsByXPath(objDataMap.get("Date_format"));
	    
	    SimpleDateFormat sdfFormatter = new SimpleDateFormat("MM/dd/yyyy");
	    Date dtLatestd =  sdfFormatter.parse(strLatesttime);
	    
	    for(int i=0; i<lst.size();i++)
	     {
	    	 String strNewdt=lst.get(i).getText().toString();
	    	 System.out.println("Date :"+strNewdt);
	    	 Date newDate =  sdfFormatter.parse(strNewdt);
	    	 
	      if(dtLatestd.compareTo(newDate)>=0)	    		 
	      {
	    	  boolExists_latest =true;
	      }
	    		  
	     }	  
	    
	    if(boolExists_latest=true)
	      {
	    	System.out.println("Lates Date Matched with date present on top");
	    	reporter.writeStepResult(("TC_32 is Successfull ").toUpperCase(),
					"TC_32 is Successfull ", "  "
							+ "TC_32 is Passed ", "Pass",
					"TC_32 is Passed ", true, webDriver);
	      }
	     else
	     {
	    	 System.out.println("Lates Date doesn't Match with date present on top");
	    	 reporter.writeStepResult(("TC_32 is Successfull ").toUpperCase(),
	 				"TC_32 is Successfull ", " "
	 						+ "TC_32 is failed ", "Fail",
	 				"TC_32 is failed ", true, webDriver);
	     }
	    
		
		System.out.println("------Test Case TC_32 successfully completed------");
		BasicOperation.Wait(3000);
		
	} catch (Exception e) {			
		e.printStackTrace();
	
	}
}

public void TC_30() throws Exception	{
	
	try	{
		System.out.println("-- News and Press_ Pagination functionality on new & press page. --");
			
		verify.verifyElementPresent(webDriver,objDataMap.get("Press_release"),"xpath","Press release  is present");
		BasicOperation.waitFor(5000);
	
		BasicOperation.click(objDataMap.get("Press_release"), webDriver);		
		BasicOperation.waitFor(5000);		
		System.out.println("Press_release link clicked...........");
		
		SCACommon.scrollForTridion(webDriver,12,"pagedownsteps","Reached Bottom");
		BasicOperation.waitFor(6000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("pagination"),"xpath","pagination  is present");
		BasicOperation.waitFor(5000);		

		verify.verifyElementPresent(webDriver,objDataMap.get("pagination1"),"xpath","pagination 1st link is present");
		BasicOperation.waitFor(5000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("pagination_Next"),"xpath","pagination next  is present");
		BasicOperation.waitFor(5000);
	
		verify.verifyElementPresent(webDriver,objDataMap.get("pagination_Prev"),"xpath","pagination previous  is present");
		BasicOperation.waitFor(5000);
	
		try {
			
			boolean boolNext=  webDriver.findElementByXPath(objDataMap.get("pagination_Next")).isEnabled();					
			if(boolNext==true)
	        {
	        	System.out.println("pass");
	        	
	        	reporter.writeStepResult(("Verify pagination Next is present ").toUpperCase(),
						"Verify pagination Next is present ", "Expected: "
								+ "pagination Next is present..... ", "Pass",
						"Verify pagination Next is present ", true, webDriver);
	        }
			else
			{
				System.out.println("fail");
				
				reporter.writeStepResult(("Verify pagination Next is present ").toUpperCase(),
						"Verify pagination Next is present ", "Expected: "
								+ "pagination Next Not Found..... ", "Fail",
						"Verify pagination Next is present ", true, webDriver);	
			}
			
			boolean boolPrev=  webDriver.findElementByXPath(objDataMap.get("pagination_Prev")).isDisplayed();					
			if(boolPrev==true)
	        {
	        	System.out.println("pass");
	        	
	        	reporter.writeStepResult(("Verify pagination Previous is present ").toUpperCase(),
						"Verify pagination Previous is present ", "Expected: "
								+ "pagination Previous is present..... ", "Pass",
						"Verify pagination Previous is present ", true, webDriver);
	        }
			else
			{
				System.out.println("fail");
				
				reporter.writeStepResult(("Verify pagination Previous is present ").toUpperCase(),
						"Verify pagination Previous is present ", "Expected: "
								+ "pagination Previous Not Found..... ", "Fail",
						"Verify pagination Previous is present ", true, webDriver);	
			}
			
		}catch (Exception e) {			
			e.printStackTrace();	
			
		}
		
		
		System.out.println("------Test Case TC_30 successfully completed------");
		BasicOperation.Wait(3000);
		
	} catch (Exception e) {			
		e.printStackTrace();
		
	}
}


}

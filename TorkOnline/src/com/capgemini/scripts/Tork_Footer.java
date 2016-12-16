	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
	        Objective - To create test scripts for Tork Footer components
	        
	 */	

package com.capgemini.scripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import jxl.read.biff.BiffException;

public class Tork_Footer {

	public String TestCase=this.getClass().getSimpleName();
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
	SCACommon scacommon=new SCACommon(reporter);
	WebDriverWait wait = null;
	JavascriptExecutor jse = (JavascriptExecutor)webDriver;
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
			/*if (!BasicOperation.FailCase) 
			main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
			else
			main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");*/
			
			
			//NextFunctionCall
			//WriteMaster.updateNextURL(TestCase,webDriver.getCurrentUrl());
			reporter.closeIterator();
			System.out.println("\t \t \t \t \t Row number: " + i);
			//webDriver.quit();
		}
		String strStopTime = reporter.stop();
		reporter.strStopTime = strStopTime;
		float timeElapsed = reporter.getElapsedTime();
		reporter.timeElapsed = timeElapsed;
		reporter.CreateSummary("Cafe#"+browserName);
		main1.final_result(TestCase,reporter);
		//webDriver.quit();
	}	

	
			
	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		try{
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
		
		stepExecutor.launchApplication("URL", DataMap, webDriver);
		BasicOperation.waitFor(10000);
		verifyLinksInCorporateNavigation();
		accessLinksInCorporateNavigation();
		clickInformationLinkAtFooter();
		clickInformationTextAtFooter();
		clickContactTextAtFooter();
		clickTorkLogoAtFooter();
		verifyIconsInFollowUsSection();
		accessLinksInFollowUs();
		verifyFootercopyofCampaignPage();
		accessLeftSideSubMenu();
		clickOnBreadcrumbOfEditorialPages();
		accessLeftSideSubMenuAndClickOnBoxImageandTitle();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_23
	 */	
	
	
	private void verifyLinksInCorporateNavigation() throws IOException, InterruptedException {		

		try{
				BasicOperation.waitForPageToLoad(webDriver);
				List<WebElement> footerLinks=BasicOperation.getElements("FooterLinksXPath");
				Boolean flagtitle=false;
				String footer_title = DataMap.get("Menu");
				String footerlinktitles[]=new String[footerLinks.size()];
				String footerlinktitlesactual[]=new String[footerLinks.size()];
				for(int i=0;i<footerLinks.size();i++)
				{
					footerlinktitlesactual[i]=footerLinks.get(i).getText();
					System.out.println("Footer link: "+footerLinks.get(i).getText());
				}
				for(int i=0;i<footerLinks.size();i++)
					{				
					if(footerLinks.get(i).getAttribute("href")!=null)
					{	
						if(footer_title.contains(footerLinks.get(i).getText()))
						{
							footerlinktitles[i]=footerLinks.get(i).getText();
							flagtitle=true;
							System.out.println("Pass");
						}
						else
						{
							flagtitle=false;
							System.out.println("Fail");
							break;
							
						}	
					}
					else
					{
						reporter.writeStepResult("Verify Footer links in corporate Navigation Bar","Expected Footer links in Navigation Bar should be present" ,"Expected Links:"+footer_title,"Fail","Footer link is not clickable", true, webDriver);
					}
				}
				if(flagtitle)
				{
					reporter.writeStepResult("Verify Footer links in corporate Navigation Bar","Expected Footer links in Navigation Bar should be present" ,"Expected Links:"+footer_title,"Pass","Actual Links:"+Arrays.toString(footerlinktitlesactual), true, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify Footer links in corporate Navigation Bar","Expected Footer links in Navigation Bar should be present" ,"Expected Links:"+footer_title,"Fail","Actual Links:"+Arrays.toString(footerlinktitlesactual), true, webDriver);
				}
		}
		catch(Exception e){
			System.out.println(e.getMessage());			
		}
		//webDriver.quit();

	}	
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_23,TC_96,TC_95,TC_97,TC_98
	 */	
	
	
	private void accessLinksInCorporateNavigation() throws IOException, InterruptedException {		

		WebElement footerlink=null;
		String footerPageTitles = DataMap.get("FooterPageTitles");
		String footerPageTitlesActual[]=null;
		boolean sflag=false;
		try{
				//BasicOperation.waitForPageToLoad(webDriver);
				List<WebElement> footerLinks=BasicOperation.getElements("FooterLinksXPath");
				footerPageTitlesActual=new String[footerLinks.size()];
				for(int i =0;i<footerLinks.size();i++)
				{
					System.out.println(i);
					//BasicOperation.Scroll(webDriver, objDataMap.get("footerscroll"), "css");
					BasicOperation.scroll(webDriver, "footerscroll");
					List<WebElement> footerLinksNew=BasicOperation.getElements("FooterLinksXPath");
					String locatornew=String.format(footerLinksNew.get(i).getText());
					System.out.println(webDriver.findElement(By.linkText(locatornew)).isDisplayed());
					footerlink=webDriver.findElement(By.linkText(locatornew));
					System.out.println(locatornew);
					footerlink.click();
					BasicOperation.waitFor(10000);
					footerPageTitlesActual[i]=webDriver.getTitle();
					if(footerPageTitles.contains(footerPageTitlesActual[i]))
					{
						sflag=true;
					}
					else
					{
						sflag=false;
					}
					webDriver.navigate().back();
				}
				}
				catch(StaleElementReferenceException e)
				{
					e.printStackTrace();
					webDriver.quit();
				}
			
				catch(ElementNotFoundException e)
				{
					System.out.println(e.getMessage());			
				}
				if(sflag)
				{
					reporter.writeStepResult("Verify expected page should get opened on click of respective footer link","Expected Footer link Page should open" ,"Expected Page Titles:"+footerPageTitles,"Pass","Actual Page Titles:"+Arrays.toString(footerPageTitlesActual), false, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify expected page should get opened on click of respective footer link","Expected Footer link Page should open" ,"Expected Page Titles:"+footerPageTitles,"Fail","Actual Page Titles:"+Arrays.toString(footerPageTitlesActual), true, webDriver);
				}
			//webDriver.quit();
			}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_100	        
	 */	
	
	
	private void clickInformationLinkAtFooter() throws IOException, InterruptedException {		

		try{
				//BasicOperation.waitForPageToLoad(webDriver);
				BasicOperation.writeNewScenario(webDriver, "Click on information link at Information and check new Information page");
				//BasicOperation.Scroll(webDriver, objDataMap.get("Informationscroll"), "xpath");
				BasicOperation.scroll(webDriver, "Informationscroll");
				//BasicOperation.clickLink("css", objDataMap.get("InformationLink"), webDriver, "Click on Information link");
				BasicOperation.clickLink("InformationLink", webDriver, "Click on Information link");
				BasicOperation.waitFor(5000);
				verify.verifyPageTitle(webDriver, "InformationPageTitle", DataMap);
		}	
				catch(ElementNotFoundException e)
				{
					System.out.println(e.getMessage());			
				}
			webDriver.quit();
			}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_99        
	 */
	
	
	private void clickInformationTextAtFooter() throws IOException, InterruptedException {		

		try{
				//BasicOperation.waitForPageToLoad(webDriver);
				BasicOperation.writeNewScenario(webDriver, "Check Information text is non clickable");
				//BasicOperation.Scroll(webDriver, objDataMap.get("Informationtitlescroll"), "xpath");
				BasicOperation.scroll(webDriver, "Informationtitlescroll");
				verify.verifyElementNotClickable(webDriver, objDataMap.get("Informationtext"),"css", "HomePageTitle", DataMap, "Information Text");
		}	
				catch(ElementNotFoundException e)
				{
					System.out.println(e.getMessage());			
				}
			webDriver.quit();
			}

	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_101        
	 */
	
	
	private void clickContactTextAtFooter() throws IOException, InterruptedException {		

		try{
				//BasicOperation.waitForPageToLoad(webDriver);
				BasicOperation.writeNewScenario(webDriver, "Check Contact text is non clickable");
				//BasicOperation.Scroll(webDriver, objDataMap.get("Informationtitlescroll"), "xpath");
				BasicOperation.scroll(webDriver, "Informationtitlescroll");
				verify.verifyElementNotClickable(webDriver, objDataMap.get("Contacttext"),"css", "HomePageTitle", DataMap, "Contact text");
		}	
				catch(ElementNotFoundException e)
				{
					System.out.println(e.getMessage());			
				}
			webDriver.quit();
			}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_102        
	 */
		
	
	private void clickTorkLogoAtFooter() throws IOException, InterruptedException {		

		try{
				//BasicOperation.waitForPageToLoad(webDriver);
				BasicOperation.writeNewScenario(webDriver, "Check Tork Footer logo is non clickable");
				//BasicOperation.Scroll(webDriver, objDataMap.get("Informationtitlescroll"), "xpath");
				BasicOperation.scroll(webDriver, "Informationtitlescroll");
				String currentpageTitle=webDriver.getTitle();
				BasicOperation.clickElement("Informationtitlescroll", webDriver, "Click on TorkFooter");
				String newpageTitle=webDriver.getTitle();
				if(currentpageTitle.equals(newpageTitle))
				{
					reporter.writeStepResult("Verify Footer logo not clickable","Tork Footer SCA logo should not be clickable" ,"NA","Pass","Tork Footer SCA logo is not clickable", true, webDriver);	
				}
				else
				{
					reporter.writeStepResult("Verify Footer logo not clickable","Tork Footer SCA logo should not be clickable" ,"NA","Fail","Tork Footer SCA logo is clickable", true, webDriver);
				}
		}	
				catch(ElementNotFoundException e)
				{
					System.out.println(e.getMessage());			
				}
			webDriver.quit();
			}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_103        
	 */
	
	
	private void verifyIconsInFollowUsSection() throws IOException, InterruptedException {		

		try{
				BasicOperation.waitForPageToLoad(webDriver);
				BasicOperation.writeNewScenario(webDriver, "Check Foolow Us section contains Follow Us static text and Social Media icons");
				//BasicOperation.Scroll(webDriver, objDataMap.get("Informationtitlescroll"), "xpath");
				BasicOperation.scroll(webDriver, "Informationtitlescroll");
				verify.verifyElementTextPresent(webDriver, objDataMap.get("FollowUsTextLocation"), "xpath","Follow us","Verify Follow Us static is present is present above social media icons");
				String followUsIconNames = DataMap.get("FollowUsLinksTitles");
				List<WebElement> followUsLinks=BasicOperation.getElements("FollowUsIcons");
				List<WebElement> followUsLinksTitle=BasicOperation.getElements("FollowUsIconsTitle");
				String followUsLinksactual[]=new String[followUsLinks.size()];
				Boolean flagtitle=false;
				for(int i=0;i<followUsLinks.size();i++)
				{
					if(followUsLinks.get(i).isDisplayed())
					{
						System.out.println("pass");
						followUsLinksactual[i]=followUsLinksTitle.get(i).getAttribute("title");
						flagtitle=true;
					}
					else
					{
						System.out.println("fail");
						followUsLinksactual[i]=followUsLinksTitle.get(i).getAttribute("title");
						flagtitle=false;
					}
				}

				if(flagtitle)
				{
					reporter.writeStepResult("Verify Social media icon links under footer section","Expected social media icon links in Navigation Bar should be present" ,"Expected Links:"+followUsIconNames,"Pass","Actual Links:"+Arrays.toString(followUsLinksactual), true, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify Social media icon links under footer section","Expected social media icon links in Navigation Bar should be present" ,"Expected Links:"+followUsIconNames,"Fail","Actual Links:"+Arrays.toString(followUsLinksactual), true, webDriver);
				}
		}
		catch(Exception e){
			System.out.println(e.getMessage());			
		}
		webDriver.quit();

	}	
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_103        
	 */
	
	private void accessLinksInFollowUs() throws IOException, InterruptedException {
		BasicOperation.writeNewScenario(webDriver, "Click on all Social media icons under Follow us and check opening of desired page");
		String followUsPageTitles = DataMap.get("FollowUsPageTitles");
		String followUsLinksactual[]=null;
		boolean sflag=false;
		try{
				//BasicOperation.waitForPageToLoad(webDriver);
			List<WebElement> followUsLinksTitle=BasicOperation.getElements("FollowUsIconsTitle");
			followUsLinksactual=new String[followUsLinksTitle.size()];
			//BasicOperation.Scroll(webDriver, objDataMap.get("Informationtitlescroll"), "xpath");
			BasicOperation.scroll(webDriver,"Informationtitlescroll");	
			for(int i =0;i<followUsLinksTitle.size();i++)
				{
					System.out.println(i);
					String winHandleBefore = webDriver.getWindowHandle();
					webDriver.switchTo().window(winHandleBefore);
					List<WebElement> followUsLinksTitleNew=BasicOperation.getElements("FollowUsIconsTitle");
					followUsLinksTitleNew.get(i).click();
					BasicOperation.waitFor(10000);					
					// Store the current window handle
					//String winHandleBefore = webDriver.getWindowHandle();
					for(String winHandle : webDriver.getWindowHandles()){
						webDriver.switchTo().window(winHandle);
						followUsLinksactual[i]=webDriver.getTitle();
						System.out.println(followUsLinksactual[i]);
					}
					webDriver.close();
					webDriver.switchTo().window(winHandleBefore);
					if(followUsPageTitles.contains(followUsLinksactual[i]))
					{
						sflag=true;						
					}
					else
					{
						sflag=false;
					}
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(sflag)
				{
					reporter.writeStepResult("Verify expected page should get opened on click of respective follow us link","Expected Social media link Page should open" ,"Expected Page Titles:"+followUsPageTitles,"Pass","Actual Page Titles:"+Arrays.toString(followUsLinksactual), true, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify expected page should get opened on click of respective follow us link","Expected Social media link Page should open" ,"Expected Page Titles:"+followUsPageTitles,"Fail","Actual Page Titles:"+Arrays.toString(followUsLinksactual), true, webDriver);
				}
			webDriver.quit();
			}
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_124        
	 */
	
	
	private void clickOnBreadcrumbOfEditorialPages() throws IOException, InterruptedException {
		try{
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.writeNewScenario(webDriver, "Check on click of breadcrumb of any editorial page, user redirects to one step above parent page");
			//BasicOperation.Scroll(webDriver, objDataMap.get("footerscroll"), "css");
			BasicOperation.scroll(webDriver,"Informationtitlescroll");
			//BasicOperation.clickLink("linktext",objDataMap.get("whytorklink"), webDriver, "Click on Why Tork updated?");
			BasicOperation.clickLink("whytorklink", webDriver,"Click on Why Tork updated?");
			BasicOperation.waitFor(5000);
			//BasicOperation.clickLink("xpath", objDataMap.get("editorialpageLink"), webDriver, "Click on editorial Page");
			BasicOperation.clickLink("editorialpageLink", webDriver,"Click on editorial Page");
			BasicOperation.waitFor(5000);
			List<WebElement> breadcrumbLinks=BasicOperation.getElements("breadCrumbPath");
			int noOfLinks=breadcrumbLinks.size();
			System.out.println("count:"+noOfLinks);
			String link=breadcrumbLinks.get(noOfLinks-1).getText();
			webDriver.findElementByLinkText(link);
			BasicOperation.waitFor(5000);
			verify.verifyPageTitle(webDriver,"BreadCrumbParentPageTitle", DataMap);
			}	
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		webDriver.quit();
	}
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_35        
	 */
	
	private void verifyFootercopyofCampaignPage() throws IOException, InterruptedException {
		try{
			BasicOperation.waitForPageToLoad(webDriver);
			//BasicOperation.Scroll(webDriver, objDataMap.get("footerscroll"), "css");
			BasicOperation.scroll(webDriver, "footerscroll");
			//BasicOperation.clickLink("linktext",objDataMap.get("whytorklink"), webDriver, "Click on Why Tork updated?");
			BasicOperation.clickLink("whytorklink", webDriver, "Click on Why Tork updated?");
			BasicOperation.waitFor(5000);
			//BasicOperation.Scroll(webDriver, objDataMap.get("Informationtitlescroll"), "xpath");
			BasicOperation.scroll(webDriver, "Informationtitlescroll");
			verify.verifyElementPresent(webDriver, objDataMap.get("Informationtext"), "css","Verify presence of Information text");
			verify.verifyElementPresent(webDriver, objDataMap.get("Contacttext"), "css","Verify presence of Contact text");
			clickInformationLinkAtFooter();
			}	
			catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
			webDriver.quit();
			}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_125        
	 */

	private void accessLeftSideSubMenu() throws IOException, InterruptedException {		
		WebElement leftsidelink;
		String leftPageTitles=DataMap.get("EditorialPageTitles");
		//String leftPageTitles = "Why Tork | Tork US,Tork is part of SCA | Tork US,Sustainability | Tork US,Ecolabels | Tork US,Tork and Hygiene | Tork US,Dispenser placement in hospitals | Tork US,Tork newslist | Tork US,Resources | Tork US,Tork Tools | Tork US";
		String leftPageTitlesActual[]=null;
		boolean sflag=false;
		try{
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.writeNewScenario(webDriver, "Check on click of all the editorial pages on left submenu ,redirected to desired page");
			//BasicOperation.Scroll(webDriver, objDataMap.get("footerscroll"), "css");
			BasicOperation.scroll(webDriver, "footerscroll");
			//BasicOperation.clickLink("linktext",objDataMap.get("whytorklink"), webDriver, "Click on Why Tork updated?");
			BasicOperation.clickLink("whytorklink", webDriver,"Click on Why Tork updated?");	
				List<WebElement> leftmenuLinks=BasicOperation.getElements("lefthandSubmenu");
				leftPageTitlesActual=new String[leftmenuLinks.size()];
				for(int i=0;i<leftmenuLinks.size();i++)
				{
					System.out.println(leftmenuLinks.get(i).getText());
				}
				for(int i=0;i<leftmenuLinks.size();i++)
				{
					System.out.println(i);
					BasicOperation.waitFor(5000);
					List<WebElement> leftmenuLinksNew=BasicOperation.getElements("lefthandSubmenu");
					String locatornew=String.format(leftmenuLinksNew.get(i).getText());
					leftsidelink=webDriver.findElement(By.linkText(locatornew));
					System.out.println(locatornew);
					leftsidelink.click();
					BasicOperation.waitFor(5000);
					leftPageTitlesActual[i]=webDriver.getTitle();
					if(leftPageTitles.contains(leftPageTitlesActual[i]))
					{
						sflag=true;
					}
					else
					{
						sflag=false;
						reporter.writeStepResult("Verify expected page should get opened on click of respective left side navigation link","Expected left side navigation link Page should open" ,"Expected Page Titles:"+leftPageTitles,"Fail","Actual Page Title:"+leftPageTitlesActual[i], false, webDriver);
					}
					if(!leftPageTitlesActual[i].contains("Why Tork | Tork US"))
					{
					webDriver.navigate().back();
					}
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					//webDriver.quit();
				}
				
				if(sflag)
				{
					reporter.writeStepResult("Verify expected page should get opened on click of respective left side navigation link","Expected left side navigation link Page should open" ,"Expected Page Titles:"+leftPageTitles,"Pass","Actual Page Titles:"+Arrays.toString(leftPageTitlesActual), false, webDriver);
				}
				else
				{
					reporter.writeStepResult("Verify expected page should get opened on click of respective left side navigation link","Expected left side navigation link Page should open" ,"Expected Page Titles:"+leftPageTitles,"Fail","Actual Page Titles:"+Arrays.toString(leftPageTitlesActual), false, webDriver);
				}
				webDriver.quit();
			
			}
	
	
	/** 
	        Created By - Siddhartha Haldar
	        Created On - 11/14/2016 
			Test case covered - TorkFooter--TC_126,TC_127        
	 */
	
	
	private void accessLeftSideSubMenuAndClickOnBoxImageandTitle() throws IOException, InterruptedException {		
		WebElement leftsidelink;		
		String leftPageTitles = DataMap.get("EditorialPageTitles");
		String leftPageTitlesActual;
		boolean sflag=false;
		try{
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.writeNewScenario(webDriver, "Check on click of all the editorial pages on left submenu ,redirected to desired page");
			//BasicOperation.Scroll(webDriver, objDataMap.get("footerscroll"), "css");
			BasicOperation.scroll(webDriver, "footerscroll");
			//BasicOperation.clickLink("linktext",objDataMap.get("whytorklink"), webDriver, "Click on Why Tork updated?");
			BasicOperation.clickLink("whytorklink", webDriver, "Click on Why Tork updated?");
				List<WebElement> leftmenuLinks=BasicOperation.getElements("lefthandSubmenu");
				for(int i =0;i<leftmenuLinks.size();i++)
				{
					System.out.println(leftmenuLinks.get(i).getText());
				}
				for(int i =1;i<leftmenuLinks.size();i++)
				{
					System.out.println(i);
					BasicOperation.waitFor(5000);
					List<WebElement> leftmenuLinksNew=BasicOperation.getElements("lefthandSubmenu");
					String locatornew=String.format(leftmenuLinksNew.get(i).getText());
					leftsidelink=webDriver.findElement(By.linkText(locatornew));
					System.out.println(locatornew);
					leftsidelink.click();
					BasicOperation.waitFor(5000);
					leftPageTitlesActual=webDriver.getTitle();
					if(leftPageTitlesActual.contains("Tork is part of SCA | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage1Title", DataMap);
						BasicOperation.clickElement("EditorialTitle1", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage1Title", DataMap);
					}
					else if(leftPageTitlesActual.contains("Sustainability | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage2Title", DataMap);
						BasicOperation.clickElement("EditorialTitle1", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage2Title", DataMap);
					}
					else if(leftPageTitlesActual.contains("Ecolabels | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage3Title", DataMap);
						BasicOperation.clickElement("EditorialTitle3", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage3Title", DataMap);
					}
					else if(leftPageTitlesActual.contains("Tork and Hygiene | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage4Title", DataMap);
						BasicOperation.clickElement("EditorialTitle4", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage4Title", DataMap);
					}
					else if(leftPageTitlesActual.contains("Dispenser placement in hospitals | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage5Title", DataMap);
						BasicOperation.clickElement("EditorialTitle5", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage5Title", DataMap);
					}
					else if(leftPageTitlesActual.contains("Tork newslist | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage6Title", DataMap);
						BasicOperation.clickElement("EditorialTitle6", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage6Title", DataMap);
					}
					else if(leftPageTitlesActual.contains("Tork Tools | Tork US"))
					{
						BasicOperation.clickImage("EditorialLink", webDriver, "Click on Editorial Image for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage8Title", DataMap);
						BasicOperation.clickElement("EditorialTitle8", webDriver, "Click on Header Title for Editorial Page "+i);
						verify.verifyPageTitle(webDriver,"EditorialPage8Title", DataMap);
					}
					webDriver.navigate().back();
				}
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());	
				}
			webDriver.quit();
			}
	
}

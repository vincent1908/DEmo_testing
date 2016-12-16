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

	/**
	
	
	
	Modified By -Prangyasini Biswal
	Modified On - 14/1/2016
	
	*/
	
public class Circuit {
	

	
	public String TestCase="Circuit";
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
			
			Login();
			testcaseMain();
			
				
			if (!BasicOperation.FailCase) 
			main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script executed successfully!");
			else
			main1.printMessage(TestCase, Integer.parseInt(DataMap.get("index")), "script execution fail!");
			
			
			//NextFunctionCall
			//WriteMaster.updateNextURL(TestCase,webDriver.getCurrentUrl());
			reporter.closeIterator();
			System.out.println("\t \t \t \t \t Row number: " + i);
		//	webDriver.close();
		}
		String strStopTime = reporter.stop();
		reporter.strStopTime = strStopTime;
		float timeElapsed = reporter.getElapsedTime();
		reporter.timeElapsed = timeElapsed;
		reporter.CreateSummary("Cafe#"+browserName);
		main1.final_result(TestCase,reporter);

	}
	


	public void Login(){
	
		BasicOperation.setDataMap(DataMap);
		BasicOperation.setReporter(TestCase, webDriver, reporter);
		BasicOperation.setPropertiesDataMap(objDataMap);
				
		
		
			if(BasicOperation.needLogin==true){
				
				BasicOperation.needLogin=false;
				
				stepExecutor.launchApplication("URL", DataMap, webDriver);
				
				BasicOperation.fill("loginField_Name", "loginField_Name" , webDriver);
				
				BasicOperation.fill("passwordField_Name", "passwordField_Name", webDriver);
				
				BasicOperation.click("login_button", webDriver);
			
				BasicOperation.selectById(".//*[@id='welcome:country']", "Territory", webDriver); 
                 BasicOperation.waitFor(2000); 
                 BasicOperation.click(".//input[@value=\"Go\"]", webDriver);
                 BasicOperation.waitFor(4000);			
                 }
	}

	public void testcaseMain() throws InterruptedException, BiffException,
			Exception {
		
	if(DataMap.get("Module").contains("Add")){
		
	    addCircuit();
	    
	}
		
		if(DataMap.get("Module").equals("Update")){
			
			updateCircuit();

		}
	}

	public void addCircuit() {

	
			BasicOperation.click(".//*[@id='menuForm:menuId']/div/div[2]/a[1]/img", webDriver);
			BasicOperation.waitFor(3000);
			webDriver.findElement(By.partialLinkText("Circuit")).click();
			BasicOperation.waitFor(3000);
			BasicOperation.click("AddCircuit", webDriver);
		
		
		BasicOperation.fill("Short_Name", "Short_Name", webDriver);
		BasicOperation.fill("Long_Name", "Long_Name", webDriver);

		BasicOperation.clickCheckBox(".//*[@id='circuitSearchForm:owning']", "Owning", webDriver);
		BasicOperation.clickCheckBox(".//*[@id='circuitSearchForm:buying']", "Buying", webDriver);
		BasicOperation.clickCheckBox(".//*[@id='circuitSearchForm:legalEntity']", "Legal", webDriver);

		BasicOperation.clickCheckBox("ChkBox_Status", "Status", webDriver);

		BasicOperation.selectById("Dropdown_Status_change_reason", 4, webDriver);

		BasicOperation.selectById("Dropdown_Circuit_Category", 2, webDriver);
		BasicOperation.selectById("Dropdown_Circuit_Indicator", 1, webDriver);
		BasicOperation.selectById("Dropdown_Global_Circuit", 2, webDriver);

		BasicOperation.fill("Address", "Address", webDriver);
		// stepExecutor.enterTextValue("findElementByxpath",ResourceReader.readValue("Textbox_Address1"),
		// strDataFileName, "Address1", webDriver);

		if (DataMap.get("Territory").equalsIgnoreCase("Mexico")) {

			BasicOperation.fill(".//*[@id='circuitSearchForm:circuitAddressPanelRegion:0:externalNumberId']",
					((int) (Math.random() * 10000)) + "", webDriver);

			BasicOperation.fill(".//*[@id='circuitSearchForm:circuitAddressPanelRegion:0:internalNumberId']",
					((int) (Math.random() * 10000)) + "", webDriver);

		}

		BasicOperation.selectById(".//*[@id='circuitSearchForm:circuitAddressPanelRegion:0:stateMapId']", 1, webDriver);

		BasicOperation.fill("City", "City", webDriver);
		// stepExecutor.enterTextValue("findElementByxpath",ResourceReader.readValue("Textbox_City"),
		// strDataFileName, "City", webDriver);

		BasicOperation.selectById("Dropdown_Country", "Territory", webDriver);

		String code = DataMap.get("PostalCode");
		if (code.endsWith(".0")) {
			code = code.substring(0, code.length() - 2);
		}

		BasicOperation.fill("Textbox_PostalCode", code, webDriver);
		// BasicOperation.takeScreenShot();

	

		System.out.println("inside search");

		// BasicOperation.waitFor(2000);
		
		billing();
		contacts();
		}
	
	
	public void updateCircuit(){
		
		BasicOperation.click(".//*[@id='menuForm:menuId']/div/div[2]/a[1]/img", webDriver);
		BasicOperation.waitFor(3000);
		webDriver.findElement(By.partialLinkText("Circuit")).click();

		BasicOperation.fill("Search Short Name", "Short_Name", webDriver);

		BasicOperation.click("Button_Search", webDriver);

		BasicOperation.waitFor(3000);

		BasicOperation.click("circuit_lookup", webDriver);

		// .//*[@id='circuitSearchForm:crtSearchResultDt']/tbody/tr/td/a

		BasicOperation.waitFor(3000);
		
	

			BasicOperation.fill("Change_Date", "Change_Date", webDriver);
		
		
		BasicOperation.waitFor(1500);

		BasicOperation.click("Button_Save", webDriver);

		BasicOperation.waitFor(7000);
		
		
	}

	public void billing() {
		try {

			BasicOperation.waitFor(4000);

			BasicOperation.click("Tab_Billing", webDriver);

			BasicOperation.waitFor(4000);

			BasicOperation.selectById("Dropdown_EstimatedInputmode", 1, webDriver);

			BasicOperation.selectById("Dropdown_ActualInputMode", 1, webDriver);

			BasicOperation.selectById("Dropdown_Est_FrequencyDetail", 1, webDriver);

			BasicOperation.selectById("Dropdown_Act_FrequencyDetail", 1, webDriver);

			/**
			 * Code for making circuit Legal Entity
			 */

			BasicOperation.selectById(".//*[@id='circuitSearchForm:masterLicenseAgreementId']", 1, webDriver);

			BasicOperation.selectById("Dropdown_CreditProfile", "47 - Satisfactory Account", webDriver);

			if (DataMap.get("Legal").equalsIgnoreCase("Yes")) {

				BasicOperation.selectById(".//*[@id='circuitSearchForm:companyType']", 1, webDriver);

				BasicOperation.fill(".//*[@id='circuitSearchForm:vat']", ((int) (Math.random() * 10000)) + "",
						webDriver);

				BasicOperation.fill(".//*[@id='circuitSearchForm:fiscalCode']", "60.262.086/0001-51" ,
						webDriver);
			}
			/**
			 * End of Entity code
			 */

			// BasicOperation.takeScreenShot();

			System.out.println("inside billing");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void contacts() {
		try {
			// Thread.sleep(2000);

			BasicOperation.click("Tab_Contacts", webDriver);

			String data = DataMap.get("Module");

			int skip = 0;
			if (data.equalsIgnoreCase("update")) {

				List<String> availableContacts = new ArrayList<>();
				BasicOperation.waitFor(2000);
				for (int k = 0; k < 5; k++) {
					try {
						String value = webDriver.findElement(
								By.xpath(".//*[@id='circuitSearchForm:contactNameTable:" + k + ":j_idt677']/label"))
								.getText();
						availableContacts.add(value);
					} catch (Exception e) {
					}
				}
				if (availableContacts.contains(DataMap.get("Contact_Name"))) {
					skip = 1;
				}
				System.out.println(availableContacts);

			}

			if (skip == 0) {
				BasicOperation.click("Button_GetExistingContact", webDriver);

				BasicOperation.fill("Textbox_CompanyName", "Contact_Name", webDriver);

				BasicOperation.click("Button_Search_Contact", webDriver);

				BasicOperation.waitFor(5000);

				BasicOperation.click(".//table[@id='contactSearchResultsForm:dtTable']/tbody/tr/td[1]/span/a",
						webDriver);

				BasicOperation.waitFor(5000);

				for (int i = 7; i < 9; i++) {
					BasicOperation.waitFor(1500);
					BasicOperation.selectById(".//*[@id='circuitSearchForm:contactAssgTable:" + i
							+ ":contactNameList']", 1, webDriver);
					BasicOperation.waitFor(1500);
					BasicOperation.selectById(".//*[@id='circuitSearchForm:contactAssgTable:" + i + ":infoType']", 1,
							webDriver);

				}
			}
			// BasicOperation.takeScreenShot();

			// LoadDriver.Wait(5000);
			BasicOperation.waitFor(1500);

			BasicOperation.click("Button_Save", webDriver);

			BasicOperation.waitFor(7000);

			BasicOperation.checkForException(webDriver);

			System.out.println("inside contacts");
			
			BasicOperation.waitFor(15000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
}

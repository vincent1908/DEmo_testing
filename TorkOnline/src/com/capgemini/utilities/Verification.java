package com.capgemini.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.Base.BasicOperation;
/**
 * Verification --- Class for verification of test steps using Webdriver
 * 
 * @author Prasad Joshi
 */

public class Verification {
	Reporter reporter;
	Utilities utils;
	Map<String, String> DataMap;

	public Verification(Reporter reporter) {
		this.reporter = reporter;

		utils = new Utilities(reporter);
		DataMap = new HashMap();

	}

	/** 
	        Updated By - Siddhartha Haldar
	        Updated On - 11/22/2016 
	        Objective - To Verify Page Title
	        
	 */	
		
		public Boolean verifyPageTitle(RemoteWebDriver webDriver,String strKey,Map<String, String> DataMap) {
			Boolean sFlag = true;
			Map<String, String> dataMapLocal = DataMap;
			String strData = null;
			boolean isExpecetdTitle = false;
			String currentPageTitle=webDriver.getTitle();
			/*String strActualTtle = null;
			String strDetails = utils.getDataFileInfo();
			String[] arrDetails = strDetails.split("_");*/

			try {
				if (dataMapLocal.containsKey(strKey)) 
				{
					strData = dataMapLocal.get(strKey);
					//isExpecetdTitle=true;
				} 
				else 
				{
					sFlag = false;
					return sFlag;
				}			
			} catch (Exception e1) {
				System.out.println("Exception occurred -- " + e1.getMessage());

			}
			if (currentPageTitle.contains(strData)) {
				isExpecetdTitle=true;
				reporter.writeStepResult("Verification of Page Title",
						"Verify title of page",strData, "Pass",
						"Page title matches with expected", true, webDriver);
			} else {
				isExpecetdTitle=false;
				reporter.writeStepResult("Verification of Page Title",
						"Verify title of page", "Expected: "
								+ strData, "Fail",
						"Page title does not match with expected (Actual: "
								+currentPageTitle  + ")", true, webDriver);
			}
			return isExpecetdTitle;
		}
		
		/** 
		        Created By- Shital Thorat
		        Updated On - 12/7/2016 
		        Objective - To Verify Error occured 
		        
		 */	
		public boolean verifyErrorOccured(RemoteWebDriver webDriver) throws Exception
		{
			System.out.println("verify ErrorPage Occured!!!!!!!!!!!................");
			String curURL=null;
			String curTitle=null;
			//Boolean Status=null;
			
			BasicOperation.waitFor(8000);
			/*curURL=webDriver.getCurrentUrl();
			System.out.println("Cur URl............"+curURL);
			*/
		
			curTitle=webDriver.getTitle();
			System.out.println("curTitle......."+curTitle);
			
		
			BasicOperation.waitFor(5000);
			if((curTitle.contains("404:"))==true)
			{
				//verifyErrorMsgContent(webDriver);
				return true;
				
				
				
			}
			else if((curTitle.contains("500:"))==true)
			{
				//verifyErrorMsgContent(webDriver);
				return true;
				
			}
			else if((curTitle.contains("Error"))==true)
			{
				//verifyErrorMsgContent(webDriver);
				reporter.writeStepResult(("Different Error Occured....").toUpperCase(),
						"Verifying Error Occured", "Expected: "
								+ "Error Occured", "Pass",
						"Verifying Error Occured", true, webDriver);
				return true;
				
			}
			else
			{
				
				return false;
			}
			
			
		}
		
		public void verifyErrorMsgContent(RemoteWebDriver webDriver) throws Exception
		{
			
			String errHeading=null;
			WebElement errHeadingElmnt;
			String errDecrption=null;
			String errHeadingObj=null;
			String errDescObj=null;
			WebElement errDescrpElmnt;
			String headingModuleXpath=".//div[@class='mainContent col-xs-12 col-md-9']";
			String headingstr=".//div[@class='mainContent col-xs-12 col-md-9']/h1";
			String ErrDescr=".//div[@class='mainContent col-xs-12 col-md-9']/p";
			System.out.println("Inside Verify Msg content method");
			
			
			
			
			BasicOperation.waitFor(6000);
			
			verifyElementPresent(webDriver,headingModuleXpath,"Error Module is present");
			BasicOperation.waitFor(6000);
			System.out.println("Error Module present...........");
			
		
			verifyElementPresent(webDriver,headingstr,"Error Heading is present");
			BasicOperation.waitFor(6000);
			System.out.println("Error Heading present...........");
			
			errHeadingObj=BasicOperation.getElementIdentification(headingstr);
			errHeadingElmnt=BasicOperation.getElement(errHeadingObj);
			errHeading=errHeadingElmnt.getText();
			
			System.out.println("Error Heading Txt...."+errHeading);
			
			if((errHeading.isEmpty())==false)
			{
				System.out.println("Error Heading Txt Present....");
				reporter.writeStepResult(("Verify Error Heading Txt is Present").toUpperCase(),
						"Verify Error Heading Txt is Present", "Expected: "
								+ "Error Heading Txt Present", "Pass",
						"Verify Error Heading Txt is Present", true, webDriver);
				
			}
			else
			{
				
				System.out.println("Error Heading Txt is empty....");
				reporter.writeStepResult(("Verify Error Heading Txt is Present").toUpperCase(),
						"Verify Error Heading Txt is Present", "Expected: "
								+ "Error Heading Txt is empty", "Fail",
						"Verify Error Heading Txt is Present", true, webDriver);
				
			}
			
			headingClickable(webDriver,headingstr,"xpath");
			BasicOperation.waitFor(6000);
			
			verifyElementPresent(webDriver,ErrDescr,"Error Msg Description is present");
			BasicOperation.waitFor(6000);
			System.out.println("Error Msg Description is present...........");
			
			errDescObj=BasicOperation.getElementIdentification(ErrDescr);
			errDescrpElmnt=BasicOperation.getElement(errDescObj);
			errDecrption=errDescrpElmnt.getText();
			
			System.out.println("Error Description Txt...."+errDecrption);
			if((errDecrption.isEmpty())==false)
			{
				System.out.println("Error Msg Description txt is present....");
				reporter.writeStepResult(("Verify Error Msg Description txt is present").toUpperCase(),
						"Verify Error Msg Description is present", "Expected: "
								+ "Error Msg Description txt is present", "Pass",
						"Verify Error Msg Description is present", true, webDriver);
				
			}
			else
			{
				
				System.out.println("Error Msg Description txt is empty....");
				reporter.writeStepResult(("Verify Error Msg Description txt is present").toUpperCase(),
						"Verify Error Msg Description is present", "Expected: "
								+ "Error Msg Description txt is empty", "Fail",
						"Verify Error Msg Description is present", true, webDriver);
				
			}
			headingClickable(webDriver,ErrDescr,"xpath");
			BasicOperation.waitFor(6000);
			
			
		}
		
		/** 
		        Created By- Shital Thorat
		        Updated On - 12/7/2016 
		        Objective - To Verify Element Present or not on Page 
		        
		 */	
		// verify element with boolean return
		public Boolean verifyElement(RemoteWebDriver webDriver,
				String strElement, String strElementProperty,String message) {
			WebElement displayelement=null;
			boolean exists = false;
			try {
					if (strElementProperty.equalsIgnoreCase("name")) {
						displayelement=webDriver.findElementByName(strElement);
						if (webDriver.findElementsByName(strElement).size() != 0
								&& webDriver.findElementByName(strElement)
										.isDisplayed()) {
							exists = true;
						}
					} else if (strElementProperty.equalsIgnoreCase("id")) {
						displayelement=webDriver.findElementById(strElement);
						if (webDriver.findElementsById(strElement).size() != 0
								&& webDriver.findElementById(strElement)
										.isDisplayed()) {
							exists = true;
						}
					} else if (strElementProperty.equalsIgnoreCase("xpath")) {
						displayelement=webDriver.findElementByXPath(strElement);
						if (webDriver.findElementsByXPath(strElement).size() != 0
								&& webDriver.findElementByXPath(strElement)
										.isDisplayed()) {
							exists = true;
						}
					} else if (strElementProperty.equalsIgnoreCase("css")) {
						displayelement=webDriver.findElementByCssSelector(strElement);
						if (webDriver.findElementsByCssSelector(strElement).size() != 0
								&& webDriver.findElementByCssSelector(strElement)
										.isDisplayed()) {
							exists = true;
						}
					} 
					else 
					{
						exists = false;
					}
					try 
					{
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (Exception e1)
			{
				exists = false;
				System.out.println("Exception occurred -- " + e1.getMessage());
				return exists;
				
				
			}
			
			return exists;
		}
		
		public void verifyElementPresent(RemoteWebDriver webDriver,
				String strElement,String message) {
			WebElement displayelement=null;
			boolean exists = false;
			
			String elementObject=null;
			elementObject=BasicOperation.getElementIdentification(strElement);
			displayelement=BasicOperation.getElement(elementObject);
			
			try {
				
				if((displayelement.getSize()!=null)&& displayelement.isDisplayed()) {
							exists = true;
						}
					 else {
						exists = false;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (Exception e1) {
				System.out.println("Exception occurred -- " + e1.getMessage());
			}
			if (exists) {
				reporter.writeStepResult(message,
						"Verify element is present on the page",
						displayelement.getText(), "Pass",
						"Element is present on the page", true, webDriver);
			} else {
				reporter.writeStepResult(message,
						"Verify element is present on the page",
						displayelement.getText(), "Fail",
						"Element is not present on the page", true, webDriver);
			}
		}
		
	/*public void verifyElementPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String message) {
		WebElement displayelement=null;
		boolean exists = false;
		try {
				if (strElementProperty.equalsIgnoreCase("name")) {
					displayelement=webDriver.findElementByName(strElement);
					if (webDriver.findElementsByName(strElement).size() != 0
							&& webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					displayelement=webDriver.findElementById(strElement);
					if (webDriver.findElementsById(strElement).size() != 0
							&& webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					displayelement=webDriver.findElementByXPath(strElement);
					if (webDriver.findElementsByXPath(strElement).size() != 0
							&& webDriver.findElementByXPath(strElement)
									.isDisplayed()) {
						exists = true;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					displayelement=webDriver.findElementByCssSelector(strElement);
					if (webDriver.findElementsByCssSelector(strElement).size() != 0
							&& webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult(message,
					"Verify element is present on the page",
					displayelement.getText(), "Pass",
					"Element is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult(message,
					"Verify element is present on the page",
					displayelement.getText(), "Fail",
					"Element is not present on the page", true, webDriver);
		}
	}*/


	public void verifyElementNotPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String message) {
		WebElement displayelement=null;
		boolean exists = false;
		try {
				if (strElementProperty.equalsIgnoreCase("name")) {
					displayelement=webDriver.findElementByName(strElement);
					if (!displayelement.isDisplayed()) {
						exists = true;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					displayelement=webDriver.findElementById(strElement);
					if (!displayelement.isDisplayed()) {
						exists = true;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					displayelement=webDriver.findElementByXPath(strElement);
					if (!displayelement.isDisplayed()) {
						exists = true;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					displayelement=webDriver.findElementByCssSelector(strElement);
					if (!displayelement.isDisplayed()) {
						exists = true;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult(message,
					"Verify element is not present on the page",
					"NA", "Pass",
					"Element is not present on the page", true, webDriver);
		} else {
			reporter.writeStepResult(message,
					"Verify element is not present on the page",
					"NA", "Fail",
					"Element is present on the page", true, webDriver);
		}
	}
	
	// Verifying whether link is present on the page
		public void verifyLinkPresent(RemoteWebDriver webDriver, String strElement) {

			boolean exists = false;
			WebElement displayelement=null;
			
			String elementObject=null;
			elementObject=BasicOperation.getElementIdentification(strElement);
			displayelement=BasicOperation.getElement(elementObject);
					
			try {
				for (int interval = 0; interval < 30; interval++) {
					if (displayelement.getSize()!=null && displayelement.isDisplayed()) {
						exists = true;
						break;
					}  else {
						exists = false;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (Exception e1) {
				System.out.println("Exception occurred -- " + e1.getMessage());
				reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
						"Verify link is present on the page",
						strElement.toUpperCase(), "Fail",
						"Link is not present on the page", true, webDriver);
			}
			if (exists) {
				reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
						"Verify link is present on the page",
						strElement.toUpperCase(), "Pass",
						"Link is present on the page", true, webDriver);
			} else {
				reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
						"Verify link is present on the page",
						strElement.toUpperCase(), "Fail",
						"Link is not present on the page", true, webDriver);
			}
		}

	
/*	// Verifying whether link is present on the page
	public void verifyLinkPresent(RemoteWebDriver webDriver, String strElement) {

		boolean exists = false;
		try {
			for (int interval = 0; interval < 30; interval++) {
				if (webDriver.findElementsByLinkText(strElement).size() != 0
						&& webDriver.findElementByLinkText(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsByName(strElement).size() != 0
						&& webDriver.findElementByName(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsById(strElement).size() != 0
						&& webDriver.findElementById(strElement).isDisplayed()) {
					exists = true;
					break;
				} else if (webDriver.findElementsByXPath(strElement).size() != 0
						&& webDriver.findElementByXPath(strElement)
								.isDisplayed()) {
					exists = true;
					break;
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Fail",
					"Link is not present on the page", true, webDriver);
		}
		if (exists) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Pass",
					"Link is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify link is present on the page",
					strElement.toUpperCase(), "Fail",
					"Link is not present on the page", true, webDriver);
		}
	}
*/
	// Verifying whether element text is present on the page
	public void verifyElementTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText,String message) {

		String strActualText = null;
		

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByXPath(strElement)
							.getText();
				}

			}
			if (strElementProperty.equalsIgnoreCase("css")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByCssSelector(strElement)
						.getAttribute("value");
				if (strActualText == null) {
					strActualText = webDriver.findElementByCssSelector(
							strElement).getText();
				}

			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.contains(strExpectedText)) {
			reporter.writeStepResult(message,
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text  is present", true, webDriver);
		} else {
			reporter.writeStepResult(message,
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	public void verifyDefaultTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {
		String strActualText = null;
	
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("defaultTxt");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("defaultTxt");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("defaultTxt");
			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify default text is present in the element",
					"Expected: " + strExpectedText, "Pass",
					"Expected text is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify default text is present in the element",
					"Expected: " + strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	// Verifying whether element attribute value is present on the page
	public void verifyTextValue(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedText) {

		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement)
						.getAttribute("value");
			}
			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getAttribute("value");
			}
			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getAttribute("value");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value attribute of element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected value is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify value attribute of element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected value is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}

	// Verifying whether element text is absent on the page
	public void verifyElementTextAbsent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {
		String strActualText = null;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementById(strElement).getText();
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByName(strElement)
						.getText();
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualText = webDriver.findElementByXPath(strElement)
						.getText();
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (!strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is not present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text is absent", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is not present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text is present", true, webDriver);
		}
	}

	// Verifying number of elemnts on the page
	public void verifyListValue(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValue) {

		WebElement listbox = null;
		boolean exists = false;
		
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.getText().equals(strExpectedValue)) {
					exists = true;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult("VERIFY",
					"Verify value is present in the listbox", "Expected: "
							+ strExpectedValue, "Pass",
					"Expected value is prsenet in the listbox", true, webDriver);
		} else {
			reporter.writeStepResult("VERIFY",
					"Verify value is present in the listbox", "Expected: "
							+ strExpectedValue, "Fail",
					"Expected value is not prsenet in the listbox", true,
					webDriver);
		}
	}

	public void verifyListValues(RemoteWebDriver webDriver, String strElement,
			String strElementProperty, String strExpectedValues) {
		WebElement listbox = null;
		String[] arrListValues = strExpectedValues.split(";");
		int counter = 0;
		
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (int i = 0; i < arrListValues.length; i++) {
				for (WebElement option : options) {
					if (option.getText().equals(arrListValues[i]))
						counter++;
					break;
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (counter == arrListValues.length) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify values are present in the listbox", "Expected: "
							+ strExpectedValues, "Pass",
					"Expected values are prsenet in the listbox", true,
					webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify values are present in the listbox", "Expected: "
							+ strExpectedValues, "Fail",
					"Expected values are not prsenet in the listbox", true,
					webDriver);
		}
	}

	public void verifySelectedListValue(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedSelectedValue) {
		WebElement listbox = null;
		boolean isSelected = false;
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementById(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByName(strElement);
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				listbox = webDriver.findElementByXPath(strElement);
			}

			List<WebElement> options = listbox.findElements(By
					.tagName("option"));
			for (WebElement option : options) {
				if (option.isSelected())
					isSelected = true;
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (isSelected) {
			reporter.writeStepResult("VERIFYING SELECT LIST VALUE",
					"VERIFY SELECTED VALUE IS PRESENT IN THE LISTBOX", "Expected: "
							+ strExpectedSelectedValue, "Pass",
					"Expected value is selected in the listbox", true,
					webDriver);
		} else {
			reporter.writeStepResult("VERIFYING SELECT LIST VALUE",
					"VERIFY SELECTED VALUE NOT PRESENT IN THE LISTBOX", "Expected: "
							+ strExpectedSelectedValue, "Fail",
					"Expected value is not selected in the listbox", true,
					webDriver);
		}
	}

	public void verifyCheckboxStatus(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedStatus) {

		String strActualStatus = null;

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementById(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByName(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByXPath(strElement)
						.getAttribute("checked");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("checked")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is checked", "Expected: "
							+ strExpectedStatus, "Pass", "Checkbox is checked",
					true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is checked", "Expected: "
							+ strExpectedStatus, "Fail",
					"Checkbox is not checked", true, webDriver);
		}

		if (strActualStatus.equalsIgnoreCase("false")
				&& strExpectedStatus.equalsIgnoreCase("unchecked")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is not checked", "Expected: "
							+ strExpectedStatus, "Pass",
					"Checkbox is not checked", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify checkbox is not checked", "Expected: "
							+ strExpectedStatus, "Fail", "Checkbox is checked",
					true, webDriver);
		}

	}

	public void verifyRadioButtonStatus(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strExpectedStatus) {
		String strActualStatus = null;
		boolean isSelected = false;
		String strDetails = utils.getDataFileInfo();
		String[] arrDetails = strDetails.split("_");
		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementById(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByName(strElement)
						.getAttribute("checked");
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				//verifyElementPresent(webDriver, strElement, strElementProperty);
				strActualStatus = webDriver.findElementByXPath(strElement)
						.getAttribute("checked");
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualStatus.equalsIgnoreCase("true")
				&& strExpectedStatus.equalsIgnoreCase("selected")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is selected", "Expected: "
							+ strExpectedStatus, "Pass",
					"Radio button is selected", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is checked", "Expected: "
							+ strExpectedStatus, "Fail",
					"Radio button is not selected", true, webDriver);
		}

		if (strActualStatus.equalsIgnoreCase("false")
				&& strExpectedStatus.equalsIgnoreCase("deselected")) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is not selected", "Expected: "
							+ strExpectedStatus, "Pass",
					"Radio button is not selected", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify radio button is not selected", "Expected: "
							+ strExpectedStatus, "Fail",
					"Radio button is selected", true, webDriver);
		}
	}

	// Verifying whether second element text is present on the page as the first
	// element is hidden
	public void verify2ndElementTextPresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty, String strExpectedText) {

		String strActualText = null;
		
		List<WebElement> l1;

		try {
			if (strElementProperty.equalsIgnoreCase("id")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("name")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}
			}

			if (strElementProperty.equalsIgnoreCase("xpath")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}

			}
			if (strElementProperty.equalsIgnoreCase("css")) {
				l1 = webDriver.findElementsByCssSelector(strElement);
				strActualText = l1.get(1).getAttribute("value");
				if (strActualText == null) {
					strActualText = l1.get(1).getText();
				}

			}

		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}

		if (strActualText.equals(strExpectedText)) {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Pass",
					"Expected text  is present", true, webDriver);
		} else {
			reporter.writeStepResult(System.getProperty("Test_Scenario_Name"),
					"Verify text is present in the element", "Expected: "
							+ strExpectedText, "Fail",
					"Expected text  is not present (Actual: " + strActualText
							+ ")", true, webDriver);
		}
	}
	public void verifyImagePresent(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,String element) {
		String temp=element.toUpperCase();
		boolean exists = false;
		try {
			for (int interval = 0; interval < 5; interval++) {
				if (strElementProperty.equalsIgnoreCase("name")) {
					if (webDriver.findElementsByName(strElement).size() != 0
							&& webDriver.findElementByName(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("id")) {
					if (webDriver.findElementsById(strElement).size() != 0
							&& webDriver.findElementById(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("xpath")) {
					if (webDriver.findElementsByXPath(strElement).size() != 0
							&& webDriver.findElementByXPath(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else if (strElementProperty.equalsIgnoreCase("css")) {
					if (webDriver.findElementsByCssSelector(strElement).size() != 0
							&& webDriver.findElementByCssSelector(strElement)
									.isDisplayed()) {
						exists = true;
						break;
					}
				} else {
					exists = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
		}
		if (exists) {
			reporter.writeStepResult("VERIFICATION OF IMAGE "+temp,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Pass",
					"Element is present on the page", true, webDriver);
		} else {
			reporter.writeStepResult("VERIFICATION OF IMAGE "+temp,
					"Verify element is present on the page",
					strElement.toUpperCase(), "Fail",
					"Element is not present on the page", true, webDriver);
		}
	}
	
	/** 
	        Created By - Sumit Kahar
	        Created On - 11/20/2016 
	        Objective - To Verify heading is clickable
	        
	 */	
	
	// Verifying heading is clickable

			public void headingClickable(RemoteWebDriver webDriver,
				String strElement, String strElementProperty) throws Exception	{
			try{
				
				String strhyperlink = null;
				
					if (strElementProperty.equalsIgnoreCase("name")) {
						strhyperlink  = webDriver.findElementByName(strElement).getAttribute("href");									
						if (strhyperlink == null)
						{
							System.out.println("Heading is not clickable..");
							reporter.writeStepResult(("Verify Heading is Clickable").toUpperCase(),
									"Verify Heading is Clickable", "Expected: "
											+ "Heading is not clickable", "Pass",
									"Verify Heading is Clickable", true, webDriver);
							
						}						
					}

					else if (strElementProperty.equalsIgnoreCase("id")) {
						strhyperlink  = webDriver.findElementById(strElement).getAttribute("href");											
						if (strhyperlink == null)
						{
							System.out.println("Heading is not clickable..");
							reporter.writeStepResult(("Verify Heading is Clickable").toUpperCase(),
									"Verify Heading is Clickable", "Expected: "
											+ "Heading is not clickable", "Pass",
									"Verify Heading is Clickable", true, webDriver);
						}
					}//webDriver.findElementByXPath(strElement).getAttribute("value");
					else if (strElementProperty.equalsIgnoreCase("xpath")) {
						strhyperlink  = webDriver.findElementByXPath(strElement).getAttribute("href");					
						System.out.println("hyperlink is "+strhyperlink);
						if (strhyperlink == null)
						{
							System.out.println("Heading is not clickable..");
							reporter.writeStepResult(("Verify Heading is Clickable").toUpperCase(),
									"Verify Heading is Clickable", "Expected: "
											+ "Heading is not clickable", "Pass",
									"Verify Heading is Clickable", true, webDriver);
						}	
						else {}
					}

			}catch (Exception e1) {
				System.out.println("Exception occurred -- " + e1.getMessage());
				System.out.println("Heading is clickable..");
				reporter.writeStepResult(("Verify Heading is Clickable").toUpperCase(),
						"Verify Heading is Clickable", "Expected: "
								+ "Heading is clickable", "Fail",
						"Verify Heading is Clickable", true, webDriver);
			}
		}


	
			/** 
			        Created By - Sumit Kahar
			        Created On - 11/20/2016 
			        Objective - To Verify image is clickable
			        
			 */	
			
	// Verifying Image is clickable-- if clickable the click on that

	public  void imageClickable(RemoteWebDriver webDriver,
		String strElement, String strElementProperty,
		String strElementhyperlink,String homestrElement,String PageTitle) throws Exception	{
		Boolean sFlag = true;
		
	try{
		
		String strsrc = null;
		
			if (strElementProperty.equalsIgnoreCase("name")) {
				strsrc = webDriver.findElementByName(strElement).getAttribute("src");	
					System.out.println("hyperlink is :"+strsrc);
					
					if (strsrc == null)
					{
						System.out.println("Image is not displayed..");
						sFlag = false;
					}			
					else
					{
						System.out.println("Image is displayed..");
						String strclick =webDriver.findElementByName(strElementhyperlink).getAttribute("href");
						System.out.println("image hyperlink is :"+strclick);
						if (strclick != null)
						{
							System.out.println("Image is clickable..");
							Thread.sleep(3000);
							webDriver.findElementByName(strElementhyperlink).click();
							Thread.sleep(3000);							
							
							// Get tiltle and verify title
							System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
							String actualTitle = webDriver.getTitle();
							String expectedTitle = PageTitle;
							if (expectedTitle.equalsIgnoreCase(actualTitle)){
							       System.out.println("Verification Successful - The correct title is displayed on the web page.");
							       sFlag = true;
							      }							
							    else
							       {
							            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
							            sFlag = false;
							        }
							
							System.out.println("Back to home page.");
							webDriver.findElementByXPath(homestrElement).click();
							Thread.sleep(3000);
						}
						else
						{
							System.out.println("Image is not clickable..");
							sFlag = false;
						}
					}
			}

			else if (strElementProperty.equalsIgnoreCase("id")) {
				strsrc = webDriver.findElementById(strElement).getAttribute("src");	
				System.out.println("hyperlink is :"+strsrc);
				if (strsrc == null)
				{
					System.out.println("Image is not displayed..");
					sFlag = false;
				}			
				else
				{
					System.out.println("Image is displayed..");
					String strclick =webDriver.findElementById(strElementhyperlink).getAttribute("href");
					System.out.println("image hyperlink is :"+strclick);
					if (strclick != null)
					{
						System.out.println("Image is clickable..");
						Thread.sleep(3000);
						webDriver.findElementById(strElementhyperlink).click();
						Thread.sleep(3000);

						// Get tile and verify title
						System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
						String actualTitle = webDriver.getTitle();
						String expectedTitle = PageTitle;
						if (expectedTitle.equalsIgnoreCase(actualTitle)){
						       System.out.println("Verification Successful - The correct title is displayed on the web page.");
						       sFlag = true;
						      }
						else
						       {
						            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						            sFlag = false;
						        }
						
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Image is not clickable..");
						 sFlag = false;
					}
				}
		}
			else if (strElementProperty.equalsIgnoreCase("xpath")) {
				System.out.println("Element is : "+strElement);
				strsrc = webDriver.findElementByXPath(strElement).getAttribute("src");	
				System.out.println("hyperlink is :"+strsrc);
				if (strsrc == null)
				{
					System.out.println("Image is not displayed..");
					 sFlag = false;
				}			
				else
				{
					System.out.println("Image is displayed..");
					String strclick =webDriver.findElementByXPath(strElementhyperlink).getAttribute("href");
					System.out.println("image hyperlink is :"+strclick);
					if (strclick != null)
					{
						System.out.println("Image is clickable..");
						Thread.sleep(3000);
						webDriver.findElementByXPath(strElementhyperlink).click();
						Thread.sleep(3000);
					
						// Get tile and verify title
						System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
						String actualTitle = webDriver.getTitle();
						String expectedTitle = PageTitle;
						if (expectedTitle.equals(actualTitle)){
						       System.out.println("Verification Successful - The correct title is displayed on the web page.");
						       sFlag = true;
						      }						
						    else
						       {
						            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						            sFlag = false;
						        }
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Image is not clickable..");
						sFlag = false;
					}
				}
		}
			
				else {
					sFlag = false;
				}
			

	}catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());
		System.out.println("Heading is clickable..");
		sFlag = false;
	}
	
	if(sFlag)
	{
			reporter.writeStepResult("Image Clickable ",
				"Verify image is clickable",
				strElement.toUpperCase(), "Pass",
				"Element is present on the page", true, webDriver);




	}
	else
	{
			reporter.writeStepResult("Image Clickable",
				"Verify image is not clickable ",strElement.toUpperCase(), "Fail",
				"Image clickable Failed", true,webDriver);
	}
}

	// Verifying Link  is clickable-- if clickable the click on that and back to home page
	
	/** 
	        Created By - Sumit Kahar
	        Created On - 11/20/2016 
	        Objective - To Verify element is clickable
	        
	 */	
	
	public  void clickable(RemoteWebDriver webDriver,
		String strElement, String strElementProperty,
		String homestrElement,String PageTitle) throws Exception	{
		Boolean sFlag = true;
		String strclick=null;
		
	try{
		
		String strtitle = null;
		
			if (strElementProperty.equalsIgnoreCase("name")) {
				strtitle = webDriver.findElementByName(strElement).getAttribute("title");	
				
				if (strtitle == null)
					{
						System.out.println("Link is not displayed..");
						sFlag = false;
					}			
					else
					{
						System.out.println("Link is displayed..");
						strclick =webDriver.findElementByName(strElement).getAttribute("href");
						System.out.println("Link hyperlink is :"+strclick);
						if (strclick != null)
						{
							System.out.println("Link is clickable..");
							webDriver.findElementByName(strElement).click();
							System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
							Thread.sleep(3000);

							if (strclick.contains(PageTitle))
							{
							      System.out.println("Verification Successful - The correct title is displayed on the web page.");
							      sFlag = true;
							 }
							else if(webDriver.getTitle().equalsIgnoreCase(PageTitle))
							   {
								 System.out.println("Verification Successful - The correct title is displayed on the web page.");
							      sFlag = true;
							    }
						  else
							  {
							         System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
							         sFlag = false;
							  }
						
							
							System.out.println("Back to home page.");
							webDriver.findElementByXPath(homestrElement).click();
							Thread.sleep(3000);
						}
						else
						{
							System.out.println("Link is not clickable..");
							sFlag = false;
						}
					}
			}

			else if (strElementProperty.equalsIgnoreCase("id")) {
				strtitle = webDriver.findElementById(strElement).getAttribute("title");	
				
				if (strtitle == null)
				{
					System.out.println("Link is not displayed..");
					sFlag = false;
				}			
				else
				{
					System.out.println("Link is displayed..");
					strclick =webDriver.findElementById(strElement).getAttribute("href");
					System.out.println("Link hyperlink is :"+strclick);
					if (strclick != null)
					{
						System.out.println("Link is clickable..");
						webDriver.findElementById(strElement).click();
						Thread.sleep(3000);		
						System.out.println(" tiltle is :"+webDriver.getTitle()+"*****************");
						// Get tile and verify title
						if (strclick.contains(PageTitle))
							{
								System.out.println("Verification Successful - The correct title is displayed on the web page.");
								sFlag = true;
								}
						else if(webDriver.getTitle().equalsIgnoreCase(PageTitle))
						   {
							 System.out.println("Verification Successful - The correct title is displayed on the web page.");
						      sFlag = true;
						    }
						    else
						       {
						            System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						            sFlag = false;
						        }
						
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Link is not clickable..");
						sFlag = false;
					}
				}
		}
			else if (strElementProperty.equalsIgnoreCase("xpath")) {							
				strtitle = webDriver.findElementByXPath(strElement).getAttribute("title");	
				
				if (strtitle == null)
				{
					System.out.println("Link is not displayed..");
					sFlag = false;
				}			
				else
				{
					System.out.println("Link is displayed..");
					strclick =webDriver.findElementByXPath(strElement).getAttribute("href");
					System.out.println("Link hyperlink is :"+strclick);
					if (strclick != null)
					{
						System.out.println("Link is clickable..");
						webDriver.findElementByXPath(strElement).click();
						Thread.sleep(4000);

						// Get tile and verify title
						if (strclick.contains(PageTitle))
						{
						      System.out.println("Verification Successful - The correct title is displayed on the web page.");
						      sFlag = true;
						 }
						 else if(webDriver.getTitle().equalsIgnoreCase(PageTitle))
						   {
							 System.out.println("Verification Successful - The correct title is displayed on the web page.");
						      sFlag = true;
						    }
						 else
						   {
						       System.out.println("Verification Failed - An incorrect title is displayed on the web page.");
						       sFlag = false;
						    }
						
						System.out.println("Back to home page.");
						webDriver.findElementByXPath(homestrElement).click();
						Thread.sleep(3000);
					}
					else
					{
						System.out.println("Link is not clickable..");
						sFlag = false;
					}
				}
		}
			
				else {sFlag = false;}
			

	}catch (Exception e1) {
		System.out.println("Exception occurred -- " + e1.getMessage());
		System.out.println("Read more  issue..");
		sFlag = false;
	}
					
	if(sFlag)
	{
		reporter.writeStepResult("VERIFY LINK IS CLICKABLE",
				"VERIFY WHETHER LINK IS CLICKABLE OR NOT","Link "+strclick, "Pass",
				"Link clickable successfull", true,webDriver);
	}
	else
	{
		reporter.writeStepResult("VERIFY LINK IS CLICKABLE",
				"VERIFY WHETHER LINK IS CLICKABLE OR NOT","Link "+strclick, "Fail",
				"Link clickable Failed", true,webDriver);
	}
	
}

// Verifying whether image is broken on the page
				public void verifyImageBroken(RemoteWebDriver webDriver,
						String strElement, String messagehead) {
					Object imgStatus = null;
					WebElement helement = null;
					boolean sflag=true;
					try {
						EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
						String script = "return (typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth>0 && arguments[0].complete)";
						helement=webDriver.findElementByXPath(strElement);
						imgStatus = eventFiringWebDriver.executeScript(script, helement);
						{
							if(imgStatus.equals(false))
							{
								sflag=false;
							}
							else
							{
								sflag=true;
							}
						}
					} catch (Exception e1) {
						//System.out.println("Exception occurred -- " + e1.getMessage());
						sflag=false;
					}
					if (sflag==false) {
						reporter.writeStepResult(messagehead,
								"Verify image present is broken or not",
								"Image", "Fail",
								"Image is broken or missing", true, webDriver);
					} else {
						reporter.writeStepResult(messagehead,
								"Verify image present is broken or not",
								"Image", "Pass",
								"Image is not broken", true, webDriver);
					}
				}
				
				/** 
				        Created By - Siddhartha Haldar
				        Created On - 11/22/2016 
				        Objective - To Verify element not clickable
				        
				 */	
				
				public void verifyElementNotClickable(RemoteWebDriver webDriver, String strElement, String strElementProperty,String message) {
					WebElement clickelement=null;
					boolean exists = false;
					try {
							if (strElementProperty.equalsIgnoreCase("name")) {
								clickelement=webDriver.findElementByName(strElement);
								if (webDriver.findElementByName(strElement).getAttribute("href")==null) {
									exists = true;
								}
								else
								{
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("id")) {
								clickelement=webDriver.findElementById(strElement);
								if (webDriver.findElementById(strElement).getAttribute("href")==null) {
									exists = true;
								}
								else
								{
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("xpath")) {
								clickelement=webDriver.findElementByXPath(strElement);
								if (webDriver.findElementByXPath(strElement).getAttribute("href")==null) {
									exists = true;
								}
								else
								{
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("css")) {
								clickelement=webDriver.findElementByCssSelector(strElement);
								if (webDriver.findElementByCssSelector(strElement).getAttribute("href")==null) {
									exists = true;
								}
								else
								{
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("linktext")) {
								clickelement=webDriver.findElementByLinkText(strElement);
								if (webDriver.findElementByLinkText(strElement).getAttribute("href")==null) {
									exists = true;
								}
								else
								{
									exists = false;
								}
							}
					} catch (Exception e1) {
						System.out.println("Exception occurred -- " + e1.getMessage());
					}
					if (exists) {
						reporter.writeStepResult(message,
								"Verify element not clickable",
								clickelement.getText(), "Pass",
								"Element is not clickable", true, webDriver);
					} else {
						reporter.writeStepResult(message,
								"Verify element not clickable",
								"NA", "Fail",
								"Element is not clickable", true, webDriver);
					}
				}

				/** 
				        Created By - Siddhartha Haldar
				        Created On - 11/22/2016 
				        Objective - To Verify element not clickable
				        
				 */	
				
				public void verifyElementNotClickable(RemoteWebDriver webDriver, String strElement, String strElementProperty,String currentpagetitlekey,Map<String, String> DataMap,String elementname) {
					WebElement clickelement=null;
					boolean exists = false;
					
					try {
							if (strElementProperty.equalsIgnoreCase("name")) {
								clickelement=webDriver.findElementByName(strElement);
								if (clickelement.getAttribute("href")==null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, currentpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("id")) {
								clickelement=webDriver.findElementById(strElement);
								if (clickelement.getAttribute("href")==null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, currentpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("xpath")) {
								clickelement=webDriver.findElementByXPath(strElement);
								if (clickelement.getAttribute("href")==null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, currentpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("css")) {
								clickelement=webDriver.findElementByCssSelector(strElement);
								if (clickelement.getAttribute("href")==null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, currentpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("linktext")) {
								clickelement=webDriver.findElementByLinkText(strElement);
								if (clickelement.getAttribute("href")==null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, currentpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
					} }catch (Exception e1) {
						System.out.println("Exception occurred -- " + e1.getMessage());
					}
					if (exists) {
						reporter.writeStepResult("Verify "+elementname+" is non clicable and click on it",
								"Verify element is non clickable",
								"NA", "Pass",
								"Element is non clickable", true, webDriver);
					} else {
						reporter.writeStepResult("Verify "+elementname+" is non clicable and click on it",
								"Verify element is non clickable",
								"NA", "Fail",
								"Element is clickable", true, webDriver);
					}
				}
				
				
				/** 
				        Created By - Siddhartha Haldar
				        Created On - 12/01/2016 
				        Objective - To Verify element clickable and if clickable ,click on that and verify the redirected page title
				        
				 */	
				
				public void verifyElementClickable(RemoteWebDriver webDriver, String strElement, String strElementProperty,String nextpagetitlekey,Map<String, String> DataMap,String elementname) 
				{
					WebElement clickelement=null;
					boolean exists = false;
					
					try {
							if (strElementProperty.equalsIgnoreCase("name")) {
								clickelement=webDriver.findElementByName(strElement);
								if (clickelement.getAttribute("href")!=null || clickelement.getAttribute("src")!=null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, nextpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("id")) {
								clickelement=webDriver.findElementById(strElement);
								if (clickelement.getAttribute("href")!=null || clickelement.getAttribute("src")!=null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									clickelement.click();
									Thread.sleep(7000);
									if(verifyPageTitle(webDriver, nextpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("xpath")) {
								clickelement=webDriver.findElementByXPath(strElement);
								if (clickelement.getAttribute("href")!=null || clickelement.getAttribute("src")!=null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, nextpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("css")) {
								clickelement=webDriver.findElementByCssSelector(strElement);
								if (clickelement.getAttribute("href")!=null || clickelement.getAttribute("src")!=null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, nextpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
							} else if (strElementProperty.equalsIgnoreCase("linktext")) {
								clickelement=webDriver.findElementByLinkText(strElement);
								if (clickelement.getAttribute("href")!=null || clickelement.getAttribute("src")!=null) {
									BasicOperation.clickLink(strElement, webDriver,"Click on "+elementname);
									Thread.sleep(5000);
									if(verifyPageTitle(webDriver, nextpagetitlekey, DataMap))
									{
										exists = true;
									}
									else
									exists = false;
								}
					} 
							
					}catch (Exception e1) {
						System.out.println("Exception occurred -- " + e1.getMessage());
					}
					if (exists) {
						reporter.writeStepResult("Verify "+elementname+" is clicable and click on it",
								"Verify element is clickable",
								"NA", "Pass",
								"Element is clickable", true, webDriver);
					} else {
						reporter.writeStepResult("Verify "+elementname+" is clicable and click on it",
								"Verify element is clickable",
								"NA", "Fail",
								"Element is not clickable", true, webDriver);
					}
				}

}

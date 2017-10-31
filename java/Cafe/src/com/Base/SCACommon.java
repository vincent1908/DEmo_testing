package com.Base;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Utilities;

public class SCACommon extends BasicOperation{
		
	public SCACommon(Reporter reporter) {
		super(reporter);
		// TODO Auto-generated constructor stub
	}

	public static final void selectProductFromProductsPage(String strDriverMethod,RemoteWebDriver webDriver,Map<String, String> DataMap, String strKey, int productscontentquantity,String msghead) {
		WebElement hlelement=null;
		int maxquantity=productscontentquantity*2;
		Boolean sFlag = true;
		Map<String, String> dataMapLocal = DataMap;
		String strData = null;
		if (dataMapLocal.containsKey(strKey)) {
			strData = dataMapLocal.get(strKey);
		} else {
			sFlag = false;
		}
		try {
			for(int n=0;n<maxquantity;n++)
			{
			if(webDriver.findElementByXPath(".//*[@id='specificProductFamily']/section/div[2]/div["+(n+1)+"]/div/div[1]/a").getAttribute("title").equalsIgnoreCase(strData))
			{
			sFlag = true;
			System.out.println("FOUND");
			webDriver.findElementByXPath(".//*[@id='specificProductFamily']/section/div[2]/div["+(n+1)+"]/div/div[1]/a").click();	
			break;
			}
			else
			{
				System.out.println("NOT FOUND");
				n++;
				sFlag = false;
			}
		}
		} catch (Exception e1) {
			sFlag = false;
			e1.printStackTrace();				
		}
		if(sFlag)
		{
			reporter.writeStepResult(msghead,
					"Click on Product","Product Name: "+strData, "Pass",
					"Product has been selected and clicked", false,webDriver);
		}
		else
		{
			reporter.writeStepResult(msghead,
					"Click on Product","Product Name: "+strData, "Fail",
					"Product is not present", false,webDriver);
		}
	}
	//scroll to the particular element
		public static final void scrollForTridion(RemoteWebDriver webDriver,int downwardsteps,String scrolldirection,String stepmessage)
			{
				JavascriptExecutor js = (JavascriptExecutor)webDriver;
				WebElement scrollelement=null;
				try {
					if(scrolldirection.equalsIgnoreCase("pagetop") && downwardsteps==0)
					{
					   js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
					}
					else if(scrolldirection.equalsIgnoreCase("pagedownsteps"))
					{
					for(int sl=0;;sl++)
			        {
			            if(sl>=downwardsteps)
			            {
			                break;
			            }
			            js.executeScript("window.scrollBy(0,200)","");
			            try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
					}
				}
					catch (WebDriverException w1) {
						System.out.println(w1.getMessage());
						reporter.writeStepResult(stepmessage,
								"Scroll Page","Test data not available", "Fail",
								"Unable to scroll", false,webDriver);
					}				
			}
		

		
		// Verifying heading is clickable

		public static final void headingClickable(RemoteWebDriver webDriver,
			String strElement, String strElementProperty) throws Exception	{
		try{
			
			String hyperlink = null;
			
				if (strElementProperty.equalsIgnoreCase("name")) {
					hyperlink  = webDriver.findElementByName(strElement).getAttribute("href");									
					if (hyperlink == null)
					{
						System.out.println("Heading is not clickable..");
					}						
				}

				else if (strElementProperty.equalsIgnoreCase("id")) {
					hyperlink  = webDriver.findElementById(strElement).getAttribute("href");											
					if (hyperlink == null)
					{
						System.out.println("Heading is not clickable..");
					}
				}//webDriver.findElementByXPath(strElement).getAttribute("value");
				else if (strElementProperty.equalsIgnoreCase("xpath")) {
					hyperlink  = webDriver.findElementByXPath(strElement).getAttribute("href");					
					System.out.println("hyperlink is "+hyperlink);
					if (hyperlink == null)
					{
						System.out.println("Heading is not clickable..");
					}	
					else {}
				}

		}catch (Exception e1) {
			System.out.println("Exception occurred -- " + e1.getMessage());
			System.out.println("Heading is clickable..");
		}
	}


		// Verifying Image is clickable-- if clickable the click on that

		public static final  void imageClickable(RemoteWebDriver webDriver,
			String strElement, String strElementProperty,
			String strElementhyperlink,String homestrElement,String PageTitle) throws Exception	{
			Boolean sFlag = true;
			
		try{
			
			String src = null;
			
				if (strElementProperty.equalsIgnoreCase("name")) {
						src = webDriver.findElementByName(strElement).getAttribute("src");	
						System.out.println("hyperlink is :"+src);
						
						if (src == null)
						{
							System.out.println("Image is not displayed..");
							sFlag = false;
						}			
						else
						{
							System.out.println("Image is displayed..");
							String clk =webDriver.findElementByName(strElementhyperlink).getAttribute("href");
							System.out.println("image hyperlink is :"+clk);
							if (clk != null)
							{
								System.out.println("Image is clickable..");
								webDriver.findElementByName(strElementhyperlink).click();
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

				else if (strElementProperty.equalsIgnoreCase("id")) {
					src = webDriver.findElementById(strElement).getAttribute("src");	
					System.out.println("hyperlink is :"+src);
					if (src == null)
					{
						System.out.println("Image is not displayed..");
						sFlag = false;
					}			
					else
					{
						System.out.println("Image is displayed..");
						String clk =webDriver.findElementById(strElementhyperlink).getAttribute("href");
						System.out.println("image hyperlink is :"+clk);
						if (clk != null)
						{
							System.out.println("Image is clickable..");
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
					src = webDriver.findElementByXPath(strElement).getAttribute("src");	
					System.out.println("hyperlink is :"+src);
					if (src == null)
					{
						System.out.println("Image is not displayed..");
						 sFlag = false;
					}			
					else
					{
						System.out.println("Image is displayed..");
						String clk =webDriver.findElementByXPath(strElementhyperlink).getAttribute("href");
						System.out.println("image hyperlink is :"+clk);
						if (clk != null)
						{
							System.out.println("Image is clickable..");
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
			
//				reporter.writeStepResult("Image Clickable",
//					"Verify image is clickable ",strElement.toUpperCase(), "Pass",
//					"Image clickable successfull", true,webDriver);
		}
		else
		{
				reporter.writeStepResult("Image Clickable",
					"Verify image is not clickable ",strElement.toUpperCase(), "Fail",
					"Image clickable Failed", true,webDriver);
		}
	}

		
		public static final  void TestCase_start(Map<String, String> DataMap, RemoteWebDriver webDriver)
		{
			Boolean sFlag = true;
			try
			{
				webDriver.navigate().to("http://www.torkusa.com/");
				BasicOperation.waitForPageToLoad(webDriver);
				sFlag = true;
			}
			catch (Exception e1) {
				sFlag = false;
				e1.printStackTrace();				
			}
			
			Reporter reptr=new Reporter();		
			if(sFlag)
			{
				reptr.writeStepResult("strScenarioName", "strStepDescription", "strTestData", "strStatus", "strRessultDescription", true, webDriver);
				//reporter.writeStepResult(strScenarioName, strStepDescription, strTestData, strStatus, strRessultDescription, isScreenshot, webDriver);
				System.out.println("Item successfull");
				//reptr.writeStepResult("hng","Click on Product","Product Name: ", "Pass","Product is not present", true,webDriver);
				System.out.println("success");
			}
			else
			{
				reptr.writeStepResult("LAUNCH APPLICATION",
						"Verify page is launched "," ", "Fail",
						"Launched Application Failed", false,webDriver);
			}
		}
		
	

		
}

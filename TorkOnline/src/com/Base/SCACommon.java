package com.Base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.capgemini.utilities.Reporter;
import com.capgemini.utilities.Verification;

public class SCACommon extends BasicOperation{
	public SCACommon(Reporter reporter) {
		super(reporter);
		// TODO Auto-generated constructor stub
	}

	public static final void verifyDownloadedPDFHasContent(RemoteWebDriver webDriver,String filepath) throws FileNotFoundException, IOException
	{
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		try{
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		PDFParser parser = new PDFParser((RandomAccessRead) fis);
		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(1);	
		pdDoc = new PDDocument(cosDoc);
		parsedText = pdfStripper.getText(pdDoc);
		if(parsedText.isEmpty())
		{
			System.out.println("No PDF Content");
			reporter.writeStepResult("No PDF Content",
					"verify PDF has some content","No PDF Content", "Pass",
					"PDF has NO content", false,webDriver);
			
		}
		else
		{
			System.out.println("Some PDF Content is present");
			reporter.writeStepResult("Some PDF Content",
					"verify PDF has some content","Some PDF Content", "Pass",
					"PDF has some content", false,webDriver);
		}
	}
	catch(Exception e)
		{
		e.printStackTrace();
		}
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

	
	/** 
	        Created By - Pratibha Bhosale
	        Created On - 11/25/2016 
	        Objective: To select Product tab from Product description and download PDF
	        
	 */	
		public static void verifyProductTabDownloadPDF(RemoteWebDriver webDriver,Map<String, String> objDataMap) throws AWTException {
			BasicOperation.scroll(webDriver,"ReadMoreLbl");
			BasicOperation.waitFor(3000);
			BasicOperation.clickButton("ProdTab", webDriver, "click on product tab");
			BasicOperation.waitFor(3000);
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.clickButton("DownloadAsPDF", webDriver, "Download as PDF");
			BasicOperation.waitForPageToLoad(webDriver);
			BasicOperation.waitFor(3000);
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			
		}
		public static void verifyProductList(RemoteWebDriver webDriver,Map<String, String> objDataMap,Verification verify,String stepmessage) throws InterruptedException {
			try {
				//for(int i =1;i<=2;i++){
				//	for(int j =1;j<=1;j++){
				//////////////product 1 addition
				BasicOperation.waitForExpectedElement("xpath",objDataMap.get("header_product"), webDriver);
				BasicOperation.click("header_product", webDriver);
				BasicOperation.waitFor(4000);			
				webDriver.findElement(By.xpath("/html/body/form/header/div[2]/div/div/div[1]/div/div[1]/div/div[1]/table/tbody/tr[1]/td[1]")).click();
				BasicOperation.waitForPageToLoad(webDriver);
				Thread.sleep(5000);
				webDriver.findElement(By.xpath(".//*[@id='productCategoriesLandingPage']/div/div/div/div/section/ul/li[1]/section")).click();
				BasicOperation.waitForPageToLoad(webDriver);
				Thread.sleep(5000);
				BasicOperation.click("adddToList", webDriver);
				verify.verifyElementPresent(webDriver, objDataMap.get("ProdArticleValue"), "Xpath","Verify Product Article Value");
				verify.verifyElementPresent(webDriver, objDataMap.get("SCCNo"), "Xpath","Verify Product SSC No.");
				verify.verifyElementPresent(webDriver, objDataMap.get("ProductDescription"), "Xpath","Verify Product Description");
				JavascriptExecutor js = (JavascriptExecutor)webDriver;
				js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
				BasicOperation.waitFor(3000);
				//////////////product 2 addition
				BasicOperation.waitForExpectedElement("xpath",objDataMap.get("header_product"), webDriver);
				BasicOperation.click("header_product", webDriver);
				BasicOperation.waitFor(4000);			
				webDriver.findElement(By.xpath("/html/body/form/header/div[2]/div/div/div[1]/div/div[1]/div/div[1]/table/tbody/tr[2]/td[1]")).click();
				BasicOperation.waitForPageToLoad(webDriver);
				Thread.sleep(5000);
				webDriver.findElement(By.xpath(".//*[@id='productCategoriesLandingPage']/div/div/div/div/section/ul/li[1]/section")).click();
				BasicOperation.waitForPageToLoad(webDriver);
				Thread.sleep(5000);
				BasicOperation.click("adddToList", webDriver);
				verify.verifyElementPresent(webDriver, objDataMap.get("ProdArticleValue"), "Xpath","Verify Product Article Value");
				verify.verifyElementPresent(webDriver, objDataMap.get("SCCNo"), "Xpath","Verify Product SSC No.");
				verify.verifyElementPresent(webDriver, objDataMap.get("ProductDescription"), "Xpath","Verify Product Description");
				//JavascriptExecutor js = (JavascriptExecutor)webDriver;
				js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
				BasicOperation.waitFor(3000);
						
				//	}
			//	}				
				
			}
				catch (WebDriverException w1) {
					System.out.println(w1.getMessage());
					reporter.writeStepResult(stepmessage,
							"verify Product List","Test data not available", "Fail",
							"Unable to find product", false,webDriver);
				}		
			
		}
/** 
	        Created By - Pratibha Bhosale
	        Created On - 11/28/2016 
	        Objective: To select multiple Products from product tab from Product description and download PDF
	        
	 */	
		public static void selectMultipleProductList(RemoteWebDriver webDriver,Map<String, String> objDataMap,Verification verify,String stepmessage) throws InterruptedException {
			try {
				for(int i =1;i<=1;i++){
					for(int j =1;j<=1;j++){
						BasicOperation.waitForExpectedElement("xpath",objDataMap.get("header_product"), webDriver);
						//Commenting BasicOperation click as it throws null pointer exception
						//BasicOperation.click("header_product", webDriver);
						webDriver.findElement(By.xpath(".//*[@id='headerNav']/div/div/div[1]/a")).click();
						BasicOperation.waitFor(4000);			
						webDriver.findElement(By.xpath("/html/body/form/header/div[2]/div/div/div[1]/div/div[1]/div/div[1]/table/tbody/tr["+i+"]/td["+j+"]")).click();
						BasicOperation.waitForPageToLoad(webDriver);
						Thread.sleep(5000);
						webDriver.findElement(By.xpath(".//*[@id='productCategoriesLandingPage']/div/div/div/div/section/ul/li["+i+"]/section")).click();
						BasicOperation.waitForPageToLoad(webDriver);
						Thread.sleep(5000);
						BasicOperation.click("adddToList", webDriver);						
						JavascriptExecutor js = (JavascriptExecutor)webDriver;
						js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
						
					}
				}
				
			}
				catch (WebDriverException w1) {
					System.out.println(w1.getMessage());
					reporter.writeStepResult(stepmessage,
							"verify Product List","Test data not available", "Fail",
							"Unable to find product", false,webDriver);
				}		
			
		}
}

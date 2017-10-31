package com.Base;


import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.capgemini.utilities.Reporter;

public class Territory extends Login{
	public Territory(Reporter reporter) {
		super(reporter);
		// TODO Auto-generated constructor stub
	}
	public static String my_territory="";
	public static String language=null;
	public static boolean changeTerritory(String territory, String Language){
		
		my_territory=territory;
		language=Language;
		
		try{
		int Lang_found=0;
		
		
		WebDriver driver=getDriver();
		if(isLogin()){
			
			System.out.println("[Info] Changing Territory");
			String title=driver.getCurrentUrl();
			//System.out.println(title);
			if(title.contains("home.xhtml")){
				
				
				
				WebElement select = driver.findElement(By.id("welcome:country"));
			    List options = (List) select.findElements(By.tagName("option"));
			    Iterator<WebElement> i=((java.util.List<WebElement>) options).iterator();
			   while( i.hasNext() ){
				   WebElement str=i.next();
				   if(territory.equalsIgnoreCase(str.getText())){
			        str.click();
			       
				   }
			      }
			   
			   System.out.println("[Info] Changing Language");
			  // Thread.sleep(1000);
			  // selectByName(".//*[@id='welcome:name']",language);
			   
			   WebElement select2 = driver.findElement(By.xpath(".//*[@id='welcome:name']"));
			    List options2 = (List) select2.findElements(By.tagName("option"));
			    Iterator<WebElement> i2=((java.util.List<WebElement>) options2).iterator();
			   while( i2.hasNext() ){
				   WebElement str=i2.next();
				   System.out.println("{Info} available language"+str.getText());
				   if(language==null || language.equals(null) ){
					   System.out.println("{Info} Passing 'NULL' Parameter to language");   
			        str.click();
			        Lang_found=1;
			        break;
				   }
				   else if(language.equalsIgnoreCase(str.getText())){
					   System.out.println("[Info] Language:"+str.getText());
					   str.click();
					   Lang_found=1;
					   break;
				   }
				 
				   
				   
			      }
			   if(Lang_found==0){
				   System.err.println("[Info] "+Language+" Language not found");
			   }
			   driver.findElement(By.className("styled-button-9")).click();
			   
				}
			else{
				
				driver.get(getProtocol()+"://"+getHost()+":"+getPort()+"/SWAppWeb/home.xhtml");
				changeTerritory(my_territory,language);
				
			}
			}
			else{
				System.out.println("info: Please create Login first!");
			}
		}catch(Exception e){ System.out.println(e); return false;}
		return true;
			
		}
		
		
	}
	
	


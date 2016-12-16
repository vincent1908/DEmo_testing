package com.Base;

import java.util.Locale;
import java.util.ResourceBundle;



public class ResourceReader {

	public static String BundleName = "com.resources.Main";
	public static ResourceBundle bundle = ResourceBundle.getBundle(BundleName,Locale.ENGLISH);
	
	
	public static String readValue(String str)
	{
		//bundle = new ResourceBundle();
		System.out.println("[Resource Handler] Retriving Component for "+str);
		return bundle.getString(str);
		
	}
	
}

package com.Base;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xsl {
	
	
	public static  void createXsl(){
		
		 Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
		 Sheet sheet = (Sheet) wb.createSheet();
		 
		try{
		 InputStream is = new FileInputStream("C:\\images\\image0.jpg");
	        byte[] bytes = IOUtils.toByteArray(is);
	        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	        CreationHelper helper = wb.getCreationHelper();
	        Drawing drawing = ((org.apache.poi.ss.usermodel.Sheet) sheet).createDrawingPatriarch();
	        ClientAnchor anchor = helper.createClientAnchor();
	        anchor.setCol1(2);
	        anchor.setRow1(2);
	        anchor.setAnchorType(2);
	        Picture pict = (Picture) drawing.createPicture(anchor, pictureIdx);
	        ((org.apache.poi.ss.usermodel.Picture) pict).resize();
	        
			//((org.apache.poi.ss.usermodel.Picture) pict).resize();
			
			 FileOutputStream fileOut = new FileOutputStream("C:\\demo.xsl");
			    wb.write(fileOut);
			    fileOut.close();
			    
		}catch(Exception e){System.out.println(e);}
		
		
	}
	
	


}

package com.Base;

import java.io.*;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
public class ImagesDoc 
{
	static String dir="";
	static String name="";
    public static void main(String[] args) throws IOException, InvalidFormatException 
    {
	   
    }
    
    public static void save(){
    	
    	 try{
    		 
    		 //JOptionPane.showMessageDialog(null, "Start ImageDoc","Error",JOptionPane.INFORMATION_MESSAGE);
    		   // String comment=JavaApplication1.comment;
    	        XWPFDocument docx = new XWPFDocument();
    	       // JOptionPane.showMessageDialog(null, "create ImageDoc","Error",JOptionPane.INFORMATION_MESSAGE);
    	        XWPFParagraph par = docx.createParagraph();
    	        XWPFRun run = par.createRun();
    	        //run.setText(comment);
    	        //run.setFontSize(13);
    	        
    	        
    	        
    	        FileOutputStream out = new FileOutputStream(BasicOperation.screenshotfile.getAbsolutePath()+"//AllImages_"+((int)(Math.random()*10000))+".doc"); 
    	        
    	        //JOptionPane.showMessageDialog(null, "Dir"+dir+"\\"+name,"Error",JOptionPane.INFORMATION_MESSAGE);
    	       // byte [] picbytes = IOUtils.toByteArray(pic);
    	        
    	       // run.setText("Software Genrated File");
    	        
    	        
    	        File f=new File(BasicOperation.screenshotfile.getAbsolutePath()+"/*.*");
    	        String str=f.getParent();
    	        File k=new File(str);
    	        String list[]=k.list();
    	        
    	       // JOptionPane.showMessageDialog(null, "Reading Files","Error",JOptionPane.INFORMATION_MESSAGE);
    	        for(int y=0;y<list.length;y++){
    	        	 InputStream pic = new FileInputStream(BasicOperation.screenshotfile.getAbsolutePath()+"\\"+list[y]);
    	        	XWPFRun run2 = par.createRun();
    	       // run2.setText("Screen Shot No."+(y+1));
    	             
    	       run2.addPicture(pic, Document.PICTURE_TYPE_JPEG, BasicOperation.screenshotfile.getAbsolutePath()+"\\"+list[y], Units.toEMU(500), Units.toEMU(350));
    	       // XWPFRun run2 = title.createRun();
	             run2.setText("ScreenShot :"+(y+1));
	             pic.close();
    	        System.gc();
    	        }
    	        
    	        
    	        
    	        //run.setText("Hello, World. This is my first java generated docx-file. Have fun2shhhh.");
    	        
    	        
    	      // FileInputStream pic2 = new FileInputStream("D:\\i.png");
    	        //byte [] picbytes2 = IOUtils.toByteArray(pic);
    	        //docx.addPictureData(IOUtils.toByteArray(pic2), Document.PICTURE_TYPE_PNG);
    	        
    	      //  docx.createParagraph().createRun().addPicture(pic, Document.PICTURE_TYPE_JPEG, "D:\\images\\image0.jpg", Units.toEMU(500), Units.toEMU(500));
    	        
    	        XWPFParagraph title = docx.createParagraph();    
    	            XWPFRun run2 = title.createRun();
    	             run2.setText(" ");
    	           run2.setBold(true);
    	            title.setAlignment(ParagraphAlignment.CENTER);
    	        
    	           // JOptionPane.showMessageDialog(null, "Writing File","Error",JOptionPane.INFORMATION_MESSAGE);
    	            
    	       
    	       // System.out.println(dir+name);
    	         
    	      
    	        docx.write(out); 
    	        
    	        out.close(); 
    	      //  pic.close();
    	      //  System.out.println("Complete all doc");
    	        
    		    }catch(Exception e){//JOptionPane.showMessageDialog(null, "No Image to save!"+e,"Error",JOptionPane.ERROR_MESSAGE);} 	
    
    		    }
    }
    
}
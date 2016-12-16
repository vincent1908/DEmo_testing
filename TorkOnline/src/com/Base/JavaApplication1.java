package com.Base;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package javaapplication1;

import java.awt.AWTException;
import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author Pravin
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    
    public static BufferedImage image[]=new BufferedImage[1000];
    public static int image_no=0;
    public static File file;
    static JButton b1; 
    static JLabel img;
   static show_image my;
   static int close=0;
   static JComboBox<Object> cb;
   static JComboBox<Object> sheet;
   static int save_as=1;
   static String comment="";
   static mark mkr;
   static String dir="";
   static String name="";
   static String save_file="C:\\Demo.doc";
   
    public static void main(String[] args) throws AWTException, IOException {
        // TODO code application logic here
         
        try{
		
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		
		File fg=new File("C:\\images");
		 if(! fg.exists() ){  fg.mkdir(); }
		
		}catch(Exception ee){JOptionPane.showMessageDialog(null, ee,"Error",JOptionPane.ERROR_MESSAGE);}
        
         
		JFrame jf=new JFrame();
		my=new show_image();
		//Border br=BorderFactory.createLineBorder(Color.blue);
		//jf.setBorder(br);
		
		//jf.setLocation(200,960);
		jf.setLocation(300,0);
		jf.setSize(750,25);
		jf.setUndecorated(true);
	    Container cp=jf.getContentPane();
	    cp.setBackground(Color.white);

	jf.setLayout(new GridLayout(0,9));

	
	    
		JButton b=new JButton("Capture" );
		
		jf.add(b);
		b.setBackground(Color.white);
		b1=new JButton("Del last");
		jf.add(b1);
		
		JPanel pp=new JPanel();
		pp.setLayout(new GridLayout(0,2));
		
		JButton ex=new JButton("X");
		ex.setBackground(Color.white);
	
		
		
		JToggleButton mak=new JToggleButton("<>");
		pp.add(mak);
		
		pp.add(ex);
		
		
		
		Thread t=new Thread(){
		public void run(){
		while(true){
		if(image_no>0)
		{
		  b1.setEnabled(true);
		}
		else{
			b1.setEnabled(false);
		}
		}
		
		}
		};
		t.start();
		
		
		JButton b3=new JButton("Clean All");
		jf.add(b3);
		b3.setBackground(Color.white);
		
		JButton b4=new JButton("Comment");
		jf.add(b4);
		b4.setBackground(Color.white);
		
		mak.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{  try{
			JToggleButton n=(JToggleButton)e.getSource();
			if(n.isSelected())
			{
				mkr=new mark();mkr.setVisible(true);
				
			}
			else{
				mkr.setVisible(false);
				mkr.pack();
				System.gc();
				
			}
		}catch(Exception er){JOptionPane.showMessageDialog(null, er,"Error",JOptionPane.ERROR_MESSAGE);}
		}
		});
		
		
		b4.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{  try{
			
			close_img();
			String inputValue = JOptionPane.showInputDialog("Enter your comment here:");
			inputValue="Screenshot "+(image_no-1)+":"+inputValue;
			comment=comment+"\n"+inputValue;
		 }catch(Exception er){JOptionPane.showMessageDialog(null, er,"Error",JOptionPane.ERROR_MESSAGE);}
		}
		});
		
		
		
		
		
		b3.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{  try{
			close_img();
			int response = JOptionPane.showConfirmDialog(null, "Do you want to clean all images?", "Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         if( response != JOptionPane.NO_OPTION ) {
		 image_no=0;
		 comment="";
		 File f=new File("C:\\images\\*.*");
		 String fe=f.getParent();
		 File f2=new File(fe);
		 String str[]=f2.list();
		 img.setText("----");
		 for(int i=0;i<str.length;i++)
		  {
		    File d=new File("C:\\images\\"+str[i]);
			d.delete();
		  }
         }
         else{
        	 // DO Nothing 
        	 System.out.println("Clean Up cancel");
         }
		  }catch(Exception er){JOptionPane.showMessageDialog(null, er,"Error",JOptionPane.ERROR_MESSAGE);}
		}
		});
		
		
		
		JButton b2=new JButton("save");
		jf.add(b2);
		
		img=new JLabel("----");
		jf.add(img);
		
		
		cb=new JComboBox<Object>();
		cb.addItem("Ms-Word");
		//cb.addItem("Ms-Excel");
		
		cb.setBackground(Color.orange);
		
		
		jf.add(cb);
		
		
		sheet=new JComboBox<Object>();
		for(int i=1;i<30;i++){
		//sheet.addItem("Sheet "+i);
		}
		
		sheet.setBackground(Color.orange);
		jf.add(sheet);
		jf.add(pp);
		
		jf.toFront();
		jf.setState(JFrame.NORMAL);
		jf.setAlwaysOnTop(true); 
		jf.setVisible(true);
		sheet.setEnabled(false);
		
		cb.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{ 
			String value=(String)cb.getSelectedItem();
			if("Ms-Word".equals(value))
			{
				
				
				sheet.setEnabled(false);
				save_as=1;
			}
			else{
				
				
				sheet.setEnabled(true);
				save_as=2;
			}
		}
		});
		
		
		b2.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{ 
		    if(save_as==1){	
		    	close=1;
		    	 save_img();
			save("");
			
		    }
		    else{
		    	close=1;
		    	close_img();
		    	JOptionPane.showMessageDialog(null, "Implementation Pending", "Message", JOptionPane.INFORMATION_MESSAGE);
		    	
		    	//Xsl.createXsl();
		    }
		
		}
		});
	
	
		 ex.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent e)
			{
				
				close_img();
				if(close==0 &&  !img.getText().equals("----")){
				int response = JOptionPane.showConfirmDialog(null, "Do you want to Save Image(s)?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	         if( response == JOptionPane.NO_OPTION ) {
				
				JOptionPane.showMessageDialog(null, "Thank you for using !  -Pravin", "Message", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);	
	         }
	         else if( response == JOptionPane.YES_OPTION ) {
	        	
	        	 try{
	        		 save_img();
	        	save("");
	        	 }catch(Exception es){JOptionPane.showMessageDialog(null, "No Image to save!"+es,"Error",JOptionPane.ERROR_MESSAGE); }
	        	 close_img();
	        		System.exit(0);	
	        	 
	         }
	         else{
	        	 // Do nothing
	         }
	         
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Thank you for using !  -Pravin", "Message", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);	
				}
			}
			});
		
	    b1.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{  
		    try{
		    	
		    image_no--; 
		    File f=new File("C:\\images\\image"+image_no+".jpg");
		    f.delete();
		    
		    if(image_no>0){
		    	img.setText("Image-"+image_no);
		    }
		    else{
		    	img.setText("----");
		    }
		    close_img();
			}catch(Exception ee){ JOptionPane.showMessageDialog(null, ee,"Error",JOptionPane.ERROR_MESSAGE);}
		}
		});
		
		
		
	    img.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				my.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				my.setVisible(false);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    	
	    	
	    	
	    });
	    
	    
		b.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e)
		{
		try{
		b1.setEnabled(true);
		file=new File("C:\\images\\image"+image_no+".jpg");
		getScreen();
        writeFile();
        img.setForeground(Color.red);
        img.setText("Image-"+(image_no+1));
		image_no++;
		close=0;
		close_img();
		
		}catch(Exception ge){ JOptionPane.showMessageDialog(null, ge,"Error",JOptionPane.ERROR_MESSAGE);}
		}
		});
       // for(int i=0;i<10;i++){
	   
	   
        //}
    }
    
    
    public static void close_img(){
    	try{
    		mkr.setVisible(false);
    		mkr.pack();
    		System.gc();
    		
    	}catch(Exception e){}
    }
    
    public static void getScreen() throws AWTException, IOException
    {
        Robot robo=new Robot();
         // File file=new File("C:\\My.jpg");
      
        //image[//image_no]=ImageIO.read(file);
        System.out.println("Capture image:"+image_no);
        image[image_no]=robo.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
       
      //  BufferedImage im=new BufferedImage();
    }
    
    public static void writeFile() throws IOException{
       //image[image_no]=ImageIO.read(file);
        //ImageIO.read(file);
       
            //ImageIO.write(ImageIO.read(file), "jpg",file );
             ImageIO.write(image[image_no], "jpg",file );
             image[image_no]=null;
        
        System.out.println("Writing Image...");
    }
    
    
    
    
    public static void save(String path){
    	
   	 try{
   		 
   		// JOptionPane.showMessageDialog(null, "Start ImageDoc","Error",JOptionPane.INFORMATION_MESSAGE);
   		    //String comment=comment;
   	        XWPFDocument docx = new XWPFDocument();
   	      
   	        
   	        //  JOptionPane.showMessageDialog(null, "create ImageDoc","Error",JOptionPane.INFORMATION_MESSAGE);
   	        
   	        XWPFParagraph par = docx.createParagraph();
   	        XWPFRun run = par.createRun();
   	        run.setText(comment);
   	        run.setFontSize(13);
   	      
   	        
   	        FileOutputStream out = new FileOutputStream(path); 
   	        
   	        //JOptionPane.showMessageDialog(null, "Dir"+dir+"\\"+name,"Error",JOptionPane.INFORMATION_MESSAGE);
   	       // byte [] picbytes = IOUtils.toByteArray(pic);
   	        
   	       // run.setText("Software Genrated File");
   	        
   	        
   	        File f=new File("C:\\images\\*.*");
   	        String str=f.getParent();
   	        File k=new File(str);
   	        String list[]=k.list();
   	        
   	        JOptionPane.showMessageDialog(null, "Reading Files","Error",JOptionPane.INFORMATION_MESSAGE);
   	        for(int y=0;y<list.length;y++){
   	        	 InputStream pic = new FileInputStream("C:\\images\\"+list[y]);
   	        	XWPFRun run2 = par.createRun();
   	       // run2.setText("Screen Shot No."+(y+1));
   	             
   	        run2.addPicture(pic, Document.PICTURE_TYPE_JPEG, "C:\\images\\"+list[y], Units.toEMU(500), Units.toEMU(350));
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
   	        
   	            JOptionPane.showMessageDialog(null, "Writing File","Error",JOptionPane.INFORMATION_MESSAGE);
   	            
   	       
   	        System.out.println(dir+name);
   	         
   	      
   	        docx.write(out); 
   	        
   	        out.close(); 
   	      //  pic.close();
   	     JOptionPane.showMessageDialog(null, "Operation Complete","Error",JOptionPane.INFORMATION_MESSAGE);
   	        
   		    }catch(Exception e){JOptionPane.showMessageDialog(null, "No Image to save!"+e,"Error",JOptionPane.ERROR_MESSAGE);} 	
   
   
   }
   
    
    public static void save_img(){
    	
    	
    	  FileDialog fd=new FileDialog(new JFrame(),"Save Here");
	         fd.setMode(FileDialog.SAVE);
	         fd.setVisible(true);
	         
	         
	        dir=fd.getDirectory();
	         name=fd.getFile();
    	
    	
    }
    
    public static void fileWrite() throws FileNotFoundException, IOException{
        //InputStream br=new InputStream(new FileInputStream("G:\\my.jpg"));
    
        OutputStream os=new FileOutputStream("C:\\my2.doc");
        
        File f=new File("G:\\images\\*.*");
        String str=f.getParent();
        String [] all= new File(str).list();
        for(int i=0;i<all.length;i++){
        InputStream is=new FileInputStream("G:\\"+all[i]);
        
        byte[] b=new byte[10000];
        int length=is.read(b);
        
        while(length >0){
            os.write(b,0,length);
        }
        
        
        is.close();
        }
        os.close();
        
        
    }
    
}





class show_image extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame jp;
	JLabel ll;
	JPanel jj;
	static int count=JavaApplication1.image_no;
	show_image(){
	jp=this;
	//jp.setState(JFrame.NORMAL);
	jp.setAlwaysOnTop(true); 
	//jp.setUndecorated(true);
	jp.setLayout(null);
	//JPanel jp=new JPanel();
  ll=new JLabel("dfsdf");
  ll.setBounds(0,0,1250,900);
	jp.add(ll);
	
	jj=new JPanel();
	jj.setOpaque(false);
	jj.setBounds(0,0,1250,900);
	jp.add(jj);
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  int width = (int)screenSize.getWidth();
	  int height = (int)screenSize.getHeight();
	
	jp.setSize(width-20,height-90);
	jp.setLocation(20,40);	
	
	//Border border = BorderFactory.createLineBorder(Color.red);

	Thread t=new Thread(){
		public void run(){
			while(true){
				 count=JavaApplication1.image_no-1;
				if(count>=0){
					jj.setVisible(true);
				//System.out.println("C:\\images\\image"+count+".jpg");	
				//ll.setIcon(new ImageIcon("C:\\images\\image"+count+".jpg"));
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File("C:\\images\\image"+count+".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try{
				Graphics g=jj.getGraphics();
				g.drawImage(img,0,0,1250,900,null);
				}catch(Exception e){
					//System.out.println("Error occus in jpnel image writing image transfer to label");
					ll.setIcon(new ImageIcon("C:\\images\\image"+count+".jpg"));
				}
				}
				
				else{
					jj.setVisible(false);
					ll.setForeground(Color.blue);
					ll.setFont(new Font("Arial Black",Font.BOLD,18));
					ll.setText("                                                                No Image captured or Buffer is empty             ");
				}
				
			}
		}
	};
	
	t.start();
	
	}
	
	
	
}




class mark extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    JFrame jf;
    JPanel jp;
    JPanel jj[]=new JPanel[20];
    Graphics gx;
    int size_x,size_y;
    int loc_x,loc_y;
    int count=0;
	mark(){
		jf=this;
		
		
		jf.setLayout(null);
		
		jp=new JPanel();
		
		
		
		jp.setLocation(2,0);
		jp.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		jf.add(jp);
		//jp.setOpaque(false);
		jp.setBackground(new Color(0.0f,0.0f,0.0f,0.1f));
		
		Border br=BorderFactory.createLineBorder(Color.blue);
		jp.setBorder(br);
		jp.setLayout(null);
		  gx=jp.getGraphics();
		  Border br2=BorderFactory.createLineBorder(Color.red);
		  for(int i=0;i<20;i++){
		  jj[i]=new JPanel();
		  jj[i].setBackground(new Color(0.0f,0.0f,0.0f,0.1f));
		  jj[i].setBorder(br2);
		  jp.add(jj[i]);
		  }
		  
		  
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		  int width = (int)screenSize.getWidth();
		  int height = (int)screenSize.getHeight();
		  
		  jf.setSize(width,height-68);
			jf.setUndecorated(true);
			jf.setLocation(0,25);
			jf.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
			jf.setAlwaysOnTop(true); 
		  
		jf.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				loc_x=e.getX();
				loc_y=e.getY();
				//System.out.println("X:"+x +" Y:"+y);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				size_x=e.getX();
				size_y=e.getY();
				//System.out.println("X2:"+x +" Y2:"+y);
				try{
				//JPanel jj=jp;
				//Graphics gh=getGraphics();
				//Graphics g=gh.setPaintMode();
				//gh.setColor(Color.red);
				//gh.drawRect(10, 10, 100, 100);
				
				//gx.setColor(Color.red);
			//	gx.drawRect(10, 10, 100, 100);
					jj[count].setOpaque(false);
				
				int x;
				int y;
				if(loc_x<size_x)
				{
					jj[count].setLocation(loc_x,loc_y);
					x=size_x-loc_x;
					y=size_y-loc_y;
					
					jj[count].setSize(x,y);
				}
				else{
					
					jj[count].setLocation(size_x,size_y);
					
					
					// Formula for opposite rectangle Don't change here
					
					size_x=size_x+loc_x;
					loc_x=size_x-loc_x;
					size_x=size_x-loc_x;
					
					size_y=size_y+loc_y;
					loc_y=size_y-loc_y;
					size_y=size_y-loc_y;
					
					
					
					
					x=size_x-loc_x;
					y=size_y-loc_y;
					
					jj[count].setSize(x,y);
				}
				
				
				
				
				jp.setOpaque(false);
				count++;
				
				/*JLabel l=new JLabel();
				Border br3=BorderFactory.createLineBorder(Color.red);
				l.setBorder(br3);
				//l.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
				l.setBounds(0,0,x,y);
				jp.add(l);
				*/
				 //gh.dispose();
				//jp.update(gh);
				}catch(Exception ew){System.out.println("Error:"+ew);}
				
			}
			
			
		});
		
	}
	
	
	
	
}



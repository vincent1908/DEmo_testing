package com.Base;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * @since 4 June 15
 * @author PRAVIRAN
 *
 */
public class ResumeScript extends JFrame {

	JFrame frame2;
	static JTextField txtObject, oldPath, newPath, oldValue, newValue, scriptName, codetxt, linetxt;
	static JLabel txtObjectlabel, newXpath, oldXpath, newVal, oldVal, scriptLabel, codelabel, linelabel;
	static JButton continueButton, Discard, Exit;
	static String action = "", newtxtValue = "", newXpathValue = "";
	public static boolean isWindowVisible = true;
	private Map datamap = BasicOperation.getDataMap();
	private Map propertymap = BasicOperation.getPropertyMap();
	private String currentApp = BasicOperation.AppName;
	private static String object = null;
	private static String xpath = null;
	private static String operation = null;
	private static int lineNo = 0;
	static String image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuZPu8utcjuJ2uDki8otAtfC-5Uoy4fshpA9SHqpCpPtyeJWAtFA";

	public static boolean isWindowVisible() {
		return isWindowVisible;
	}

	public static void setWindowVisible(boolean isWindowVisible) {
		ResumeScript.isWindowVisible = isWindowVisible;
	}

	public ResumeScript(final String object, final String xpath, String value, String Operation) {
		// TODO Auto-generated constructor stub
		this.object = object;
		this.xpath = xpath;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}

		frame2 = this;
		java.awt.Container cp = frame2.getContentPane();
		cp.setBackground(Color.white);
		frame2.setTitle("BasicOperation Framework Resume Script");
		frame2.setSize(600, 300);
		frame2.setLocation(150, 150);
		frame2.setLayout(new GridLayout(0, 1));
		frame2.setAlwaysOnTop(true);
		// frame.setUndecorated(true);

		final JPanel frame = new JPanel();
		frame.setLayout(new GridLayout(0, 2));

		scriptLabel = new JLabel("Script Name:");
		frame.add(scriptLabel);
		scriptName = new JTextField(100);
		frame.add(scriptName);
		scriptName.setEditable(false);
		scriptName.setText(currentApp);

		txtObjectlabel = new JLabel("Excel Sheet Column name:");
		frame.add(txtObjectlabel);
		txtObject = new JTextField(100);
		frame.add(txtObject);
		txtObject.setEditable(false);

		if (!object.equalsIgnoreCase(xpath))
			txtObject.setText(object);
		else
			txtObject.setText("Object not present in ObjectIdentifier or hardcode object");

		oldXpath = new JLabel("Old Xpath");
		frame.add(oldXpath);
		oldPath = new JTextField(100);
		frame.add(oldPath);
		oldPath.setEditable(false);
		oldPath.setText(xpath);

		newXpath = new JLabel("New Xpath");
		frame.add(newXpath);
		newPath = new JTextField(100);
		frame.add(newPath);

		oldVal = new JLabel("Old Value");
		frame.add(oldVal);
		oldValue = new JTextField(100);
		frame.add(oldValue);
		oldValue.setEditable(false);
		oldValue.setText(value);

		newVal = new JLabel("New Value");
		frame.add(newVal);
		newValue = new JTextField(100);
		frame.add(newValue);

		codelabel = new JLabel("Executed Code :");
		frame.add(codelabel);
		codetxt = new JTextField(100);
		frame.add(codetxt);
		codetxt.setForeground(Color.red);
		codetxt.setText("BasicOperation." + Operation + "('" + object + "','" + value + "',webDriver);");

		linelabel = new JLabel("Line No :");
		frame.add(linelabel);
		linetxt = new JTextField(100);
		frame.add(linetxt);

		frame2.add(frame);

		try {

			FileReader read = new FileReader("src//com//capgemini//scripts//" + currentApp + ".java");
			Scanner fileRead = new Scanner(read);
			String code = "";
			int changeInLine = 0;
			while (fileRead.hasNext()) {
				changeInLine++;
				String currentline = fileRead.nextLine();
				if (currentline.contains(object)) {
					break;

				}

			}
			linetxt.setText(changeInLine + "");
		} catch (Exception e) {
		}

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		continueButton = new JButton("Continue");
		panel.add(continueButton);

		Discard = new JButton("Discard");
		panel.add(Discard);

		Exit = new JButton("Exit");
		panel.add(Exit);
		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BasicOperation.endReport();
				System.exit(0);
				
			}

		});

		continueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				action = "exit";
				if (!xpath.equalsIgnoreCase(object)) {
					try {
						FileInputStream fis = new FileInputStream("data\\ObjectIdentifier.xls");
						Workbook wb = WorkbookFactory.create(fis);
						Sheet sheet = wb.getSheet(currentApp);
						int territoryrow = 0;
						Row row = null;
						Cell cell;
						for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
							row = sheet.getRow(j);

							String val = null;
							cell = row.getCell(0);
							if (cell != null) {
								int type = cell.getCellType();
								if (type == HSSFCell.CELL_TYPE_STRING)
									val = cell.getRichStringCellValue().toString();
								else if (type == HSSFCell.CELL_TYPE_NUMERIC)
									val = cell.getNumericCellValue() + "";
								else if (type == HSSFCell.CELL_TYPE_BOOLEAN)
									val = cell.getBooleanCellValue() + "";
								else if (type == HSSFCell.CELL_TYPE_BLANK)
									val = cell.getColumnIndex() + "] = BLANK CELL";
								if (datamap.get("Territory").toString().equalsIgnoreCase(val)) {
									territoryrow = j;
									break;
								}

							}
						}
						System.out.println(">> Territory Row :" + territoryrow);
						row = sheet.getRow(0);

						Iterator cells = row.cellIterator();
						int column = 0;
						;
						String val2 = null;
						while (cells.hasNext()) {

							Cell cel = (Cell) cells.next();
							int type = cel.getCellType();
							if (type == HSSFCell.CELL_TYPE_STRING)
								val2 = cel.getRichStringCellValue().toString();
							else if (type == HSSFCell.CELL_TYPE_NUMERIC)
								val2 = cel.getNumericCellValue() + "";
							else if (type == HSSFCell.CELL_TYPE_BOOLEAN)
								val2 = cel.getBooleanCellValue() + "";
							if (val2.equalsIgnoreCase(object)) {
								System.out.println(">>>>> Object Column :" + column);
								break;
							}
							column++;
						}

						// declare a Cell object

						cell = sheet.getRow(territoryrow).getCell(column);
						cell.setCellValue(newPath.getText());
						fis.close(); // Close the InputStream
						System.out.println(">>>> Writing new value in Excel sheet");
						FileOutputStream output_file = new FileOutputStream(new File("data\\ObjectIdentifier.xls"));
						wb.write(output_file); // write changes

						output_file.close();
						System.out.println(">>>> File writing  complete");
					} catch (Exception es) {
						es.printStackTrace();
					}

					try {
						if (BasicOperation.fetchXpathFromServerDb) {
							
							Socket s = new Socket(BasicOperation.serverHost,10006);
							BufferedReader br =  new BufferedReader(new InputStreamReader(s.getInputStream()));
							PrintWriter pr = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
							
							pr.write(BasicOperation.DataBase+"\r\n"); pr.flush();
							
							pr.write(scriptName.getText()+"\r\n"); pr.flush();
							pr.write(datamap.get("Territory") +"\r\n"); pr.flush();
							pr.write(object+"\r\n"); pr.flush();
							pr.write(newPath.getText()+"\r\n");
							
							String changed = br.readLine();
							if(changed.equalsIgnoreCase("true")){
								System.out.println(" ===== >>>>>> Xpath changed in database.");
							}
							else{
								System.err.println(" ===== >>>>>> No Xpath changed in database.");
							}
							
							pr.close();
							br.close();
							s.close();
							
						}

					} catch (Exception e2) {
						e2.printStackTrace();
						// TODO: handle exception
					}
				} else if (xpath.equalsIgnoreCase(object)) {
					try {

						FileReader read = new FileReader("src//com//capgemini//scripts//" + currentApp + ".java");
						Scanner fileRead = new Scanner(read);
						String code = "";
						int changeInLine = 0;
						while (fileRead.hasNext()) {
							changeInLine++;
							String currentline = fileRead.nextLine();
							if (currentline.contains(xpath)) {
								currentline = currentline.replace(xpath, newPath.getText());
								currentline += "\r\n";
								System.out.println(">>>> change In Line :" + changeInLine);

							}
							code += currentline + "\r\n";
						}

						FileWriter wr = new FileWriter("D://" + currentApp + ".java");
						wr.write(code);
						wr.close();

					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}

				action = "Continue";
				newtxtValue = newValue.getText();
				newXpathValue = newPath.getText();
				setWindowVisible(false);
				frame.setVisible(false);

				frame2.dispose();
			}

		});

		Discard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				action = "Discard";
				BasicOperation.endReport();
				newtxtValue = newValue.getText();
				newXpathValue = newPath.getText();
				setWindowVisible(false);
				frame.setVisible(false);

				frame2.dispose();
			}

		});

		frame2.add(panel);

		frame2.setVisible(true);
	}

	public static String getNewXpath() {

		return newPath.getText();
	}

	public static String newValue() {

		return newValue.getText();
	}

	public static void main(String s[]) {
		new ResumeScript("Google-Search", ".//[@id='Searchtext']", "Avast", "fill");
	}

}

package com.springboot.springboot.app;

import java.awt.Font;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateInvoice {

	public static void main(String[] args) {
		try {
			//Creating the  workbook in .xlsx format
			Workbook workbook = new XSSFWorkbook();
			//For .xsl workbooks use new HSSFWorkbook();
			//Now i am creating Sheet
			Sheet sh = workbook.createSheet("Invoices");
			
			//Creating top row with column headings
			
			String[] columnHeadings = {"Item id","Item Name","Qty","Item Price","Sold Date"};
			//making it bold with a foreground color.
			
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short)12);
			headerFont.setColor(IndexedColors.BLACK.index);
			
			//Creating a CellStyle with the font
			
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
			
			//Creating the header row
			
			Row headerRow = sh.createRow(0);
			
			//Iterate over the column headings to create columns
			
			for(int i=0;i<columnHeadings.length;i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnHeadings[i]);
				cell.setCellStyle(headerStyle);
			}
			//Freezing Header Row
			
			sh.createFreezePane(0, 1);
			
			//Filling data logic will be implementing
			
			ArrayList<Invoices> a = createData();
			CreationHelper creationHelper= workbook.getCreationHelper();
			CellStyle dateStyle = workbook.createCellStyle();
			dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd/yyyy"));
			int rownum =1;
			for(Invoices i : a) {
				
				//System.out.println("rownum-before"+(rownum));
				
				Row row = sh.createRow(rownum++);
				
				//System.out.println("rownum-after"+(rownum));
				
				row.createCell(0).setCellValue(i.getItemId());
				row.createCell(1).setCellValue(i.getItemName());
				row.createCell(2).setCellValue(i.getItemQty());
				row.createCell(3).setCellValue(i.getTotalPrice());
				Cell dateCell = row.createCell(4);
				dateCell.setCellValue(i.getItemSoldDate());
				dateCell.setCellStyle(dateStyle);
			}
			//Group and collapse rows(that is the for collapsing the table result of column and rows).
			
			int noOfRows = sh.getLastRowNum();
			sh.groupRow(1, noOfRows);
			sh.setRowGroupCollapsed(1, true);
			
			//Creating a sum row
			
			Row sumRow = sh.createRow(rownum);
			Cell sumRowTitle = sumRow.createCell(0);
			sumRowTitle.setCellValue("Total");
			sumRowTitle.setCellStyle(headerStyle);
			
			String strFormula = "SUM(D2:D"+rownum+")";
			Cell sumcell = sumRow.createCell(3);
			sumcell.setCellFormula(strFormula);
			sumcell.setCellValue(true);
			
			//Autosizing the  columns
			
			for(int i=0;i<columnHeadings.length;i++) {
				sh.autoSizeColumn(i);
			}
			Sheet sh2 = workbook.createSheet("Second");
			
			//Writing  the output to file
			
			FileOutputStream fileOut = new FileOutputStream("/home/parallels/Documents/docs/invoices.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			System.out.println("Completed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	private static ArrayList<Invoices> createData() throws ParseException {ArrayList<Invoices> a = new ArrayList();
	a.add(new Invoices(1, "Apple", 2, 15.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(2, "Orange", 1, 55.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(3, "Lemon", 5, 105.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(4, "PineApple", 100, 25.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(5, "Apple", 2, 15.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(6, "Orange", 1, 55.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(7, "Lemon", 5, 105.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(8, "PineApple", 100, 25.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(9, "Apple", 2, 15.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(10, "Orange", 1, 55.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(11, "Lemon", 5, 105.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(12, "PineApple", 100, 25.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(13, "Apple", 2, 15.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	a.add(new Invoices(14, "Orange", 1, 55.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/02/2020")));
	a.add(new Invoices(15, "Lemon", 5, 105.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
	return a;
	}

}

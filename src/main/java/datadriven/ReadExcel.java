package datadriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	


	public ArrayList<String> getData(String testCaseName) throws Exception {
		
		
		ArrayList<String> dataSet=new ArrayList<String>();
		
		File src = new File ("C:\\Users\\Chibugo Kaine\\Documents\\tauriatest.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheetname = wb.getSheetAt(0);
		
		int sheets=wb.getNumberOfSheets();
		
		for(int i=0;i<sheets;i++)
		{
			if(wb.getSheetName(i).equalsIgnoreCase("tauriadata"))
			{
				XSSFSheet sheet=wb.getSheetAt(i);
				Iterator<Row>  rows= sheet.iterator();
				Row firstrow= rows.next();
				Iterator<Cell> ce=firstrow.cellIterator();
				
				int k=0;
				int coloumn = 0;
				while(ce.hasNext())
				{
					Cell value=ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
						coloumn=k;
					}
					
					k++;
				}
				
				while(rows.hasNext())
				{
					Row r=rows.next();
					if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						Iterator<Cell>  cv=r.cellIterator();
						while(cv.hasNext())
						{
							Cell c= cv.next();
							if(c.getCellTypeEnum()==CellType.STRING)
							{
								dataSet.add(c.getStringCellValue());
							}
							else{

								dataSet.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								
								//System.out.println(dataSet);

								}
						}
					}
				}
			}
		}
		return dataSet;

	}
	
	

}

package holistapetSite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ExcelDataReadForBlogValidation {


	String sitelink="";
	String Image_1="";
	String Image_2="";
	String Image_3="";
	String Image_4="";
	String Image_5="";


	static DataFormatter dataFormatter=new DataFormatter();

	public synchronized void holistaexceldata(WebDriver driver,ExtentTest test,String workbook) throws FileNotFoundException, IOException, InterruptedException
	{
		
		//String workbook = "C:\\newproject\\Book2.xlsx";
		 String Sheet = "Blog Image Validation";
		
		XSSFWorkbook objWorkbook = new XSSFWorkbook(new FileInputStream(new File(workbook))); 
		XSSFSheet objSheet = objWorkbook.getSheet(Sheet);
		XSSFRow headerRow = objSheet.getRow(0);
		int iRowCount = objSheet.getLastRowNum();
		int iColCount = headerRow.getLastCellNum();

		XSSFRow currentRow = null;
		String sKey = "";
		String sValue = "";	
		for (int iRowCounter = 1; iRowCounter <= iRowCount; iRowCounter++) {
			currentRow = objSheet.getRow(iRowCounter);
			for (int iColCounter = 0; iColCounter < iColCount; iColCounter++) {
				sKey=dataFormatter.formatCellValue(headerRow.getCell(iColCounter)).trim();
				
				sValue=dataFormatter.formatCellValue(currentRow.getCell(iColCounter)).trim();
				if( sValue.isEmpty() || sValue==null)
				{
					sValue=null;
				}else
				{
					
				}
				switch(sKey)
				{
			
				case "Site Link":
					sitelink=sValue;
					break;
				case "Image-1":
					Image_1=sValue;
					break;
				case "Image-2":
					Image_2=sValue;
					break;
				case "Image-3":
					Image_3=sValue;
					break;
				case "Image-4":
					Image_4=sValue;
					break;
				case "Image-5":
					Image_5=sValue;
					break;
				
				
					default:
						System.out.println("Invalid Input");
				}

			}
			MethodsClass methodclassfile=new MethodsClass();

		methodclassfile.BlogImageValidation( driver, sitelink, Image_1, Image_2, Image_3,Image_4,Image_5,iRowCounter, test);
		}
		objWorkbook.close();
	}

	public synchronized void BlogImageValidationDataFromExcel(WebDriver driver,String workbook,ExtentTest test) throws FileNotFoundException, IOException, InterruptedException
	{
		
		
	
		holistaexceldata(driver,test,workbook);	
	}
	
	
	


}

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


public class ExcelDataReadForSingleImageValidation {

	String ImageSiteLink="";
	String ImagePath="";
	String ImageWidth="";
	String ImageHeight="";
	

	static DataFormatter dataFormatter=new DataFormatter();
	
	public synchronized void holistaexceldata(WebDriver driver,ExtentTest test,String workbook) throws FileNotFoundException, IOException, InterruptedException
	{
		
		MethodsClass methodclassfile=new MethodsClass();
		//String workbook = "C:\\newproject\\Book2.xlsx";
		 String Sheet = "single image validation";
		
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
				if(sValue.isEmpty() || sValue==null)
				{
					sValue=null;
				}else
				{
					
				}
				switch(sKey)
				{
					
				case "Image site link":
					ImageSiteLink=sValue;
					break;
				case "image path":
					ImagePath=sValue;
					break;
				case "Image Width":
					ImageWidth=sValue;
					break;
				case "Image Height":
					ImageHeight=sValue;
					break;
				
				
					default:
						System.out.println("Invalid Input");
				}

			}

			methodclassfile.SingleImageValidation( driver, ImageSiteLink, ImagePath, ImageWidth, ImageHeight,iRowCounter, test);
		}
		objWorkbook.close();
	}

	public synchronized void SingleImageValidationDataFromExcel(WebDriver driver,String workbook,ExtentTest test) throws FileNotFoundException, IOException, InterruptedException
	{
		
		
	
		holistaexceldata(driver,test,workbook);	
	}
	
	
	
}

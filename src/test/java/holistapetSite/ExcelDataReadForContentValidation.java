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


public class ExcelDataReadForContentValidation {

	//content validation
	String ContentSiteLink="";
	String ContentText="";
	String ContentTextValidation="";
	static DataFormatter dataFormatter=new DataFormatter();
	public synchronized void holistaexceldata(WebDriver driver,String workbook,String sheet,ExtentTest test) throws FileNotFoundException, IOException, InterruptedException
	{

		MethodsClass methodclassfile=new MethodsClass();
		//String workbook="C:\\SeleniumProjects\\exceldata\\Book1.xlsx";
		XSSFWorkbook objWorkbook = new XSSFWorkbook(new FileInputStream(new File(workbook))); 
		XSSFSheet objSheet = objWorkbook.getSheet(sheet);
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

				switch(sKey)
				{


				case "Content Site Link":
					ContentSiteLink=sValue;
					break;
				case "Content Text":
					ContentText=sValue;
					break;
				case "Content Text Validation":
					ContentTextValidation=sValue;
					break;
				default:
					break;
				}
			}

			boolean status=methodclassfile.linkcheck( driver, ContentSiteLink,test,iRowCounter);
			if(!status)
			{
			methodclassfile.ContentValidation( driver,  ContentSiteLink, ContentText, ContentTextValidation,iRowCounter, test);
			}
		}
		objWorkbook.close();
	}
	public synchronized void ContentValidationDataFromExcel(WebDriver driver,String workbook,ExtentTest test) throws FileNotFoundException, IOException, InterruptedException
	{
		String Sheet = "content validation";
		holistaexceldata(driver, workbook, Sheet, test);	
	}


}

package holistapetSite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;


public class ExcelDataReadForProductValidation {


	//content validation
		String ProductSiteLink="";
		
		String ProductImageValidation="";
		String ProductClickLink="";
		String ProductLinkVerify="";
		String MainImageValidation="";
		String MainImageWidth="";
		String MainImageHeight="";
		String SubImage_1="";
		String SubImage_2="";
		String SubImage_3="";
		String SubImage_4="";
		String SubImage_5="";
		String SubImage_6="";
		String SubImage_7="";
		String SubImage_8="";
		String SubImage_9="";
		String SubImage_10="";
		String ProductQuantitySize="";
		
		static DataFormatter dataFormatter=new DataFormatter();
		public synchronized void holistaexceldata(WebDriver driver,String workbook,String sheet,ExtentTest test) throws FileNotFoundException, IOException, InterruptedException
		{
			
			MethodsClass methodclassfile=new MethodsClass();
			

			XSSFWorkbook objWorkbook = new XSSFWorkbook(new FileInputStream(workbook)); 
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
					if(sValue.isEmpty() || sValue==null)
					{
						sValue=null;
					}else
					{
						
					}
						switch(sKey)
						{
						case "Product Site Link":
							ProductSiteLink=sValue;
							break;
						
						case "Product Image validation":
							ProductImageValidation=sValue;
							break;
						case "Product Click Link":
							ProductClickLink=sValue;
							break;
						case "Product Link Verify":
							ProductLinkVerify=sValue;
							break;
						case "Main Image Validation":
							MainImageValidation=sValue;
							break;
						case "Main Image width":
							MainImageWidth=sValue;
							break;
						case "Main Image Height":
							MainImageHeight=sValue;
							break;	
						case "Sub Image-1":
							SubImage_1=sValue;
							break;
						case "Sub Image-2":
							SubImage_2=sValue;
							break;
						case "Sub Image-3":
							SubImage_3=sValue;
							break;
						case "Sub Image-4":
							SubImage_4=sValue;
							break;
						case "Sub Image-5":
							SubImage_5=sValue;
							break;
						case "Sub Image-6":
							SubImage_6=sValue;
							break;
						case "Sub Image-7":
							SubImage_7=sValue;
							break;
						case "Sub Image-8":
							SubImage_8=sValue;
							break;
						case "Sub Image-9":
							SubImage_9=sValue;
							break;
						case "Sub Image-10":
							SubImage_10=sValue;
							break;
						case "Product Quantity Size":
							ProductQuantitySize=sValue;
							break;
						default:
								System.out.println("Invalid Input");
						}
				}	
			

				methodclassfile.ProductValidation(driver,ProductSiteLink,ProductImageValidation,ProductClickLink,ProductLinkVerify,MainImageValidation,MainImageWidth,MainImageHeight,SubImage_1,SubImage_2,SubImage_3,SubImage_4,SubImage_5,SubImage_6,SubImage_7,SubImage_8,SubImage_9,SubImage_10,ProductQuantitySize, test,iRowCounter);
			}
			objWorkbook.close();
		}
		public synchronized void ContentValidationDataFromExcel(WebDriver driver,String workbook,ExtentTest test) throws FileNotFoundException, IOException, InterruptedException
		{
			//String workbook = "C:\\newproject\\Book2.xlsx";
			 String Sheet = "Product Validation";
			holistaexceldata(driver, workbook, Sheet, test);	
		}
		


}

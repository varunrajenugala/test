package holistapetSite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;


public class ProductValidation extends BaseFile{

	ExcelDataReadForProductValidation data=new ExcelDataReadForProductValidation();
	@Test
	public synchronized void start() throws FileNotFoundException, IOException, InterruptedException
	{
		initializer();
		before("Product Validations Results","PRODUCTS VALIDATION","PRODUCTS  VALIDATION  RESULTS");
		ExtentTest test=extent.createTest("Product Validation Results ");
		String workbook=prop.getProperty("excelpath");
		System.out.println("Products Validation Started");
		data.ContentValidationDataFromExcel(driver,workbook, test);
		System.out.println("Products Validation Completed");
		extent.flush();
		driver.quit();

	}

}

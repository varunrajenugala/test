package holistapetSite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class SingleImageValidation extends BaseFile {
	ExcelDataReadForSingleImageValidation imagevalidation=new ExcelDataReadForSingleImageValidation();
	@Test
	public synchronized void start() throws FileNotFoundException, IOException, InterruptedException
	{
		initializer();
		before("Image Validations Results","IMAGE VALIDATION","IMAGE  VALIDATION  RESULTS");
		ExtentTest test=extent.createTest("Image Validation Results");
		String workbook=prop.getProperty("excelpath");
		System.out.println("Image Validation Started");
		imagevalidation.SingleImageValidationDataFromExcel(driver,workbook, test);
		System.out.println("Image Validation Completed");
		extent.flush();
		driver.quit();

	}

	
}

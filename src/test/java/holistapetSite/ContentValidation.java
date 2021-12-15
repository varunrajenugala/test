package holistapetSite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;


public class ContentValidation extends BaseFile{

	ExcelDataReadForContentValidation data=new ExcelDataReadForContentValidation();
	@Test
	public synchronized void start() throws FileNotFoundException, IOException, InterruptedException
	{
		initializer();
		before("Content Validations Results","CONTENT VALIDATION","CONTENT  VALIDATION  RESULTS");
		ExtentTest test=extent.createTest("Content Validation Result");
		String workbook=prop.getProperty("excelpath");
		System.out.println("content Validation Started");
		data.ContentValidationDataFromExcel(driver,workbook, test);
		System.out.println("Products Validation ended");
		extent.flush();
		driver.quit();
	}
}

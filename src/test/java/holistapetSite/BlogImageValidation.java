package holistapetSite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class BlogImageValidation extends BaseFile{

	ExcelDataReadForBlogValidation Blogimagevalidation=new ExcelDataReadForBlogValidation();
	@Test
	public synchronized void start() throws FileNotFoundException, IOException, InterruptedException
	{
		initializer();
		before("Blog Image Validations Results","BLOG IMAGE VALIDATION","BLOG IMAGE  VALIDATION  RESULTS");
		ExtentTest test=extent.createTest("Blog Image Validation Results");
		String workbook=prop.getProperty("excelpath");
		System.out.println("Blog Image Validation Started");
		Blogimagevalidation.BlogImageValidationDataFromExcel(driver,workbook, test);
		System.out.println("Blog Image Validation Completed");
		extent.flush();
		driver.quit();

	}

	

}

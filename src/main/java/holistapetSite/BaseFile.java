package holistapetSite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseFile {
	public WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports extent;
	Properties prop;

	//ExtentTest test;
	public WebDriver initializer() throws IOException
	{
		prop=new Properties();
		FileInputStream config=new FileInputStream("C:\\Users\\lenovo\\Downloads\\abc\\src\\main\\java\\holistapetSite\\data.properties");
		prop.load(config);
		String browser=prop.getProperty("browser");
		if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\lenovo\\Desktop\\chromedriver driver\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();

		}



		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;	
	}



	public void before(String FileName,String DocumentTitle,String ReportName)
	{		
		
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\reports\\"+FileName+".html");

		htmlreporter.config().setDocumentTitle(DocumentTitle);
		htmlreporter.config().setReportName(ReportName);
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setEncoding("utf-8");
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
	}

//	@BeforeSuite
//	public void beforeSuite() throws InstantiationException{
//
//	}
//
//	@BeforeTest
//	public void beforeTest() throws InstantiationException, IOException{
//
//		initializer();
//		
//
//
//	}
//
//	@BeforeClass
//	public void beforeClass() throws InstantiationException, IOException{
//
//
//	}
//
//	@BeforeMethod
//	public void beforeMethod() throws InstantiationException{
//
//	}
//
//	@AfterMethod
//	public void afterMethod()throws InstantiationException {
//		extent.flush();
//		driver.quit();
//
//	}
//
//	@AfterClass
//	public void afterClass() throws InstantiationException{
//
//	}
//
//	@AfterTest
//	public void afterTest() {
//
//		
//
//	}
//
//	@AfterSuite
//	public void afterSuite() {
//		
//	
//System.out.println("* * * * * * Execution Completed, Results Generated Successfully * * * * * *");
//
//	}



	public WebDriver OpenBrowser(WebDriver driver)
	{

		return driver;
	}
	public WebDriver CloseBrowser(WebDriver driver)
	{
		return driver;
	}
	public WebDriver StartBrowser(WebDriver driver)
	{	return driver;
	}

}


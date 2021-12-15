package holistapetSite;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
public class MethodsClass extends BaseFile{

	public boolean ClickElement(WebDriver driver, By ele) {
		try {

			PageLoading(driver);
			driver.findElement(ele).click();
		} catch (Exception e) {
			try {
				WebElement ele1 = driver.findElement(ele);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", ele1);
			} catch (Exception e2) {
				return false;
			}

		}
		return true;
	}
	public void PageLoading(WebDriver driver) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		int count = 0;
		if ((Boolean) executor.executeScript("return window.jQuery != undefined")) {
			while (!(Boolean) executor.executeScript("return jQuery.active == 0")) {
				Thread.sleep(1);
				if (count > 2000)
					break;
				count++;
			}
		}
	}
	public boolean ScrollDown(WebDriver driver, By ele) {
		try {
			WebElement element = driver.findElement(ele);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000);
			driver.findElement(ele).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean ScrollUp(WebDriver driver, By ele) {
		try {
			WebElement element = driver.findElement(ele);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);
			driver.findElement(ele).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitForElement(WebDriver driver, By elemnt) {
		try {
			PageLoading(driver);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(elemnt));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public By search = By.xpath("//div[contains(@style,'visibility: visible;')]//input");
	public boolean SelectDropDownValue(WebDriver driver, String labelName, String value) {
		boolean status;
		try {
			By dropDowntype = By.xpath("//label[contains(text(),'"+ labelName +"')]//ancestor::ejs-dropdownlist/div");
			By dropdownValue = By.xpath(
					"//div[contains(@style,'visibility: visible;')]/div/ul/li[contains(text(),'" + value + "')]");
			status = ScrollDown(driver, dropDowntype);
			status = waitForElement(driver, dropDowntype);
			status = ClickElement(driver, dropDowntype);
			if (!status) {

				return false;
			}
			status = waitForElement(driver, search);
			status = EnterText(driver, search, value);
			if (!status) {
				return false;
			}
			status = waitForElement(driver, dropdownValue);
			status = ClickElement(driver, dropdownValue);
			if (!status) {
				return false;
			}
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean EnterText(WebDriver driver, By ele, String testdata) {
		try {
			PageLoading(driver);
			driver.findElement(ele).clear();
			driver.findElement(ele).sendKeys(testdata);

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean waitForElementVisibility(WebDriver driver, By elemnt) {
		try {
			PageLoading(driver);
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(elemnt));



		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean SelectDirectDropDownValue(WebDriver driver, String labelName, String dropDownValue) {
		boolean status;
		try {
			By DropDowntype = By.xpath("//label[contains(text(),'" + labelName + "')]//ancestor::ejs-dropdownlist/div");
			By DropDownValue = By.xpath("//div[contains(@style,'visibility: visible;')]/div/ul/li[contains(text(),'" + dropDownValue + "')]");

			status = ScrollDown(driver, DropDowntype);
			status = waitForElement(driver, DropDowntype);
			status = ClickElement(driver, DropDowntype);
			if (!status) {
				return false;
			}
			Thread.sleep(3000);
			status = ScrollDown(driver, DropDownValue);
			status = waitForElement(driver, DropDownValue);
			status = ClickElement(driver, DropDownValue);
			if (!status) {
				return false;
			}
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public synchronized void SingleImageValidation(WebDriver driver,String ImageSiteLink,String ImagePath,String ImageWidth,String ImageHeight,int count,ExtentTest test) throws InterruptedException
	{



		String currenturl=	driver.getCurrentUrl();
		if(ImageSiteLink.equals(currenturl))
		{

		}else
		{
			driver.get(ImageSiteLink);

		}
		PageLoading(driver);

		try
		{
			PageLoading(driver);

			By img=By.xpath("//img[contains(@src,'"+ImagePath+"')]");
			boolean status=scrollagainCheck( driver  , img);
			if(status)
			{

				if(ImageWidth.equals("null") || ImageHeight.equals("null"))
				{

				}else
				{

					WebElement SingleImage= driver.findElement(By.xpath("//img[contains(@src,'"+ImagePath+"')]"));

					String width=SingleImage.getAttribute("width");
					String height=SingleImage.getAttribute("height");

					if(ImageWidth.equals(width))
					{
						if(!ImageHeight.equals(height))
						{

							test.fail("height not matched=,found height ="+height+"from excel = "+ImageHeight);
						}

					}else
					{
						test.fail("width not matched=,fount width = "+width+"from excel = "+ImageWidth);
					}

				}
				test.pass("Successfully Passed ="+count);
			}else
			{
				test.fail("404-Image Not Found"+count);
			}
		}
		catch(Exception e)
		{
			test.fail("400 -Bad Request response ="+count);

		}


	}

	public synchronized void ContentValidation(WebDriver driver, String ContentSiteLink,String ContentText,String ContentTextValidation,int iRowCounter,ExtentTest test) throws InterruptedException
	{
		String currenturl=	driver.getCurrentUrl();
		if(ContentSiteLink.equals(currenturl))
		{

		}else
		{
			driver.get(ContentSiteLink);

		}


		boolean status=false;

		By ContentLinkClick=By.xpath("//span[contains(text(),'"+ContentText+"')]");
		status=ClickElement(driver,ContentLinkClick);
		By contentLink=By.xpath("//a[contains(text(),'"+ContentText+"')]");
		boolean statustwo=ClickElement(driver,contentLink);

		if(status || statustwo)
		{
			PageLoading(driver);

			String VerifyUrl=driver.getCurrentUrl();
			status=VerifyUrl.equals(ContentTextValidation);
			if(status)
			{
				test.pass("completed="+iRowCounter);
			}else
			{
				test.pass("Failed="+iRowCounter);

			}

		}else
		{
			test.fail("content text click failed"+iRowCounter);

		}

	}




	public boolean scrollagainCheck(WebDriver driver  ,By img)
	{
		boolean status=false;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,0)", "");
		int scroll=100;
		for(int i=0;i<=20;i++)
		{
			js.executeScript("window.scrollBy(0,"+scroll+")", "");
			status=waitForElementVisibility( driver,  img);	
			if(status)
			{
				js.executeScript("window.scrollBy(0,-5000)", "");
				break;	
			}else
			{
				scroll=scroll+100;
			}
		}

		return status;

	}

	public synchronized void ProductValidation(WebDriver driver,String ProductSiteLink,String ProductImageValidation,String ProductClickLink,String ProductLinkVerify,String MainImageValidation,String MainImageWidth,String MainImageHeight,String SubImage_1,String SubImage_2,String SubImage_3,String SubImage_4,String SubImage_5,String SubImage_6,String SubImage_7,String SubImage_8,String SubImage_9,String SubImage_10,String ProductQuantitySize,ExtentTest test,int count) throws InterruptedException
	{
		boolean status=true;

		driver.get(ProductSiteLink);
		By productclicklink=By.xpath("//h2[contains(text(),'"+ProductClickLink+"')]");
		By img=By.xpath("//img[contains(@src,'"+ProductImageValidation+"')]");
		status=scrollagainCheck( driver  , img);
		if(status)
		{
			status=ClickElement( driver,  productclicklink);
			if(status)
			{
				Thread.sleep(3000);
				String URL=driver.getCurrentUrl();
				Thread.sleep(1000);
				if(ProductLinkVerify.equals(URL))
				{
					By MainImg=By.xpath("//a[contains(@href,'"+MainImageValidation+"')]");
					//By MainImg=By.xpath(MainImageValidation);
					Thread.sleep(1000);
					status=waitForElementVisibility( driver,  MainImg);

					if(status)
					{
						boolean SubStatus=false;
						SubStatus=	SubImageValidation( driver, SubImage_1, SubImage_2, SubImage_3, SubImage_4, SubImage_5, SubImage_6, SubImage_7, SubImage_8, SubImage_9, SubImage_10,test,count);
						if(SubStatus)
						{
							//add details
							ProductDetails( driver,test ,count);
						}else
						{

						}

					}else
					{
						test.fail("Main image not found "+count);

					}
				}else
				{
					test.fail("Navigated to wrong path "+count);
				}

			}else
			{
				test.fail("Click on Product Failed "+count);
			}

		}else
		{
			test.fail("Image Not Found "+count);

		}
	}
	public void ProductDetails(WebDriver driver,ExtentTest test ,int count) throws InterruptedException
	{
		By AddCart=By.xpath("//button[contains(text(),'Add to cart')]");
		By checkOut=By.xpath("//a[contains(@href,'https://www.holistapet.com/checkout/')]");

		boolean status=false;
		status=ClickElement( driver,  AddCart);

		String URL=driver.getCurrentUrl();
		if(URL.equals("https://www.holistapet.com/cart/"))
		{

			Thread.sleep(1000);
			//proced to checkout
			status=ClickElement( driver,  checkOut);
			if(status)
			{
				Thread.sleep(2000);
				By emailinput=By.xpath("//input[contains(@id,'billing_email')]");
				By mobileinput=By.xpath("//input[contains(@id,'billing_phone')]");
				By firstName=By.xpath("//input[contains(@id,'billing_first_name')]");
				By lastName=By.xpath("//input[contains(@id,'billing_last_name')]");
				By address=By.xpath("//input[contains(@id,'shipping_address_1')]");
				By town=By.xpath("//input[contains(@id,'shipping_city')]");
				By postcode=By.xpath("//input[contains(@id,'shipping_postcode')]");
				//By country=By.xpath("//input[contains(@id,'shipping_country')]");
				//By state=By.xpath("select2-shipping_state-container");
				By continueshipping=By.xpath("//button[contains(text(),'CONTINUE TO shipping →')]");

				EnterText( driver,  emailinput,  "abc@gmail.com");
				EnterText( driver,  mobileinput,  "0123456789");
				EnterText( driver,  firstName,  "nameone");
				EnterText( driver,  lastName,  "nametwo");
				EnterText( driver,  address,  "address");
				EnterText( driver,  town,  "new york");
				EnterText( driver,  postcode,  "12345");
				Thread.sleep(1000);
				ClickElement( driver,  continueshipping);
				test.pass("finished "+count);
			}else
			{
				test.fail("Click on CheckOut Failed "+count);
			}
		}else
		{
			test.fail("Navigated to wrong URL "+count);
		}

	}


	public Boolean SubImageValidation(WebDriver driver,String SubImage_1,String SubImage_2,String SubImage_3,String SubImage_4,String SubImage_5,String SubImage_6,String SubImage_7,String SubImage_8,String SubImage_9,String SubImage_10,ExtentTest test,int count)
	{
		Boolean status=true;
		String[] data= { SubImage_1, SubImage_2, SubImage_3, SubImage_4, SubImage_5, SubImage_6, SubImage_7, SubImage_8, SubImage_9, SubImage_10};
		for(int i=0;i<10;i++)
		{
			if(data[i]!=null)
			{
				try
				{
					By path=By.xpath("//img[contains(@src,'"+data[i]+"')]");
					//String path="//img[contains(@src,'"+data[i]+"')])";
					//status = driver.findElement(By.xpath(path)).isDisplayed();
					status=waitForElementVisibility( driver,  path);
					if(status)
					{
						status=true;

					}
				}catch(Exception e)
				{
					status=false;
					test.fail("Sub Image " +(i+1)+" failed =" +count);
					break;
				}

			}
		}
		return status;
	}
	public boolean linkcheck(WebDriver driver,String url,ExtentTest test,int count)
	{
		boolean status=false;
		String[] urltwo= {"https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4","https://search.example.net/legal%3E;%20rel=%22blocked-by%22","https://www.example.org/index.asp"};

		for(int i=0;i<urltwo.length;i++)
		{
			if(url.equals(urltwo[i]))
			{
				status=true;
				switch(url)
				{
				case "https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4":
					test.fail("404-Page Not Found"+count);
					break;
				case "https://search.example.net/legal%3E;%20rel=%22blocked-by%22":
					test.fail("451-Unavailable For Legal Reasons"+count);
					break;
				case "https://www.example.org/index.asp":
					test.fail("301 Moved Permanently"+count);
					break;

				}
			}
		}
		return status;
	}
	public void BlogImageValidation(WebDriver driver,String ImagePath,String Image_1,String Image_2,String Image_3,String Image_4,String Image_5,int iRowCounter,ExtentTest test) throws InterruptedException
	{
		driver.get(ImagePath);
		PageLoading(driver);
		boolean status=blogImageValidation( driver,ImagePath, Image_1, Image_2, Image_3, Image_4, Image_5, iRowCounter, test);
		if(status)
		{
			test.pass("blog image validation pass "+iRowCounter);	
		}else
		{
			test.fail("Blog image validation failed "+iRowCounter);	
		}

	}


	public Boolean blogImageValidation(WebDriver driver,String ImagePath,String SubImage_1,String SubImage_2,String SubImage_3,String SubImage_4,String SubImage_5,int count,ExtentTest test)
	{
		Boolean status=true;
		String[] data= { SubImage_1, SubImage_2, SubImage_3, SubImage_4, SubImage_5};
		for(int i=0;i<5;i++)
		{
			if(data[i]!=null)
			{

				By path=By.xpath("//img[contains(@src,'"+data[i]+"')]");
				status=scrollagainCheck( driver  , path);
				if(status)
				{
					status=true;
					test.pass("Blog Image " +(i+1)+" pass =" +count);
				}else
				{
					status=false;
					
					test.fail("Blog Image " +(i+1)+" fail =" +count);
				}


			}
		}
		return status;
	}
}









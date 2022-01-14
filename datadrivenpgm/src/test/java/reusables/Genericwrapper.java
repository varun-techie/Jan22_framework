package reusables;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Genericwrapper extends Browserbase {

	//wrapper,common,generic(all selenium methods)
	//trycatch
	
	public static String screenshotName;

	
	public void getmyurl(String input,String url) {
		try {
			launchbrowser(input);
			driver.get(url);
			driver.manage().window().maximize();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(url+" didn't open the site properly"+e.getMessage());
		}
		
	}
	
	public void click(String locator) {
		
	try {
		driver.findElement(By.xpath(locator)).click();
		logger.info("my locator is working "+locator);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		logger.info("mine not working during thz clikc operation"+locator);
	}	
		
	}
	
	
	public void entertext(String locator,String text) {
		
	By by=By.xpath(locator);
		driver.findElement(by).sendKeys(text);	
	}
	
	//dragdrop,windows,frame,waits,right click,doooooooooooooooooooooooooooooo
	public void dropdown(String locator,int index) {
	
		WebElement element=driver.findElement(By.xpath(locator));
		Select sel= new Select(element);
		sel.selectByIndex(index);
		
	}
	
	public void doubleClick(String locator) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(locator));
		action.doubleClick(element).perform();

	}

	public void rightclick(String locator) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(locator));
		action.contextClick(element).perform();

	}

	public void sleep(int timeOutInSeconds) throws InterruptedException {

		Thread.sleep(timeOutInSeconds * 1000);
	}

	public void implicitwait(int timeOutInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	}

	public void acceptalert() {
		driver.switchTo().alert().accept();
	}

	public void rejectalert() {
		driver.switchTo().alert().dismiss();

	}

	public void mousehover(String locator) {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(locator));
		action.moveToElement(element).build().perform();
	}

	public void switchToFrame(int frameId) {
		driver.switchTo().frame(frameId);
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);

	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText(String locator) {
	
		return driver.findElement(By.xpath(locator)).getText();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public String getWindowTitle() {
		return driver.getTitle();
	}

	public boolean switchToWindowWithElement(String locator) {
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			if (driver.findElements(By.xpath(locator)).size() > 0)
				return true;
		}
		return false;
	}

	public boolean isElementselected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			return true;
		}

		return false;

	}

	public boolean isElementPresent(String locator) {

		WebElement element = driver.findElement(By.xpath(locator));
		if (element != null) {

			return true;
		}

		return false;

	}

	public boolean isElementVisible(String locator) {

		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		}

		return false;

	}

	
	  public static String screenshot() throws IOException {

	        TakesScreenshot scrShot =(TakesScreenshot) driver;
	        
	        //Call getScreenshotAs method to create image file
	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	                LocalDateTime d = LocalDateTime.now();
	                screenshotName=d.toString().replace(":", "_").replace(" ", "_");
	             String Dest="D:\\Framework_allkind\\HybridDriven\\screenshots\\"+screenshotName+".png";
	  	       File DestFile=new File("D:\\Framework_allkind\\HybridDriven\\screenshots\\"+screenshotName+".png");
	               FileUtils.copyFile(SrcFile, DestFile);
	               
	               return Dest;
	               //ReportBase.test.log(Status.FAIL, (Markup) ReportBase.test.addScreenCaptureFromPath(Dest));  
	  }
	  
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void javascriptClick(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void switchToPopUpWindow(String parentWindow) {
		String windowId = "";
		Set<String> nos = driver.getWindowHandles();
		System.out.println(nos.size());
		Iterator<String> iterator = nos.iterator();
		while (iterator.hasNext()) {
			windowId = (String) iterator.next();
			if (!windowId.equals(parentWindow)) {
				driver.switchTo().window(windowId);
			}

		}
	}

	public void scrollDown() {

		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("window.scrollTo(0, document.body.scrollHeight)", "");

	}

	
	public By locatorValue(String locatorType, String value) {
		By by;
		switch (locatorType) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}
	
}

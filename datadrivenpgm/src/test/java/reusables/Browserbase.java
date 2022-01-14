package reusables;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Browserbase {

    public static Logger logger = Logger.getLogger("devpinoyLogger");//fixed in log4j,class
    
    ExtentHtmlReporter htmlReporter;
    protected ExtentReports extent;
    protected  ExtentTest test;

		//dummydriver
		//webdriver--reference
		static ChromeDriver chrome;
		static FirefoxDriver ff;
	public	static WebDriver driver;
	
	@BeforeSuite
	public void logger() {
		PropertyConfigurator.configure("Log4j.properties");//load
		
	}
	
	
	@AfterTest 
	public void tearDown() {
		 
		 extent.flush();
		 }
	
	
	@BeforeTest
	public void report() {
		
		    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	        
		    htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle("Demo report");
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName("Test");

	        //initialize ExtentReports and attach the HtmlReporter
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);

	        extent.setSystemInfo("Automation Tester", "Varun");
	        extent.setSystemInfo("Organization", "Aimore");
	        extent.setSystemInfo("Build no", "W2A-1234");
	        extent.setSystemInfo("OS", "Windows 10");

	         
	        //To add system or environment info by using the setSystemInfo method.
	        
	        //configuration items to change the look and feel
	        //add content, manage tests etc
	       		
	}
		
		public static WebDriver launchbrowser(String input) {
			
				
			
			if(input.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "D:\\sepbatchdriver\\chromedriver.exe");//dummy pointing
				 driver =new ChromeDriver();
			logger.info("lauched chrome");	 
			}else if(input.equals("ff")) {
				
				
				System.setProperty("webdriver.gecko.driver", "D:\\sepbatchdriver\\geckodriver.exe");
				 driver = new FirefoxDriver();
			}
			
			//edge and IE----
			
		return driver;	
		}
		
		
		@AfterMethod
		public  void endTest(ITestResult result) throws IOException
		{
		//report.endTest(test);
			 if(result.getStatus() == ITestResult.FAILURE)
		        {
				
		            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
		            test.fail(result.getThrowable());
		            String path="D:\\Framework_allkind\\HybridDriven\\screenshots\\";
		            //test.log(Status.FAIL,(Markup) MediaEntityBuilder.createScreenCaptureFromPath(path,KeywordWrappers.screenshotName+".png").build());
		            
		            test.addScreenCaptureFromPath(Genericwrapper.screenshot()); 
		        }
		        else if(result.getStatus() == ITestResult.SUCCESS)
		        {
		            test.log(Status.PASS,  MarkupHelper.createLabel(result.getName()+" Test case Passed", ExtentColor.GREEN));
		            test.addScreenCaptureFromPath(Genericwrapper.screenshot()); 
		               }
		        else
		        {
		            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
		            test.skip(result.getThrowable());
		        }

		}
		
	}



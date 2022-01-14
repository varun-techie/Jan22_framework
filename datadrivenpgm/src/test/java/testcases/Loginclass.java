package testcases;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.Homepage;
import pages.Loginpage;
import reusables.Browserbase;
import reusables.Genericwrapper;
import utilities.Excelreader;
import utilities.Propreader;

public class Loginclass extends Browserbase{
	
	
	Genericwrapper gw= new Genericwrapper();
	Propreader pr= new Propreader();
	
	
	//user story 1,alt+shift+j
	/**
	 * @author varun
	 * this method is used to login my application
	 * @param pass content from commonmethods
	 * @throws IOException 
	 */
	public void firstcase() throws IOException {
		
		
		
		gw.getmyurl("chrome", "http://automationpractice.com/index.php");//excel read
		gw.click(pr.getobjects("signin"));//property
		gw.entertext(pr.getobjects("username"), "");
		//gw.dropdown(locator, index);
		
			
	}
	
	//dataprovider
	@Test(dataProviderClass = Excelreader.class,dataProvider = "myownfile",enabled=false)
	public void secondcase(String myvalue) {
		
		System.out.println(myvalue);
		
	}
	
	
	@Test(dataProviderClass = Excelreader.class,dataProvider="readexcel")
	public void thirdcase(Map<Object,Object> data) throws IOException, InterruptedException {
        test = extent.createTest("Test Case 1", "going to run tc");
		//System.out.println(data.get("Browser").toString());
		gw.getmyurl(data.get("Browser").toString(), data.get("url").toString());
		gw.click(pr.getobjects("signin"));
		Thread.sleep(4000);
		gw.entertext(pr.getobjects("email"), data.get("email").toString());
		
		test.log(Status.INFO,"finishing the tc");
		
		
	}
	
	//Loginpage lp=new Loginpage();
	
	@Test(dataProviderClass = Excelreader.class,dataProvider="readexcel")
	public void fourthcase(Map<Object,Object> data) throws IOException, InterruptedException {
        test = extent.createTest("Test Case 2", "going to run tc");

		//System.out.println(data.get("Browser").toString());
		Loginpage lp=new Loginpage();
		test.log(Status.INFO, "invoking loginpage");
		Homepage hp=lp.clicksignin(data);
		hp.dummy();
		Thread.sleep(4000);
		logger.info("signed in enter the username");
		logger.info("came to second line");
		
	
		/*
		 * lp.enterusername(data); logger.info("entered email");
		 */
		test.log(Status.INFO,"finishing the tc");

		//gw.entertext(pr.getobjects("email"), data.get("email").toString());
		
		
		
		
	}
	
	
	

}

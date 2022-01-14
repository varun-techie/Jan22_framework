package testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import reusables.Genericwrapper;
import utilities.Excelreader;
import utilities.Propreader;

public class Homeclass {

	
	
	
	Genericwrapper gw= new Genericwrapper();
	Propreader pr= new Propreader();
	
	
	@Test(dataProviderClass = Excelreader.class,dataProvider="readexcel")
	public void firstcase(Map<Object,Object> data) throws IOException, InterruptedException {
	
		gw.getmyurl(data.get("Browser").toString(), data.get("url").toString());
		gw.click(pr.getobjects("signin"));
		Thread.sleep(4000);
		
		
	}
		
			
	}
	


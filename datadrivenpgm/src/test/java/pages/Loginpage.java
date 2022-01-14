package pages;

import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.By;

import reusables.Genericwrapper;
import utilities.Propreader;

public class Loginpage extends Genericwrapper{

	By password=By.xpath("");
	String username="//*[@id='email']";
	//By username=By.xpath("//*[@id='email']");
	 
	
	Propreader pr= new Propreader();

	public void enterusername(Map<Object,Object> data) {
		
		entertext(username, data.get("email").toString());
	}
	
	
	public Homepage clicksignin(Map<Object,Object> data) throws IOException {
	
		getmyurl(data.get("Browser").toString(), data.get("url").toString());
		click(pr.getobjects("signin"));
		
		return new Homepage();
	}
	
	
	
}

package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propreader {

	
	public String getobjects(String key) throws IOException {
		// TODO Auto-generated method stub

		File f = new File("D:\\Nimishafolder\\testam\\locator.properties");

		// read file image,audio,xls
		FileInputStream fis = new FileInputStream(f);
		
		Properties prop = new Properties();
		prop.load(fis);
		
		
		String value=prop.getProperty(key);
		
		return value;
	}
	
}

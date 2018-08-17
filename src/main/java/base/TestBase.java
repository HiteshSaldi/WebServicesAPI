package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties pro;
	FileInputStream fs=null;
	
	public TestBase(){
		
		
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//config//config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pro = new Properties();
		try {
			pro.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

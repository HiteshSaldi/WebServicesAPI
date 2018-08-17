package testApi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import base.TestBase;
import client.Clients;
import data.Users;

public class Post_Api_Users {
	
	TestBase testBase;
	String srvcUrl;
	String apiUrl;
	String URL;
	String userName;
	String password;
	
	
	@BeforeMethod
	public void setUp(){
		
		testBase = new TestBase();
		//srvcUrl = pro.getProperty("ServiceURL");
		//apiUrl = pro.getProperty("ApiURL");
		URL = srvcUrl+apiUrl;
		URL = "https://reqres.in/api/users";
		
	}
	
	@Test(priority=1)
	public void test_Post_Api_Users_with_header() throws IOException{
		//1. call the httpClient and get the response.
		Clients httpClient = new Clients();
				
		//2. Add Header.
		HashMap<String,String> getHeader=new HashMap<String,String>();
		getHeader.put("Content-Type","application/json" );
		
		//3.Json Data by using jackson API.
		//jackson api.
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("mehul","manager");
				
		//Object into json.
		mapper.writeValue(new File(System.getProperty("user.dir")+"//src//main//java//data//Post_user.json"), users);
				
		//json into  string.
		String str=mapper.writeValueAsString(users);
		
		CloseableHttpResponse httpResponse = httpClient.POST_With_Header_HttpClient(URL,getHeader,str);
		
		int statusCode=httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		Assert.assertEquals(statusCode, 201);
	
	
	}
	
	
}

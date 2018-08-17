package testApi;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import client.Clients;
import utils.TestUtils;


public class Get_Api_Users {
	
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
	public void test_Users_without_header() throws IOException{
	
		//1. call the httpClient and get the response.
		Clients httpClient = new Clients();
		CloseableHttpResponse httpResponse = httpClient.GET_Without_Header_HttpClient(URL);
		
		//2. print the status code.
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//3. print the headers.
		Header[] headerArray = httpResponse.getAllHeaders();
		
		HashMap<String,String> getHeader = new HashMap<String,String>();
		for(Header header:headerArray ){
			getHeader.put(header.getName(), header.getValue());
		}
		System.out.println(getHeader);
		
		//4. print the response string.
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println(responseString);
		
		JSONObject jsonObject=new JSONObject(responseString);
		System.out.println(TestUtils.getValueByJPath(jsonObject, "/per_page"));
		Assert.assertEquals(TestUtils.getValueByJPath(jsonObject, "/per_page"), "3");
		
		
	}
	
	@Test(priority=2)
	public void test_User_with_header() throws IOException{
	
		//1. call the httpClient and get the response.
		Clients httpClient = new Clients();
		
		//2. Add Header.
		HashMap<String,String> getHeader=new HashMap<String,String>();
		getHeader.put("Content-Type","application/json" );
		//getHeader.put("username", "test123");
		//getHeader.put("password", "Test123");
		//getHeader.put("Auth-Token", "12345");
		
		CloseableHttpResponse httpResponse = httpClient.GET_With_Header_HttpClient(URL,getHeader);
		
		//3. print the status code.
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//4. print the response string.
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		System.out.println(responseString);
		
		JSONObject jsonObject=new JSONObject(responseString);
		System.out.println(TestUtils.getValueByJPath(jsonObject, "/per_page"));
		Assert.assertEquals(TestUtils.getValueByJPath(jsonObject, "/per_page"), "3");
		
		System.out.println(TestUtils.getValueByJPath(jsonObject, "/data[0]/id"));
		System.out.println(TestUtils.getValueByJPath(jsonObject, "/data[0]/first_name"));
		
		System.out.println(TestUtils.getValueByJPath(jsonObject, "/data[1]/id"));
		System.out.println(TestUtils.getValueByJPath(jsonObject, "/data[1]/first_name"));
			
	}
	
	
	
}

package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Clients {
	
	public CloseableHttpResponse GET_Without_Header_HttpClient(String url) throws ClientProtocolException, IOException{
		
		//1. Create Default HTTP Client.
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		//2. Create the Get Call.
		HttpGet httpGet = new HttpGet(url);
		
		//3/ Execute the Client by passing the GET Call.
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		
		return closeableHttpResponse;
		
	}
	
	
	public CloseableHttpResponse GET_With_Header_HttpClient(String url,HashMap<String,String> header) throws ClientProtocolException, IOException{
		
		//1. Create Default HTTP Client.
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		//2. Create the Get Call.
		HttpGet httpGet = new HttpGet(url);
		
		
		for(Map.Entry<String, String> getHeader: header.entrySet()){
			httpGet.addHeader(getHeader.getKey(),getHeader.getValue());
		}
		
		//4. Execute the Client by passing the GET Call.
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		
		return closeableHttpResponse;
		
	}
	
	
	public CloseableHttpResponse POST_With_Header_HttpClient(String url,HashMap<String,String> header,String jsonData) throws ClientProtocolException, IOException{
		
		//1. Create Default HTTP Client.
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		
		//2. Create the POST Call.
		HttpPost httpPost=new HttpPost(url);
		
		
		for(Map.Entry<String, String> getHeader: header.entrySet()){
			httpPost.addHeader(getHeader.getKey(),getHeader.getValue());
		}
		
		httpPost.setEntity(new StringEntity(jsonData));
		
		//4. Execute the Client by passing the GET Call.
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		
		return closeableHttpResponse;
		
	}

}

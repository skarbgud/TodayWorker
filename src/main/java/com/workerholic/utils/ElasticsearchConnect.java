package com.workerholic.utils;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

import lombok.Data;

@Data
public class ElasticsearchConnect {

	private String address = "localhost";
	private Integer port = 9200;
	private String userId = "elastic";
	private String password = "elastic";
	
	public ElasticsearchConnect(String address, Integer port, String userId, String password) 
	{
		if(address != null)
		{
			setAddress(address);
		}
		
		if(port != null)
		{
			setPort(port);
		}
		
		if(userId != null)
		{
			setUserId(userId);
		}
		
		if(password != null)
		{
			setPassword(password);
		}
	}
	
	public ElasticsearchConnect(String address, Integer port) 
	{
		if(address != null)
		{
			setAddress(address);
		}
		
		if(port != null)
		{
			setPort(port);
		}
	}
	
	public CredentialsProvider getCredentialsProvider()
	{
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(getUserId(), getPassword()));
		
		return credentialsProvider;
	}
		
	public RestHighLevelClient getConnection() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost(this.address, this.port, "http")));
	}
}

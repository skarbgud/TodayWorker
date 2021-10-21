package com.workerholic.utils;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticsearchConnect {

	private String address = "localhost";
	private Integer port = 9200;
	private String userId = "elastic";
	private String password = "password123";
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ElasticsearchConnect(String address, Integer port, String userId, String password) {
		if (address != null) {
			setAddress(address);
		}

		if (port != null) {
			setPort(port);
		}

		if (userId != null) {
			setUserId(userId);
		}

		if (password != null) {
			setPassword(password);
		}
	}

	public ElasticsearchConnect(String address, Integer port) {
		if (address != null) {
			setAddress(address);
		}

		if (port != null) {
			setPort(port);
		}
	}

	public CredentialsProvider getCredentialsProvider() {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(getUserId(), getPassword()));

		return credentialsProvider;
	}

	public RestHighLevelClient getConnection() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost(this.address, this.port, "http"))
				.setHttpClientConfigCallback(new HttpClientConfigCallback() {
					@Override
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						// TODO Auto-generated method stub
						return httpClientBuilder.setDefaultCredentialsProvider(getCredentialsProvider());
					}
				}));
	}
}

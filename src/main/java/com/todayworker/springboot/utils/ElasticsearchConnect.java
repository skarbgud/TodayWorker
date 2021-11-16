package com.todayworker.springboot.utils;

import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;

@Data
public class ElasticsearchConnect{

	private String host = "192.168.1.103";
	private Integer port = 9200;
	private String userId = "elastic";
	private String password = "password123";

	public ElasticsearchConnect() {
		if (host != null) {
			setHost(host);
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

	public ElasticsearchConnect(String address, Integer port, String userId, String password) {
		if (address != null) {
			setHost(address);
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
			setHost(address);
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
		return new RestHighLevelClient(RestClient.builder(new HttpHost(this.host, this.port, "http"))
				.setHttpClientConfigCallback(new HttpClientConfigCallback() {
					@Override
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(getCredentialsProvider());
					}
				}));
	}
}

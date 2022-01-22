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
import org.springframework.beans.factory.annotation.Value;

// TODO : Connection을 직접 맺거나 저수준의 API(High Level RestClient)를 직접 접근해서 구현하기 보다 Spring 프레임워크에서 제공하는 기능을 활용하는 것이 좋습니다.
@Data
public class ElasticsearchConnect{

	private String host =  "localhost"; //TODO : IP는 각자 로컬 PC마다 다르게 할당될 수 있으니 호스트는 웬만하면 도메인 형식으로 해야되지만,, 모종의 이슈 때문에 IP로 쓰셨겠죠...?
	private Integer port = 9200;
	private String userId = "elastic"; //TODO : id랑 password이건 뭔가요? AuthScope.ANY라서 의미는 없을 것 같은데, CredentialsProvider에서 username과 password를 강제해서 그런건가요?
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

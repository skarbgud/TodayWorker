package com.workerholic.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workerholic.utils.ElasticsearchConnect;
import com.workerholic.vo.ElasticSearchVO;

@Component
public class ElasticApi {

	private String esAddress = "localhost";
	private Integer esPort = 9200;
	private String esUserId = "elastic";
	private String esPassword = "elastic";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ElasticsearchConnect getElasticsearchConnect() {
		ElasticsearchConnect connect = new ElasticsearchConnect(esAddress, esPort);

		return connect;
	}

	private ElasticSearchVO getSearchCondition() {
		ElasticSearchVO search = new ElasticSearchVO();

		try {
			Date startDatetime = sdf.parse("2021-09-25 00:00:00");
			Date endDatetime = sdf.parse("2021-09-28 00:00:00");

			search.setStartDatetime(startDatetime);
			search.setEndDatetime(endDatetime);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return search;
	}

	private String hostname = "localhost";
	private Integer port = 9200;

	@Bean
	public RestHighLevelClient restHighLevelClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port, "http")));
	}

	private final RestHighLevelClient client = restHighLevelClient();

	@Test
	public void indexCreatetest() {
		String indexName = "board";
		CreateIndexRequest request = new CreateIndexRequest(indexName);

		request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));

		try {
			client.indices().create(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();

	@Test
	public void executeCreateDocument() {
		String index = "test";
		String id = "3";
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("user", "nam");
		jsonMap.put("postDate", new Date());
		jsonMap.put("message", "trying out Elasticsearch2");
		try {
			createDocument(index, id, gson.toJson(jsonMap).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDocument(String index, String id, String jsonBody) throws IOException {
		IndexRequest request = new IndexRequest(index).id(id).source(jsonBody, XContentType.JSON);

		client.index(request, RequestOptions.DEFAULT);
	}

	@Test
	public void executeReadDocument() {
		String index = "test";
		String id = "3";

		try {
			System.out.println(gson.toJson(getDocument(index, id)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Object> getDocument(String index, String id) throws IOException {
		GetRequest request = new GetRequest(index, id);
		return client.get(request, RequestOptions.DEFAULT).getSourceAsMap();
	}

	@Test
	public void executeUpdateDocument() {
		String index = "test";
		String id = "2";

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("user", "updateNam");

		try {
			updateDocument(index, id, jsonMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateDocument(String index, String id, Map<String, Object> bodyMap) throws IOException {
		UpdateRequest request = new UpdateRequest(index, id).doc(bodyMap);

		client.update(request, RequestOptions.DEFAULT);
	}

	@Test
	public void executeDeleteDocument() {
		String index = "test";
		String id = "3";
		
		try {
			deleteDocument(index, id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteDocument(String index, String id) throws IOException {
		DeleteRequest request = new DeleteRequest(index, id);
		client.delete(request, RequestOptions.DEFAULT);
	}

}

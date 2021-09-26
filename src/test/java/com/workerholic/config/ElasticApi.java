package com.workerholic.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
//	RestHighLevelClient client = new RestHighLevelClient(
//			RestClient.builder(new HttpHost("localhost", 9200, "http")));
//
//	CreateIndexRequest request = new CreateIndexRequest("twitter");

	
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
	
	
	
	
	
	@Test
	public void testService() throws Exception {
//		ElasticSearchVO search = getSearchCondition();
//
//		ElasticsearchConnect connect = getElasticsearchConnect();
//
//		
////		IndexRequest request = new IndexRequest("posts");
////		request.id("1");
////		Map<String, Object> jsonMap = new HashMap<>();
////		jsonMap.put("user", "kimchy");
////		jsonMap.put("postDate", new Date());
////		jsonMap.put("message", "trying out Elasticsearch");
////		IndexRequest indexRequest = new IndexRequest("posts").id("1").source(jsonMap);
////		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
//
//		
//		request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
//		Map<String, Object> message = new HashMap<>();
//		message.put("type", "text");
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("message", message);
//		Map<String, Object> mapping = new HashMap<>();
//		mapping.put("properties", properties);
//		request.mapping(mapping);
//		XContentBuilder builder = XContentFactory.jsonBuilder();
//		builder.startObject();
//		{
//			builder.startObject("properties");
//			{
//				builder.startObject("message");
//				{
//					builder.field("type", "text");
//				}
//				builder.endObject();
//			}
//			builder.endObject();
//		}
//		builder.endObject();
//		request.mapping(builder);
//		request.alias(new Alias("twitter_alias").filter(QueryBuilders.termQuery("user", "kimchy")));
//		request.source("{\n" + "    \"settings\" : {\n" + "        \"number_of_shards\" : 1,\n"
//				+ "        \"number_of_replicas\" : 0\n" + "    },\n" + "    \"mappings\" : {\n"
//				+ "        \"properties\" : {\n" + "            \"message\" : { \"type\" : \"text\" }\n" + "        }\n"
//				+ "    },\n" + "    \"aliases\" : {\n" + "        \"twitter_alias\" : {}\n" + "    }\n" + "}",
//				XContentType.JSON);
		
		
		
	}
	
}

package com.todayworker.springboot.elasticsearch.helper;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

// 참고 : https://honeyinfo7.tistory.com/301
public class ElasticSearchExtension implements BeforeAllCallback, AfterAllCallback {

    public static String[] INDEX_NAMES = {"board"};
    private ElasticsearchContainer elasticSearchContainer;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        elasticSearchContainer = new ElasticsearchContainer(
            DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.9.2"))
            .withExposedPorts(9200)
            .withEnv("node.name", "elasticSearch")
            .withEnv("discovery.seed_hosts", "elasticSearch")
//                .withEnv("cluster.initial_master_nodes","elasticSearch")
            .withEnv("cluster.name", "docker-cluster")
            .withEnv("discovery.type", "single-node")
            .withEnv("ES_JAVA_OPTS", "-Xms128m -Xmx128m")
            .withStartupTimeout(Duration.of(120, ChronoUnit.SECONDS));

        elasticSearchContainer.start();

        System.setProperty("spring.elasticsearch.rest.uris",
            String.format("http://localhost:%d", elasticSearchContainer.getFirstMappedPort()));
        System.setProperty("elasticsearch.test.container.host.port",
            elasticSearchContainer.getFirstMappedPort().toString());

        // board 인덱스가 있는지 확인후 없으면 생성
        for (String indexName : INDEX_NAMES) {
            this.isExistIndex(indexName);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        // do nothing, Testcontainers handles container shutdown
//        elasticSearchContainer.stop();
    }

    private void isExistIndex(String indexName) {
        System.out.println(
            "TEST ELASTIC SEARCH HOST : " + System.getProperty("spring.elasticsearch.rest.uris"));
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
            HttpHost.create(System.getProperty("spring.elasticsearch.rest.uris"))));
        // 이 값에서 존재하는 인덱스를 삭제 할 것인지를 판단
        Boolean deleteFlag = false;

        if (client != null) {
            GetIndexRequest request = new GetIndexRequest(indexName);

            // 인덱스 생성 요청
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0));
            // 인덱스 삭제 요청
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);

            try {
                boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

                // 존재한다면 생성 X => 삭제를 원하면 deleteFlag로 삭제 가능
                if (exists && deleteFlag) {
                    client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
                }
                // 존재하지 않는다면 생성
                else if (!exists) {
                    CreateIndexResponse response = client.indices()
                        .create(createIndexRequest, RequestOptions.DEFAULT);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

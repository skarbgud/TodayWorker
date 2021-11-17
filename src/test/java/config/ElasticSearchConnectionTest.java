package config;

import com.todayworker.springboot.utils.ElasticsearchConnect;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
public class ElasticSearchConnectionTest {

    private ElasticsearchConnect connect = new ElasticsearchConnect();

    private final RestHighLevelClient client = connect.getConnection();

    // board 인덱스가 있는지 확인후 없으면 생성
    @Test
    public void isExistIndexTest() {

        String[] indexNames = {"board"};

        for (String indexName : indexNames)
        {
            isExistIndex(indexName);
        }
    }

    public void isExistIndex(String indexName) {
        // 이 값에서 존재하는 인덱스를 삭제 할 것인지를 판단
        Boolean deleteFlag = false;

        if (client != null) {
            GetIndexRequest request = new GetIndexRequest(indexName);

            // 인덱스 생성 요청
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));
            // 인덱스 삭제 요청
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);

            try {
                boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

                // 존재한다면 생성 X => 삭제를 원하면 deleteFlag로 삭제 가능
                if(exists && deleteFlag) {
                    client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
                }
                // 존재하지 않는다면 생성
                else if (!exists){
                    CreateIndexResponse response = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

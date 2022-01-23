package com.todayworker.springboot.elasticsearch;

import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ExtendWith(ElasticSearchExtension.class)
@ActiveProfiles("test")
@Disabled
public class ElasticSearchConnectionTest {

    @Test
    public void isExistIndexTest() {

    }
}

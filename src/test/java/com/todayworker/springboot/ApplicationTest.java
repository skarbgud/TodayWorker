package com.todayworker.springboot;

import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(ElasticSearchExtension.class)
public class ApplicationTest {

    @Test
    void contextLoads() {

    }
}

package com.workerholic.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {

    @Inject
    private DataSource ds;

    @Test
    public void testConnection() throws Exception{
        try {
            Connection con = ds.getConnection();
            log.info(con);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
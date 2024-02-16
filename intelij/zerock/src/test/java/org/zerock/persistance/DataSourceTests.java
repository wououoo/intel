package org.zerock.persistance;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;
import org.zerock.sample.Restaurant;

import javax.sql.DataSource;
import java.sql.Connection;

@ContextConfiguration(classes = {RootConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class DataSourceTests {
    @Setter(onMethod_ = {@Autowired})
    private DataSource dataSource;

    @Setter(onMethod_ = {@Autowired})
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection(){
        try(Connection con = dataSource.getConnection()){
            System.out.println(con);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testMyBatis(){
        try(SqlSession session = sqlSessionFactory.openSession(); Connection con = dataSource.getConnection();){
            System.out.println("session : " + session);
            System.out.println(con);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}


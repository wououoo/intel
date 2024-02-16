package org.zerock.persistance;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;
import org.zerock.mapper.TimeMapper;

import javax.sql.DataSource;

@ContextConfiguration(classes = {RootConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class TimeMapperTests {
    @Setter(onMethod_ = {@Autowired})
    private TimeMapper timeMapper;


    @Test
    public void testGetTime(){
        System.out.println(timeMapper.getClass().getName());
        System.out.println("gettime(): " +timeMapper.getTime());

    }
}
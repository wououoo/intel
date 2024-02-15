package org.zerock.sample;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@ContextConfiguration(classes = {RootConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class sampleTests {
   @Setter(onMethod_ = {@Autowired})
    private Restaurant restaurant1;

    private Restaurant restaurant2;

    @Test
    public void testExists(){
        assertNotNull(restaurant1);

        //log.debug("log:" + restaurant1);
        System.out.println(restaurant1.getChef());
        System.out.println(restaurant1);
        System.out.println("-------------------");
    }

    @Test
    public void testExists2(){
        Restaurant res1 = new Restaurant();
        res1.getChef();
//        Restaurant res2 = null;
//        res2.getChef();
    }
}
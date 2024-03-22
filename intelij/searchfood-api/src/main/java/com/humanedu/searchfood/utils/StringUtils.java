package com.humanedu.searchfood.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class StringUtils {
    private int cnt1 = 0;
    private int cnt2 = 0;
    public static String removeTags(String str){
        if(Strings.isEmpty(str)){
            return str;
        } else{
            return Pattern.compile("<.+?>").matcher(str)
                    .replaceAll("");
        }
    }
    
    @Scheduled(fixedDelay = 1000)
    public void scheduleTest1(){
        System.out.println("1초마다 실행 하는 로그" + (cnt1++));
    }

    @Scheduled(cron = "0/2 * * * * * ", zone = "Asia/Seoul")
    public void scheduleTest2(){
        System.out.println("2초마다 실행 하는 로그" + (cnt2++));
    }
}

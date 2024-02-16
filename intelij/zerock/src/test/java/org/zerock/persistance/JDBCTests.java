package org.zerock.persistance;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;
import static org.junit.jupiter.api.Assertions.fail;

@Log4j
public class JDBCTests {
    static {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        System.out.println("실행");
        try(Connection con
            = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
                "jsp",
                "123456" )){
            System.out.println("con: " +  con);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
 @Test
     public void switchTest() {
        String hobby = "영화";
        switch (hobby) {
            case "영화" -> System.out.println("영화");
            case "게임" -> System.out.println("게임");
            default -> System.out.println("case에 없다");
     }
        System.out.println(
             switch (hobby) {
                 case "게임"-> "좋아하는 취미";
                 case "오잉" -> "이건 취미가 아닌데";
                 default -> "취미가 없네 ?";
             }
     );
    }

}



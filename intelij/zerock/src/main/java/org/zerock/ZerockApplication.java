package org.zerock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zerock.sample.Chef;
import org.zerock.sample.Restaurant;

@SpringBootApplication
public class ZerockApplication {

	public static void main(String[] args) {
		System.out.println("main실행");
		SpringApplication.run(ZerockApplication.class, args);
		System.out.println("main끝");
	}
}

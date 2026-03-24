package com.redactd.contentms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ContentmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentmsApplication.class, args);
	}

}







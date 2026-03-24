package com.redactd.platformms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PlatformmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformmsApplication.class, args);
	}

}







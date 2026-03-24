package com.redactd.verdictms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VerdictmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerdictmsApplication.class, args);
	}

}







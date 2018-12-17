package com.ripcitysoftware.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	S3StorageService createStorageService() {
		S3StorageService storageService = new S3StorageService();
		storageService.init();

		return storageService;
	}
}


package com.example.abhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.*" })
public class FeedsApplication {

	public static void main(String[] args) {
		loadData();
		SpringApplication.run(FeedsApplication.class, args);
	}

	private static void loadData() {
		
	}

}

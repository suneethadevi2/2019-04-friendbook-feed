package com.example.abhi;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.*" })
public class FeedsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FeedsApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", args[0]));
		app.run(args);
		System.out.println(args[0]);
	}


}

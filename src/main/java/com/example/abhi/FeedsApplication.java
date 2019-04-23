package com.example.abhi;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.*" })
public class FeedsApplication {

	public static void main(String[] args) {
		String port = "8080";
		SpringApplication app = new SpringApplication(FeedsApplication.class);
		if(args != null && args.length > 0)
		{
			port = args[0];
		}
		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(port);
		System.out.println(port);
	}


}

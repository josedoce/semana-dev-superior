package com.sds.bootcamp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdsApplication.class, args);
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "funcionando";
	}
}

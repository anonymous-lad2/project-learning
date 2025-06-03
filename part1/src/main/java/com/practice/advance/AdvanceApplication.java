package com.practice.advance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AdvanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvanceApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam (value = "name", required = false) String name) {
		return "hello " + (name != null && !name.isEmpty() ? name : " ");
	}
}

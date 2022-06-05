package com.example.waslatask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/main")
public class WaslaTask1Application {

	public static void main(String[] args) {
		SpringApplication.run(WaslaTask1Application.class, args);
	}

	@GetMapping("/hi")
	public String hi(){
		return "hi";
	}

}

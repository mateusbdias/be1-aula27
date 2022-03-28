package com.dhbrasil.springboot.aula21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Aula21Application {

	public static void main(String[] args) {
		SpringApplication.run(Aula21Application.class, args);
	}

	/*@GetMapping
	public String Ola() {
		return "CTD 2022 c/ Spring Boot!";
	}*/

}

package com.TLCN;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectTlcnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTlcnApplication.class, args);
		System.out.println("Run success!!!");
	}

}

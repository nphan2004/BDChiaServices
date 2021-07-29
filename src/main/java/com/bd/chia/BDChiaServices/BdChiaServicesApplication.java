package com.bd.chia.BDChiaServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({ "com.bd.chia"})
public class BdChiaServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BdChiaServicesApplication.class, args);
	}
}

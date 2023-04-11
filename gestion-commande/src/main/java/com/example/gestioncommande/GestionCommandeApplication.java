package com.example.gestioncommande;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestionCommandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCommandeApplication.class, args);
	}

}

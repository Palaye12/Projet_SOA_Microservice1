package com.example.gestionproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestionProduitApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionProduitApplication.class, args);
	}

}

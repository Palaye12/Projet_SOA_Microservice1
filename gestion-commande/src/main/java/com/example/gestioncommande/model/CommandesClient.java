package com.example.gestioncommande.model;

import lombok.Data;

import java.util.List;

@Data
public class CommandesClient {
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private List<Commande> cmdsClient;

}


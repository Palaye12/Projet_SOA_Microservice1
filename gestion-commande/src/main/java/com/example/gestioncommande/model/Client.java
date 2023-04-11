package com.example.gestioncommande.model;

import lombok.Data;

@Data
    public class Client {
        private Long idClient;
        private String prenom;
        private String nom;
        private String adresse;
        private String tel;
}

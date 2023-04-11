package com.example.gestionclient.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Client {
    @Id
    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String prenom;
    private String nom;
    private String adresse;
    private String tel;
}

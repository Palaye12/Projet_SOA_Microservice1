package com.example.gestioncommande.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCmd;
    private String codeCmd ;
    private String codeProd;
    private Long idClient;
    private Date dateCmd;
    private int quantite;
    private double prix;
    private String nomClient;
    private String prenomClient;
    private String adrClient;
    private String telClient;
    private String nomProd;
    private double prixTotal;

}

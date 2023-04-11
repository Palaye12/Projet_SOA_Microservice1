package com.example.gestionproduit.model;

import lombok.Data;

import java.util.Date;
@Data
public class Commande {

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

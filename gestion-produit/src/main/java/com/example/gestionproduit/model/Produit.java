package com.example.gestionproduit.model;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Produit {
    @Id
    @Column(unique=true)
    private String  codeProd;
    private String nomProd;
    private double prix;

}

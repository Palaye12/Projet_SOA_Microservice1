package com.example.gestionproduit.model;

import lombok.Data;


import java.util.List;


@Data
public class ProduitCommandes {
    private Long idClient;
    private List<ProdCmd> produitsCmd;

}

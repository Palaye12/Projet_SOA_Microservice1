package com.example.gestionproduit.controller;

import com.example.gestionproduit.exception.InvalidProduitException;
import com.example.gestionproduit.exception.ProduitInconnuException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    private static final Map<String, String> error = new HashMap<>();

    /**
     * fonction appelée pour gerer l'exception concernant l'ajout d'un produit invalide
     * @param exception
     * @return
     */
    @ExceptionHandler(InvalidProduitException.class)
    public ResponseEntity handleInvalidProduitException(InvalidProduitException exception) {
        error.put("erreur", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    /**
     * fonction appelée pour gerer l'exception concernant un produit inconnu de la base de données lors de la modification ou de la suppression
     * @param exception
     * @return
     */
    @ExceptionHandler(ProduitInconnuException.class)
    public ResponseEntity handleProduitInconnuException(ProduitInconnuException exception) {
        error.put("erreur", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}

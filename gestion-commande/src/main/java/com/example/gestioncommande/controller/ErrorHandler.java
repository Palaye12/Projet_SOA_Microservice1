package com.example.gestioncommande.controller;

import com.example.gestioncommande.Exception.CmdInconnuException;
import com.example.gestioncommande.Exception.InvalidCmdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {

    private static final Map<String, String> error = new HashMap<>();

    /**
     * fonction appelée pour gerer l'exception concernant l'ajout d'une commande invalide
     * @param exception
     * @return
     */
    @ExceptionHandler(InvalidCmdException.class)
    public ResponseEntity handleInvalidCmdException(InvalidCmdException exception) {
        error.put("erreur", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    /**
     * fonction appelée pour gerer l'exception concernant un client inconnu de la base de données lors de la modification ou de la suppression
     * @param exception
     * @return
     */
    @ExceptionHandler(CmdInconnuException.class)
    public ResponseEntity handleProduitInconnuException(CmdInconnuException exception) {
        error.put("erreur", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}

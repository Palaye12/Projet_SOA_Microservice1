package com.example.gestionclient.controller;

import com.example.gestionclient.exception.InvalidClientException;
import com.example.gestionclient.exception.ClientInconnuException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    private static final Map<String, String> error = new HashMap<>();

    /**
     * fonction appelée pour gerer l'exception concernant l'ajout d'un client invalide
     * @param exception
     * @return
     */
    @ExceptionHandler(InvalidClientException.class)
    public ResponseEntity handleInvalidClientException(InvalidClientException exception) {
        error.put("erreur", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    /**
     * fonction appelée pour gerer l'exception concernant un client inconnu de la base de données lors de la modification ou de la suppression
     * @param exception
     * @return
     */
    @ExceptionHandler(ClientInconnuException.class)
    public ResponseEntity handleClientInconnuException(ClientInconnuException exception) {
        error.put("erreur", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}

package com.example.gestionclient.exception;


/**
 * Exception lancée si on essaie une modification sur un client qui n'existe pas
 */
public class ClientInconnuException extends Exception {
    public ClientInconnuException(String message) {
        super(message);
    }
}

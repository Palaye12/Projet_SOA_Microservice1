package com.example.gestionclient.controller;

import com.example.gestionclient.model.Client;
import com.example.gestionclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestionclient.exception.*;

import java.util.List;

/**
 * Rest controller permettant d'exposer le service de gestion des clients
 */
@RestController
@RequestMapping("client")
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientService clientService;

    /**
     * Recuperation de la liste des clients
     * @return Liste des clients
     */
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClient() {
        return ResponseEntity.ok(clientService.getAllClient());
    }

    /**
     * recuperation client par son ID
     * @param idClient
     * @return
     */
    @GetMapping("/{idClient}")
    public ResponseEntity<Client> getClientByIdClient(
            @PathVariable("idClient") Long idClient) {
        return ResponseEntity.ok(clientService.getClientById(idClient));
    }

    /**
     * Ajout d'un nouveau client
     * @param client
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws InvalidClientException
     */
    @PostMapping
    public ResponseEntity ajouterClient(
            @RequestBody  Client client) throws InvalidClientException {
        clientService.ajouterNouveauClient(client);
        return ResponseEntity.ok().build();
    }


    /**
     * modification d'un client
     * @param client
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws ClientInconnuException
     */
    @PutMapping
    public ResponseEntity modifierClient(
            @RequestBody  Client client) throws ClientInconnuException {
        clientService.updateClient(client);
        return ResponseEntity.ok().build();
    }


    /**
     * suppression d'un client
     * @param idCli
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws ClientInconnuException
     */
    @DeleteMapping("/{idClient}")
    public ResponseEntity supprimerClient(
            @PathVariable("idClient") Long idCli
    ) throws ClientInconnuException {
        clientService.supprimerClient(idCli);
        return ResponseEntity.ok().build();
    }


}

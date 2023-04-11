package com.example.gestioncommande.controller;

import com.example.gestioncommande.Exception.CmdInconnuException;
import com.example.gestioncommande.Exception.InvalidCmdException;
import com.example.gestioncommande.model.Commande;
import com.example.gestioncommande.model.CommandesClient;
import com.example.gestioncommande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("commande")
@CrossOrigin("*")
public class CommandeController {
    @Autowired
    private CommandeService cmdServ;

    /**
     * Recuperation de la liste des commandes
     * @return Liste des commandes
     */
    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommande() {
        return ResponseEntity.ok(cmdServ.getAllCommande());
    }

    /**
     * recuperation commande par son ID
     * @param idCmd
     * @return
     */
    @GetMapping("/{idCmd}")
    public ResponseEntity<Commande> getCommandeById(
            @PathVariable("idCmd") Long idCmd) {
        return ResponseEntity.ok(cmdServ.getCommandeById(idCmd));
    }

    /**
     * Ajout d'une nouvelle commande
     * @param commande
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws InvalidCmdException
     */
    @PostMapping
    public ResponseEntity ajouterCommande(
            @RequestBody Commande commande) throws Exception {
        cmdServ.ajoutCommande(commande);
        return ResponseEntity.ok().build();
    }


    /**
     * recuperation de l'ensemble des commandes d'un client par son ID
     * @param idClient
     * @return
     */
    @GetMapping("/commandes-client/{idClient}")
    public ResponseEntity <CommandesClient> getCommandeByIdClient(
            @PathVariable("idClient") Long idClient) {
        return ResponseEntity.ok(cmdServ.getCmdsClient(idClient));
    }


    /**
     * recuperation de l'ensemble des commandes par code commande
     * @param codeCmd
     * @return
     */
    @GetMapping("/commandes-produit/{codeCmd}")
    public ResponseEntity <List<Commande>> getCommandesByCodeCmd(
            @PathVariable("codeCmd") String codeCmd) {
        return ResponseEntity.ok(cmdServ.getCommandesByCode(codeCmd));
    }

    /**
     * modification d'une commande
     * @param commande
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws CmdInconnuException
     */
    @PutMapping
    public ResponseEntity modifierCommande(
            @RequestBody  Commande commande) throws Exception {
        cmdServ.updateCommande(commande);
        return ResponseEntity.ok().build();
    }


    /**
     * suppression d'une commande
     * @param idCmd
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws CmdInconnuException
     */
    @DeleteMapping("/{idCmd}")
    public ResponseEntity supprimerCommande(
            @PathVariable("idCmd") Long idCmd
    ) throws CmdInconnuException {
        cmdServ.supprimerCommande(idCmd);
        return ResponseEntity.ok().build();
    }

    /**
     * Recuperation de la dernière commande
     * @return commande
     */
    @GetMapping("/last-cmd")
    public ResponseEntity <Commande> getLastCommande() {
        return ResponseEntity.ok(cmdServ.getDernierCommande());
    }



}

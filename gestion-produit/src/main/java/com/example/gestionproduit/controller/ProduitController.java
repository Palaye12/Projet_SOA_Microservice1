package com.example.gestionproduit.controller;

import com.example.gestionproduit.exception.InvalidProduitException;
import com.example.gestionproduit.model.Commande;
import com.example.gestionproduit.model.ProdCmd;
import com.example.gestionproduit.model.Produit;
import com.example.gestionproduit.model.ProduitCommandes;
import com.example.gestionproduit.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.gestionproduit.exception.*;

@RestController
@RequestMapping("produit")
@CrossOrigin("*")
public class ProduitController {
    @Autowired
    private ProduitService prodServ;

    /**
     * Recuperation de la liste des produits
     * @return Liste des produits
     */
    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProduit() {
        return ResponseEntity.ok(prodServ.getAllProduit());
    }

    /**
     * recuperation produit par son ID
     * @param codeProd
     * @return
     */
    @GetMapping("/{codeProd}")
    public ResponseEntity<Produit> getProduitById(
            @PathVariable("codeProd") String codeProd) {
        return ResponseEntity.ok(prodServ.getProduitById(codeProd));
    }

    /**
     * Ajout d'un nouveau produit
     * @param produit
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws InvalidProduitException
     */
    @PostMapping
    public ResponseEntity ajouterProduit(
            @RequestBody  Produit produit) throws InvalidProduitException {
        prodServ.ajoutNewProd(produit);
        return ResponseEntity.ok().build();
    }


    /**
     * Ajout des produits commandés
     * @param pros
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws .InvalidProduitException
     */
    @PostMapping("/commandes-produit")
    public ResponseEntity ajouterCommandes(
            @RequestBody ProduitCommandes pros) throws InvalidProduitException {
        prodServ.passerCommande(pros);
        return ResponseEntity.ok().build();
    }

    /**
     * modification d'un produit
     * @param produit
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws .ProduitInconnuException
     */
    @PutMapping
    public ResponseEntity modifierProd(
            @RequestBody  Produit produit) throws ProduitInconnuException {
        prodServ.updateProd(produit);
        return ResponseEntity.ok().build();
    }

    /**
     * modification d'une commande
     * @param commande
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws .InvalidProduitException
     */
    @PutMapping("/commandes-produit")
    public ResponseEntity modifierCommande(
            @RequestBody  Commande commande) throws InvalidProduitException {
        prodServ.modifierCommande(commande);
        return ResponseEntity.ok().build();
    }

    /**
     * liste des produits commandés
     * @param codeCmd
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     */
    @GetMapping("/commandes-produit/{codeCmd}")
    public ResponseEntity getProdsCommande(
            @PathVariable("codeCmd") String codeCmd) {

        return ResponseEntity.ok(prodServ.getAllProduitCommande(codeCmd));
    }

    /**
     * liste des  commandes
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     */
    @GetMapping("/code-commandes")
    public ResponseEntity getcodesCommande() {

        return ResponseEntity.ok(prodServ.getAllCodesClient());
    }


    /**
     * suppression d'un produit
     * @param codeProd
     * @return reponse vide avec un statut 200 pour indiquer que tout s'est bien passé
     * @throws ProduitInconnuException
     */
    @DeleteMapping("/{codeProd}")
    public ResponseEntity supprimerProduit(
            @PathVariable("codeProd") String codeProd
    ) throws ProduitInconnuException {
        prodServ.deleteProd(codeProd);
        return ResponseEntity.ok().build();
    }





}

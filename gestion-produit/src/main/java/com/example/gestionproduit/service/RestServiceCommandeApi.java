package com.example.gestionproduit.service;

import com.example.gestionproduit.model.Commande;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8082/commande", name = "commande-service-api")
public interface RestServiceCommandeApi {


        @PostMapping
        Commande ajouterCommande(@RequestBody Commande commande);

        @PutMapping
         Commande modifierCommande(@RequestBody Commande commande);

        @RequestMapping(method = RequestMethod.GET, value = "/last-cmd")
        Commande getLastCommande();

    @RequestMapping(method = RequestMethod.GET, value = "/commandes-produit/{codeCmd}")
    ResponseEntity <List<Commande>> getCommandesByCodeCmd(@PathVariable("codeCmd") String codeCmd);


    @RequestMapping(method = RequestMethod.GET, value = "/all")
    List<Commande> getAllCommande();

    }

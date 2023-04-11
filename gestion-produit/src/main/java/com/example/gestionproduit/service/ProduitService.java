package com.example.gestionproduit.service;

import com.example.gestionproduit.model.*;
import com.example.gestionproduit.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestionproduit.exception.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository prodRepository;
    @Autowired
    RestServiceCommandeApi restServiceCommandeApi;
    public static String code;

    /**
     * Ajout d'un produit
     * @param produit produit à ajouter
     * @throws InvalidProduitException
     */
    public void ajoutNewProd(Produit produit) throws InvalidProduitException {
        if (validationProduit(produit)) {
            prodRepository.save(produit);
        } else throw new InvalidProduitException("informations invalides ou produit déjà existant dans la base de données");

    }
    /**
     * Validation d'un produit en verifiant si le code du produit est déjà enregistré
     * @param produit
     * @return boolean booleen permettant de savoir ou pas si l'objet produit reçu est valide.
     */
    private boolean validationProduit(Produit produit) {
        /*
        verifier que l'objet envoyé n'est pas null, mais aussi que l'id (codeProduit) donné n'est pas null ou vide
         */
        return produit != null &&
                produit.getCodeProd() != null &&
                !produit.getCodeProd().isEmpty() &&
                !prodRepository.existsById(produit.getCodeProd());
    }

    /**
     * Modification d'un produit
     * @param produit produit à modifier
     */
    public void updateProd(Produit produit) throws ProduitInconnuException {
        if (!prodRepository.existsById(produit.getCodeProd()) ) {
            throw new ProduitInconnuException("Ce produit n'existe pas dans la base de données");
        }
        prodRepository.save(produit);
    }


    /**
     * Suppression d'un produit par son identifiant (code produit)
     * @param codeProd id produit
     * @throws ProduitInconnuException
     */
    public void deleteProd(String codeProd) throws ProduitInconnuException {
        if (!prodRepository.existsById(codeProd)) {
            throw new ProduitInconnuException("Ce produit n'existe pas dans la base de données");
        }
        prodRepository.deleteById(codeProd);
    }

    /**
     * recupération des produits enregistrés dans la bdd
     * @return liste produits enregistrés
     */
    public List<Produit> getAllProduit() {
        return prodRepository.findAll();
    }

    /**
     * Recuperation d'un produit par son ID (code produit)
     * @param codeProd code du produit
     * @return produit si trouvé, sinon null
     */
    public Produit getProduitById(String codeProd) {
        return prodRepository.findByCodeProd(codeProd);
    }





    /**
     * ajouter commande produit
     * @param prodCmd
     * @throws InvalidProduitException
     */
    public void passerCommande(ProduitCommandes prodCmd) throws InvalidProduitException{
        //appel du microservice commandes grâce à Feign et on récupère la dernière commande créée
            Commande dernierCmd =restServiceCommandeApi.getLastCommande();
            if(dernierCmd== null){
               code= "CM1";
            }
            else {

                int codeCmd= parseInt( dernierCmd.getCodeCmd().substring(2))+1;
                code= "CM"+Integer.toString(codeCmd);

            }



                for (ProdCmd prod: prodCmd.getProduitsCmd()
                ) {
                    Commande commande = new Commande();

                    Produit produit = this.getProduitById(prod.getCodeProd());

                    //On renseigne les propriétés de l'objet de type Commande que nous avons crée
                    commande.setCodeProd(produit.getCodeProd());
                    commande.setQuantite(prod.getQuantite());
                    commande.setPrix(produit.getPrix());
                    commande.setIdClient(prodCmd.getIdClient());
                    commande.setDateCmd(new Date());
                    commande.setCodeCmd(code);
                    commande.setNomProd(produit.getNomProd());
                    //appel du microservice commandes grâce à Feign et on récupère en retour les détails de la commande créée, notamment son ID (étape 4).
                    restServiceCommandeApi.ajouterCommande(commande);
                }





    }


    /**
     * modifier commande produit
     * @param commande
     * @throws InvalidProduitException
     */
    public void modifierCommande(Commande commande) throws InvalidProduitException {



            //On renseigne les propriétés de l'objet de type Commande que nous avons crée



                Produit produit = this.getProduitById(commande.getCodeProd());

                //On renseigne les propriétés de l'objet de type Commande que nous avons crée
                commande.setCodeProd(produit.getCodeProd());
                commande.setQuantite(commande.getQuantite());
                commande.setPrix(produit.getPrix());
                commande.setIdClient(commande.getIdClient());
                commande.setDateCmd(new Date());
                commande.setNomProd(produit.getNomProd());
                //appel du microservice commandes grâce à Feign et on récupère en retour les détails de la commande créée, notamment son ID (étape 4).
                restServiceCommandeApi.modifierCommande(commande);




        }


    /**
     * recupération des produits commandés
     * @return liste commandes enregistrés
     */
    public List<Commande> getAllProduitCommande(String codeCmd) {
       return  restServiceCommandeApi.getCommandesByCodeCmd(codeCmd).getBody();
    }


    /**
     * recupération des codes commandes et des clients
     * @return liste commandes enregistrés
     */
    public List<CodesClient> getAllCodesClient() {
       List<Commande> commandes = restServiceCommandeApi.getAllCommande();
       List<CodesClient> codesClients=  new ArrayList<CodesClient>();
        for (Commande cmd: commandes
        ) {
           CodesClient code =new CodesClient();
           code.setCodeCmd(cmd.getCodeCmd());
           code.setNomClient(cmd.getNomClient());
           code.setPrenomClient(cmd.getPrenomClient());
           if(!codesClients.contains(code) || codesClients.isEmpty()){
            codesClients.add(code);
           }
        }
        return codesClients;
    }



    }

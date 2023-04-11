package com.example.gestioncommande.service;

import com.example.gestioncommande.Exception.CmdInconnuException;
import com.example.gestioncommande.Exception.InvalidCmdException;
import com.example.gestioncommande.model.Client;
import com.example.gestioncommande.model.Commande;
import com.example.gestioncommande.model.CommandesClient;
import com.example.gestioncommande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    CommandeRepository cmdRepository;

    @Autowired
    RestServiceClienApi restSerCliApi;

    /**
     * Ajout d'une commande pour un client existant
     * @param commande
     * @throws Exception
     */
    public void ajoutCommande(Commande commande) throws Exception {
        if (commande.getIdClient()== null) {
            throw new InvalidCmdException("L'id du client ne doit pas etre null");
        }
        ResponseEntity<Client> response = restSerCliApi.getClientByIdClient(commande.getIdClient());
        Client client = response.getBody();
        if (client == null) {
            throw new CmdInconnuException("Client non enregistré dans la base de données, le client n'est pas inscrit ");
        }
        Commande  cmd  = new Commande();
            cmd.setCodeProd(commande.getCodeProd());
            cmd.setQuantite(commande.getQuantite());
            cmd.setPrix(commande.getPrix());
            cmd.setIdClient(commande.getIdClient());
            cmd.setDateCmd(commande.getDateCmd());
            cmd.setCodeCmd(commande.getCodeCmd());
            cmd.setAdrClient(client.getAdresse());
            cmd.setNomClient(client.getNom());
            cmd.setPrenomClient(client.getPrenom());
            cmd.setTelClient(client.getTel());
            cmd.setNomProd(commande.getNomProd());
            cmd.setPrixTotal(commande.getPrix() * commande.getQuantite());
        cmdRepository.save(cmd);
    }

    /**
     * Mise à jour de la commande d'un client
     * @param cmd
     * @throws Exception
     */
    public void updateCommande(Commande cmd) throws Exception {

        if (cmd.getIdCmd() == null) {
            throw new InvalidCmdException("Le numero de la commande ne doit pas etre null");
        }

        if (!cmdRepository.existsById(cmd.getIdCmd())) {
            throw new CmdInconnuException("Aucune commande enregistré pour ce numero de commande ");
        }
        if (cmd.getIdClient()== null) {
            throw new InvalidCmdException("L'id du client ne doit pas etre null");
        }
        ResponseEntity<Client> response = restSerCliApi.getClientByIdClient(cmd.getIdClient());
        Client client = response.getBody();
        if (client == null) {
            throw new CmdInconnuException("Client non enregistré dans la base de données, le client n'est pas inscrit ");
        }

        Commande commande = cmdRepository.findByIdCmd(cmd.getIdCmd());
            commande.setIdClient(cmd.getIdClient());
            commande.setQuantite(cmd.getQuantite());
            commande.setPrix(cmd.getPrix());
            commande.setPrixTotal(cmd.getPrix()*cmd.getQuantite());
            commande.setAdrClient(client.getAdresse());
            commande.setNomClient(client.getNom());
            commande.setPrenomClient(client.getPrenom());
            commande.setTelClient(client.getTel());
            commande.setNomProd(cmd.getNomProd());
          
        cmdRepository.save(commande);
        List<Commande> commandes= this.getCommandesByCode(cmd.getCodeCmd());
        for (Commande cmdCode: commandes
        ) {
            cmdCode.setIdClient(cmd.getIdClient());

            cmdCode.setAdrClient(client.getAdresse());
            cmdCode.setNomClient(client.getNom());
            cmdCode.setPrenomClient(client.getPrenom());
            cmdCode.setTelClient(client.getTel());
            cmdRepository.save(cmdCode);
        }
    }


    /**
     * suppression d'une moyenne via l'ID de la commande
     * @param idCmd
     * @throws CmdInconnuException
     */
    public void supprimerCommande(long idCmd) throws CmdInconnuException {
        if (!cmdRepository.existsById(idCmd)) {
            throw new CmdInconnuException("Aucune commande enregistré pour ce numero de commande ");
        }
        cmdRepository.deleteById(idCmd);
    }

    /**
     * Recuperation de la liste des commandes
     * @return
     */
    public List<Commande> getAllCommande() {
        return cmdRepository.findAll();
    }

    /**
     * Recuperation de la commande via son ID
     * @param idCmd
     * @return
     */
    public Commande getCommandeById(Long idCmd) {
        return cmdRepository.findByIdCmd(idCmd);
    }

     /**
     * Recuperation de l'ensemble des commandes commande via idClient
     * @param idClient
     * @return
     */
    public List<Commande> getCommandeByClient(Long idClient) {
        return cmdRepository.findAllByIdClient(idClient);
    }

    public List<Commande> getCommandesByCode(String codeCmd) {
        return cmdRepository.findAllByCodeCmd(codeCmd);
    }


    public CommandesClient getCmdsClient(Long idClient){
        CommandesClient cmdcli = new CommandesClient();
        List<Commande> commandes= this.getCommandeByClient(idClient);
        List<Commande> cmdsCli = null;
        ResponseEntity<Client> response = restSerCliApi.getClientByIdClient(idClient);
        Client client = response.getBody();
        for (Commande cmd: commandes
             ) {
            Commande coCli = new Commande();
            coCli.setIdCmd(cmd.getIdCmd());
            coCli.setCodeCmd(cmd.getCodeCmd());
            coCli.setNomProd(cmd.getNomProd());
            coCli.setPrix(cmd.getPrix());
            coCli.setQuantite(cmd.getQuantite());
            coCli.setPrixTotal(cmd.getPrixTotal());
            cmdsCli.add(coCli);

        }
        cmdcli.setCmdsClient(cmdsCli);
        cmdcli.setIdClient(idClient);
        cmdcli.setNomClient(client.getNom());
        cmdcli.setPrenomClient(client.getPrenom());
        return cmdcli;
    }

    /**
     * Recuperation de la dernière commande
     * @return
     */
    public Commande getDernierCommande() {
        return cmdRepository.findFirstByOrderByIdCmdDesc();
    }

}

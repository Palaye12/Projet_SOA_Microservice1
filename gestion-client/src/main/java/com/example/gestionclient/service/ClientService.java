package com.example.gestionclient.service;

import com.example.gestionclient.model.Client;
import com.example.gestionclient.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestionclient.exception.*;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * Ajout d'un client
     * @param client client à ajouter
     * @throws InvalidClientException
     */
    public void ajouterNouveauClient(Client client) throws InvalidClientException {
        if (validationClient(client)) {
            clientRepository.save(client);
        } else throw new InvalidClientException("informations invalides ou client déjà existant dans la base de données");

    }


    /**
     * Modification d'un client
     * @param client client à modifier
     */
    public void updateClient(Client client) throws ClientInconnuException {
        if (!clientRepository.existsById(client.getIdClient())) {
            throw new ClientInconnuException("Ce client n'existe pas dans la base de données");
        }
        clientRepository.save(client);
    }


    /**
     * Suppression d'un client par son identifiant (id client)
     * @param idClient id client
     * @throws ClientInconnuException
     */
    public void supprimerClient(Long idClient) throws ClientInconnuException {
        if (!clientRepository.existsById(idClient)) {
            throw new ClientInconnuException("Ce client n'existe pas dans la base de données");
        }
        clientRepository.deleteById(idClient);
    }

    /**
     * recupération des clients enregistrés dans la bdd
     * @return liste clients enregistrés
     */
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    /**
     * Recuperation d'un client par son ID (id client)
     * @param idClient id du client
     * @return client si trouvé, sinon null
     */
    public Client getClientById(Long idClient) {
        return clientRepository.findByIdClient(idClient);
    }


    /**
     * Validation d'un client en verifiant si l'id' est déjà enregistré pour un autre client
     * @param client
     * @return boolean booleen permettant de savoir ou pas si l'objet client reçu est valide.
     */
    private boolean validationClient(Client client) {
        /*
        verifier que l'objet envoyé n'est pas null, mais aussi que l'id (numCarte) donné n'est pas null ou vide
         */
        return client != null  &&
                !clientRepository.existsById(client.getIdClient());
    }
}

package com.example.gestioncommande.service;

import com.example.gestioncommande.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "client-api", url = "http://localhost:8081/client")

public interface RestServiceClienApi {



        @RequestMapping(method = RequestMethod.GET, value = "/all")
        ResponseEntity<List<Client>> getAllClient();

        @RequestMapping(method = RequestMethod.GET, value = "/{idClient}")
        ResponseEntity<Client> getClientByIdClient(@PathVariable("idClient") Long idClient);


}

package com.example.gestionclient.repository;

import com.example.gestionclient.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
   Client findByIdClient(Long idClient);
}

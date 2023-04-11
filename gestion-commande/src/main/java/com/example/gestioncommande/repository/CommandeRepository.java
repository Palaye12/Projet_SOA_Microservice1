package com.example.gestioncommande.repository;

import com.example.gestioncommande.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    Commande findByIdCmd(Long idCmd);
    List<Commande> findAllByIdClient(Long idClient);
    List<Commande> findAllByCodeCmd(String codeCmd);
    Commande findFirstByOrderByIdCmdDesc();
}

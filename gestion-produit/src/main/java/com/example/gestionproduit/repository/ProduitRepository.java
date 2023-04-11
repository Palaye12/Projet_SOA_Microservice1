package com.example.gestionproduit.repository;

import com.example.gestionproduit.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,String> {
    Produit findByCodeProd(String codeProd);
}

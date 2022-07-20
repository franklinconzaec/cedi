package com.franklinconza.cedi.repository;

import com.franklinconza.cedi.model.Ponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonenteRepository extends JpaRepository<Ponente, Integer> {

}
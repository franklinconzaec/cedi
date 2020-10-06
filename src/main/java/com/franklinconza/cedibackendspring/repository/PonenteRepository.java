package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Ponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonenteRepository extends JpaRepository<Ponente, Integer> {

}
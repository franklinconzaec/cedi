package com.franklinconza.cedi.repository;

import com.franklinconza.cedi.model.Ponencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonenciaRepository extends JpaRepository<Ponencia, Integer> {

}
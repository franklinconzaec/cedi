package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Ponencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonenciaRepository extends JpaRepository<Ponencia, Integer> {

}
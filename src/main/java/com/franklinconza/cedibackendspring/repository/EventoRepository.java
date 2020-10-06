package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
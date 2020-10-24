package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

}
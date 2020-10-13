package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {

    @Query("select p from Parametro p where p.id = :id")
    Parametro getById(@Param("id") Integer id);

}
package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    @Query("select distinct i, p from Inscripcion i left join i.ponentes p where upper(i.nombre) like upper(concat('%', :nombre, '%')) order by p.orden")
    List<Inscripcion> getByNombre(@Param("nombre") String nombre);

}
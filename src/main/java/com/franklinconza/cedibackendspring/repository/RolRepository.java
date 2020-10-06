package com.franklinconza.cedibackendspring.repository;

import com.franklinconza.cedibackendspring.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {

}
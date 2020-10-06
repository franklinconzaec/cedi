package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(schema = "certificados", name = "roles")
public class Rol {

    @Id
    private String id;

    private String descripcion;

    @OneToMany(mappedBy = "rol")
    private List<Inscripcion> inscripciones;

}
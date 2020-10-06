package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "certificados", name = "eventos")
public class Evento {

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;

    @OneToMany(mappedBy = "evento")
    private List<Inscripcion> inscripciones;

}
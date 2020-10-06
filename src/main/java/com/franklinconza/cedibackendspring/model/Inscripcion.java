package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "certificados", name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento")
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol")
    private Rol rol;

    private String nombre;

    @OneToMany(mappedBy = "inscripcion")
    private List<Ponente> ponentes;

}
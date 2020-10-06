package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "certificados", name = "ponentes")
public class Ponente {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscripcion")
    private Inscripcion inscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponencia")
    private Ponencia ponencia;

    private Integer orden;

}
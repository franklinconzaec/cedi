package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Certificado {

    @Id
    private String id;

    private String eventoid;

    private String evento;

    private String rolid;

    private String rol;

    private String participante;

    private String tema;

}
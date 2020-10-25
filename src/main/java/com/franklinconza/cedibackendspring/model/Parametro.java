package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "certificados", name = "parametros")
public class Parametro {

    @Id
    @GeneratedValue
    private Integer id;

    private String empresa;

    private String inicial;

}
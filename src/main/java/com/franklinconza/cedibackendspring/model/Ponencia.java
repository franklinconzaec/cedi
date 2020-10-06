package com.franklinconza.cedibackendspring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "certificados", name = "ponencias")
public class Ponencia {

    @Id
    @GeneratedValue
    private Integer id;

    private String tema;

    @OneToMany(mappedBy = "ponencia")
    private List<Ponente> ponentes;

}
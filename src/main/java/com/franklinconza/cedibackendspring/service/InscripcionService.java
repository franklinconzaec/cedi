package com.franklinconza.cedibackendspring.service;

import com.franklinconza.cedibackendspring.model.Inscripcion;

import java.util.List;

public interface InscripcionService {

    List<Inscripcion> getByNombre(String nombre);

}

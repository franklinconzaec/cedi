package com.franklinconza.cedibackendspring.service;

import com.franklinconza.cedibackendspring.model.Inscripcion;
import com.franklinconza.cedibackendspring.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    @Autowired
    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<Inscripcion> getByNombre(String nombre) {
        return inscripcionRepository.getByNombre(nombre);
    }
}
package com.franklinconza.cedi.service;

import com.franklinconza.cedi.model.Parametro;
import com.franklinconza.cedi.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParametroServiceImpl implements ParametroService {

    private final ParametroRepository parametroRepository;

    @Autowired
    public ParametroServiceImpl(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }

    public Parametro getById(Integer id) {
        return parametroRepository.getById(id);
    }

}
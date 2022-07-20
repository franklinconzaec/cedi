package com.franklinconza.cedi.service;

import com.franklinconza.cedi.model.Certificado;
import com.franklinconza.cedi.repository.CertificadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificadoServiceImpl implements CertificadoService {

    private final CertificadoRepository certificadoRepository;

    @Autowired
    public CertificadoServiceImpl(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public List<Certificado> getByNombre(String nombre) {
        return certificadoRepository.getByNombre(nombre);
    }

}
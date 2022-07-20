package com.franklinconza.cedi.service;

import com.franklinconza.cedi.model.Certificado;

import java.util.List;

public interface CertificadoService {

    List<Certificado> getByNombre(String nombre);

}

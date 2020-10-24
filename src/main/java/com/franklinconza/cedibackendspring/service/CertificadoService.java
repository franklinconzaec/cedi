package com.franklinconza.cedibackendspring.service;

import com.franklinconza.cedibackendspring.model.Certificado;
import com.franklinconza.cedibackendspring.model.Inscripcion;

import java.util.List;

public interface CertificadoService {

    List<Certificado> getByNombre(String nombre);

}

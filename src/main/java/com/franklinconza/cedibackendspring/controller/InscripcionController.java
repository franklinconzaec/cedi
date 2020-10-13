package com.franklinconza.cedibackendspring.controller;

import com.franklinconza.cedibackendspring.model.Inscripcion;
import com.franklinconza.cedibackendspring.model.Parametro;
import com.franklinconza.cedibackendspring.service.InscripcionService;
import com.franklinconza.cedibackendspring.service.ParametroService;
import com.franklinconza.cedibackendspring.service.ReporteService;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Controller
public class InscripcionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final ParametroService parametroService;
    private final InscripcionService inscripcionService;
    private final ReporteService reporteService;

    @Getter
    @Setter
    private String empresa;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private List<Inscripcion> inscripciones;

    @Getter
    @Setter
    private Inscripcion inscripcion;

    @Autowired
    public InscripcionController(ParametroService parametroService, InscripcionService inscripcionService, ReporteService reporteService) {
        this.parametroService = parametroService;
        this.inscripcionService = inscripcionService;
        this.reporteService = reporteService;
    }

    @PostConstruct
    public void postConstruct() {
        Parametro parametro = parametroService.getById(1);
        empresa = parametro.getEmpresa();
    }

    public void buscar() {
        inscripciones = inscripcionService.getByNombre(nombre);
    }

    public void descargarCertificado() throws IOException, JRException {
        reporteService.certificadoPdf(inscripcion);
    }

}
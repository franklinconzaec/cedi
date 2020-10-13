package com.franklinconza.cedibackendspring.controller;

import com.franklinconza.cedibackendspring.model.Inscripcion;
import com.franklinconza.cedibackendspring.service.InscripcionService;
import com.franklinconza.cedibackendspring.service.ReporteService;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Controller
public class InscripcionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final InscripcionService inscripcionService;
    private final ReporteService reporteService;

    //@Autowired
    //private InscripcionPonenciaService inscripcionPonenciaService;

    //@Autowired
    //private ReporteService reporteService;

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
    public InscripcionController(InscripcionService inscripcionService, ReporteService reporteService) {
        this.inscripcionService = inscripcionService;
        this.reporteService = reporteService;
    }

    public void buscar() {
        inscripciones = inscripcionService.getByNombre(nombre);
    }

    public void descargarCertificado() throws IOException, JRException {
        reporteService.certificadoPdf(inscripcion);
    }

}
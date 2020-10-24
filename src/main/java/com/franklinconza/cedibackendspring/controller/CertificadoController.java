package com.franklinconza.cedibackendspring.controller;

import com.franklinconza.cedibackendspring.model.Certificado;
import com.franklinconza.cedibackendspring.model.Parametro;
import com.franklinconza.cedibackendspring.service.CertificadoService;
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
public class CertificadoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final ParametroService parametroService;
    private final CertificadoService certificadoService;
    private final ReporteService reporteService;

    @Getter
    @Setter
    private String empresa;

    @Getter
    @Setter
    private String nombre;

    @Getter
    @Setter
    private List<Certificado> certificados;

    @Getter
    @Setter
    private Certificado certificado;

    @Autowired
    public CertificadoController(ParametroService parametroService, CertificadoService certificadoService, ReporteService reporteService) {
        this.parametroService = parametroService;
        this.certificadoService = certificadoService;
        this.reporteService = reporteService;
    }

    @PostConstruct
    public void postConstruct() {
        Parametro parametro = parametroService.getById(1);
        empresa = parametro.getEmpresa();
    }

    public void buscar() {
        certificados = certificadoService.getByNombre(nombre);
    }

    public void descargarCertificado() throws IOException, JRException {
        reporteService.certificadoPdf(certificado);
    }

}
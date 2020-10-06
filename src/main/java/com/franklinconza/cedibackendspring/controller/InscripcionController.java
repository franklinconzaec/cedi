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
        /*List<String> nombres = new ArrayList<>();
        Map<String, Object> parametro = new HashMap<String, Object>();
        String nombreReporte = null;
        String nombreCertificado = null;

        nombres.add("A");

        if (certificado.getPonenciaId() != null) {
            List<Ponente> ponentes = null;
            if (certificado.getEventoId() == 5 || certificado.getEventoId() == 19 || certificado.getEventoId() == 20)
                ponentes = inscripcionPonenciaService.obtenerLista(
                        "select ip from Ponente ip inner join ip.ponencia p inner join ip.inscripcion i where p.id=?1 and i.id=?2 order by ip.orden",
                        new Object[]{certificado.getPonenciaId(), certificado.getParticipanteId()}, 0, false,
                        new Object[]{});
            else
                ponentes = inscripcionPonenciaService.obtenerLista(
                        "select ip from Ponente ip inner join ip.ponencia p where p.id=?1 order by ip.orden",
                        new Object[]{certificado.getPonenciaId()}, 0, false, new Object[]{});

            int ix = 1;
            for (Ponente ip : ponentes)
                parametro.put("P" + ix++, ip.getInscripcion().getNombre().trim().toUpperCase());

            parametro.put("PONENCIA", certificado.getPonencia());
        } else
            parametro.put("NOMBRES", certificado.getParticipante().trim().toUpperCase());

        parametro.put("CODEQR", "I" + certificado.getParticipanteId()
                + (certificado.getPonenciaId() != null ? "_P" + certificado.getPonenciaId() : ""));

        nombreReporte = certificado.getReporte();

        nombreCertificado = "E" + certificado.getEventoId() + "_" + certificado.getTipoParticipanteId() + "_I"
                + certificado.getParticipanteId()
                + (certificado.getPonenciaId() != null ? "_P" + certificado.getPonenciaId() + "_" : "_") + "F"
                + UtilsDate.fechaFormatoString(new Date(), "ddMMyyyy-hhmmss");

        reporteService.generarReportePDF(nombres, parametro, nombreReporte, nombreCertificado);*/
        reporteService.certificadoPdf(inscripcion);
    }

}
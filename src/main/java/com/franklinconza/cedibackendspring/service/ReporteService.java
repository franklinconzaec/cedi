package com.franklinconza.cedibackendspring.service;

import com.franklinconza.cedibackendspring.model.Inscripcion;
import com.franklinconza.cedibackendspring.model.Ponente;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

    public <T> void certificadoPdf(Inscripcion inscripcion) throws IOException, JRException {

        Map<String, Object> parameters = new HashMap<>();

        if (inscripcion.getPonentes().get(0).getPonencia().getId() != null) {
            String ponentes = "";
            if (inscripcion.getEvento().getId() == 5 || inscripcion.getEvento().getId() == 19 || inscripcion.getEvento().getId() == 20)
                ponentes = inscripcion.getNombre();
            else
                for (Ponente ponente : inscripcion.getPonentes().get(0).getPonencia().getPonentes())
                    ponentes = ponentes.concat(ponente.getInscripcion().getNombre()).concat("\n");

            parameters.put("NOMBRES", ponentes);
            parameters.put("PONENCIA", inscripcion.getPonentes().get(0).getPonencia().getTema());
        } else
            parameters.put("NOMBRES", inscripcion.getNombre().trim().toUpperCase());

        parameters.put("CODEQR", "I" + inscripcion.getId()
                + (inscripcion.getPonentes() != null && inscripcion.getPonentes().size() > 0 ? "_P" + inscripcion.getPonentes().get(0).getPonencia().getId() : ""));

        File file;
        String nombreReporte = inscripcion.getEvento().getId() + inscripcion.getRol().getId() + ".jasper";
        if (System.getProperty("os.name").startsWith("Windows") || System.getProperty("os.name").startsWith("Mac")) {
            file = ResourceUtils.getFile("classpath:cedi/reports/" + nombreReporte);
        } else {
            file = new File("/app/src/main/resources/cedi/reports/" + nombreReporte);
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parameters, new JREmptyDataSource(1));

        String nombreCertificado = "E" + inscripcion.getEvento().getId() + "_" + inscripcion.getRol().getId() + "_I"
                + inscripcion.getId() + (inscripcion.getPonentes().get(0).getPonencia().getId() != null ? "_P" + inscripcion.getPonentes().get(0).getPonencia().getId() : "");
        respondeServidor(jasperPrint, nombreCertificado);
    }

    public void respondeServidor(JasperPrint jasperPrint, String nombreCertificado) throws JRException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=" + nombreCertificado + ".pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
    }

}
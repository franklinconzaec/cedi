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

    public void certificadoPdf(Inscripcion inscripcion) throws IOException, JRException {

        Map<String, Object> parameters = new HashMap<>();
        String codeqr = "I" + inscripcion.getId();

        if (inscripcion.getPonentes().size() > 0 && inscripcion.getPonentes().get(0).getPonencia().getId() != null) {
            String ponentes = "";
            if (inscripcion.getEvento().getId() == 5 || inscripcion.getEvento().getId() == 19 || inscripcion.getEvento().getId() == 20)
                ponentes = inscripcion.getNombre().toUpperCase();
            else
                for (Ponente ponente : inscripcion.getPonentes().get(0).getPonencia().getPonentes())
                    ponentes = ponentes.concat(ponente.getInscripcion().getNombre().toUpperCase()).concat("\n");

            if (inscripcion.getRol().getId().compareTo("PONE") == 0)
                codeqr = codeqr + "_P";
            else if (inscripcion.getRol().getId().compareTo("CONF") == 0)
                codeqr = codeqr + "_C";
            else if (inscripcion.getRol().getId().compareTo("INVE") == 0)
                codeqr = codeqr + "_D";

            codeqr = codeqr + inscripcion.getPonentes().get(0).getPonencia().getId();

            parameters.put("NOMBRES", ponentes);
            parameters.put("PONENCIA", inscripcion.getPonentes().get(0).getPonencia().getTema());
        } else
            parameters.put("NOMBRES", inscripcion.getNombre().trim().toUpperCase());

        parameters.put("CODEQR", "I" + codeqr);

        File file;
        String nombreReporte = inscripcion.getEvento().getId() + inscripcion.getRol().getId() + ".jasper";
        if (System.getProperty("os.name").startsWith("Windows") || System.getProperty("os.name").startsWith("Mac")) {
            file = ResourceUtils.getFile("classpath:cedi/reports/" + nombreReporte);
        } else {
            file = new File("/app/src/main/resources/cedi/reports/" + nombreReporte);
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parameters, new JREmptyDataSource(1));

        respondeServidor(jasperPrint, codeqr);
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
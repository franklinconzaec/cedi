package com.franklinconza.cedi.service;

import com.franklinconza.cedi.model.Certificado;
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

    public void certificadoPdf(Certificado certificado, String empresa) throws IOException, JRException {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("NOMBRES", certificado.getParticipante());
        parameters.put("CODEQR", certificado.getId());
        if (certificado.getTema() != null)
            parameters.put("PONENCIA", certificado.getTema());

        File file;
        String nombreReporte = certificado.getEventoid() + certificado.getRolid() + ".jasper";
        if (System.getProperty("os.name").startsWith("Windows") || System.getProperty("os.name").startsWith("Mac"))
            file = ResourceUtils.getFile("classpath:reports/cedi/" + empresa + "/reports/" + nombreReporte);
        else
            file = new File("/app/src/main/resources/reports/cedi/" + empresa + "/reports/" + nombreReporte);

        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parameters, new JREmptyDataSource(1));

        respondeServidor(jasperPrint, certificado.getId());
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
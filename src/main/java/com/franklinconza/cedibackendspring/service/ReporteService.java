package com.franklinconza.cedibackendspring.service;

import com.franklinconza.cedibackendspring.model.Inscripcion;
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
        File file = ResourceUtils.getFile("classpath:cedi/reports/" + inscripcion.getEvento().getId() + inscripcion.getRol().getId() + ".jasper");
        //InputStream reportStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(
        //        "classpath:cedi/reports/" + inscripcion.getEvento().getId() + inscripcion.getRol().getId() + ".jasper");

        String pathToMyFile = null;
        if (!System.getProperty("os.name").startsWith("Windows")) {
            file = ResourceUtils.getFile("classpath:cedi/reports/" + inscripcion.getEvento().getId() + inscripcion.getRol().getId() + ".jasper");
        } else {
            file = ResourceUtils.getFile(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes") + "/" + inscripcion.getEvento().getId() + inscripcion.getRol().getId() + ".jasper");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("NOMBRES", inscripcion.getNombre().trim().toUpperCase());
        parameters.put("CODEQR", "I" + inscripcion.getId()
                + (inscripcion.getPonentes() != null && inscripcion.getPonentes().size() > 0 ? "_P" + inscripcion.getPonentes().get(0).getPonencia().getId() : ""));

        JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parameters, new JREmptyDataSource(1));
        //JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, new JREmptyDataSource(1));

        respondeServidor(jasperPrint, inscripcion.getId().toString());
    }

    public void respondeServidor(JasperPrint jasperPrint, String nombreReporte) throws JRException, IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=" + nombreReporte + ".pdf");

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
    }

}
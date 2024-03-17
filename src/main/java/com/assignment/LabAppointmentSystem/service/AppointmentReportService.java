package com.assignment.LabAppointmentSystem.service;

import com.assignment.LabAppointmentSystem.model.Appointment;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppointmentReportService {

    public byte[] generateAppointmentReport(List<Appointment> appointments) throws JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/AppointmentReport.jrxml"));




        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(appointments);


        Map<String, Object> parameters = null;


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

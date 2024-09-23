package com.aarinc.randevusistemi.service;

import com.aarinc.randevusistemi.exception.ResourceNotFoundException;
import com.aarinc.randevusistemi.model.Appointment;
import com.aarinc.randevusistemi.model.Business;
import com.aarinc.randevusistemi.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;

    public AppointmentService(AppointmentRepository appointmentRepository, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.emailService = emailService;
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        emailService.sendAppointmentCreatedEmail(appointment.getUser(), appointment.getBusiness(), appointment);
    }

    public void approveAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Randevu bulunamadı! ID: " + id));
        appointment.setApproved(true);
        appointmentRepository.save(appointment);
        emailService.sendAppointmentApprovedEmail(appointment.getUser(), appointment.getBusiness(), appointment);
    }

    public List<Appointment> getAppointmentsForBusiness(Business business) {
        return appointmentRepository.findByBusiness(business);
    }
}

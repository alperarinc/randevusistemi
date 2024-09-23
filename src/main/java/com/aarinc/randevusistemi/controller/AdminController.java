package com.aarinc.randevusistemi.controller;

import com.aarinc.randevusistemi.service.AppointmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AppointmentService appointmentService;

    public AdminController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        // İşletme ve randevu bilgilerini buradan yöneteceksin.
        return "Admin dashboard data";
    }

    @PostMapping("/appointments/{id}/approve")
    public String approveAppointment(@PathVariable Long id) {
        appointmentService.approveAppointment(id);
        return "Randevu " + id + " onaylandı.";
    }
}

package com.aarinc.randevusistemi.controller;

import com.aarinc.randevusistemi.dto.AppointmentDTO;
import com.aarinc.randevusistemi.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AppointmentService appointmentService;

    // Admin dashboard'u için temel bir endpoint
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public String getDashboard() {
        // Dashboard'a özel bir veri döndürebilirsiniz
        return "Admin dashboard data";
    }

    // Admin tarafından randevu onaylama işlemi
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/appointments/{id}/approve")
    public String approveAppointment(@PathVariable Long id) {
        appointmentService.approveAppointment(id);
        return "Randevu " + id + " başarıyla onaylandı.";
    }

    // Tüm randevuları listelemek için (admin)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/appointments")
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
}

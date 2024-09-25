package com.aarinc.randevusistemi.service;

import com.aarinc.randevusistemi.dto.AppointmentDTO;
import com.aarinc.randevusistemi.exception.BusinessNotFoundException;
import com.aarinc.randevusistemi.exception.UserNotFoundException;
import com.aarinc.randevusistemi.model.Appointment;
import com.aarinc.randevusistemi.model.Business;
import com.aarinc.randevusistemi.model.User;
import com.aarinc.randevusistemi.repository.AppointmentRepository;
import com.aarinc.randevusistemi.repository.BusinessRepository;
import com.aarinc.randevusistemi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    // Tüm randevuları alır ve DTO'ya çevirir
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Yeni bir randevu oluşturur
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = convertToEntity(appointmentDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDTO(savedAppointment);
    }

    // Randevuyu onaylar
    public void approveAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Randevu bulunamadı"));
        appointment.setApproved(true);
        appointmentRepository.save(appointment);
    }

    // DTO'yu Entity'ye çevirir
    private Appointment convertToEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setApproved(appointmentDTO.isApproved());

        // Kullanıcı ve İşletmeyi veritabanından al
        User user = userRepository.findById(appointmentDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı: " + appointmentDTO.getUserId()));
        Business business = businessRepository.findById(appointmentDTO.getBusinessId())
                .orElseThrow(() -> new BusinessNotFoundException("İşletme bulunamadı: " + appointmentDTO.getBusinessId()));

        appointment.setUser(user);
        appointment.setBusiness(business);
        return appointment;
    }

    // Entity'yi DTO'ya çevirir
    private AppointmentDTO convertToDTO(Appointment appointment) {
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getDate(),
                appointment.isApproved(),
                appointment.getUser().getId(),
                appointment.getBusiness().getId()
        );
    }
}

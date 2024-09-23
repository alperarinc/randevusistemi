package com.aarinc.randevusistemi.repository;

import com.aarinc.randevusistemi.model.Appointment;
import com.aarinc.randevusistemi.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByBusiness(Business business);
}

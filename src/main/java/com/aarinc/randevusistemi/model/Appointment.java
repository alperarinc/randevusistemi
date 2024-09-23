package com.aarinc.randevusistemi.model;

import com.aarinc.randevusistemi.model.Business;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private boolean approved = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Business business;
}

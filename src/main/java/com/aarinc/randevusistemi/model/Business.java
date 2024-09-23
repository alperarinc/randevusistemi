package com.aarinc.randevusistemi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contactInfo;

    @OneToMany(mappedBy = "business")
    private List<User> users;

    @OneToMany(mappedBy = "business")
    private List<Appointment> appointments;

    // Getter ve Setter
}

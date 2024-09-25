package com.aarinc.randevusistemi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDTO {
    private Long id;
    private String name;
    private String address;
    private String contactInfo;
}

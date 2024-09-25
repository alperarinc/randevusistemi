package com.aarinc.randevusistemi.controller;

import com.aarinc.randevusistemi.dto.BusinessDTO;
import com.aarinc.randevusistemi.model.Business;
import com.aarinc.randevusistemi.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessRepository businessRepository;

    @GetMapping
    public List<BusinessDTO> getAllBusinesses() {
        return businessRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BusinessDTO convertToDTO(Business business) {
        return new BusinessDTO(
                business.getId(),
                business.getName(),
                business.getAddress(),
                business.getContactInfo()
        );
    }
}

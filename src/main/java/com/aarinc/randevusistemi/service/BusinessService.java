package com.aarinc.randevusistemi.service;

import com.aarinc.randevusistemi.dto.BusinessDTO;
import com.aarinc.randevusistemi.model.Business;
import com.aarinc.randevusistemi.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    public List<BusinessDTO> getAllBusinesses() {
        return businessRepository.findAll().stream()
                .map(business -> new BusinessDTO(
                        business.getId(),
                        business.getName(),
                        business.getAddress(),
                        business.getContactInfo()
                ))
                .collect(Collectors.toList());
    }

    public BusinessDTO createBusiness(BusinessDTO businessDTO) {
        Business business = new Business();
        business.setName(businessDTO.getName());
        business.setAddress(businessDTO.getAddress());
        business.setContactInfo(businessDTO.getContactInfo());

        Business savedBusiness = businessRepository.save(business);
        return new BusinessDTO(
                savedBusiness.getId(),
                savedBusiness.getName(),
                savedBusiness.getAddress(),
                savedBusiness.getContactInfo()
        );
    }
}


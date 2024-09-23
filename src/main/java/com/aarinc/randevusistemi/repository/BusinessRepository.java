package com.aarinc.randevusistemi.repository;

import com.aarinc.randevusistemi.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    Optional<Business> findByName(String name);
}

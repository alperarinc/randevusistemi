package com.aarinc.randevusistemi.repository;

import com.aarinc.randevusistemi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // E-posta adresiyle kullanıcıyı bulmak için sorgu
    Optional<User> findByEmail(String email);

    // Kullanıcı adıyla kullanıcıyı bulmak için sorgu (login için kullanılabilir)
    Optional<User> findByUsername(String username);

    // Kullanıcı adı ve şifre ile kullanıcıyı bulmak için sorgu (giriş işlemi için)
    Optional<User> findByUsernameAndPassword(String username, String password);
}

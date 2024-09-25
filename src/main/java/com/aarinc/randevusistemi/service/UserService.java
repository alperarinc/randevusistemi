package com.aarinc.randevusistemi.service;

import com.aarinc.randevusistemi.dto.UserRegisterDTO;
import com.aarinc.randevusistemi.model.User;
import com.aarinc.randevusistemi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Kullanıcı kaydı
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        // Kullanıcı adı zaten kayıtlı mı kontrol et
        if (userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
        }

        // E-posta zaten kayıtlı mı kontrol et
        if (userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Bu e-posta zaten kullanılıyor.");
        }

        // Yeni bir User oluştur
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setRoles(List.of("USER"));  // Varsayılan rol "USER"

        // Kullanıcıyı kaydet
        userRepository.save(user);
    }

    // Kullanıcı giriş işlemi
    public User loginUser(String username, String password) {
        // Kullanıcıyı bul ve şifreyi doğrula
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Geçersiz kullanıcı adı veya şifre"));
    }
}

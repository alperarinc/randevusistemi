package com.aarinc.randevusistemi.controller;

import com.aarinc.randevusistemi.dto.UserLoginDTO;
import com.aarinc.randevusistemi.dto.UserRegisterDTO;
import com.aarinc.randevusistemi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    // Kullanıcı kayıt endpoint'i
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        // Kullanıcı kaydı gerçekleştirilir
        userService.registerUser(userRegisterDTO);
        return ResponseEntity.ok("Kullanıcı başarıyla kaydedildi");
    }

    // Kullanıcı giriş endpoint'i
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        // Kullanıcı doğrulama işlemi
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Kullanıcı başarıyla giriş yaptı");
    }
}

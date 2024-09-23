package com.aarinc.randevusistemi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login", "/register").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()// Bu URL'lere izin ver
                .anyRequest().authenticated() // Diğer tüm URL'ler için doğrulama zorunlu
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll() // Giriş işlemi herkes için erişilebilir
                .and()
                .logout()
                .permitAll(); // Çıkış işlemi de herkes için serbest

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Şifreleri Bcrypt ile şifreleme
    }
}

package com.aarinc.randevusistemi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private List<String> roles; // Rolleri tutacak alan

    // Eğer frontend'den roller gelmezse varsayılan bir değer atanabilir
    public UserDTO() {
        this.roles = List.of("USER"); // Varsayılan rol "USER" olabilir
    }

    public UserDTO(Long id, String username, String password, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles != null ? roles : List.of("USER"); // Eğer frontend'den roles boşsa, "USER" olarak ayarlayın
    }
}

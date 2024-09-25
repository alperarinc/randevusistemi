package com.aarinc.randevusistemi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO {
    private String username;
    private String password;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

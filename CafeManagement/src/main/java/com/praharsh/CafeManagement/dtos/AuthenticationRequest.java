package com.praharsh.CafeManagement.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthenticationRequest {
    private String email;
    private String password;
}

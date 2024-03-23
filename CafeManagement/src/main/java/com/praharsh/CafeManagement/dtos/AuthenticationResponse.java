package com.praharsh.CafeManagement.dtos;

import com.praharsh.CafeManagement.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse{
    private String jwt;
    private UserRole userRole;
    private long userId;
}

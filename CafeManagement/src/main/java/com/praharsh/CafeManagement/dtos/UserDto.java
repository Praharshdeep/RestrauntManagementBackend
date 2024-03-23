package com.praharsh.CafeManagement.dtos;

import com.praharsh.CafeManagement.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}

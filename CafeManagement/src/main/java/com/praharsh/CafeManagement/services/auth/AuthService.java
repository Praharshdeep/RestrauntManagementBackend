package com.praharsh.CafeManagement.services.auth;

import com.praharsh.CafeManagement.dtos.Signuprequest;
import com.praharsh.CafeManagement.dtos.UserDto;

public interface AuthService {
    UserDto createUser(Signuprequest signuprequest);
}

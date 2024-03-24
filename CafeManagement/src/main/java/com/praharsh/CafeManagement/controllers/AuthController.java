package com.praharsh.CafeManagement.controllers;

import com.praharsh.CafeManagement.dtos.AuthenticationRequest;
import com.praharsh.CafeManagement.dtos.AuthenticationResponse;
import com.praharsh.CafeManagement.dtos.Signuprequest;
import com.praharsh.CafeManagement.dtos.UserDto;
import com.praharsh.CafeManagement.entities.User;
import com.praharsh.CafeManagement.repositries.UserRepo;
import com.praharsh.CafeManagement.services.auth.AuthService;
import com.praharsh.CafeManagement.services.jwt.UserDetailsServiceImpl;
import com.praharsh.CafeManagement.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UserRepo userRepo) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> signupUser(@RequestBody Signuprequest signuprequest) {
//        UserDto createdUserDto = authService.createUser(signuprequest);
//
//        if (createdUserDto == null) {
//            return new ResponseEntity<>("User not Created. Come again later", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
//    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or Password");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not Active!");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }
}
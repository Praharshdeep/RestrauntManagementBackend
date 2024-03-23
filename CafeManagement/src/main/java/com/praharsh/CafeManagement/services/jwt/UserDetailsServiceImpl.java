package com.praharsh.CafeManagement.services.jwt;

import com.praharsh.CafeManagement.entities.User;
import com.praharsh.CafeManagement.repositries.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Logic Here to get user from DB
        Optional<User> userOptional = userRepo.findFirstByEmail(email);
        if(userOptional.isEmpty()) throw new UsernameNotFoundException("User Not Found!", null);
        return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),userOptional.get().getPassword(),new ArrayList<>());
    }
}

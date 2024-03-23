package com.praharsh.CafeManagement.services.auth;

import com.praharsh.CafeManagement.dtos.Signuprequest;
import com.praharsh.CafeManagement.dtos.UserDto;
import com.praharsh.CafeManagement.entities.User;
import com.praharsh.CafeManagement.enums.UserRole;
import com.praharsh.CafeManagement.repositries.UserRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//Here we write logic
@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepo userRepo;

    public AuthServiceImplementation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostConstruct //Apne aap invoke hoge jab bhi ye app run hogi
    public void createAdminAccount(){
        User adminAccount = userRepo.findByUserRole(UserRole.ADMIN);
        if(adminAccount == null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepo.save(user);
        }

    }
    @Override
    public UserDto createUser(Signuprequest signuprequest) {
        User user =new User();
        user.setName(signuprequest.getName());
        user.setEmail(signuprequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signuprequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepo.save(user);
        UserDto createdUserDto = new UserDto();
        createdUserDto.setEmail(createdUser.getEmail());
        createdUserDto.setUserRole(createdUser.getUserRole());
        createdUserDto.setName(createdUser.getName());
        createdUserDto.setId(createdUser.getId());
        return createdUserDto;
    }
}

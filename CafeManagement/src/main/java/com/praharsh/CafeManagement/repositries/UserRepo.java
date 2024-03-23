package com.praharsh.CafeManagement.repositries;

import com.praharsh.CafeManagement.entities.User;
import com.praharsh.CafeManagement.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}

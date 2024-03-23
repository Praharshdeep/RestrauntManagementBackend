package com.praharsh.CafeManagement.entities;

import com.praharsh.CafeManagement.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;
    private UserRole userRole;

}

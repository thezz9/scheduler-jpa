package com.thezz9.schedulerjpa.user.entity;

import com.thezz9.schedulerjpa.config.JpaAuditingConfig;
import com.thezz9.schedulerjpa.user.dto.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends JpaAuditingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateUser(UserUpdateRequestDto dto) {
        this.username = dto.getUsername();
        this.email = dto.getEmail();
    }

}

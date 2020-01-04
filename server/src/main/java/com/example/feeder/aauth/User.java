package com.example.feeder.aauth;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Entity
@Table( uniqueConstraints =  {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotBlank
//    @Size(max = 20)
    private String username;

//    @NotBlank
//    @Size(max = 50)
//    @Email
    private String email;

//    @NotBlank
//    @Size(max = 50)
    private String password;

    private Role role;

    public User() {
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        // if role doesn't equals default role is USER
        this.role = chooseRole(role).orElse(Role.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Optional<Role> chooseRole(String role) {
        if (role.equalsIgnoreCase("A"))
            return Optional.of(Role.ADMIN);
        if (role.equalsIgnoreCase("U"))
            return Optional.of(Role.USER);
        return Optional.empty();
    }
}

package com.openclassroom.chatop.dto;

public class UserDto {

    private Long id;
    private String name;  // Changement de "username" à "name"
    private String password;
    private String email;
    private String role;

    public UserDto() {}

    public UserDto(Long id, String name, String password, String email, String role) {
        this.id = id;
        this.name = name;  // Changement de "username" à "name"
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {  // Changement de "getUsername" à "getName"
        return name;
    }

    public void setName(String name) {  // Changement de "setUsername" à "setName"
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
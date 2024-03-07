package com.Discipline.cinehub.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class UserDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String address;
    private String provider;
    @NotBlank
    private String email;
    @NotBlank
    private String phonenumber;
    private String role;


}

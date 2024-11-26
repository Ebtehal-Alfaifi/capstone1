package com.example.capstone1.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Valid
public class UserModel {



    @NotEmpty(message = " id can not be empty")
    private String userId;

    @NotEmpty(message = " user name can not be empty")
    @Size(min = 6,message = " user name have to be more than 6 length long ")
    private String userName;

    @NotEmpty(message = " password can not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$",
            message = "Password must be at least 7 characters long and contain both letters and digits")
    private String password;

    @NotEmpty(message = " email can not be empty")
    @Email(message = " you should enter valid email")
    private String email;

    @NotEmpty(message = " role can not be empty")
    @Pattern(regexp = "(?i)^(Admin|Customer)$",
    message = "Role must be either 'Admin' or 'Customer'")
    private  String role;

    @NotNull(message = "balance can not be null")
    @Positive(message = " you should enter positive number")
    private double balance;




}

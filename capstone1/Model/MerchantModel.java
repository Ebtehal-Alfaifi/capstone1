package com.example.capstone1.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Valid
public class MerchantModel {
    @NotEmpty(message = " id can not be empty")
    @Size(min = 3,message = " your id length should be 3 charactar only")
    private String merchantID;

    @NotEmpty(message = " your name can not be empty")
    @Size(min =4,message = " name has to be more than 3 length long")
    private String name;



}

package com.example.capstone1.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Valid
public class CategoryModel {
    @NotEmpty(message = " id can not be null")
    @Size(min = 3,message = " you should enter valid id ")
    private String id;


    @NotEmpty(message = "name can not be null")
    @Size(min = 4,message = " name has to be more than 3 length long")
    private String name;

}

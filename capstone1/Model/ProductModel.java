package com.example.capstone1.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Valid
public class ProductModel {
    @NotEmpty(message = " id can not be empty")
    @Size(min = 3,max = 3,message = " id should has 3 characters only")
    private  String pID;

    @NotEmpty(message = " name can not be null")
    @Size(min = 4,message = "must not be empty, have to be more than 3 length long")
    private String name;

    @NotNull(message = "price can not be null")
    @Positive(message = "must be positive number")
    private double price;

    @NotEmpty(message = " category id can not be null")
    @Size(min = 3,message = " length of category id at least should has 3 characters")
    private String categoryID;


    //************** extra credit*****************
    //  1 of 3 extra credit
    @NotNull(message = "can not be empty")
    @Positive(message = " can not be negative number ")
    private int saleCount;

    @NotNull(message = " total rating sum can not be null")
    @Positive(message = " you can not enter negative number")
    private int totalRatingSum;


    @NotNull(message = " total rating count can not be null")
    @Positive(message = " you can not enter negative number")
    private int totalRatingCount;




}

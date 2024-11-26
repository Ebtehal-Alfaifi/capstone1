package com.example.capstone1.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Valid
@Data
@AllArgsConstructor
public class StockModel {

    @NotEmpty(message = " id can not be empty")
    @Size(min = 3,message = " the id at least should has 3 characters")
    private String id;

    @NotEmpty(message = " id can not be empty")
    private String productid;

    @NotEmpty(message = "Merchant ID must not be empty")
    private String merchantID;

    @NotNull(message = "Stock must not be null")
    @Min(value = 10, message = "Stock must be at least 10 at start")
    private Integer stock;
}

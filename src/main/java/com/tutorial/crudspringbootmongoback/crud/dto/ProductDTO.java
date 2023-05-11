package com.tutorial.crudspringbootmongoback.crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotBlank(message = "product name is mandatory")
    private String name;
    @Min(value = 1, message = "product price is mandatory")
    private int price;
}

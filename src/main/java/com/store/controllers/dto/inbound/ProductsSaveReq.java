package com.store.controllers.dto.inbound;

import com.store.model.ProductTypes;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductsSaveReq {

    @NotBlank(message = "Name must not be blank")
    @Pattern(
        regexp = "^(?!true$|false$)[a-zA-Z ]+$",
        message = "Name must contain only letters and cannot be true or false"
    )
    private String name;

    @NotNull(message = "Product type is required")
    private ProductTypes type;

    @NotNull(message = "Inventory is required")
    @Min(value = 1, message = "Inventory must be at least 1")
    @Max(value = 9999, message = "Inventory must be at most 9999")
    private Integer inventory;

}

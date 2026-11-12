package com.store.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.controllers.dto.inbound.ProductsSaveReq;
import com.store.controllers.dto.outbound.ProductsSaveResp;
import com.store.model.ProductTypes;
import com.store.model.Products;
import com.store.services.IProductsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {

    private final IProductsService productsService;

    @PostMapping("/products")
    public ResponseEntity<ProductsSaveResp> save(
        @Valid
        @RequestBody
        ProductsSaveReq payload
    ) {

        try {
            return ResponseEntity
                .status(
                    201
                )
                .body(
                    new ProductsSaveResp(
                        productsService.save(
                            payload
                        )
                    )
                );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/products")
    public ResponseEntity<List<Products>> get(
        @RequestParam(required = false, name = "type")
        ProductTypes type
    ) {

        try {
            return ResponseEntity.ok(
                productsService.get(
                    Optional.ofNullable(
                        type
                    )
                )
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

}

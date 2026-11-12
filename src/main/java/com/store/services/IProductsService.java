package com.store.services;

import java.util.List;
import java.util.Optional;

import com.store.controllers.dto.inbound.ProductsSaveReq;
import com.store.model.ProductTypes;
import com.store.model.Products;

public interface IProductsService {

    public Integer save(
        ProductsSaveReq payload
    );

    public List<Products> get(
        Optional<ProductTypes> type
    );

}

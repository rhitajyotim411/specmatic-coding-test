package com.store.services.impls;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.store.controllers.dto.inbound.ProductsSaveReq;
import com.store.model.ProductTypes;
import com.store.model.Products;
import com.store.services.IProductsService;

@Service
public class ProductsServiceImpl implements IProductsService {

    private final Map<Integer, Products> productsStore;
    private int idGen;

    public ProductsServiceImpl() {

        productsStore = new ConcurrentHashMap<>();
        idGen = 1;

    }

    @Override
    public Integer save(
        ProductsSaveReq payload
    ) {

        int currId = idGen;

        Products currProduct = new Products(
            currId,
            payload.getName(),
            payload.getType(),
            payload.getInventory()
        );

        productsStore.put(
            currId,
            currProduct
        );

        idGen += 1;

        return currId;

    }

    @Override
    public List<Products> get(
        Optional<ProductTypes> type
    ) {

        Stream<Products> productsStream = productsStore.values().stream();

        if (type.isPresent()) {
            productsStream = productsStream.filter(
                product -> product.getType() == type.get()
            );
        }

        return productsStream.toList();

    }

}

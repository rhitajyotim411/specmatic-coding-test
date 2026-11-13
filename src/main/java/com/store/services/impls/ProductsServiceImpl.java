package com.store.services.impls;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.store.controllers.dto.inbound.ProductsSaveReq;
import com.store.model.ProductTypes;
import com.store.model.Products;
import com.store.services.IProductsService;

@Service
public class ProductsServiceImpl implements IProductsService {

    private final Map<Integer, Products> productsStore;
    // Concurrent saves need unique IDs.
    private final AtomicInteger idGen;

    public ProductsServiceImpl() {

        productsStore = new ConcurrentHashMap<>();
        idGen = new AtomicInteger(
            1
        );

    }

    @Override
    public Integer save(
        ProductsSaveReq payload
    ) {

        int currId = idGen.getAndIncrement();

        Products currProduct = new Products(
            currId,
            payload.getName(),
            payload.getType(),
            payload.getInventory(),
            // An omitted cost is treated as zero.
            Objects.requireNonNullElse(
                payload.getCost(),
                0.0
            )
        );

        productsStore.put(
            currId,
            currProduct
        );

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

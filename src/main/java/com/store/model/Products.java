package com.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Products {

    private final Integer id;
    private final String name;
    private final ProductTypes type;
    private final Integer inventory;

}

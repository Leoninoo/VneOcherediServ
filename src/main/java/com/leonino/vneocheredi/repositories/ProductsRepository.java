package com.leonino.vneocheredi.repositories;

import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.enums.Shop;
import com.leonino.vneocheredi.models.Product;

import java.util.List;

public interface ProductsRepository {
    List<Product> findAll(Shop shops, Integer page, Category category);

    List<Product> findAllByHrefs(String[] hrefs);
}

package com.leonino.vneocheredi.memory;

import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.enums.Shop;
import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.repositories.ProductsRepository;

import java.util.Arrays;
import java.util.List;

//@Repository
public class ProductsRepositoryMemory implements ProductsRepository {
    @Override
    public List<Product> findAll(Shop shops, Integer page, Category category) {
        return Arrays.asList(
                new Product(0L, "Сырок", "25 руб", "", ""),
                new Product(1L, "Молоко", "89 руб", "", "")
        );
    }

    @Override
    public List<Product> findAllByHrefs(String[] hrefs) {
        return null;
    }
}

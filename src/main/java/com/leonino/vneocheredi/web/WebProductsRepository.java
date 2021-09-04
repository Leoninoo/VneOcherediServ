package com.leonino.vneocheredi.web;


import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.enums.Shop;
import com.leonino.vneocheredi.factory.ProductFactory;
import com.leonino.vneocheredi.factory.ProductFactoryLenta;
import com.leonino.vneocheredi.factory.ProductFactoryVprok;
import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.repositories.ProductsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WebProductsRepository implements ProductsRepository {
    @Override
    public List<Product> findAll(Shop shops, Integer page, Category category) {
        ProductFactory productFactory;

        if(shops.equals(Shop.PEREKRESTOK))
            productFactory = new ProductFactoryVprok(category);
        else
            productFactory = new ProductFactoryLenta(category);

        return productFactory.factory(page);
    }
}

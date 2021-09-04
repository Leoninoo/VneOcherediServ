package com.leonino.vneocheredi.factory;

import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.models.Product;

import java.util.List;

public interface ProductFactory {
    List<Product> factory(Integer page);
}

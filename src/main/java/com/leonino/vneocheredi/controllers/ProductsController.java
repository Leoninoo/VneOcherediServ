package com.leonino.vneocheredi.controllers;

import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.enums.Shop;
import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.repositories.ProductsRepository;
import com.leonino.vneocheredi.repositories.TokenRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ProductsController {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/products")
    public List<Product> getProducts(@Param("page") Integer page,
                                     @Param("category") Category category,
                                     @Param("shop") String shop) {
        if(shop.indexOf("vprok") > 0)
            return productsRepository.findAll(Shop.PEREKRESTOK, page, category);
        else
            return productsRepository.findAll(Shop.LENTA, page, category);
    }

    //@GetMapping("/products")
    public String getDiv() {
        try {
            String s = Jsoup.connect("https://www.vprok.ru/catalog/1310/konservy-orehi-sousy").get().getAllElements().toString();
            System.out.println(s);
            return s;
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}

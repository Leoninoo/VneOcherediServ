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
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductsController {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/p")
    @ResponseBody
    public String getYa(@Param("n") Integer n) {
        return String.valueOf(n);
    }

    @GetMapping("/products")
    public List<Product> getProducts(@Param("page") Integer page,
                                     @Param("category") Category category,
                                     @Param("shop") String shop) {

        return productsRepository.findAll(Shop.PEREKRESTOK, page, category);
    }

    @GetMapping("/basket")
    @ResponseBody
    public List<Product> getBasketProducts(@Param("products") String products) {
        String[] hrefs = products.split("~~");

        return productsRepository.findAllByHrefs(hrefs);
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

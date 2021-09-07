package com.leonino.vneocheredi.factory;

import com.leonino.vneocheredi.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HrefsProductFactory {
    private final String[] hrefs;

    public HrefsProductFactory(String[] hrefs) {
        this.hrefs = hrefs;
    }

    public List<Product> factory() {
        List<Product> result = new ArrayList<>();

        for(String URLString : hrefs) {
            Product p = getProduct(URLString);

            System.out.println(p.getName());

            result.add(p);
        }

        return result;
    }

    private Product getProduct(String URLString){
        try {
            Document document = Jsoup.connect(URLString).get();

            Elements elementsName = document.getElementsByClass("xf-product-new__title js-product__title js-product-new-title");
            Elements elementsPrice = document.getElementsByClass("js-price-rouble");

            return Product.builder()
                    .id(getId(URLString))
                    .name(elementsName.get(0).text())
                    .price(elementsPrice.get(0).text())
                    .build();
        }
        catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    private Long getId(String URLString) {
        return Long.parseLong(URLString.split("--")[1]);
    }
}

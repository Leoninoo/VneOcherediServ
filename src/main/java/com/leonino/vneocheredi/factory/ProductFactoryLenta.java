package com.leonino.vneocheredi.factory;

import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProductFactoryLenta implements ProductFactory {
    private final String URLString;
    private Document document;

    public ProductFactoryLenta(Category category) {
        if(category == null) {
            URLString = "https://lenta.com/weekly/";
            return;
        }

        switch (category) {
            case meat:
                URLString = "https://lenta.com/catalog/myaso-ptica-kolbasa/";
                break;
            case vegetables:
                URLString = "https://lenta.com/catalog/frukty-i-ovoshchi/";
                break;
            case sweet:
                URLString = "https://lenta.com/catalog/konditerskie-izdeliya/";
                break;
            case tea:
                URLString = "https://lenta.com/catalog/chajj-kofe-kakao/";
                break;
            case grocery:
                URLString = "https://lenta.com/catalog/bakaleya/";
                break;
            case freeze:
                URLString = "https://lenta.com/catalog/zamorozhennaya-produkciya/";
                break;
            case milk:
                URLString = "https://lenta.com/catalog/moloko-syr-yajjco/";
                break;
            case fish:
                URLString = "https://lenta.com/catalog/ryba-i-moreprodukty/";
                break;
            case health:
                URLString = "https://lenta.com/catalog/zdorovoe-pitanie/";
                break;
            case water:
                URLString = "https://lenta.com/catalog/bezalkogolnye-napitki/";
                break;
            case bread:
                URLString = "https://lenta.com/catalog/hleb-i-hlebobulochnye-izdeliya/";
                break;
            case beauty:
                URLString = "https://lenta.com/catalog/krasota-i-zdorove/";
                break;
            case house:
                URLString = "https://lenta.com/catalog/bytovaya-himiya/";
                break;
            case animals:
                URLString = "https://lenta.com/catalog/tovary-dlya-zhivotnyh/";
                break;
            default:
                URLString = "https://lenta.com/weekly/";
        }
    }

    @Override
    public List<Product> factory(Integer page) {
        List<Product> products = new ArrayList<>();

        try {
            if(page == null) {
                document = Jsoup.connect(URLString).get();
            }
            else {
                System.out.println();

                document = Jsoup.connect(URLString + "?page=" + page + "&flag").get();

                if(!document.baseUri().endsWith("flag"))
                    return products;
            }
        } catch (IOException e) {
            List<Product> r = new ArrayList<>();
            r.add(new Product(1L, "AN", "100 p", ""));
            return r;
        }

        //Получение списков div
        Elements elementsName = document.getElementsByClass("sku-card-small__title");
        Elements elementsPrice = document.getElementsByClass("price-label__integer");
        Elements elementsImage = document.getElementsByClass("square__inner");

        for (long i = 0; i < elementsName.size(); i++) {
            Product p = new Product(i,
                    elementsName.get((int) i).text(),
                    elementsPrice.get((int) i).text() + " руб",
                    getImage(elementsImage.get((int) i).toString()));

            products.add(p);
        }

        return products;
    }

    private String getImage(String div) {
        String[] divElements = div.split("\"");

        //Заменяем на полную версию картинки
        return divElements[7];
    }
}

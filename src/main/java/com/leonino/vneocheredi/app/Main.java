package com.leonino.vneocheredi.app;

import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.service.TokenGenerator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<>();

        Document document = Jsoup.connect("https://www.vprok.ru/catalog/1303/moloko-syr-yaytsa")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .cookie("region","2")
                .get();

        Elements elementsName = document.getElementsByClass(
                "xf-product-title__link js-product__title  xf-product-title__link--additional ");
        Elements elementsPrice = document.getElementsByClass("xf-price__rouble js-price-rouble");
        Elements elementsImage = document.getElementsByClass("xf-product__picture xf-product-picture");
        Elements elementsIds = document.getElementsByClass("js-catalog-product _additionals xf-catalog__item");
        Elements elementsHrefs = document.getElementsByClass("xf-product__main-link");

        for (long i = 0; i < elementsPrice.size(); i++) {
            System.out.println(elementsName.get((int) i).text());
        }
    }

    private static String getImage(String div) {
        String[] divElements = div.split("\"");

        //Заменяем на полную версию картинки
        return divElements[7].replace("list", "full.w");
    }

    private static Long getId(String div) {
        String[] divElements = div.split("\"");

        return Long.parseLong(divElements[5]);
    }
}

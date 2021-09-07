package com.leonino.vneocheredi.app;

import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.service.TokenGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.vprok.ru/product/parmalat-parmal-moloko-pit-ulster-3-5-1l--306634").get();

        Elements elementsName = document.getElementsByClass("xf-product-new__title js-product__title js-product-new-title");
        Elements elementsPrice = document.getElementsByClass("js-price-rouble");


        System.out.println(elementsName.get(0).text());
        System.out.println(elementsPrice.get(0).text());
    }
}

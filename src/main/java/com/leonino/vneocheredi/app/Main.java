package com.leonino.vneocheredi.app;

import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.service.TokenGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.vprok.ru/catalog/1377/moloko").get();

        Elements elementsHrefs = document.getElementsByClass("xf-product__main-link");
        System.out.println(elementsHrefs.get(0).attr("abs:href"));
    }
}

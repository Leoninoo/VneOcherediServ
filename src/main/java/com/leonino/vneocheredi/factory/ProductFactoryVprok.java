package com.leonino.vneocheredi.factory;

import com.leonino.vneocheredi.enums.Category;
import com.leonino.vneocheredi.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductFactoryVprok implements ProductFactory {
    private final String URLString;
    private Document document;

    public ProductFactoryVprok(Category category) {
        if(category == null) {
            URLString = "https://www.vprok.ru/catalog/3547/skidki";
            return;
        }

        switch (category) {
            case discounts:
                URLString = "https://www.vprok.ru/catalog/3547/skidki";
                break;

            case vegetables:
                URLString = "https://www.vprok.ru/catalog/1301/ovoschi-frukty-griby";
                break;

            case milk:
                URLString = "https://www.vprok.ru/catalog/1303/moloko-syr-yaytsa";
                break;

            case meat:
                URLString = "https://www.vprok.ru/catalog/1307/myaso-ptitsa-delikatesy";
                break;

            case fish:
                URLString = "https://www.vprok.ru/catalog/1304/ryba-i-moreprodukty";
                break;

            case water:
                URLString = "https://www.vprok.ru/catalog/1312/soki-vody-napitki";
                break;

            case tea:
                URLString = "https://www.vprok.ru/catalog/1302/kofe-chay-sahar";
                break;

            case cereals:
                URLString = "https://www.vprok.ru/catalog/1300/makarony-krupy-spetsii";
                break;

            case sauces:
                URLString = "https://www.vprok.ru/catalog/1310/konservy-orehi-sousy";
                break;

            case bread:
                URLString = "https://www.vprok.ru/catalog/1309/hleb-sladosti-sneki";
                break;

            case sweet:
                URLString = "https://www.vprok.ru/catalog/4019/sladosti-i-sneki";
                break;

            case health:
                URLString = "https://www.vprok.ru/catalog/3453/zdorovoe-pitanie";
                break;

            case freeze:
                URLString = "https://www.vprok.ru/catalog/1311/zamorojennye-produkty";
                break;

            case beauty:
                URLString = "https://www.vprok.ru/catalog/1306/krasota-gigiena-bytovaya-himiya";
                break;

            case house:
                URLString = "https://www.vprok.ru/catalog/2348/bytovaya-himiya-i-hoztovary";
                break;


            default: URLString = "https://www.vprok.ru/catalog/3547/skidki";
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
                document = Jsoup.connect(URLString +
                        "?attr%5Brate%5D%5B%5D=0&page=" + page + "&sort=rate_desc").get();

                if(!document.baseUri().endsWith("rate_desc"))
                    return products;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получение списков div
        Elements elementsName = document.getElementsByClass(
                "xf-product-title__link js-product__title  xf-product-title__link--additional ");
        Elements elementsPrice = document.getElementsByClass("xf-price__rouble js-price-rouble");
        Elements elementsImage = document.getElementsByClass("xf-product__picture xf-product-picture");
        Elements elementsIds = document.getElementsByClass("js-catalog-product _additionals xf-catalog__item");

        for (long i = 0; i < elementsPrice.size(); i++) {
            products.add(new Product(getId(elementsIds.get((int) i).toString()),
                    elementsName.get((int) i).text(),
                    elementsPrice.get((int) i).text() + " руб",
                    getImage(elementsImage.get((int) i).toString())));
        }

        return products;
    }

    private String getImage(String div) {
        String[] divElements = div.split("\"");

        //Заменяем на полную версию картинки
        return divElements[7].replace("list", "full.w");
    }

    private Long getId(String div) {
        String[] divElements = div.split("\"");

        return Long.parseLong(divElements[5]);
    }
}

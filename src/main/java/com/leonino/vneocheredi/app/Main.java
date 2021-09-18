package com.leonino.vneocheredi.app;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.leonino.vneocheredi.models.Product;
import com.leonino.vneocheredi.service.TokenGenerator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new BCryptPasswordEncoder().matches("524310kleo", "$2a$10$QUaU2EOIDMGIdKv3GSuY0eBpvNDTqRBndWiXbdsMaMXnmRFcRhyDm"));
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

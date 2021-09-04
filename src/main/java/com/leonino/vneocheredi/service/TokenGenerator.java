package com.leonino.vneocheredi.service;

import com.leonino.vneocheredi.models.Token;

import java.util.List;

public class TokenGenerator {
    public static String generate(List<Token> tokens) {
        String result = generate();

        for(Token token : tokens) {
            if(result.equals(token.getToken()))
                return generate();
        }

        return result;
    }

    private static String generate() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            switch ((int) (Math.random() * 3)) {
                case 0:
                    result.append((char) ((Math.random() * 26) + 97));
                    break;
                case 1:
                    result.append((char) ((Math.random() * 26) + 65));
                    break;
                case 2:
                    result.append((char) ((Math.random() * 10) + 48));
                    break;
            }
        }
        return result.toString();
    }
}

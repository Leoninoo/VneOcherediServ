package com.leonino.vneocheredi.app;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str1 = reader.readLine().split(" ");

        int n = Integer.parseInt(str1[0]);
        int t = Integer.parseInt(str1[1]) / 30;

        boolean[] res = new boolean[21];

        if(n > 0) {
            for(int i = 0; i < n; i++) {
                int k = Integer.parseInt(reader.readLine());

                if(k > 0) {
                    for(int j = 0; j < k; j++) {
                        String[] timeStr = reader.readLine().split("-");

                        String[] first = timeStr[0].split(":");
                        String[] second = timeStr[1].split(":");

                        int timeStart = rep(first[0]) + rep(first[1]);
                        int timeEnd = rep(second[0]) + rep(second[1]);

                        for(int time = timeStart; time < timeEnd; time++) {
                            res[time-1] = true;
                        }
                    }
                }
            }
        }

        int counter = 0;

        for(int i = 0; i < 21; i++) {
            boolean flag = true;

            for(int j = i; j < t + i; j++) {
                if(j > 20) {
                    flag = false;
                    break;
                }
                if(res[j]) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                System.out.println(ob(i + 1) + "-" + ob(i + 1+ t));
                counter++;
            }
        }

        if(counter == 0) {
            System.out.println("No way");
        }
    }

    private static String ob(int i) {
        switch (i) {
            case 1:
                return "10:00";
            case 2:
                return "10:30";
            case 3:
                return "11:00";
            case 4:
                return "11:30";
            case 5:
                return "12:00";
            case 6:
                return "12:30";
            case 7:
                return "13:00";
            case 8:
                return "13:30";
            case 9:
                return "14:00";
            case 10:
                return "14:30";
            case 11:
                return "15:00";
            case 12:
                return "15:30";
            case 13:
                return "16:00";
            case 14:
                return "16:30";
            case 15:
                return "17:00";
            case 16:
                return "17:30";
            case 17:
                return "18:00";
            case 18:
                return "18:30";
            case 19:
                return "19:00";
            case 20:
                return "19:30";
            case 21:
                return "20:00";
            default:
                return "00:00";
        }
    }

    private static int rep(String s) {
        switch (s) {
            case "30":
            case "10":
                return 1;
            case "11":
                return 3;
            case "12":
                return 5;
            case "13":
                return 7;
            case "14":
                return 9;
            case "15":
                return 11;
            case "16":
                return 13;
            case "17":
                return 15;
            case "18":
                return 17;
            case "19":
                return 19;
            case "20":
                return 21;
            default:
                return 0;
        }
    }

    void ex() throws Exception {
        URL obj = new URL("http://localhost:7777/");
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("MEW");
        connection.setRequestProperty("X-cat-variable", "as");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int comCounter = Integer.parseInt(reader.readLine());

        Set<String> result = new TreeSet<>();

        if(comCounter > 0) {
            for(int i = 0; i < comCounter; i++) {
                String com = reader.readLine();

                if(com.equals("MERGE")) {
                    String[] specialAttr = reader.readLine().split(" ");

                    int jsonCounter = Integer.parseInt(reader.readLine());

                    if(jsonCounter > 0) {
                        for(int j = 0; j < jsonCounter; j++) {
                            String str = reader.readLine().replaceAll("\\s","");

                            if(str.equals("}"))
                                continue;

                            if(i == 0) {
                                result.add(str);
                            }

                            String[] strSpl = str.split(":");
                        }
                    }
                }
            }
        }


        System.out.println("{");

        for (String s : result) {
            System.out.println(s);
        }

        System.out.println("}");
    }
}

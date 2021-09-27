package com.leonino.vneocheredi.app;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static long[] alarms;
    private static Set<Long> allAlarms;
    private static int x;

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        String[] vars = r.readLine().split(" ");

        int n = Integer.parseInt(vars[0]);
        x = Integer.parseInt(vars[1]);
        int k = Integer.parseInt(vars[2]);

        String[] ints = r.readLine().split(" ");

        alarms = new long[ints.length];

        for (int i = 0; i < n; i++) {
            alarms[i] = Long.parseLong(ints[i]);
        }

        Arrays.sort(alarms);
        allAlarms = new TreeSet<>();

        for(long i = 0; i < k; i++) {
            long l = alarms[0] + i * x;

//            System.out.println(l + " :: first");
            allAlarms.add(l);

            if(l >= alarms[1]) {
                any(1);
            }

            if(allAlarms.size() > k)
                break;
        }

        System.out.println(allAlarms.toArray()[k - 1]);
    }

    private static void any(int counter) {
        allAlarms.add(alarms[counter]);

//        System.out.println(alarms[counter] + " :: " + counter);
        alarms[counter] = alarms[counter] + x;

        if(counter <= alarms.length - 2 && alarms[counter] >= alarms[counter + 1])
            any(counter + 1);
    }
}

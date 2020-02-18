package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {
        Scanner mk = new Scanner(System.in);

        List<String> searchList = null;
        List<String> inputList = null;
        try {
             searchList = Files.readAllLines(Paths.get("C:\\Users\\Nahid MK\\IdeaProjects\\hello\\sentences.csv"));
            StringBuilder st1 = new StringBuilder();
             ArrayList<String> strings = new ArrayList<>();
            System.out.println("search List has been read.. \n");
            inputList = Files.readAllLines(Paths.get("C:\\Users\\Nahid MK\\IdeaProjects\\hello\\search.txt"));
            System.out.println("input list has been read.. \n");

            List<String> finalSearchList = searchList;
            final int[] i = {0};
           inputList.forEach(st->
            {
                try {
                    BufferedWriter wr = new BufferedWriter(new FileWriter("Output" + (i[0] + 1) + ".txt"));
                    long time = System.currentTimeMillis();

                    finalSearchList.forEach(s -> {
                            int l = s.indexOf("\t");
                            s = s.substring(0, 0) + s.substring(l + 5, s.length()) + "\n";
                            if (s.contains(st)) {
                                st1.append(s);
                            }
                    });
                    long end_time = System.currentTimeMillis();
                    String text = "key word : " + st + "  Time : " + (end_time - time) + " milli second\n\n" + st1.toString();
                    wr.write(text);
                    wr.close();
                    System.out.println("output" + (i[0] + 1) + ".txt create successful..");
                    st1.setLength(0);
                    i[0]++;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


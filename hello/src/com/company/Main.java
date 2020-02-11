package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner mk = new Scanner(System.in);

        List<String> searchList = null;
        List<String> inputList = null;
        try {
            long start = System.currentTimeMillis();
             searchList = Files.readAllLines(Paths.get("C:\\Users\\Nahid MK\\IdeaProjects\\hello\\sentences.csv"));
             long end = System.currentTimeMillis();
             ArrayList<String> strings = new ArrayList<>();
            System.out.println("search List has been read.. \n");
            inputList = Files.readAllLines(Paths.get("C:\\Users\\Nahid MK\\IdeaProjects\\hello\\search.txt"));
            long second_end = System.currentTimeMillis();
            System.out.println("input list has been read.. \n");

            for(int i = 0;i<inputList.size();i++)
            {
                String st = "";
                BufferedWriter wr = new BufferedWriter(new FileWriter("Output"+(i+1)+".txt"));
                long time = System.currentTimeMillis();
                for(int j = 0;j<searchList.size();j++)
                {
                    if(searchList.get(j).contains(inputList.get(i)))
                    {
                        String s = searchList.get(j);

                        int l = s.indexOf("\t");
                        st =s.substring(0,0)+s.substring(l+5,s.length())+"\n";
                        wr.write(st);
                        if(strings.isEmpty())
                        {
                            strings.add(st);
                        }
                    }

                }
                long end_time = System.currentTimeMillis();
                wr.close();
                String s = "key word : "+inputList.get(i)+"  Time : "+(end_time-time)+" milli second\n\n"+strings.get(0);

                byte data[] = s.getBytes();
                    RandomAccessFile f = new RandomAccessFile(new File("Output"+(i+1)+".txt"), "rw");
                    f.getChannel().position(0);
                    f.write(data);
                    f.close();

                System.out.println("output"+(i+1)+".txt create successful..");
                strings.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

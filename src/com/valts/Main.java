package com.valts;

import com.sun.tools.jconsole.JConsoleContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

//        Get all data
        List<List<String>> data1 = new ArrayList<>();
        try (BufferedReader br1 = new BufferedReader(new FileReader("data/MOCK_DATA.csv"))) {
            String line;
            while ((line = br1.readLine()) != null) {
                String[] values = line.split(",");
                data1.add(List.of(values));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        gonna store datalist with no errors or smth
        List<String> data2 = new ArrayList<>();

        for (int i = 0; i < data1.size(); i++) {
            List<String> person = data1.get(i);

//            1. check if line is good/email is good
            if (person.size() != 3) {
                System.out.println("Error, bad line " + (i + 1));
                continue;
            }

            String email = person.get(0);

            if (!email.contains("@")) {
                System.out.println("Error, bad email " + (i + 1));
                continue;
            }

//            stores value if
            boolean isAdded = false;

            for (int j = 0; j < data1.size(); j++) {
                List<String> otherPerson = data1.get(j);

                if (otherPerson.get(0) == email && !isAdded) {
                    data2.add(otherPerson.get(2));
                    isAdded = true;
                    continue;
                }

                if (otherPerson.get(0) == email && isAdded) {
                    System.out.println("duplicate email line " + (j + 1));
                }
            }


        }

        System.out.println(data2);

    }
}


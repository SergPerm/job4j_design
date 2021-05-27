package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            int numeric;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] numerics = text.toString().split(System.lineSeparator());
            for (String s : numerics) {
                numeric = Integer.parseInt(s);
                if (numeric % 2 == 0) {
                    System.out.println("Число " + numeric + " чётное");
                } else {
                    System.out.println("Число " + numeric + " нечётное");
                }

            }
//            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

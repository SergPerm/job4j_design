package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    table[i][j] = (i + 1) * (j + 1);
                    out.write(("" + table[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void main(String[] args) {
        MultiplicationTable.multiple(9);
    }
}

package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist file %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not exist directory %s", file.getAbsoluteFile()));
        }
        System.out.printf("total size : %s Mb%n", file.getTotalSpace() >>> 20);
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            if (subfile.isDirectory()) {
                System.out.printf("%s%n", subfile.getName());
            } else {
                System.out.printf("%s размер : %d Kb%n", subfile.getName(), subfile.length() >>> 10);
            }
        }
    }
}

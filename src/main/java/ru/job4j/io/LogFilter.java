package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("log.txt"))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] strings = line.split(" ");
                Integer number = Integer.parseInt(strings[strings.length - 2]);
                if (number.equals(404)) {
                    list.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> list, String file) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : list) {
                writer.write(str + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}


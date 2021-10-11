package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean isWorking = true;
        String[] strings;
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            while (reader.ready()) {
                strings = reader.readLine().split(" ");
                int statusNumber = Integer.parseInt(strings[0]);
                if (statusNumber == 400 || statusNumber == 500) {
                    if (isWorking) {
                        writer.print(strings[1] + ";");
                        isWorking = false;
                    }
                } else {
                    if (!isWorking) {
                        writer.println(strings[1] + ";");
                        isWorking = true;
                    }
                }
                if (!reader.ready() && !isWorking) {
                    writer.println(strings[1] + ";");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy an = new Analizy();
        an.unavailable("source", "unavailable");
    }
}

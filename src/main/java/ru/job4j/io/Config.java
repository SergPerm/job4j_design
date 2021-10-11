package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        String line;
        try {
            if (!path.endsWith(".properties")) {
                throw new UnsupportedOperationException("Wrong file name");
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            while (reader.ready()) {
                line = reader.readLine();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] keyVal = line.split("=");
                    if (keyVal.length == 1) {
                        throw new IllegalArgumentException("wrong configuration");
                    }
                    values.put(keyVal[0], keyVal[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int getSizeValues() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config conf = new Config("./data/app1.properties");
        conf.load();
        System.out.println(conf.value("hibernate.connection.driver_class"));
        System.out.println(new Config("./data/app1.properties"));
    }
}

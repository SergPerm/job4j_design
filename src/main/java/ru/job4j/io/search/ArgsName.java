package ru.job4j.io.search;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String result = values.get(key);
        if (result == null) {
            throw new IllegalArgumentException(String.format("Not found parameter with key : %s", key));
        }
        return result;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] tmp = arg.split("=");
            if (tmp.length < 2) {
                throw new IllegalArgumentException(String.format("Wrong parameter toGroshi : %s", arg));
            }
            values.put(tmp[0], tmp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

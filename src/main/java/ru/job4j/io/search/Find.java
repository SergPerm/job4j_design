package ru.job4j.io.search;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    private static ArgsName argsName;

    private static Predicate<Path> getCondition(String nameForSearch, String typeOfNameForSearch) {
        return switch (typeOfNameForSearch.toLowerCase()) {
            case "name" -> p -> p.toFile().getName().equals(nameForSearch);
            case "mask" -> p -> p.toFile().getName().endsWith(nameForSearch);
            case "regex" -> p -> {
                Pattern pattern = Pattern.compile(nameForSearch);
                Matcher matcher = pattern.matcher(p.toFile().getName());
                return matcher.find();
            };
            default -> throw new IllegalStateException("Unexpected value: " + typeOfNameForSearch.toLowerCase());
        };
    }

    private static void writeToFile(Path nameFile) {
        try (FileWriter fw = new FileWriter(argsName.get("-o"))) {
            fw.write(nameFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get("-d"));
        search(start, getCondition(argsName.get("-n"), argsName.get("-t"))).forEach(Find::writeToFile);
    }
}
//3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
//        4. Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
//        Ключи
//        -d - директория, в которой начинать поиск.
//        -n - имя файла, маска, либо регулярное выражение.
//        -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
//        -o - результат записать в файл.
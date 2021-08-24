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
//            case "mask" -> p -> p.toFile().getName().endsWith(nameForSearch);
            case "mask" -> p -> {
                Pattern pattern = Pattern.compile(nameForSearch.split("\\.")[1]);
                Matcher matcher = pattern.matcher(p.toFile().getName());
                return matcher.find();
            };
            case "regex" -> p -> {
                Pattern pattern = Pattern.compile(nameForSearch);
                Matcher matcher = pattern.matcher(p.toFile().getName());
                return matcher.find();
            };
            default -> throw new IllegalStateException("Unexpected value: " + typeOfNameForSearch.toLowerCase());
        };
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        argsName = ArgsName.of(args);
        String direction = argsName.get("-d");
        if (direction == null) {
            System.out.println("что-то пошло не так, проверьте имя директории");
            return;
        }
        String nameToFind = argsName.get("-n");
        if (nameToFind == null) {
            System.out.println("что-то пошло не так, проверьте имя для поиска");
            return;
        }
        String typeOfFind = argsName.get("-t");
        if (typeOfFind == null) {
            System.out.println("что-то пошло не так, проверьте тип запроса");
            return;
        }
        String nameFileToResult = argsName.get("-o");
        if (nameFileToResult == null) {
            System.out.println("что-то пошло не так, проверьте имя файла для результата");
            return;
        }
        Path start = Paths.get(direction);
        List<Path> result = search(start, getCondition(nameToFind, typeOfFind));
        try (FileWriter fw = new FileWriter(nameFileToResult)) {
            for (Path path : result) {
                fw.write(path.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(необязательно).
//        4. Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
//        Ключи
//        -d - директория, в которой начинать поиск.
//        -n - имя файла, маска, либо регулярное выражение.
//        -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
//        -o - результат записать в файл.
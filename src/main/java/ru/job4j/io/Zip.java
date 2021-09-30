package ru.job4j.io;

import ru.job4j.io.search.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("C:\\projects\\job4j_design\\pom.xml"),
                new File("C:\\projects\\job4j_design\\pom.zip")
        );
        ArgsName names = ArgsName.of(args);
        Path start = Paths.get(names.get("d"));
        Predicate<Path> condition = p -> !p.toFile().getName().endsWith(names.get("e"));
        List<File> sources = Find.search(start, condition).stream().map(Path::toFile).collect(Collectors.toList());
        new Zip().packFiles(sources, Paths.get(names.get("o")).toFile());
    }
}

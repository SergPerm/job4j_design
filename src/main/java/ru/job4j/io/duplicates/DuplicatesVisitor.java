package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> setOriginalFiles = new HashSet<>();
    List<Path> listDuplicateFiles = new ArrayList<>();
    FileProperty temp;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        temp = new FileProperty(file.toFile().getName(), file.toFile().length());
        if (!setOriginalFiles.add(temp)) {
            listDuplicateFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }
}

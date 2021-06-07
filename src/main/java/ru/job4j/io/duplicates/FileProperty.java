package ru.job4j.io.duplicates;

import java.util.Objects;

public class FileProperty {
    private String name;
    private long size;

    public FileProperty(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.size && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}

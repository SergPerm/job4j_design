package ru.job4j.collection.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private final SimpleArray<T> simpSet = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (this.contains(value)) {
            return false;
        }
        simpSet.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : this) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return simpSet.iterator();
    }
}

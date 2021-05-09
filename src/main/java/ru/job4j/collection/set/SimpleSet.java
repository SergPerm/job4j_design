package ru.job4j.collection.set;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;
import java.util.Optional;

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
        Iterator<T> it = simpSet.iterator();
        Optional<T> val = Optional.ofNullable(value);
        while (it.hasNext()) {
            T t = it.next();
            if (t != null) {
                if (t.equals(value)) {
                    return true;
                }
            } else if (val.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < simpSet.getSize();
            }

            @Override
            public T next() {
                return simpSet.get(point++);
            }
        };
    }
}

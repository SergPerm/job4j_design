package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public void push(T value) {
        in.push(value);
    }

    public T poll() {
        if (out.getSize() == 0 && in.getSize() == 0) {
            throw new NoSuchElementException();
        } else if (out.getSize() == 0 && in.getSize() != 0) {
            while (in.getSize() > 0) {
                out.push(in.pop());
            }
            return out.pop();
        } else {
            return out.pop();
        }
    }
}

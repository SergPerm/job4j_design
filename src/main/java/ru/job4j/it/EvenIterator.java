package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int point;

    public EvenIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        while (point < numbers.length && numbers[point] % 2 != 0) {
            point++;
        }
        return point < numbers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}

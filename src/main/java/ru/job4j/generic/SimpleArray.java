package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable {
    private final T[] array;
    private int size;

    public SimpleArray(T[] array) {
        this.array = array;
        this.size = (int) Arrays.stream(array)
                .filter(Objects::nonNull)
                .count();
    }

    /**
     * возвращает итератор, предназначенный для обхода данной структуры.
     * @return - итератор для обхода данной структуры.
     */
    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(array).iterator();
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     * @param model -  значение элемента;
     */
    public void add(T model) {
        array[size++] = model;
    }

    /**
     * заменяет указанным элементом (model) элемент,
     * находящийся по индексу index;
     * @param index - индекс элемента;
     * @param model - значение элемента;
     */
     public void set(int index, T model) {
         Objects.checkIndex(index, size);
         array[index] = model;
     }

    /**
     * удаляет элемент по указанному индексу;
     * @param index - индекс элемента;
     */
    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(array,
                index + 1,
                array,
                index,
                array.length - index - 1);
        size--;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     * @param index - индекс элемента;
     * @return - возвращает элемент массива;
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }
}

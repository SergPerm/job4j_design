package ru.job4j.collection.list;

import java.util.*;

public class SimpleArrayList<T> implements Iterable {
    private Object[] array;
    private int size;
    private long modCount;

    public SimpleArrayList() {
        this.array = new Object[5];
        this.size = 0;
        modCount = 0;
    }

    public SimpleArrayList(int length) {
        this.array = new Object[length];
        this.size = 0;
        modCount = 0;
    }

    /**
     * возвращает итератор, предназначенный для обхода данной структуры.
     * @return - итератор для обхода данной структуры.
     */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;
            final long expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[point++];
            }
        };
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку;
     * @param model -  значение элемента;
     */
    public void add(T model) {
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) array.length * 2);
        }
        array[size++] = model;
        modCount++;
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
        modCount++;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     * @param index - индекс элемента;
     * @return - возвращает элемент массива;
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }
}

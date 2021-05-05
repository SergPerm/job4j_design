package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int modCount;

    /**
     * при создании нового списка, инициализируем головной {@code head} и хвастовой {@code tail} узлы
     * значениями null, а счётчики размера и количества изменений 0.
     */
    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * добавляет элемент в конец списка
     * @param value - значение элемента.
     */
    @Override
    public void add(E value) {
        final Node<E> last = tail;
        final Node<E> newNode = new Node<>(value, null);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * проходит по списку в поиске элемента по индексу, и возвращает его
     * @param index - искомый индекс.
     * @return - найденный элемент.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.value;
    }

    @Override
    public E deleteFirst() {
        Node<E> current = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = current.next;
        current.next = null;
        return current.value;
    }

    /**
     * возвращает итератор для прохода по списку
     * @return - итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;
            final long expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}

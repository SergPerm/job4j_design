package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    private int modCount;

    public void addFirst(T value) {
        final Node<T> current = head;
        final Node<T> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        }
        head.next = current;
        modCount++;
    }

    public void add(T value) {
        final Node<T> last = tail;
        final Node<T> newNode = new Node<>(value, null);
        tail = newNode;
        if (last == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        modCount++;
    }

    public T deleteFirst() {
        Node<T> current = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = current.next;
        current.next = null;
        modCount++;
        size--;
        return current.value;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
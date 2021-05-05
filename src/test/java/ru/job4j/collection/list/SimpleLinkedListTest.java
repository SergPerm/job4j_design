package ru.job4j.collection.list;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetFromNoSuchElementThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> first = list.iterator();
        first.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> first = list.iterator();
        first.next();
        list.add(4);
        first.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        List<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        List<Integer> linked = new SimpleLinkedList<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        List<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }
}
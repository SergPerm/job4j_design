package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void iterator() {
        SimpleArray<Integer> simple = new SimpleArray<>(5);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        Iterator<Integer> it = simple.iterator();
        assertThat(1, is(it.next()));
        assertThat(2, is(it.next()));
        assertThat(true, is(it.hasNext()));
        assertThat(3, is(it.next()));
        assertThat(4, is(it.next()));
        assertThat(false, is(it.hasNext()));

    }

    @Test
    public void whenAdd10ThenGet10() {
        SimpleArray<Integer> simple = new SimpleArray<>(5);
        simple.add(10);
        assertThat(10, is(simple.get(0)));
    }

    @Test
    public void whenAdd10ThenSet12AndGet12() {
        SimpleArray<Integer> simple = new SimpleArray<>(5);
        simple.add(10);
        simple.set(0, 12);
        assertThat(12, is(simple.get(0)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAdd10ThenSet12InIndex1() {
        SimpleArray<Integer> simple = new SimpleArray<>(5);
        simple.add(10);
        simple.set(1, 12);
    }

    @Test
    public void remove() {
        SimpleArray<Integer> simple = new SimpleArray<>(5);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.remove(1);
        assertThat(3, is(simple.get(1)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveAndGetLastElement() {
        SimpleArray<Integer> simple = new SimpleArray<>(5);
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.remove(1);
        simple.get(3);
    }

    @Test
    public void get() {
        SimpleArray<String> simple = new SimpleArray<>(5);
        simple.add("1");
        simple.add("two");
        simple.add("3");
        String tmp = simple.get(1);
        assertEquals(tmp, "two");
    }
}
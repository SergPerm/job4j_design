package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutDifferent() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "serg"));
        assertTrue(simpleMap.put(2, "serg"));
        assertFalse(simpleMap.put(2, "serg"));
    }

    @Test
    public void whenPutEquals() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(2, "serg"));
        assertFalse(simpleMap.put(2, "serg"));
    }

    @Test
    public void whenPut9Different() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "serg"));
        assertTrue(simpleMap.put(2, "serg"));
        assertTrue(simpleMap.put(3, "serg"));
        assertTrue(simpleMap.put(4, "serg"));
        assertTrue(simpleMap.put(5, "serg"));
        assertTrue(simpleMap.put(6, "serg"));
        assertTrue(simpleMap.put(7, "serg"));
        assertTrue(simpleMap.put(8, "serg"));
        assertTrue(simpleMap.put(9, "petr"));
        assertFalse(simpleMap.put(9, "serg"));
        assertThat(simpleMap.get(9), is("petr"));
    }

    @Test
    public void whenPutAndGet() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "petr");
        simpleMap.put(2, "serg");
        assertThat(simpleMap.get(2), is("serg"));
    }

    @Test
    public void whenPutAndGetNull() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "petr");
        simpleMap.put(2, null);
        assertNull(simpleMap.get(2));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "petr");
        simpleMap.put(2, "serg");
        assertTrue(simpleMap.remove(2));
        assertNull(simpleMap.get(2));
        assertFalse(simpleMap.remove(3));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iterator() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "petr");
        simpleMap.put(2, "serg");
        Iterator it = simpleMap.iterator();
        it.hasNext();
        simpleMap.put(3, "alex");
        it.hasNext();

    }
}
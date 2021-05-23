package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (key == null) {
            return false;
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexForKey(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = indexForKey(key);
        MapEntry res = table[index];
        if (res != null && res.key.equals(key)) {
            return (V) res.value;
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        int index = indexForKey(key);
        if (table[index] == null) {
            return false;
        }
        if (table[index].key == key || table[index].key.equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final MapEntry<K, V>[] oldTable = table;
            final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int pointNext = point;
                boolean result = false;
                while (pointNext < oldTable.length) {
                    if (oldTable[pointNext++] != null) {
                        result = true;
                        point = pointNext - 1;
                        break;
                    }
                }
                return result;

            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return oldTable[point++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private int indexForKey(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> mE : oldTable) {
            if (mE != null) {
                put(mE.key, mE.value);
            }
        }
        modCount++;
    }

    public static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}

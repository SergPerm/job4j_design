package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int tmp = findIndexById(id);
        if (tmp != -1) {
            mem.set(tmp, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int tmp = findIndexById(id);
        if (tmp != -1) {
            mem.remove(tmp);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int tmp = findIndexById(id);
        if (tmp != -1) {
            return mem.get(tmp);
        }
        return null;
    }

    private int findIndexById(String id) throws NoSuchElementException {
        int tmp = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                tmp = i;
                break;
            }
        }
        return tmp;
    }

}

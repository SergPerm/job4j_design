package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        mem.set(findIndexById(id), model);
        return mem.get(findIndexById(id)).equals(model);
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        return mem.get(findIndexById(id));
    }

    private int findIndexById(String id) {
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

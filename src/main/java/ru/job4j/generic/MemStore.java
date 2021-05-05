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
        return true;
    }

//    @Override
//    public boolean replace(String id, T model) {
//        for (int i = 0; i < mem.size(); i++) {
//            if (mem.get(i).getId().equals(id)) {
//                mem.set(i, model);
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean delete(String id) {
        mem.remove(findIndexById(id));
        return true;
    }

//    @Override
//    public boolean delete(String id) {
//        T tmp = findById(id);
//        return mem.remove(tmp);
//    }

    @Override
    public T findById(String id) {
        return mem.get(findIndexById(id));
    }
//
//    @Override
//    public T findById(String id) {
//        T tmp = null;
//        for (T t : mem) {
//            if (t.getId().equals(id)) {
//                tmp = t;
//                break;
//            }
//        }
//        return tmp;
//    }

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

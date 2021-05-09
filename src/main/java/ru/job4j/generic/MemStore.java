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
        int tmp = findIndexById(id);
        if (tmp != -1) {
            mem.remove(tmp);
            return true;
        }
        return false;
    }

//    @Override
//    public boolean delete(String id) {
//        T tmp = findById(id);
//        return mem.remove(tmp);
//    }

    @Override
    public T findById(String id) {
        int tmp = findIndexById(id);
        if (tmp != -1) {
            return mem.get(tmp);
        }
        return null;
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

    private int findIndexById(String id) throws NoSuchElementException {
        int tmp = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                tmp = i;
                break;
            }
        }
//        if (tmp == -1) {
//            System.out.println("нет элемента с таким id");
//            throw new NoSuchElementException("no such id");
//        }
        return tmp;
    }

}

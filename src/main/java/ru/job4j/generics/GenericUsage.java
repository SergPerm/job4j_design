package ru.job4j.generics;

import java.util.*;

public class GenericUsage {

    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println("Текущий элемент : " + next);
        }
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add(new Person("name", 21, new Date(913716000000L)));
        Object o = list.get(3);
        List<Integer> l = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Количество элементов в списке: " + list.size());
        new GenericUsage().printRsl(list);
        System.out.println("Количество элементов в списке: " + l.size());
        new GenericUsage().printRsl(l);

    }
}
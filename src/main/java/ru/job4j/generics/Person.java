package ru.job4j.generics;

import java.util.Date;

public class Person {
    private String name;
    private int i;
    private  Date date;

    public Person(String name, int i, Date date) {
        this.name = name;
        this.i = i;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", i=" + i
                + ", date=" + date
                + '}';
    }
}

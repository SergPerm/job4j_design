package ru.job4j.collection.hash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar bd = new GregorianCalendar(2005, 6, 18);
        User user1 = new User("Serg", 1, bd);
        User user2 = new User("Serg", 1, bd);
        Map<User, Object> map = new HashMap<User, Object>();
        map.put(user1, new Object());
        System.out.println("user1 hashCode : " + user1.hashCode());
        map.put(user2, new Object());
        System.out.println("user2 hashCode : " + user2.hashCode());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

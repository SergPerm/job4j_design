package ru.job4j.collection.hash;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
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

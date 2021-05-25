package ru.job4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {
    private final Map<Integer, String> mapPrevious = new HashMap<>();
    private final Info info = new Info();

    public Info diff(List<User> previous, List<User> current) {
        for (User user : previous) {
            mapPrevious.put(user.id, user.name);
        }
        for (User us : current) {
            if (mapPrevious.containsKey(us.id)) {
                if (!mapPrevious.get(us.id).equals(us.name)) {
                    info.changed++;
                }
                mapPrevious.remove(us.id);
            } else {
                info.added++;
            }
        }
        info.deleted = mapPrevious.size();
        return info;

    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id && name.equals(user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }
}

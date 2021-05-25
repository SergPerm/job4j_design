package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenNotChanges() {
        Analize.User user1 = new Analize.User(123, "Vikont");
        Analize.User user2 = new Analize.User(456, "Ilya");
        Analize.User user3 = new Analize.User(789, "Dush");
        Analize.User user4 = new Analize.User(147, "Serg");
        Analize.User user5 = new Analize.User(258, "Petr");
        List<Analize.User> previous = List.of(user1, user2, user3);
        List<Analize.User> current = List.of(user1, user2, user3);
        Analize analize = new Analize();
        Analize.Info info = new Analize.Info();
        info.deleted = 0;
        info.changed = 0;
        info.added = 0;
        assertEquals(analize.diff(previous, current), info);
    }

    @Test
    public void whenOnlyAdded() {
        Analize.User user1 = new Analize.User(123, "Vikont");
        Analize.User user2 = new Analize.User(456, "Ilya");
        Analize.User user3 = new Analize.User(789, "Dush");
        Analize.User user4 = new Analize.User(147, "Serg");
        Analize.User user5 = new Analize.User(258, "Petr");
        List<Analize.User> previous = List.of(user1, user2, user3);
        List<Analize.User> current = List.of(user1, user2, user3, user4, user5);
        Analize analize = new Analize();
        Analize.Info info = new Analize.Info();
        info.deleted = 0;
        info.changed = 0;
        info.added = 2;
        assertEquals(analize.diff(previous, current), info);
    }

    @Test
    public void whenOnlyDeleted() {
        Analize.User user1 = new Analize.User(123, "Vikont");
        Analize.User user2 = new Analize.User(456, "Ilya");
        Analize.User user3 = new Analize.User(789, "Dush");
        Analize.User user4 = new Analize.User(147, "Serg");
        Analize.User user5 = new Analize.User(258, "Petr");
        List<Analize.User> previous = List.of(user1, user2, user3, user4, user5);
        List<Analize.User> current = List.of(user2, user3);
        Analize analize = new Analize();
        Analize.Info info = new Analize.Info();
        info.deleted = 3;
        info.changed = 0;
        info.added = 0;
        assertEquals(analize.diff(previous, current), info);
    }

    @Test
    public void whenOnlyChanged() {
        Analize.User user1 = new Analize.User(123, "Vikont");
        Analize.User user2 = new Analize.User(456, "Ilya");
        Analize.User user3 = new Analize.User(789, "Dush");
        Analize.User user4 = new Analize.User(147, "Serg");
        Analize.User user5 = new Analize.User(258, "Petr");
        List<Analize.User> previous = List.of(user1, user2, user3);
        Analize.User user31 = new Analize.User(789, "Dushes");
        List<Analize.User> current = List.of(user1, user2, user31);
        Analize analize = new Analize();
        Analize.Info info = new Analize.Info();
        info.deleted = 0;
        info.changed = 1;
        info.added = 0;
        assertEquals(analize.diff(previous, current), info);
    }

    @Test
    public void whenAddedAndChanged() {
        Analize.User user1 = new Analize.User(123, "Vikont");
        Analize.User user2 = new Analize.User(456, "Ilya");
        Analize.User user3 = new Analize.User(789, "Dush");
        Analize.User user4 = new Analize.User(147, "Serg");
        Analize.User user5 = new Analize.User(258, "Petr");
        List<Analize.User> previous = List.of(user1, user2, user3);
        Analize.User user31 = new Analize.User(789, "Dushes");
        List<Analize.User> current = List.of(user1, user5, user2, user31, user4);
        Analize analize = new Analize();
        Analize.Info info = new Analize.Info();
        info.deleted = 0;
        info.changed = 1;
        info.added = 2;
        assertEquals(analize.diff(previous, current), info);
    }

    @Test
    public void whenAddedDeletedChanged() {
        Analize.User user1 = new Analize.User(123, "Vikont");
        Analize.User user2 = new Analize.User(456, "Ilya");
        Analize.User user3 = new Analize.User(789, "Dush");
        Analize.User user4 = new Analize.User(147, "Serg");
        Analize.User user5 = new Analize.User(258, "Petr");
        List<Analize.User> previous = List.of(user1, user2, user3);
        Analize.User user31 = new Analize.User(789, "Dushes");
        List<Analize.User> current = List.of(user5, user2, user31, user4);
        Analize analize = new Analize();
        Analize.Info info = new Analize.Info();
        info.deleted = 1;
        info.changed = 1;
        info.added = 2;
        assertEquals(analize.diff(previous, current), info);
    }
}
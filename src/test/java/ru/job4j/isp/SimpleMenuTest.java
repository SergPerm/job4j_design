package ru.job4j.isp;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class SimpleMenuTest {

    @Test
    public void whenPrintMenuThenMenu() {
        SimpleMenu<String> sm = new SimpleMenu<>("item1", new SimpleAction("action1"));
        sm.add("item2", "item1", new SimpleAction("action2"));
        sm.add("item3", "item2", new SimpleAction("action3"));
        sm.add("item4", "item1", new SimpleAction("action4"));
        sm.add("item5", "item4", new SimpleAction("action5"));
        sm.add("item6", "item4", new SimpleAction("action6"));
        sm.add("item7", "item6", new SimpleAction("action7"));
        sm.add("item8", "item6", new SimpleAction("action8"));
        sm.add("item9", "item2", new SimpleAction("action9"));
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("Задача 1. Node = item1")
                .add("  Задача 1.1. Node = item2")
                .add("    Задача 1.1.1. Node = item3")
                .add("    Задача 1.1.2. Node = item9")
                .add("  Задача 1.2. Node = item4")
                .add("    Задача 1.2.1. Node = item5")
                .add("    Задача 1.2.2. Node = item6")
                .add("      Задача 1.2.2.1. Node = item7")
                .add("      Задача 1.2.2.2. Node = item8");
        assertThat(sm.printMenu(), is(result.toString()));
    }
}
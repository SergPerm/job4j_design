package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.search.ArgsName;

/**
 *   Test
 *   public void whenGetFirst() {
 *       ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
 *       assertThat(jvm.get("Xmx"), is("512")); }
 *   Test
 *   public void whenGetFirstReorder() {
 *       ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
 *       assertThat(jvm.get("Xmx"), is("512")); }
 *    Test(expected = IllegalArgumentException.Class)
 *   public void whenWrongSomeArgument() {
 *       ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx="});
 *   }
 */
public class ArgsNameTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {});
        jvm.get("Xmx");
    }

}
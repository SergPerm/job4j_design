package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
            out.println("400 12:05:06");
            out.println("500 13:15:15");
        }
        Analizy an = new Analizy();
        an.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rslt = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(rslt::append);
        }
        assertThat(rslt.toString(), is("10:57:01;10:59:01;11:01:02;11:02:02;12:05:06;13:15:15;"));
    }
}
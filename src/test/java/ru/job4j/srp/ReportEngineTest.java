package ru.job4j.srp;

import org.junit.Test;
import ru.job4j.srp.report.*;
import ru.job4j.srp.store.MemStore;
import java.util.Calendar;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReportEngineTest {

    @Test
    public void whenSimpleGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employer = new Employee("Ivan", now, now, 100);
        store.add(employer);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(employer.getName()).append(";")
                .append(employer.getHired()).append(";")
                .append(employer.getFired()).append(";")
                .append(employer.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenBookkeepingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employer = new Employee("Ivan", now, now, 100);
        store.add(employer);
        Report engine = new BookkeepingReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(employer.getName()).append(";")
                .append(employer.getHired()).append(";")
                .append(employer.getFired()).append(";")
                .append(MoneyChange.toGroshi(employer.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHrReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee serg = new Employee("Serg", now, now, 100);
        Employee petr = new Employee("Petr", now, now, 120);
        store.add(serg);
        store.add(petr);
        Report engine = new HRReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(petr.getName()).append(";")
                .append(petr.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(serg.getName()).append(";")
                .append(serg.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }


    @Test
    public void whenProgrammerReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee serg = new Employee("Serg", now, now, 1000);
        store.add(serg);
        Report engine = new ProgrammerReport(store);
        StringJoiner text = new StringJoiner(System.lineSeparator());
        text.add("<!DOCTYPE html>");
        text.add("<html>");
        text.add("<head>");
        text.add("<meta charset=\"UTF-8\">");
        text.add("<title>Employers</title>");
        text.add("</head>");
        text.add("<body>");
        text.add("<table>");
        text.add("<tr>");
        text.add("<th>Name</th>");
        text.add("<th>Hired</th>");
        text.add("<th>Fired</th>");
        text.add("<th>Salary</th>");
        text.add("</tr>");
        text.add("<tr>");
        text.add(String.format("<td>%s</td>", serg.getName()));
        text.add(String.format("<td>%s</td>", serg.getHired()));
        text.add(String.format("<td>%s</td>", serg.getFired()));
        text.add(String.format("<td>%s</td>", serg.getSalary()));
        text.add("</tr>");
        text.add("</table>");
        text.add("</body>");
        text.add("</html>");

        assertThat(engine.generate(em -> true), is(text.toString()));
    }
}
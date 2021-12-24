package ru.job4j.srp;

import org.junit.Test;
import ru.job4j.srp.report.*;
import ru.job4j.srp.store.MemStore;
import java.text.SimpleDateFormat;
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

    @Test
    public void whenXMLReport() {
        MemStore store = new MemStore();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Calendar now = Calendar.getInstance();
        Employee serg = new Employee("Serg", now, now, 100);
        Employee petr = new Employee("Petr", now, now, 120);
        store.add(serg);
        store.add(petr);
        Report engine = new XMLReport(store);
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n"
                + "<xmlReport>" + "\n"
                + "    <employees>" + "\n"
                + String.format("        <fired>%s</fired>",
                        formatter.format(serg.getFired().getTime())) + "\n"
                + String.format("        <hired>%s</hired>",
                        formatter.format(serg.getHired().getTime())) + "\n"
                + "        <name>Serg</name>" + "\n"
                + "        <salary>100.0</salary>" + "\n"
                + "    </employees>" + "\n"
                + "    <employees>" + "\n"
                + String.format("        <fired>%s</fired>",
                        formatter.format(petr.getFired().getTime())) + "\n"
                + String.format("        <hired>%s</hired>",
                        formatter.format(petr.getHired().getTime())) + "\n"
                + "        <name>Petr</name>" + "\n"
                + "        <salary>120.0</salary>" + "\n"
                + "    </employees>" + "\n"
                + "</xmlReport>" + "\n"
                + "";
        assertThat(engine.generate(em -> true), is(text));
    }

    @Test
    public void whenJsonReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee serg = new Employee("Serg", now, now, 100);
        Employee petr = new Employee("Petr", now, now, 120);
        store.add(serg);
        store.add(petr);
        Report engine = new JsonReport(store);
        String text = "[{"
                + String.format("\"name\":\"%s\","
                                + "\"hired\":{\"year\":%d,\"month\":%d,\"dayOfMonth\":%d,"
                                + "\"hourOfDay\":%d,\"minute\":%d,\"second\":%d},"
                                + "\"fired\":{\"year\":%d,\"month\":%d,\"dayOfMonth\":%d,"
                                + "\"hourOfDay\":%d,\"minute\":%d,\"second\":%d},"
                                + "\"salary\":%s",
                        serg.getName(),
                        now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), now.get(Calendar.SECOND),
                        now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), now.get(Calendar.SECOND),
                        serg.getSalary())
                + "},{"
                + String.format("\"name\":\"%s\","
                                + "\"hired\":{\"year\":%d,\"month\":%d,\"dayOfMonth\":%d,"
                                + "\"hourOfDay\":%d,\"minute\":%d,\"second\":%d},"
                                + "\"fired\":{\"year\":%d,\"month\":%d,\"dayOfMonth\":%d,"
                                + "\"hourOfDay\":%d,\"minute\":%d,\"second\":%d},"
                                + "\"salary\":%s",
                        petr.getName(),
                        now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), now.get(Calendar.SECOND),
                        now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), now.get(Calendar.SECOND),
                        petr.getSalary())
                + "}]";
        assertThat(engine.generate(em -> true), is(text));
    }
}

package ru.job4j.srp.report;

import ru.job4j.srp.Employee;
import ru.job4j.srp.store.Store;

import java.util.StringJoiner;
import java.util.function.Predicate;

public class ProgrammerReport implements Report {

    private final Store store;

    public ProgrammerReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
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
        for (Employee employer : store.findBy(filter)) {
            text.add("<tr>");
            text.add(String.format("<td>%s</td>", employer.getName()));
            text.add(String.format("<td>%s</td>", employer.getHired()));
            text.add(String.format("<td>%s</td>", employer.getFired()));
            text.add(String.format("<td>%s</td>", employer.getSalary()));
            text.add("</tr>");
        }
        text.add("</table>");
        text.add("</body>");
        text.add("</html>");
        return text.toString();
    }
}

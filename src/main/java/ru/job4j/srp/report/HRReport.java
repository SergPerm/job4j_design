package ru.job4j.srp.report;

import ru.job4j.srp.Employee;
import ru.job4j.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HRReport implements Report {

    private final Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter)
                .stream()
                .sorted((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()))
                .collect(Collectors.toList());
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

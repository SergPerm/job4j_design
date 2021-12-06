package ru.job4j.srp.report;

import ru.job4j.srp.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}

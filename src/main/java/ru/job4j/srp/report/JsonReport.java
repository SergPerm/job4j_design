package ru.job4j.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.srp.Employee;
import ru.job4j.srp.store.Store;

import java.util.function.Predicate;

public class JsonReport implements Report {

    private final Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var lib = new GsonBuilder().create();
        return lib.toJson(store.findBy(filter));
    }
}
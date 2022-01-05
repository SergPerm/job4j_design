package ru.job4j.isp;

public class SimpleAction implements Action {

    private String name;

    public SimpleAction(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void doSomething() {
        System.out.println(" action name = " + name);

    }
}

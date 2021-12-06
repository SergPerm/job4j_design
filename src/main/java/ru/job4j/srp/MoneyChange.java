package ru.job4j.srp;

public class MoneyChange {

    private static final double GROSHI = 0.1;

    public static String toGroshi(double salary) {
        return String.format("%.2f", salary / GROSHI);
    }
}



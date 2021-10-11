package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Serg";
        int countFriends = 176;
        float weight = 78.5f;
        short height = 176;
        char sex = 'm';
        boolean isProgrammer = true;
        double salary = 250000.56;
        long skillLevel = 100500L;
        byte age = 45;
/*
  для проверки оставляли только один уровень логирования
 */
        LOG.trace("trace message");
        LOG.debug("User name: {}, sex: {}, age {}, height: {}, weight: {}, "
                + "is a programmer: {}, skillLevel {}, have friends {}, have salary : {}", name,
                sex, age, height, weight, isProgrammer, skillLevel, countFriends, salary);
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}

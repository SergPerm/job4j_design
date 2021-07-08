package ru.job4j.io.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Person {
    private final String name;
    private final int age;
    private final Contact contact;
    private final boolean isSpeakEnglish;
    private final Sex sex;
    private final String[] statuses;

    public Person(String name, int age, Contact contact, boolean isSpeakEnglish, Sex sex, String... statuses) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.isSpeakEnglish = isSpeakEnglish;
        this.sex = sex;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + ", age=" + age
                + ", sex=" + sex
                + ", isSpeakEnglish=" + isSpeakEnglish
                + ", Contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + "}";
    }

    public static void main(String[] args) {
        Person person = new Person("Serg", 45,
                new Contact(614111, "8(902)47-44-350"),
                true, Sex.MALE, "student", "married");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        final String personJson = "{"
                + "\"name\":\"Mary\","
                + "\"age\":43,"
                + "\"contact\":{\"zipCode\":614111,\"phone\":\"8(902)80-82-651\"},"
                + "\"isSpeakEnglish\":true,"
                + "\"sex\":\"FEMALE\","
                + "\"statuses\":[\"worker\",\"married\"]}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}

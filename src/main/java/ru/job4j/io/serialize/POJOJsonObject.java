package ru.job4j.io.serialize;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class POJOJsonObject {
    public static void main(String[] args) {
        Person person = new Person("Serg", 45,
                new Contact(614111, "8(902)47-44-350"),
                true, Sex.MALE, "student", "married");

        JSONObject jsonContact = new JSONObject("{\"zipCode\":614111,\"phone\":\"8(902)80-82-651\"}");

        List<String> statuses = new ArrayList<>();
        statuses.add("Worker");
        statuses.add("Married");
        JSONArray jsonStatuses = new JSONArray(statuses);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", person.getName());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("isSpeakEnglish", person.isSpeakEnglish());
        jsonObject.put("gender", person.getSex());
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject);
        System.out.println();
        System.out.println(new JSONObject(person));
    }
}

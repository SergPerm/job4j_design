package ru.job4j.io.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private String name;
    private int age;
    private Contact contact;
    private boolean isSpeakEnglish;

    @XmlElement(name = "gender")
    private Sex sex;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Person() {
    }

    public Person(String name, int age, Contact contact, boolean isSpeakEnglish, Sex sex, String... statuses) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.isSpeakEnglish = isSpeakEnglish;
        this.sex = sex;
        this.statuses = statuses;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public boolean isSpeakEnglish() {
        return isSpeakEnglish;
    }

    public Sex getSex() {
        return sex;
    }

    public String[] getStatuses() {
        return statuses;
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

    public static void main(String[] args) throws JAXBException {
        Person person = new Person("Serg", 45,
                new Contact(614111, "8(902)47-44-350"),
                true, Sex.MALE, "student", "married");
        /**
         * от "final Gson1 gson = new GsonBuilder().create()";
         * временный код, для другого задания
         * до System.out.println(personMod);
         */
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

        String xml = "";
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person personFromXml = (Person) unmarshaller.unmarshal(reader);
            System.out.println(personFromXml);
        }

    }
}

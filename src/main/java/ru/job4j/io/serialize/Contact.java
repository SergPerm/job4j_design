package ru.job4j.io.serialize;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.nio.file.Files;

@XmlRootElement(name = "contact")
public class Contact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    private int zipCode;
    @XmlAttribute
    private String phone;

    public Contact() {
    }

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone
                + "'}";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(614111, "8(902)47-44-350");
        System.out.println(contact);
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}

package ru.job4j.io.serialize;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gender")
public enum Sex {
    MALE,
    FEMALE
}

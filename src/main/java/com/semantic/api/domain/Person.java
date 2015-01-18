package com.semantic.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Person implements FeedItem {
    private final String name;
    private final String age;
    private final String palceOfBirth;

    public Person(String name, String age, String palceOfBirth) {
        this.name = name;
        this.age = age;
        this.palceOfBirth = palceOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPalceOfBirth() {
        return palceOfBirth;
    }
}

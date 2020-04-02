package com.security.Models;

/**
 * Created by CAPTAN GHOURI on 31/03/2020.
 */
public class Student {

    private int age;
    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

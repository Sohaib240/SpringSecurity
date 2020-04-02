package com.security.Models;

/**
 * Created by CAPTAN GHOURI on 31/03/2020.
 */
public class Teacher {
    private String name;
    private String subject;
    private int experiance;

    public Teacher(String name, String subject, int experiance) {
        this.name = name;
        this.subject = subject;
        this.experiance = experiance;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public int getExperiance() {
        return experiance;
    }
}

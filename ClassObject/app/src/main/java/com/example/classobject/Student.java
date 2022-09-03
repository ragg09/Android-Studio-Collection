package com.example.classobject;

import android.widget.TextView;

public class Student {
    String name;
    Integer year;
    String section;
    String course;

    public Student(String name, Integer year, String section, String course) {
        this.name = name;
        this.year = year;
        this.section = section;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

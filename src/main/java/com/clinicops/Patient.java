package com.clinicops;

public class Patient {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String mobileNumber;

    public Patient(String id, String name, String gender, int age, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Gender: %s | Age: %d | Mobile: %s",
                id, name, gender, age, mobileNumber);
    }
}

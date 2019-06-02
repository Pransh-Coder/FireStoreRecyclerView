package com.example.firestorerecyclerview;

public class Users {
    String Name,age;
    public Users(){
    }

    public Users(String name, String age) {
        Name = name;
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

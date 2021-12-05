package com.example.computershop;

import java.io.Serializable;
import java.util.Objects;

public class Personal extends Humans implements Serializable {
    private String post;
    private double salary;

    public Personal() {

    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Personal(String name, String Surname, String ThirdName, String MobilePhone, String BirthDate, String post, double salary) {
        super.setName(name);
        super.setSurname(Surname);
        super.setThirdName(ThirdName);
        super.setMobilePhone(MobilePhone);
        super.setBirthDate(BirthDate);
        super.setId(hashCode());
        this.post = post;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "Name='" + super.getName() + '\'' +
                ", Surname='" + super.getSurname() + '\'' +
                ", ThirdName='" + super.getThirdName() + '\'' +
                ", MobilePhone=" + super.getMobilePhone() +
                ", BirthDate=" + super.getBirthDate() +
                ", id=" + super.getId() +
                ", post='" + post + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal)) return false;
        Personal personal = (Personal) o;
        return Double.compare(personal.getSalary(), getSalary()) == 0 && Objects.equals(getPost(), personal.getPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPost(), getSalary(), super.getName());
    }
}
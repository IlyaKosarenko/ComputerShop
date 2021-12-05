package com.example.computershop;

import java.io.Serializable;
import java.util.Objects;

public class Clients extends Humans implements Serializable {
    private String coupon;

    public Clients() {

    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Clients(String name, String Surname, String ThirdName, String MobilePhone, String BirthDate, String coupon) {
        super.setName(name);
        super.setSurname(Surname);
        super.setThirdName(ThirdName);
        super.setMobilePhone(MobilePhone);
        super.setBirthDate(BirthDate);
        super.setId(hashCode());
        this.coupon = coupon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clients)) return false;
        Clients clients = (Clients) o;
        return Objects.equals(getCoupon(), clients.getCoupon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoupon(), super.getName());
    }

    @Override
    public String toString() {
        return "Clients{" +
                "coupon='" + coupon + '\'' +
                ", Name='" + super.getName() + '\'' +
                ", Surname='" + super.getSurname() + '\'' +
                ", ThirdName='" + super.getThirdName() + '\'' +
                ", MobilePhone=" + super.getMobilePhone() +
                ", BirthDate=" + super.getBirthDate() +
                ", id=" + super.getId() +
                '}';
    }
}

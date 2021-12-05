package com.example.computershop;

import java.io.Serializable;

public class Goods extends ComputerShop implements Serializable {
    private String type;
    private String name;
    private double cost;
    private int quanity;

    public Goods(String type, String name, double cost, int quanity) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.quanity = quanity;
    }
    public Goods() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}

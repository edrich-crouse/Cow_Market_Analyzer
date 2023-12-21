package com.example.cowproject;

public class Cow {
    int year;
    Type type;
    Classes classes;
    int weight;
    double price;

    public Cow(int year, Type type, Classes classes, int weight, double price) {
        this.year = year;
        this.type = type;
        this.classes = classes;
        this.weight = weight;
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public Type getType() {
        return type;
    }

    public Classes getClasses() {
        return classes;
    }

    public int getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public void editYear(int newYear) {
        this.year = newYear;
    }

    public void editType(Type newType) {
        this.type = newType;
    }

    public void editClasses(Classes newClasses) {
        this.classes = newClasses;
    }

    public void editWeight(int newWeight) {
        this.weight = newWeight;
    }

    public void editPrice(double newPrice) {
        this.price = newPrice;
    }
}

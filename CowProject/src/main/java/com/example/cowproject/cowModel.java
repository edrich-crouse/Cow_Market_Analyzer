package com.example.cowproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class cowModel {
    ListView<Cow> cowList;
    ObservableList<Cow> cows;
    ArrayList<cowModelSubscriber> subs;
    int yearModel;
    int weightModel;
    double priceModel;
    Type typeModel;
    Classes classesModel;
    int cowError;
    XYChart.Series currentSeries;
    Boolean hasCowListChanged;

    public cowModel() {
        cowList = new ListView<Cow>();
        cows = FXCollections.observableArrayList();
        cowList.setItems(cows);
        subs = new ArrayList<>();

        yearModel = 0;
        weightModel = 0;
        typeModel = Type.Premium;
        priceModel = 0;
        classesModel = Classes.Heifer;
        cowError = 0;

        currentSeries = new XYChart.Series();
        hasCowListChanged = false;
    }

    public void clearCowModel() {
        cows.clear();
        notifySubscribers();
    }

    protected Boolean getHasCowListChanged() {
        return hasCowListChanged;
    }

    protected void setHasCowListChanged(Boolean newValue) {
        hasCowListChanged = newValue;
        changeCurrentSeries(typeOfSeries.totalAverage);
    }

    public int getNumberOfCows() {
        return cows.size();
    }

    public XYChart.Series getCurrentSeries() {
        return currentSeries;
    }

    public void changeCurrentSeries(typeOfSeries newSeries) {
        currentSeries.getData().clear();

        switch (newSeries) {
            case totalAverage:
                int year = 0;
                double price = 0;
                if (cows != null) {
                    for (Cow cow : cows) {
                        currentSeries.getData().add(new XYChart.Data(cow.year, cow.price));
                    }
                }
                break;

            case weight100:
                break;
        }
    }

    public int getErrorCode () {
        return cowError;
    }

    public int getYear(){
        return yearModel;
    }

    public int getWeight(){
        return weightModel;
    }

    public Type getType(){
        return typeModel;
    }

    public double getPrice(){
        return priceModel;
    }

    public Classes getClasses(){
        return classesModel;
    }

    public void changeErrorCode (int newError) {
        cowError = newError;
        notifySubscribers();
    }

    public void changeYear(int newYear) {
        yearModel = newYear;
        notifySubscribers();
    }

    public void changeWeight(int newWeight) {
        weightModel = newWeight;
        notifySubscribers();
    }

    public void changePrice(double newPrice) {
        priceModel = newPrice;
        notifySubscribers();
    }

    public void changeType(Type newType) {
        typeModel = newType;
        notifySubscribers();
    }

    public void changeClass(Classes newClass) {
        classesModel = newClass;
        notifySubscribers();
    }

    public ListView<Cow> getCowList() {
        return cowList;
    }

    public void addCow(Cow newCow) {
        cows.add(newCow);
        for (Cow cow : this.cows) {
            System.out.println(cow.year);
        }
        changeCurrentSeries(typeOfSeries.totalAverage);
        setHasCowListChanged(true);
        notifySubscribers();
    }

    public int getLowestYear() {
        int lowestYear = 3000;

        for (Cow cow : this.cows) {
            if (cow.year < lowestYear) {
                lowestYear = cow.year;
            }
        }

        return lowestYear;
    }

    public double getAveragePriceForWeight300400 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 300 && cow.weight <= 400 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeight400500 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 400 && cow.weight <= 500 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeight500600 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 500 && cow.weight <= 600 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeight600700 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 600 && cow.weight <= 700 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeight700800 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 700 && cow.weight <= 800 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeight800900 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 800 && cow.weight <= 900 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeight9001000 (int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 900 && cow.weight <= 1000 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getAveragePriceForWeightOver1000(int year) {
        double averagePrice = 0;
        int counter = 0;

        for(Cow cow : this.cows) {
            if (cow.weight > 1000 && cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }

        averagePrice = averagePrice/counter;

        return averagePrice;
    }

    public double getTotalAveragePrice(int year) {
        double averagePrice = 0;
        int counter = 0;

        for (Cow cow : this.cows) {
            if (cow.year == year) {
                averagePrice += cow.price;
                counter += 1;
            }
        }
        if (counter != 0) {
            averagePrice = averagePrice / counter;
        }

        System.out.println(averagePrice);
        return averagePrice;
    }

    public int getHighestYear() {
        int highestYear = 0;

        for(Cow cow : this.cows) {
            if (cow.year > highestYear) {
                highestYear = cow.year;
            }
        }

        return highestYear;
    }

    public void addSubscriber(cowModelSubscriber newSub) {
        subs.add(newSub);
    }

    private void notifySubscribers() {
        subs.forEach(cowModelSubscriber::modelUpdated);
    }
}

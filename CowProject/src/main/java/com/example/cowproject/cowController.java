package com.example.cowproject;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

import java.lang.Exception;

public class cowController {
    cowModel cowModel;

    public cowController() {
    }

    public void setCowModel(cowModel newCowModel) {
        cowModel = newCowModel;
    }

    public void handleClearClick(ActionEvent event) {
        cowModel.clearCowModel();
    }

    public int handleAddCowClick(ActionEvent event) {
            if (cowModel.getErrorCode() == 0) {
                Cow addNewCow = new Cow(cowModel.getYear(), cowModel.getType(), cowModel.getClasses(), cowModel.getWeight(), cowModel.getPrice());
                cowModel.addCow(addNewCow);
                return 0;
            } else {
                return cowModel.getErrorCode();
            }
    }

    public void handleYearTextChange(String newValue) {
        int newYear;
        // change the error value to no error when the listener activates and then check if error is still there
        cowModel.changeErrorCode(0);
        System.out.println("year text changed from " + cowModel.yearModel + " to " + newValue);
        if (newValue == null || newValue == "") {
             newYear = 0;
        } else {
            try {
                newYear = Integer.parseInt(newValue);
                cowModel.changeYear(newYear);
            } catch (Exception e) {
                //change the error code to the error for the year text box
                cowModel.changeErrorCode(1);
            }
        }
    }

    public void handleWeightTextChange(String newValue) {
        int newWeight;
        // change the error value to no error when the listener activates and then check if error is still there
        cowModel.changeErrorCode(0);
        System.out.println("weight text changed from " + cowModel.weightModel + " to " + newValue);
        if (newValue == null || newValue == "") {
            newWeight = 0;
        } else {
            try {
                newWeight = Integer.parseInt(newValue);
                cowModel.changeWeight(newWeight);
            } catch (Exception e) {
                // change the error code to the error for the weight text box
                cowModel.changeErrorCode(2);
            }

        }

    }

    public void handlePriceTextChange(String newValue) {
        double newPrice;
        // change the error value to no error when the listener activates and then check if error is still there
        cowModel.changeErrorCode(0);
        System.out.println("price text changed from " + cowModel.priceModel + " to " + newValue);
        if (newValue == null || newValue == "") {
            newPrice = 0;
        } else {
            try {
                newPrice = Double.parseDouble(newValue);
                cowModel.changePrice(newPrice);
                if (newPrice < 10.0) {
                    cowModel.changeErrorCode(5);
                }
            } catch (Exception e) {
                // change the error code to the error for the price text box
                cowModel.changeErrorCode(3);
            }
        }

    }

    public void handleTypeChange(String newValue) {
        Type newType = Type.Premium;
        System.out.println("type changed from " + cowModel.typeModel + " to " + newValue);
        switch (newValue) {
            case "Regular":
                newType = Type.Regular;
                break;

            case "Premium (PS)":
                newType = Type.Premium;
                break;
        }
        cowModel.changeType(newType);
    }

    public void handleClassChange(String newValue) {
        Classes newClass = Classes.Heifer;
        System.out.println("type changed from " + cowModel.classesModel + " to " + newValue);
        switch (newValue) {
            case "Steer":
                newClass = Classes.Steer;
                break;

            case "Cow":
                newClass = Classes.Cow;
                break;

            case "Bull":
                newClass = Classes.Bull;
                break;

            case "Heifer":
                newClass = Classes.Heifer;
                break;
        }
        cowModel.changeClass(newClass);
    }
}

/**
 * Class for the visual components of the applications of the cow price analyzer
 *
 * @author Edrich Crouse
 */

package com.example.cowproject;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.*;
import java.util.Scanner;


public class cowView extends StackPane implements cowModelSubscriber {
    //private final Object cowCell;
    int averageWeight300400;
    int averageWeight400500;
    int averageWeight500600;
    int averageWeight600700;
    int averageWeight700800;
    int averageWeightOver800;

    LineChart<String, Number> cowLineChart;

    ListView<Cow> cowListView;

    cowModel cowModel;
    Scanner cowScanner;

    double textBoxHeight = 25;
    double textBoxWidth = 120;

    Label errorMessage;

    Label title;

    Label yearTitle;
    TextArea yearTextBox;

    Label weightTitle;
    TextArea weightTextBox;

    Label priceTitle;
    TextArea priceTextBox;

    Label classTitle;
    ComboBox classDropDown;
    ObservableList classList;

    Label typeTitle;
    ComboBox typeDropDown;
    ObservableList typeList;

    Button addCow;

    Button clearCowModel;

    Button compareChartButton;

    Button addFile;
    TextArea fileTextBox;

    XYChart.Series cowSeriesTotalAveragePrice = new XYChart.Series();

    /**
     * Constructor/Setup for how the application looks
     */
    public cowView() {
        // error message that is blank when there is no error and has a message when there is
        errorMessage = new Label ("");
        errorMessage.setTextFill(Color.color(1,0,0));
        errorMessage.setFont(Font.font(16));

        // error HBox
        HBox errorHBox = new HBox(errorMessage);
        errorHBox.setAlignment(Pos.CENTER);

        // title properties
        title = new Label("Cow Price Analyzer");
        title.setFont(Font.font(32));

        // title HBox
        HBox titleHBox = new HBox();
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.getChildren().add(title);

        // year properties
        yearTitle = new Label("Year (XXXX): ");
        yearTitle.setFont(Font.font(18));

        yearTextBox = new TextArea();
        yearTextBox.setPromptText("e.g. 2023");
        yearTextBox.setPrefHeight(textBoxHeight);
        yearTextBox.setPrefWidth(textBoxWidth);


        // HBox for year
        HBox yearHBox = new HBox(yearTitle, yearTextBox);

        // type properties
        typeTitle = new Label("Type: ");
        typeTitle.setFont(Font.font(18));

        typeList = FXCollections.observableArrayList();
        typeList.addAll("Premium (PS)", "Regular");
        typeDropDown = new ComboBox(typeList);
        typeDropDown.setPrefHeight(textBoxHeight);
        typeDropDown.setPrefWidth(textBoxWidth);
        typeDropDown.getSelectionModel().selectFirst();

        // HBox for type
        HBox typeHBox = new HBox(typeTitle, typeDropDown);

        // class properties
        classTitle = new Label("Class: ");
        classTitle.setFont(Font.font(18));

        classList = FXCollections.observableArrayList();
        classList.addAll("Heifer", "Steer", "Bull", "Cow");
        classDropDown = new ComboBox(classList);
        classDropDown.setPrefHeight(textBoxHeight);
        classDropDown.setPrefWidth(textBoxWidth);
        classDropDown.getSelectionModel().selectFirst();

        // HBox for class
        HBox classHBox = new HBox(classTitle, classDropDown);

        // HBox for year, class, and type
        HBox yctHBox = new HBox(yearHBox, typeHBox, classHBox);
        yctHBox.setAlignment((Pos.CENTER));
        yctHBox.setSpacing(30);

        // weight properties
        weightTitle = new Label("Weight (lbs): ");
        weightTitle.setFont(Font.font(18));

        weightTextBox = new TextArea();
        weightTextBox.setPromptText("lbs");
        weightTextBox.setPrefHeight(textBoxHeight);
        weightTextBox.setPrefWidth(textBoxWidth);

        // HBox for weight
        HBox weightHBox = new HBox(weightTitle, weightTextBox);

        // price properties
        priceTitle = new Label("Price (Cents): ");
        priceTitle.setFont(Font.font(18));

        priceTextBox = new TextArea();
        priceTextBox.setPromptText("Cents");
        priceTextBox.setPrefHeight(textBoxHeight);
        priceTextBox.setPrefWidth(textBoxWidth);

        // HBox for price
        HBox priceHBox = new HBox(priceTitle, priceTextBox);


        // HBox for weight and price
        HBox wpHBox = new HBox(weightHBox, priceHBox);
        wpHBox.setAlignment(Pos.CENTER);
        wpHBox.setSpacing(30);

        // button for adding cows to model
        addCow = new Button("Add Cow");
        addCow.setPrefHeight(textBoxHeight);
        addCow.setPrefWidth(textBoxWidth);

        // Button for removing all cows from model
        clearCowModel = new Button("Remove All Cows");
        clearCowModel.setPrefHeight(textBoxHeight);
        clearCowModel.setPrefWidth(textBoxWidth);

        /*
        addCow.setOnAction(event -> {

            // make a new cow with the values inputted in the form
            int addYear;
            if (yearTextBox.getText() == null || yearTextBox.getText() == "") {
                addYear = 0;
            } else {
                addYear = Integer.parseInt(yearTextBox.getText());
            }

            Type addType = Type.Premium;
            switch ((String) typeDropDown.getValue()) {
                case "Regular":
                    addType = Type.Regular;
                    break;

                case "Premium (PS)":
                    addType = Type.Premium;
                    break;
            }

            Classes addClass = Classes.Heifer;
            switch ((String) classDropDown.getValue()) {
                case "Steer":
                    addClass = Classes.Steer;
                    break;

                case "Cow":
                    addClass = Classes.Cow;
                    break;

                case "Bull":
                    addClass = Classes.Bull;
                    break;

                case "Heifer":
                    addClass = Classes.Heifer;
                    break;
            }

            int addWeight;
            if (weightTextBox.getText() == null || weightTextBox.getText().equals("")) {
                addWeight = 0;
            } else {
                addWeight = Integer.parseInt(weightTextBox.getText());
            }

            double addPrice;
            if (priceTextBox.getText() == null || priceTextBox.getText().equals("")) {
                addPrice = 0;
            } else {
                addPrice = Double.parseDouble(priceTextBox.getText());
            }

            Cow addNewCow = new Cow(addYear, addType, addClass, addWeight, addPrice);

            cowModel.addCow(addNewCow);
        });
        */

        //HBox for add cow button
        HBox addCowHBox = new HBox();
        addCowHBox.getChildren().addAll(addCow,clearCowModel);
        addCowHBox.setAlignment(Pos.CENTER);
        addCowHBox.setSpacing(20);

        /*
        // list of items in model
        ListView<Cow> cowListView = cowModel.cowList;
        cowListView.setItems(cowModel.cows);
         */
        cowListView = new ListView<>();
        cowListView.setCellFactory(new Callback<ListView<Cow>, ListCell<Cow>>() {
            @Override
            public ListCell<Cow> call(ListView<Cow> cowListView) {
                return new cowCell();
            }
        });

        //cowListView.setCellFactory((param -> new cowCell()));
        
        cowListView.setPrefHeight(150);
        cowListView.setPrefWidth(400);


        //HBox for list
        HBox cowModelHBox = new HBox(cowListView);
        cowModelHBox.setAlignment(Pos.CENTER);

        // button for adding a file to the model
        addFile = new Button("Add File");
        addFile.setPrefHeight(textBoxHeight);
        addFile.setPrefWidth(textBoxWidth);

        addFile.setOnAction(event -> {
            try {
                try {
                    cowScanner = new Scanner(new File(".\\" + fileTextBox.getText()));
                    cowScanner.useDelimiter(",|\n");
                } catch (FileNotFoundException e) {
                    cowScanner = new Scanner(new File(".\\CowData.csv"));
                    cowScanner.useDelimiter(",|\n");
                }

                String yearToParse = "";
                String typeToParse = "";
                String classToParse = "";
                String weightToParse = "";
                String priceToParse = "";
                String tokenToParse = "";

                int newYear = 0;
                Type newType = Type.Premium;
                Classes newClass = Classes.Cow;
                int newWeight = 0;
                double newPrice = 0;

                int i = 0;
                while (cowScanner.hasNext()) {
                    if (i < 6) {
                        cowScanner.next();
                    } else {
                        tokenToParse = cowScanner.next();
                        tokenToParse = tokenToParse.replace("\n", "").replace("\r", "");
                        if (tokenToParse.equals("")) {
                            i++;
                            continue;
                        }
                        if (i % 6 == 0) {
                            if (tokenToParse.length() > 4) {
                                // only want to use the first four numbers
                                tokenToParse = tokenToParse.substring(0, 4);
                                newYear = Integer.parseInt(tokenToParse);
                                System.out.println(newYear);
                            } else {
                                newYear = 0;
                                System.out.println("something wrong with int parser");
                            }
                        } else if (i % 6 == 1) {
                            ;
                            if (tokenToParse.equals("PS")) {
                                newType = Type.Premium;
                            } else {
                                newType = Type.Regular;
                            }
                            System.out.println(newType);
                        } else if (i % 6 == 2) {
                            if (tokenToParse.equals("Heifer")) {
                                newClass = Classes.Heifer;
                            } else if (tokenToParse.equals("Steer")) {
                                newClass = Classes.Steer;
                            } else if (tokenToParse.equals("Bull")) {
                                newClass = Classes.Bull;
                            } else {
                                newClass = Classes.Cow;
                            }
                            System.out.println(newClass);
                        } else if (i % 6 == 3) {

                        } else if (i % 6 == 4) {
                            newWeight = Integer.parseInt(tokenToParse);
                            System.out.println(newWeight);
                        } else {
                            newPrice = Double.parseDouble(tokenToParse);
                            System.out.println(newPrice);
                            Cow cowToAdd = new Cow(newYear, newType, newClass, newWeight, newPrice);
                            cowModel.addCow(cowToAdd);
                        }
                    }
                    i++;
                }

                cowScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("problem with reading file");

            }
        });

        // text area for adding file
        fileTextBox = new TextArea();
        fileTextBox.setPromptText("abc.csv");
        fileTextBox.setPrefHeight(textBoxHeight);
        fileTextBox.setPrefWidth(textBoxWidth);

        // HBox for add file button and text box
        HBox addFileHBox = new HBox(fileTextBox, addFile);
        addFileHBox.setAlignment(Pos.CENTER);
        addFileHBox.setSpacing(20);

        // make compare button
        compareChartButton = new Button("Select Compare Options");


        // make label that shows what the user is comparing right now
        Label compareButtonText = new Label("Current Selection: ");
        Label currentSelection = new Label("All");
        HBox compareHBox = new HBox(compareButtonText, currentSelection);
        compareHBox.setSpacing(10);
        compareHBox.setAlignment(Pos.CENTER);

        // HBox for line chart buttons
        HBox lineChartButtons = new HBox(compareChartButton, compareHBox);
        lineChartButtons.setAlignment(Pos.CENTER);
        lineChartButtons.setSpacing(20);

        // HBox for line chart
        HBox addLineChart = new HBox();
        addLineChart.setAlignment(Pos.CENTER);
        addLineChart.setSpacing(20);


        // making a line chart from the data in the model when clicked

        // defining the labels for the axis
        final NumberAxis xAxis = new NumberAxis(2010, 2023, .5);
        xAxis.setLabel("Year");

        final NumberAxis yAxis = new NumberAxis(0, 700, 20);
        yAxis.setLabel("Price in Cents Per Pound");

        cowLineChart = new LineChart(xAxis, yAxis);


        xAxis.setAutoRanging(true);
        xAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);

        cowLineChart.setAnimated(false);
        cowLineChart.setTitle("Average Price in Cents Per Year");
        cowLineChart.setPrefHeight(600);
        cowLineChart.setPrefWidth(800);

        /*

        XYChart.Series cowSeriesWeight300400 = new XYChart.Series();
        cowSeriesWeight300400.setName("Weight Category 300-400 lbs");

        int currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight300400.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight300400(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeight400500 = new XYChart.Series();
        cowSeriesWeight400500.setName("Weight Category 400-500 lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight400500.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight400500(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeight500600 = new XYChart.Series();
        cowSeriesWeight500600.setName("Weight Category 500-600 lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight500600.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight500600(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeight600700 = new XYChart.Series();
        cowSeriesWeight600700.setName("Weight Category 600-700 lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight600700.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight600700(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeight700800 = new XYChart.Series();
        cowSeriesWeight700800.setName("Weight Category 700-800 lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight700800.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight700800(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeight800900 = new XYChart.Series();
        cowSeriesWeight800900.setName("Weight Category 800-900 lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight800900.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight800900(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeight9001000 = new XYChart.Series();
        cowSeriesWeight9001000.setName("Weight Category 900-1000 lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeight9001000.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeight9001000(currentYear)));
            currentYear += 1;
        }

        XYChart.Series cowSeriesWeightOver1000 = new XYChart.Series();
        cowSeriesWeightOver1000.setName("Weight Category 1000+ lbs");

        currentYear = 2020;

        for (int i = 0; i < 3; i++) {
            cowSeriesWeightOver1000.getData().add(new XYChart.Data(currentYear, cowModel.getAveragePriceForWeightOver1000(currentYear)));
            currentYear += 1;
        }

        cowLineChart.getData().addAll(cowSeriesWeight300400, cowSeriesWeight400500, cowSeriesWeight500600, cowSeriesWeight600700, cowSeriesWeight700800, cowSeriesWeight800900,
                cowSeriesWeight9001000, cowSeriesWeightOver1000);


         */
        addLineChart.getChildren().add(cowLineChart);
        addLineChart.setPrefWidth(700);

        VBox root1 = new VBox();
        root1.getChildren().addAll(errorHBox, titleHBox, yctHBox, wpHBox, addCowHBox, cowModelHBox, addFileHBox, lineChartButtons);
        root1.setPadding(new Insets(50));
        root1.setSpacing(30);
        root1.setPrefWidth(750);
        root1.setPrefHeight(700);
        root1.setAlignment(Pos.CENTER);

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(root1, addLineChart);

        /*
        VBox lineup = new VBox();
        lineup.getChildren().add(root);
        lineup.setAlignment(Pos.CENTER);

         */

        this.getChildren().add(root);
    }

    /**
     * Method used by CowProject.java to link the model to the view
     *
     * @param newCowModel The model used for the application from cowModel.java
     */
    public void setCowModel(cowModel newCowModel) {
        cowModel = newCowModel;

        cowListView.setItems(cowModel.cows);

        modelUpdated();
    }

    /**
     * Method used by CowProject.java to link the controller to the view
     *
     * @param newCowController the controller used for the application from cowController.java
     */
    public void setCowController(cowController newCowController) {

        yearTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
            newCowController.handleYearTextChange(newValue);
        });

        weightTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
            newCowController.handleWeightTextChange(newValue);
        });

        priceTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
            newCowController.handlePriceTextChange(newValue);
        });

        typeDropDown.valueProperty().addListener((observable, oldValue, newValue) -> {
            newCowController.handleTypeChange((String) newValue);
        });

        classDropDown.valueProperty().addListener((observable, oldValue, newValue) -> {
            newCowController.handleClassChange((String) newValue);
        });

        cowListView.getItems().addListener(new ListChangeListener<Cow>() {
            @Override
            public void onChanged(Change<? extends Cow> change) {
                cowModel.setHasCowListChanged(true);
                modelUpdated();
            }
        });

        addCow.setOnAction(event -> {
            newCowController.handleAddCowClick(event);
        });

        clearCowModel.setOnAction(newCowController::handleClearClick);

        // show the popup that shows the compare options
        compareChartButton.setOnAction(event -> comparePopup.display());

    }

    /**
     * Method used to update all parts of the view that are linked to the model. When the model
     * is changed the view is notified and updated according to below.
     */
    @Override
    public void modelUpdated() {
        // the autoranging does weird stuff when you clear and add empty series
        if (cowModel.getHasCowListChanged() == true) {
            cowLineChart.getData().clear();
            cowLineChart.getData().add(cowModel.getCurrentSeries());
            cowModel.setHasCowListChanged(false);
        }
        System.out.println(cowModel.getNumberOfCows());

        switch (cowModel.getErrorCode()) {
            case 0:
                errorMessage.setText("");
                break;

            case 1:
                errorMessage.setText("Invalid year.");
                break;

            case 2:
                errorMessage.setText("Invalid weight.");
                break;

            case 3:
                errorMessage.setText("Invalid price. Price is in cents. ($2.3 = 230 cents)");
                break;

            case 4:
                errorMessage.setText("File not found. Please check spelling.");
                break;

            case 5:
                errorMessage.setText("Price is in cents. ($2.30 = 230 cents)");
                break;
        }
    }
}

package com.example.cowproject;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.util.ArrayList;
import java.util.List;

public class comparePopup {

    public static void display() {
        Stage cowPopupStage = new Stage();

        cowPopupStage.initModality(Modality.APPLICATION_MODAL);
        cowPopupStage.setTitle("Select Compare Options for Line Chart");

        VBox popup = new VBox();
        popup.setSpacing(20);

        Label compareTitle = new Label("Please select which compare options appear on the line chart.");

        GridPane popupStage = new GridPane();
        popupStage.setAlignment(Pos.CENTER);
        popupStage.setHgap(20);
        popupStage.setVgap(20);

        popup.getChildren().addAll(compareTitle, popupStage);

        List<CheckBox> compareOptionsList = new ArrayList<CheckBox>();

        String compareOptions[] = {"Total Average", "300-400 lbs Weight", "400-500 lbs Weight", "500-600 lbs Weight",
                "600-700 lbs Weight", "700-800 lbs Weight", "800-900 lbs Weight", "900-1000 lbs Weight", "1000+ lbs Weight",
                "Steers Only", "Heifers Only", "Cows Only", "Bulls Only", "Regular Only", "Premium Only"};

        int j = 0;
        for (int i = 0; i < compareOptions.length; i++) {

            if ((i % 3 == 0) && (i != 0)) {
                j++;
            }

            // create a new checkbox for each option
            CheckBox option = new CheckBox(compareOptions[i]);

            // add the checkbox to the page
            popupStage.add(option, i % 3, j);

            // make each one false to start except total average
            option.setIndeterminate(false);
            if (i == 0) {
                option.setSelected(true);
            } else {
                option.setSelected(false);
            }

            // add the option to the list so we can reference it later
            compareOptionsList.add(option);
        }

        Button saveButton = new Button("Save");

        saveButton.setOnAction(event -> cowPopupStage.close());

        popup.getChildren().add(saveButton);
        popup.setAlignment(Pos.CENTER);

        Scene cowPopupScene = new Scene(popup, 600, 500);
        cowPopupStage.setScene(cowPopupScene);
        cowPopupStage.showAndWait();

    }

}

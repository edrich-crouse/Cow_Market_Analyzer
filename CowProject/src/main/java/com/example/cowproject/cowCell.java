package com.example.cowproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class cowCell extends ListCell<Cow> {
    HBox cowCellHBox = new HBox();
    Label yearLabel = new Label("");
    Label typeLabel = new Label("");
    Label classLabel = new Label("");
    Label weightLabel = new Label("");
    Label priceLabel = new Label("");
    Pane pane = new Pane();
    Button button = new Button("X");

    public cowCell() {
        super();

        cowCellHBox.getChildren().addAll(yearLabel, typeLabel, classLabel, weightLabel, priceLabel, pane, button);
        cowCellHBox.setSpacing(5);
        HBox.setHgrow(pane, Priority.ALWAYS);

        button.setOnAction(event -> {
            getListView().getItems().remove(getItem());
        });

    }

    @Override
    protected void updateItem(Cow item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item!= null && !empty) {
            yearLabel.setText(Integer.toString(item.year));
            typeLabel.setText(item.type.name());
            classLabel.setText(item.classes.name());
            weightLabel.setText(Integer.toString(item.weight));
            priceLabel.setText(Double.toString(item.price));

            setGraphic(cowCellHBox);
        } else {
            setText("");
        }
    }
}

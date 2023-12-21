package com.example.cowproject;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CowProject extends Application {
    @Override
    public void start(Stage stage) {

        // MVC setup
        cowView cView = new cowView();
        cowModel model = new cowModel();
        cowController controller = new cowController();
        cView.setCowModel(model);
        cView.setCowController(controller);
        controller.setCowModel(model);
        model.addSubscriber(cView);

        // layout
        StackPane root = new StackPane();
        root.getChildren().add(cView);

        ScrollPane page = new ScrollPane();
        page.setContent(root);
        page.setFitToWidth(true);

        Scene scene = new Scene(page);
        stage.setTitle("Cow Project");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
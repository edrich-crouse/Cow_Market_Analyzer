module com.example.cowproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cowproject to javafx.fxml;
    exports com.example.cowproject;
}
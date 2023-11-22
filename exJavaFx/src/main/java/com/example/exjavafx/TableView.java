package com.example.exjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class TableView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final Pane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("table.fxml")));
        Scene scene = new Scene(pane, 600,400);
        stage.setScene(scene);
        stage.show();

//        Product p = new Product(1, "Opala", 1 , 14999);
//        save(p);
    }
}

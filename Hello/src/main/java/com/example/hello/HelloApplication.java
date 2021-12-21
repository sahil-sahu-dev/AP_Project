package com.example.hello;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
        //Scene scene = new Scene(fxmlLoader, 320, 240);
        stage.setTitle("Snakes and Ladders");
        stage.setScene(new Scene(fxmlLoader));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
 }
package com.example.hello;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LandingPage {

    private Parent root;
    private Scene scene;
    private Stage stage;

    public void onStartGameButtonClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try{
            root = loader.load();


            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(IOException exception){
            System.out.println(exception.getLocalizedMessage());
        }
    }
}

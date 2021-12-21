package com.example.hello;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOver {

    @FXML
    private Label winnerName;

    public void setWinnerName(String name) {
        winnerName.setText(name + " wins");
    }
}

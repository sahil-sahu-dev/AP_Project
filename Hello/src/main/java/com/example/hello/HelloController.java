package com.example.hello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.util.ArrayList;



class Pair {

    double x;
    double y;

    public Pair(double x, double y) {
        this.x = x;
        this.y = y;
    }
}


public class HelloController {

    static private int numOfClicks = 0;
    ArrayList<Pair> loc = new ArrayList<Pair>();

    public HelloController () {
        double locx = 100;
        double locy = 480;
        int current_row = 1;
        int current_col = 1;

        for(int i = 0;i<10;i++) {

            for(int j = 1;j<=10;j++) {
                if(i%2==0){
                    if(j < 10) {
                        loc.add(new Pair(locx+50, locy));
                        locx+=50;
                    }
                    else {
                        loc.add(new Pair(locx,locy-50));
                        locy-=50;
                    }
                }
                else{
                    if(j < 10) {
                        loc.add(new Pair(locx-50, locy));
                        locx-=50;
                    }
                    else {
                        loc.add(new Pair(locx,locy-50));
                        locy-=50;
                    }
                }

            }
        }
    }

    @FXML
    private Button next;

    @FXML
    private Label label;

    @FXML
    private ImageView image;

    @FXML
    private Button b;



    public void click(ActionEvent event) {

        label.setText("X:"+String.valueOf(loc.get(numOfClicks).x)+"Y:"+String.valueOf(loc.get(numOfClicks).y) + " Index = " + numOfClicks);
//        Bounds bis= b.localToScene(b.getBoundsInLocal());
//
//        double orgx = bis.getMinX();
//        double orgy = bis.getMinY();
//
//        System.out.println("org x =" + orgx + "org y = " + orgy);


        b.setLayoutX(loc.get(numOfClicks).x);
        b.setLayoutY(loc.get(numOfClicks).y);

        numOfClicks+=1;

    }

}
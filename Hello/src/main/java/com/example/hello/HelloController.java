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


//class BoardElement{
//
//    private Pair originalPosition;
//    private Pair newPosition;
//
//    public void addElement(Pair originalPosition, Pair newPosition) {
//        this.originalPosition = originalPosition;
//        this.newPosition = newPosition;
//    }
//}

class Snake {
    private Pair originalPosition;
    private Pair newPosition;


    public Snake(Pair originalPosition, Pair newPosition) {
        this.originalPosition = originalPosition;
        this.newPosition = newPosition;
    }

    public Snake(){}

    public Pair getNewPosition() {
        return newPosition;
    }

    public Pair getOriginalPosition() {
        return originalPosition;
    }
}

class Ladder {
    private Pair originalPosition;
    private Pair newPosition;


    public Ladder(Pair originalPosition, Pair newPosition) {
        this.originalPosition = originalPosition;
        this.newPosition = newPosition;
    }

    public Ladder(){}

    public Pair getNewPosition() {
        return newPosition;
    }

    public Pair getOriginalPosition() {
        return originalPosition;
    }
}


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
    ArrayList<Snake> snakes = new ArrayList<Snake>();
    ArrayList<Ladder> ladders = new ArrayList<Ladder>();

    public HelloController () {System.out.println(numOfClicks);
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


        //creating list for snakes
        createSnakesList(snakes, loc);

        //creatinglist for ladders
        createLaddersList(ladders, loc);


    }


    private void createSnakesList(ArrayList<Snake> a,ArrayList<Pair> loc) {

            //create snakes list

            a.add(new Snake(loc.get(8), loc.get(4)));
            a.add(new Snake(loc.get(20), loc.get(16)));
            a.add(new Snake(loc.get(33), loc.get(12)));
            a.add(new Snake(loc.get(61), loc.get(36)));
            a.add(new Snake(loc.get(74), loc.get(41)));
            a.add(new Snake(loc.get(66), loc.get(49)));
            a.add(new Snake(loc.get(67), loc.get(47)));
            a.add(new Snake(loc.get(86), loc.get(69)));
            a.add(new Snake(loc.get(97), loc.get(80)));

    }

    public void createLaddersList(ArrayList<Ladder> a, ArrayList<Pair> loc) {


            a.add(new Ladder(loc.get(2), loc.get(14)));
            a.add(new Ladder(loc.get(15), loc.get(37)));
            a.add(new Ladder(loc.get(24), loc.get(33)));
            a.add(new Ladder(loc.get(19), loc.get(58)));
            a.add(new Ladder(loc.get(30), loc.get(46)));
            a.add(new Ladder(loc.get(54), loc.get(72)));
            a.add(new Ladder(loc.get(60), loc.get(78)));
            a.add(new Ladder(loc.get(84), loc.get(92)));
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

        int indexOfSnake = containsSnake(loc.get(numOfClicks).x, loc.get(numOfClicks).y);
        int indexOfLadder = containsLadder(loc.get(numOfClicks).x, loc.get(numOfClicks).y);



        if(indexOfSnake>=0) {
            //theres a snake here. Location in snakes is at indexOfSnakes

            numOfClicks = loc.indexOf(snakes.get(indexOfSnake).getNewPosition()) +1;

            b.setLayoutX(snakes.get(indexOfSnake).getNewPosition().x);
            b.setLayoutY(snakes.get(indexOfSnake).getNewPosition().y);



            System.out.println(numOfClicks );
        }

        else if(indexOfLadder>=0) {
            //theres a ladder here. Location in ladders is at indexOfLadder

            numOfClicks = loc.indexOf(ladders.get(indexOfLadder).getNewPosition())+1 ;

            b.setLayoutX(ladders.get(indexOfLadder).getNewPosition().x);
            b.setLayoutY(ladders.get(indexOfLadder).getNewPosition().y);

            System.out.println(numOfClicks);

            //label.setText("X:"+String.valueOf(loc.get(numOfClicks).x)+"Y:"+String.valueOf(loc.get(numOfClicks).y) + " Index = " + numOfClicks);

        }

        else{
            //label.setText("X:"+String.valueOf(loc.get(numOfClicks).x)+"Y:"+String.valueOf(loc.get(numOfClicks).y) + " Index = " + numOfClicks);

            b.setLayoutX(loc.get(numOfClicks).x);
            b.setLayoutY(loc.get(numOfClicks).y);
            numOfClicks+=1;


            System.out.println(numOfClicks);
        }

        //label.setText("X:"+String.valueOf(loc.get(numOfClicks).x)+"Y:"+String.valueOf(loc.get(numOfClicks).y) + " Index = " + numOfClicks);


    }



    public int containsSnake(double x, double y) {

        for(int i = 0;i<snakes.size();i++) {
            if(snakes.get(i).getOriginalPosition().x == x && snakes.get(i).getOriginalPosition().y == y) {
                //snake at this position
                return i; //index of the snake in snakes
            }
        }

        return -1;
    }

    public int containsLadder(double x, double y) {

        for(int i = 0;i<ladders.size();i++) {
            if(ladders.get(i).getOriginalPosition().x == x && snakes.get(i).getOriginalPosition().y == y) {
                //ladder at this position
                return i;
            }
        }

        return -1;
    }

}
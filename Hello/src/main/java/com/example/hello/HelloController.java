package com.example.hello;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import java.io.FileInputStream;


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

    public void setOriginalPosition(Pair originalPosition) {
        this.originalPosition = originalPosition;
    }
    public void setNewPosition(Pair newPosition) {
        this.newPosition = newPosition;
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

    public void setOriginalPosition(Pair originalPosition) {
        this.originalPosition = originalPosition;
    }
    public void setNewPosition(Pair newPosition) {
        this.newPosition = newPosition;
    }

}


class Pair {

    private double x;
    private double y;

    public Pair(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

}


public class HelloController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button next;
    @FXML
    private ImageView image;
    @FXML
    private ImageView blue;
    @FXML
    private  ImageView red;
    @FXML
    private ImageView diceImage;
    @FXML
    private Button b;
    @FXML
    private Label diceRoll;
    @FXML
    private Button b2;


     int firstRan = randomNum();
     int firstRan2 = randomNum();
     private int numOfClicks = firstRan -1;
     private int numOfClicks2 = firstRan2 - 1;
     int roll = 0;
     int roll2 = 0;

    ArrayList<Pair> loc = new ArrayList<Pair>();
    ArrayList<Snake> snakes = new ArrayList<Snake>();
    ArrayList<Ladder> ladders = new ArrayList<Ladder>();

    public HelloController () {


        double locx = 100;
        double locy = 550;
        int current_row = 1;
        int current_col = 1;

        int locationFactorX = 42;
        int locationFactorY = 58;

        for(int i = 0;i<10;i++) {

            for(int j = 1;j<=10;j++) {
                if(i%2==0){
                    if(j < 10) {
                        loc.add(new Pair(locx+locationFactorX, locy));
                        locx+=locationFactorX;
                    }
                    else {
                        loc.add(new Pair(locx,locy-locationFactorY));
                        locy-=locationFactorY;
                    }
                }
                else{
                    if(j < 10) {
                        loc.add(new Pair(locx-locationFactorX, locy));
                        locx-=locationFactorX;
                    }
                    else {
                        loc.add(new Pair(locx,locy-locationFactorY));
                        locy-=locationFactorY;
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

            a.add(new Snake(loc.get(21), loc.get(3)));
            a.add(new Snake(loc.get(30), loc.get(7)));
            a.add(new Snake(loc.get(44), loc.get(23)));
            a.add(new Snake(loc.get(57), loc.get(38)));
            a.add(new Snake(loc.get(64), loc.get(54)));
            a.add(new Snake(loc.get(49), loc.get(9)));
            a.add(new Snake(loc.get(79), loc.get(60)));
            a.add(new Snake(loc.get(90), loc.get(46)));
            a.add(new Snake(loc.get(93), loc.get(52)));
            a.add(new Snake(loc.get(96), loc.get(63)));

    }

    public void createLaddersList(ArrayList<Ladder> a, ArrayList<Pair> loc) {


            a.add(new Ladder(loc.get(0), loc.get(19)));
            a.add(new Ladder(loc.get(4), loc.get(25)));
            a.add(new Ladder(loc.get(6), loc.get(31)));
            a.add(new Ladder(loc.get(14), loc.get(32)));
            a.add(new Ladder(loc.get(22), loc.get(62)));
            a.add(new Ladder(loc.get(36), loc.get(56)));
            a.add(new Ladder(loc.get(68), loc.get(89)));
            a.add(new Ladder(loc.get(61), loc.get(80)));
            a.add(new Ladder(loc.get(71), loc.get(92)));
            a.add(new Ladder(loc.get(83), loc.get(95)));
    }

    int turn = 0;
    boolean hitSnakeOrLadder = false;
    boolean hitSnakeOrLadder2 = false;

    public void click(MouseEvent event) {

        if(turn % 2 == 0) {

            //if we reached the last position of the board

            if(numOfClicks >=99) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
                try{
                    root = loader.load();
                    GameOver gameOver = loader.getController();
                    gameOver.setWinnerName("Blue");

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }catch(IOException exception){
                    System.out.println(exception.getLocalizedMessage());
                }

                return;

            }

            //first button moves on even index

            if(firstRan != -1) {
                setLabelOfDice(firstRan);
                firstRan = -1;
            }else {
                setLabelOfDice(roll);
            }


            int indexOfSnake = containsSnake(loc.get(numOfClicks).x, loc.get(numOfClicks).y);
            int indexOfLadder = containsLadder(loc.get(numOfClicks).x, loc.get(numOfClicks).y);

            roll = randomInt();


            if(indexOfSnake != -1) {
                //theres a snake here. Location in snakes is at indexOfSnakes

                numOfClicks = loc.indexOf(snakes.get(indexOfSnake).getNewPosition())+roll ;

                blue.setLayoutX(snakes.get(indexOfSnake).getNewPosition().x);
                blue.setLayoutY(snakes.get(indexOfSnake).getNewPosition().y);

            }

            else if(indexOfLadder != -1) {
                //theres a ladder here. Location in ladders is at indexOfLadder


                numOfClicks = loc.indexOf(ladders.get(indexOfLadder).getNewPosition()) +roll;

                blue.setLayoutX(ladders.get(indexOfLadder).getNewPosition().x);
                blue.setLayoutY(ladders.get(indexOfLadder).getNewPosition().y);

            }

            else{

                blue.setLayoutX(loc.get(numOfClicks).x);
                blue.setLayoutY(loc.get(numOfClicks).y);

                numOfClicks+= roll;
            }


        }

        else {

            //second button moves on odd index

            //if we reached the last position of the board

            if(numOfClicks2 >=99) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
                try{
                    root = loader.load();
                    GameOver gameOver = loader.getController();
                    gameOver.setWinnerName("Red");

                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }catch(IOException exception){
                    System.out.println(exception.getLocalizedMessage());
                }
                return;
            }

            if(firstRan2 != -1) {
                setLabelOfDice(firstRan2);
                firstRan2 = -1;
            }else{
                setLabelOfDice(roll2);
            }


            int indexOfSnake = containsSnake(loc.get(numOfClicks2).x, loc.get(numOfClicks2).y);
            int indexOfLadder = containsLadder(loc.get(numOfClicks2).x, loc.get(numOfClicks2).y);
            roll2 = randomInt();


            if(indexOfSnake != -1) {
                //theres a snake here. Location in snakes is at indexOfSnakes

                numOfClicks2 = loc.indexOf(snakes.get(indexOfSnake).getNewPosition()) + roll2;

                red.setLayoutX(snakes.get(indexOfSnake).getNewPosition().x);
                red.setLayoutY(snakes.get(indexOfSnake).getNewPosition().y);

            }

            else if(indexOfLadder != -1) {
                //theres a ladder here. Location in ladders is at indexOfLadder

                numOfClicks2 = loc.indexOf(ladders.get(indexOfLadder).getNewPosition()) + roll2;

                red.setLayoutX(ladders.get(indexOfLadder).getNewPosition().x);
                red.setLayoutY(ladders.get(indexOfLadder).getNewPosition().y);

            }

            else{

                red.setLayoutX(loc.get(numOfClicks2).x);
                red.setLayoutY(loc.get(numOfClicks2).y);

                numOfClicks2+= roll2;
            }


        }

        turn++;

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
            if(ladders.get(i).getOriginalPosition().x == x && ladders.get(i).getOriginalPosition().y == y) {
                //ladder at this position
                return i;
            }
        }

        return -1;
    }

    public int randomInt() {
        Random r = new Random();
        int ran = r.nextInt(6) + 1;

        return ran;
    }

    public int randomNum() {
        Random r = new Random();
        int ran = r.nextInt(6) + 1;
        return ran;
    }

    public void setLabelOfDice(int prevDice) {
        diceRoll.setText("You rolled " + String.valueOf(prevDice));
    }


    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Parent getRoot() {
        return this.root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Button getNext() {
        return this.next;
    }

    public void setNext(Button next) {
        this.next = next;
    }

    public ImageView getImage() {
        return this.image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getBlue() {
        return this.blue;
    }

    public void setBlue(ImageView blue) {
        this.blue = blue;
    }

    public ImageView getRed() {
        return this.red;
    }

    public void setRed(ImageView red) {
        this.red = red;
    }

    public ImageView getDiceImage() {
        return this.diceImage;
    }

    public void setDiceImage(ImageView diceImage) {
        this.diceImage = diceImage;
    }

    public Button getB() {
        return this.b;
    }

    public void setB(Button b) {
        this.b = b;
    }

    public Label getDiceRoll() {
        return this.diceRoll;
    }

    public void setDiceRoll(Label diceRoll) {
        this.diceRoll = diceRoll;
    }

    public Button getB2() {
        return this.b2;
    }

    public void setB2(Button b2) {
        this.b2 = b2;
    }

    public int getFirstRan() {
        return this.firstRan;
    }

    public void setFirstRan(int firstRan) {
        this.firstRan = firstRan;
    }

    public int getFirstRan2() {
        return this.firstRan2;
    }

    public void setFirstRan2(int firstRan2) {
        this.firstRan2 = firstRan2;
    }

    public int getNumOfClicks() {
        return this.numOfClicks;
    }

    public void setNumOfClicks(int numOfClicks) {
        this.numOfClicks = numOfClicks;
    }

    public int getNumOfClicks2() {
        return this.numOfClicks2;
    }

    public void setNumOfClicks2(int numOfClicks2) {
        this.numOfClicks2 = numOfClicks2;
    }

    public int getRoll() {
        return this.roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getRoll2() {
        return this.roll2;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }

    public ArrayList<Pair> getLoc() {
        return this.loc;
    }

    public void setLoc(ArrayList<Pair> loc) {
        this.loc = loc;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public void setSnakes(ArrayList<Snake> snakes) {
        this.snakes = snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public void setLadders(ArrayList<Ladder> ladders) {
        this.ladders = ladders;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isHitSnakeOrLadder() {
        return this.hitSnakeOrLadder;
    }

    public boolean getHitSnakeOrLadder() {
        return this.hitSnakeOrLadder;
    }

    public void setHitSnakeOrLadder(boolean hitSnakeOrLadder) {
        this.hitSnakeOrLadder = hitSnakeOrLadder;
    }

    public boolean isHitSnakeOrLadder2() {
        return this.hitSnakeOrLadder2;
    }

    public boolean getHitSnakeOrLadder2() {
        return this.hitSnakeOrLadder2;
    }

    public void setHitSnakeOrLadder2(boolean hitSnakeOrLadder2) {
        this.hitSnakeOrLadder2 = hitSnakeOrLadder2;
    }


}
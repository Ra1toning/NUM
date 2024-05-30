package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class LAB2 extends Application {
    Random random = new Random();

    @Override
    public void start(Stage primaryStage){
        GridPane gameBoard = new GridPane();
        Image X = new Image("C:\\Users\\Newtech\\OneDrive\\Desktop\\x.png");
        Image O = new Image("C:\\Users\\Newtech\\OneDrive\\Desktop\\o.png");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Pane pane = new Pane();
                ImageView imageView = new ImageView();
                if (getRandomXO()) {
                    if (getRandomXO()) {
                        imageView.setImage(X);
                    } else {
                        imageView.setImage(O);
                    }

                }
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(100);
                pane.getChildren().add(imageView);
                gameBoard.add(pane, i, j);
            }
        }
        Scene scene = new Scene(gameBoard, 315, 315);
        primaryStage.setTitle(getClass().getName());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    boolean getRandomXO() {
        return random.nextBoolean();
    }
}
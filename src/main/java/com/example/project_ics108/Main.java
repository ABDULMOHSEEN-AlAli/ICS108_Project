package com.example.project_ics108;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    // created a list that will contain the numbers and deal with duplicated
    public ArrayList<Integer> checkDuplicate = new ArrayList<Integer>();
    public Button[] buttonsList = new Button[9];
    public Button[] resultButtonsList = new Button[16];

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // make a gridPane
        GridPane gridPane = new GridPane();
        // make a new scene
        Scene scene = new Scene(gridPane);
        // make a list to check if the number is taken or not

        // make the 9 buttons that will show out on the screen using for loop

        for (int i = 0; i < buttonsList.length; i++) {
            // a new button is created inside the for loop with a fixed sized
            buttonsList[i] = new Button("0");
            buttonsList[i].setStyle("-fx-font-size: 5em; ");
            buttonsList[i].setMinHeight(100);
            buttonsList[i].setMinWidth(100);
        }
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            buttonsList[i].setOnMouseClicked(mouseEvent ->
                    {

                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            increaseButtonNumber(finalI);
                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            setToHash(finalI);
                        }

                        resultCircleValues();
                    }
            );
        }


        // adding the buttons in the gridPane using a for loop
        int counter = 0; // the counter is used for moving inside the list elements
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                gridPane.add(buttonsList[counter++], j, i);
            }
        }
        for (int i = 0; i < 16; i++) {
            resultButtonsList[i] = new Button();
            resultButtonsList[i].setShape(new Circle(10));
            resultButtonsList[i].setMinWidth(50);
            resultButtonsList[i].setMinHeight(50);
        }
        counter = 0;
        for (int j = 0; j < 5; j++) {
            if (j == 0 || j == 4) {
                for (int k = 0; k < 5; k++) {
                    gridPane.add(resultButtonsList[counter], j, k);
                    GridPane.setMargin(resultButtonsList[counter], new Insets(20, 20, 20, 20));
                    resultButtonsList[counter].setText(0+"");
                    counter++;

                    if(k != 0 && k!= 4){
                        gridPane.add(resultButtonsList[counter], k, j);
                        GridPane.setMargin(resultButtonsList[counter], new Insets(20, 20, 20, 20));
                        resultButtonsList[counter].setText(0+"");

                        counter++;
                    }

                }
            }
        }







//        Button resultCircle = new Button();
//        resultCircle.setShape(new Circle(10));
//        resultCircle.setMinWidth(50);
//        resultCircle.setMinHeight(50);
//        gridPane.add(resultCircle, 1, 0);
//       GridPane.setMargin(resultCircle, new Insets(20, 5, 10, 20));


        stage.setTitle("Magic Square Game");
        stage.setScene(scene);
        stage.show();


    }


    private void setToHash(int buttonIndex) {

        if (!buttonsList[buttonIndex].getText().equals("0")) {
            checkDuplicate.remove(checkDuplicate.indexOf(Integer.parseInt(buttonsList[buttonIndex].getText())));
            buttonsList[buttonIndex].setText("0");
        }
    }


    public void increaseButtonNumber(int buttonIndex) {
        int index = 0;
        boolean isChecked = true;
        if (buttonsList[buttonIndex].getText().equals("0")) {
            while (isChecked) {
                if (!checkDuplicate.contains(index + 1)) {
                    buttonsList[buttonIndex].setText(index + 1 + "");
                    checkDuplicate.add(index + 1);
                    isChecked = false;
                } else {

                    index++;
                }

            }
        } else {
            index = Integer.parseInt(buttonsList[buttonIndex].getText());

            checkDuplicate.remove(checkDuplicate.indexOf(index));
            if (index >= 9) {
                index = 0;
            }

            while (isChecked) {
                if (!checkDuplicate.contains(index + 1)) {
                    buttonsList[buttonIndex].setText(index + 1 + "");
                    checkDuplicate.add(index + 1);

                    isChecked = false;
                } else {

                    index++;
                    if (index >= 9) {
                        index = 0;
                    }


                }
            }
        }
    }
    public void resultCircleValues(){

        resultButtonsList[0].setText(Integer.parseInt(buttonsList[0].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[8].getText())+"");
        resultButtonsList[15].setText(Integer.parseInt(buttonsList[0].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[8].getText())+"");

        resultButtonsList[1].setText(Integer.parseInt(buttonsList[0].getText()) + Integer.parseInt(buttonsList[1].getText()) + Integer.parseInt(buttonsList[2].getText())+"");
        resultButtonsList[9].setText(Integer.parseInt(buttonsList[0].getText()) + Integer.parseInt(buttonsList[1].getText()) + Integer.parseInt(buttonsList[2].getText())+"");

        resultButtonsList[3].setText(Integer.parseInt(buttonsList[3].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[5].getText())+"");
        resultButtonsList[11].setText(Integer.parseInt(buttonsList[3].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[5].getText())+"");

        resultButtonsList[5].setText(Integer.parseInt(buttonsList[6].getText()) + Integer.parseInt(buttonsList[7].getText()) + Integer.parseInt(buttonsList[8].getText())+"");
        resultButtonsList[13].setText(Integer.parseInt(buttonsList[6].getText()) + Integer.parseInt(buttonsList[7].getText()) + Integer.parseInt(buttonsList[8].getText())+"");

        resultButtonsList[2].setText(Integer.parseInt(buttonsList[0].getText()) + Integer.parseInt(buttonsList[3].getText()) + Integer.parseInt(buttonsList[6].getText())+"");
        resultButtonsList[10].setText(Integer.parseInt(buttonsList[0].getText()) + Integer.parseInt(buttonsList[3].getText()) + Integer.parseInt(buttonsList[6].getText())+"");

        resultButtonsList[4].setText(Integer.parseInt(buttonsList[1].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[7].getText())+"");
        resultButtonsList[12].setText(Integer.parseInt(buttonsList[1].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[7].getText())+"");

        resultButtonsList[6].setText(Integer.parseInt(buttonsList[2].getText()) + Integer.parseInt(buttonsList[5].getText()) + Integer.parseInt(buttonsList[8].getText())+"");
        resultButtonsList[14].setText(Integer.parseInt(buttonsList[2].getText()) + Integer.parseInt(buttonsList[5].getText()) + Integer.parseInt(buttonsList[8].getText())+"");

        resultButtonsList[8].setText(Integer.parseInt(buttonsList[2].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[6].getText())+"");
        resultButtonsList[7].setText(Integer.parseInt(buttonsList[2].getText()) + Integer.parseInt(buttonsList[4].getText()) + Integer.parseInt(buttonsList[6].getText())+"");

        for(Button button:resultButtonsList){
            if(Integer.parseInt(button.getText()) == 15)
                button.setStyle("-fx-border-color:green;");
            else
                button.setStyle("-fx-border-color:red;");

        }
    }
}

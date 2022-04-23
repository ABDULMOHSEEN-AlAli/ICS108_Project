package com.example.project_ics108;
// import the FX libraries
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


// make a main class that contain the main function
public class Main extends Application {

    // created a list that will contain the numbers, and deal with duplicated
    public ArrayList<Integer> checkDuplicate = new ArrayList<Integer>();

    // make a list that contain the buttons of the magic square
    public Button[] buttonsList = new Button[9];

    // make a list that contain the circular buttons which has the results
    public Button[] resultButtonsList = new Button[16];
    public Button[] resultButtonsList2 = new Button[16];

// launch the application
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // make a gridPane
        GridPane gridPane = new GridPane();
        // make a new scene
        Scene scene = new Scene(gridPane);

// Make the square buttons

        // make the 9 buttons that will show out on the screen using for loop
        for (int i = 0; i < buttonsList.length; i++) {
            // a new button is created inside the for loop with a fixed sized
            buttonsList[i] = new Button(""); // set text to empty
            buttonsList[i].setStyle("-fx-font-size: 5em; "); // set the size of font
            // set the size
            buttonsList[i].setMinHeight(100);
            buttonsList[i].setMinWidth(100);
        }

// make the buttons clickable and add the handlers using a for loop
        for (int i = 0; i < 9; i++) {
            // make a value that represent the button location in the gridPane
            int buttonLocation = i;
            buttonsList[i].setOnMouseClicked(mouseEvent ->
                    {

                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            // if the click come from Primary button increase the number inside the button
                            increaseButtonNumber(buttonLocation);
                          // if the click come from the secondary button so set the button to empty
                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            setToEmpty(buttonLocation);
                        }
                        // after make a change in the numbers call the function that will update the circleButtonsValue
                        resultCircleValues();
                    }
            );
        }

// set the buttons in the grid pane

        // adding the  buttons in the gridPane using a for loop
        int counter = 0; // the counter is used for moving inside the list elements
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                gridPane.add(buttonsList[counter++], j, i);
            }
        }
// define the circleButtons using a for loop
        for (int i = 0; i < 16; i++) {
            // initialize the button and set a size
            //#resultButtonsList2[i] = new CircleButton(10,50,50);
            resultButtonsList[i] = new Button();
            resultButtonsList[i].setShape(new Circle(10));
            resultButtonsList[i].setMinWidth(50);
            resultButtonsList[i].setMinHeight(50);
        }

        // make a counter that will be used to add the circleButtons to the grid pane
        counter = 0;

        for (int j = 0; j < 5; j++) {
            if (j == 0 || j == 4) {
                for (int k = 0; k < 5; k++) {
                    gridPane.add(resultButtonsList[counter], j, k);
                    GridPane.setMargin(resultButtonsList[counter], new Insets(20, 20, 20, 20));
                    resultButtonsList[counter].setText(0 + "");
                    counter++;
                    if (k != 0 && k != 4) {
                        gridPane.add(resultButtonsList[counter], k, j);
                        GridPane.setMargin(resultButtonsList[counter], new Insets(20, 20, 20, 20));
                        resultButtonsList[counter].setText(0 + "");
                        counter++;
                    }

                }
            }
        }

        stage.setTitle("Magic Square Game");
        stage.setScene(scene);
        stage.show();
    }

// a function that will set the value or the text of the button to empty
    private void setToEmpty(int buttonIndex) {
        // check if the button is empty or not
        if (!buttonsList[buttonIndex].getText().equals("")) {
            // remove the inside number from the duplicate list to make the number available for use
            checkDuplicate.remove(checkDuplicate.indexOf(Integer.parseInt(buttonsList[buttonIndex].getText())));
            buttonsList[buttonIndex].setText("");
        }
    }

// a function that will increase the button number
    public void increaseButtonNumber(int buttonIndex) {
        int index = 0;
        boolean isChecked = true;
        if (buttonsList[buttonIndex].getText().equals("")) {
            while (isChecked) {
                // check if the net number is available or not
                if (!checkDuplicate.contains(index + 1)) {
                    buttonsList[buttonIndex].setText(index + 1 + "");
                    checkDuplicate.add(index + 1);
                    // stop the loop
                    isChecked = false;

                // if not so try the next one
                } else
                    index++;
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

    public void resultCircleValues() {
        ArrayList<Integer> resultButtonsValue = new ArrayList<Integer>();
        for (int i = 0; i < buttonsList.length; i++) {
            if (buttonsList[i].getText().equals("")) {
                resultButtonsValue.add(0);

            } else {
                resultButtonsValue.add(Integer.parseInt(buttonsList[i].getText()));
            }
        }


        resultButtonsList[0].setText((resultButtonsValue.get(0)) + resultButtonsValue.get(4) + resultButtonsValue.get(8) + "");
        resultButtonsList[15].setText(Integer.parseInt(resultButtonsList[0].getText()) + "");

        resultButtonsList[1].setText((resultButtonsValue.get(0)) + (resultButtonsValue.get(1)) + (resultButtonsValue.get(2)) + "");
        resultButtonsList[9].setText(Integer.parseInt(resultButtonsList[1].getText()) + "");

        resultButtonsList[3].setText((resultButtonsValue.get(3)) + (resultButtonsValue.get(4)) + (resultButtonsValue.get(5)) + "");
        resultButtonsList[11].setText(Integer.parseInt(resultButtonsList[3].getText()) + "");

        resultButtonsList[5].setText((resultButtonsValue.get(6)) + (resultButtonsValue.get(7)) + (resultButtonsValue.get(8)) + "");
        resultButtonsList[13].setText(Integer.parseInt(resultButtonsList[5].getText()) + "");

        resultButtonsList[2].setText((resultButtonsValue.get(0)) + (resultButtonsValue.get(3)) + (resultButtonsValue.get(6)) + "");
        resultButtonsList[10].setText(Integer.parseInt(resultButtonsList[2].getText()) + "");

        resultButtonsList[4].setText((resultButtonsValue.get(1)) + (resultButtonsValue.get(4)) + (resultButtonsValue.get(7)) + "");
        resultButtonsList[12].setText(Integer.parseInt(resultButtonsList[4].getText()) + "");

        resultButtonsList[6].setText((resultButtonsValue.get(2)) + (resultButtonsValue.get(5)) + (resultButtonsValue.get(8)) + "");
        resultButtonsList[14].setText(Integer.parseInt(resultButtonsList[6].getText()) + "");

        resultButtonsList[8].setText((resultButtonsValue.get(2)) + (resultButtonsValue.get(4)) + (resultButtonsValue.get(6)) + "");
        resultButtonsList[7].setText(Integer.parseInt(resultButtonsList[8].getText()) + "");

        for (Button button : resultButtonsList) {
            if (Integer.parseInt(button.getText()) == 15)
                button.setStyle("-fx-border-color:green;");
            else
                button.setStyle("-fx-border-color:red;");

        }

    }
}

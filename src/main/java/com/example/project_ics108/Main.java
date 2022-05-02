package com.example.project_ics108;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


// make a main class that contain the main function
public class Main extends Application {
    // created a list that will contain the numbers, and deal with duplicated
    public ArrayList<Integer> checkDuplicate = new ArrayList<>();

    // make a list that contain the buttons of the magic square
    public Button[] buttonsList = new Button[9];

    // make a list that contain the circular buttons which has the results
    public Button[] resultButtonsList = new Button[16];

    // launch the application
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
//---------------------------GAME-PAGE-SCENE---------------------------//
        // make a gridPane
        GridPane gamePagePane = new GridPane();
        // make a new scene
        Scene gamePageScene = new Scene(gamePagePane);


        // ------------------------ Make the square buttons ------------------------\\
        // make the 9 buttons that will show out on the screen using for loop
        for (int i = 0; i < buttonsList.length; i++) {
            buttonsList[i] = new Button(""); // set text to empty
            // create the buttons using a function that will take ...
            // @style of the button, @height @width
            initSquareButton(buttonsList[i], "-fx-font-size: 5em; ", 100, 100);
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

                        // here we call popup function in order to check if the answers are correct it will show popup page.
                        try {
                            popUp();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        // here it will check whether click exit button is clicked and if it is clicked it will close the stage.
                        if (Popup.getIsExit()) {
                            stage.close();
                            // here call the function change isExist to make its value false so the stage does not close if the exit button is not clicked
                            Popup.changeIsExit();
                        }
                    }
            );
        }

// set the buttons in the grid pane

        // adding the  buttons in the gridPane using a for loop
        int counter = 0; // the counter is used for moving inside the list elements
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                gamePagePane.add(buttonsList[counter++], j, i + 1);
            }
        }

// define the circleButtons using a for loop
        for (int i = 0; i < 16; i++) {
            // initialize the button and set a size
            resultButtonsList[i] = new Button();
            resultButtonsList[i].setShape(new Circle(10));
            resultButtonsList[i].setStyle("-fx-border-color:#747982;");
            resultButtonsList[i].setMinWidth(50);
            resultButtonsList[i].setMinHeight(50);
        }

        // make a counter that will be used to add the circleButtons to the grid pane
        counter = 0;

        for (int j = 0; j < 5; j++) {
            if (j == 0 || j == 4) {
                for (int k = 0; k < 5; k++) {
                    gamePagePane.add(resultButtonsList[counter], j, k + 1);
                    GridPane.setMargin(resultButtonsList[counter], new Insets(20, 20, 20, 20));
                    resultButtonsList[counter].setText(0 + "");
                    counter++;
                    if (k != 0 && k != 4) {
                        gamePagePane.add(resultButtonsList[counter], k, j + 1);
                        GridPane.setMargin(resultButtonsList[counter], new Insets(20, 20, 20, 20));
                        resultButtonsList[counter].setText(0 + "");
                        counter++;
                    }

                }
            }
        }
        // Defining clean and exit buttons.
        Button clean = new Button("Clean");
        Button exit = new Button("Exit");
        // Adding the buttons to the pane.
        gamePagePane.add(clean, 2, 6);
        gamePagePane.add(exit, 4, 0);
        // styling the button using css.
        clean.setStyle("-fx-background-color: " +
                "        #000000," +
                "        linear-gradient(#7ebcea, #2f4b8f)," +
                "        linear-gradient(#426ab7, #263e75)," +
                "        linear-gradient(#395cab, #223768);" +
                "    -fx-background-insets: 0,1,2,3;" +
                "    -fx-background-radius: 3,2,2,2;" +
                "    -fx-padding: 12 30 12 30;" +
                "    -fx-text-fill: white;" +
                "    -fx-font-size: 15px;");
        exit.setStyle("-fx-background-color: " +
                "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%)," +
                "        #9d4024," +
                "        #d86e3a," +
                "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);" +
                "    -fx-background-insets: 0,1,2,3;" +
                "    -fx-background-radius: 3,2,2,2;" +
                "    -fx-padding: 12 33 12 33;" +
                "    -fx-text-fill: white;" +
                "    -fx-font-size: 15px;");
        // Adding action to clean and exit buttons.
        clean.setOnAction(e -> {
            // on clean button restart the texts and colors
            for (int i = 0; i < buttonsList.length; i++) {
                setToEmpty(i);
            }
            resultCircleValues();

            for (Button button : resultButtonsList) {
                button.setStyle("-fx-border-color:#747982;");
            }


        });
        exit.setOnAction(e -> stage.close());


//---------------------------HOME-PAGE-SCENE--------------------------//
        // make a gridPane
        GridPane homePagePane = new GridPane();
        // make a new scene
        Scene homePageScene = new Scene(homePagePane);
        // putting color to the pane background using css.
        homePagePane.setStyle("-fx-background-color:white");
        // Defining the title label .
        Label title = new Label("Magic Square");
        // Styling the title using css.
        title.setStyle("-fx-font-weight: bold;" + "-fx-font-size:40px;" + "-fx-text-fill:#555555");
        // Defining imageView object.
        ImageView imageView = new ImageView(new Image(new FileInputStream("image.gif")));
        // Editing the image coordinates.
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        // the usage of this method is to preserve the aspect ratio of the source image when scaling to fit the image within the fitting bounding box.
        imageView.setPreserveRatio(true);
        // Adding the image and the title to the pane.
        homePagePane.add(imageView, 0, 0);
        homePagePane.add(title, 0, 0);
        // Setting the alignment of the title.
        GridPane.setValignment(title, VPos.TOP);
        GridPane.setHalignment(title, HPos.CENTER);

        // Defining start and exit buttons.
        Button startButton = new Button("Start");
        Button exitButton = new Button("Exit");
        // Styling the buttons and changing their width and height.
        startButton.setStyle("-fx-background-color:#D8AC9C;" + "-fx-font-weight: bold;" + "-fx-font-size:18px;");
        exitButton.setStyle("-fx-background-color:#D8AC9C;" + "-fx-font-weight: bold;" + "-fx-font-size:18px;");
        startButton.setMinHeight(45);
        startButton.setMinWidth(80);
        exitButton.setMinHeight(45);
        exitButton.setMinWidth(80);
        // Adding the buttons to the pane.
        homePagePane.add(startButton, 0, 0);
        homePagePane.add(exitButton, 0, 1);
        // Setting the alignment of the buttons and adding margin.
        GridPane.setValignment(startButton, VPos.BOTTOM);
        GridPane.setHalignment(startButton, HPos.CENTER);
        GridPane.setHalignment(exitButton, HPos.CENTER);
        GridPane.setMargin(exitButton, new Insets(20, 20, 20, 20));
        // Adding action to the buttons.
        startButton.setOnAction(e -> stage.setScene(gamePageScene));
        exitButton.setOnAction(e -> stage.close());
//---------------------------STAGE-SETTING------------------------------//

        // Putting title to the stage and setting a scene to it and show it.
        stage.getIcons().add(new Image(new FileInputStream("Cube.png")));
        stage.setTitle("Magic Square Game");
        stage.setScene(homePageScene);
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

    // this function will take all the numbers inside the Circle buttons
    public void resultCircleValues() {
        ArrayList<Integer> resultButtonsValue = new ArrayList<>();
        for (Button value : buttonsList) {
            if (value.getText().equals("")) {
                resultButtonsValue.add(0);

            } else {
                resultButtonsValue.add(Integer.parseInt(value.getText()));
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

    public void initSquareButton(Button button, String style, int height, int width) {
        button.setStyle(style);
        button.setMinHeight(height);
        button.setMinWidth(width);
    }

    // Defining the function pop up to check if the answers is right it shows pop up.
    public void popUp() throws FileNotFoundException {
        // The condition to check the answers
        boolean condition = (Integer.parseInt(resultButtonsList[0].getText()) == 15 && Integer.parseInt(resultButtonsList[1].getText()) == 15 && Integer.parseInt(resultButtonsList[3].getText()) == 15 && Integer.parseInt(resultButtonsList[5].getText()) == 15 && Integer.parseInt(resultButtonsList[2].getText()) == 15 && Integer.parseInt(resultButtonsList[4].getText()) == 15 && Integer.parseInt(resultButtonsList[6].getText()) == 15 && Integer.parseInt(resultButtonsList[8].getText()) == 15);
        // Here it checks if the condition is valid.
        if (condition) {
            // Here it calls display method from Popup class to show the popup and it returns true if the play again button is the button that the user press otherwise it will return false.
            boolean playAgainOrExit = Popup.display();
            // It checks if playAgain button is pressed it removes the values using for loop and setToEmpty function
            if (playAgainOrExit) {
                for (int i = 0; i < buttonsList.length; i++) {
                    setToEmpty(i);
                }
                // Here call the resultCircleValues function to delete the result buttons values.
                resultCircleValues();
                // Make the color of the resultButtons to the default color.
                for (Button button : resultButtonsList) {
                    button.setStyle("-fx-border-color:#747982;");
                }
                // Here it calls the function changeIsRestart from the class Popup to make sure it does not return true when the exit button is pressed.
                Popup.changeIsRestart();
            }
        }

    }

}

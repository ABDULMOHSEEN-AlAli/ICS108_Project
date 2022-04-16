package com.example.project_ics108;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class CircleButton {
    private Button button = new Button();
    private String buttonText;
    private String buttonColor;

    CircleButton(int radius,int width, int height){
        button.setShape(new Circle(radius));
        button.setMinHeight(height);
        button.setMinWidth(width);
    }

    // change or set a text for the button
    public void setText(String text){
        buttonText = text;
        button.setText(buttonText);
    }
    // Change button color function
    public void setColor(String buttonColor){
        this.buttonColor = "-fx-border-color:"+buttonColor+";";
        button.setStyle(this.buttonColor);
    }

    // update the text with a new value
    public void updateValue(int buttonNum1, int buttonNum2, int buttonNum3){
        int total = buttonNum1+buttonNum2+buttonNum3;
        button.setText(total+"");
    }

}

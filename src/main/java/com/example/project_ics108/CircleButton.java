package com.example.project_ics108;

import javafx.scene.control.Button;

public class CircleButton {
    private Button button = new Button();
    private String buttonText;
    private String buttonColor;

    CircleButton(String buttonText){
        button.setText(buttonText);
    }

    public void setColor(String buttonColor){
        this.buttonColor = "-fx-border-color:"+buttonColor+";";
        button.setStyle(this.buttonColor);

    }
}

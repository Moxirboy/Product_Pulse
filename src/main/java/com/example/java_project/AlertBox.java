// Alert box used to warn the user
// int will pop uot a new window with a warning message

package com.example.java_project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    AlertBox(String alert)
    {
        Stage window =new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label text=new Label(alert);
    
        text.setTextFill(Color.RED);
        text.setFont(Font.font("Times",FontWeight.BOLD,20));
        text.setWrapText(true);
        text.setAlignment(Pos.CENTER);

        Button button=new Button("Ok");
        button.setOnAction(e->window.close());
        button.setMinWidth(100);
        button.setMinHeight(20);
        button.setFont(Font.font("Bauhous", FontWeight.BOLD, 15));
        VBox layout =new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(text,button);
        Scene scene=new Scene(layout);

        window.setScene(scene);
        window.showAndWait();
    }   
}

package com.example.java_project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class alert{
    public static void alert(String title,String message){
        Stage window =new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("hi motherfuc");
        window.setMinWidth(200);
        Label label =new Label();
        label.setText(message);
        Button button=new Button("close button");
        button.setOnAction(e->window.close());
        VBox layout =new VBox(10);
        layout.getChildren().addAll(label,button);
        layout.setAlignment(Pos.CENTER);
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.show();

    }
}
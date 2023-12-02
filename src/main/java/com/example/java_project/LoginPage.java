package com.example.java_project;

import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginPage {
    private static Stage mainStage;
    private static  TextField nameField;
    private static PasswordField passwordField;
    private static boolean connected=false;

    public static boolean pass()
    {
        boolean connected=false;
        mainStage=new Stage();
        mainStage.initModality(Modality.APPLICATION_MODAL);
        Label label=new Label("Log in");
        label.setFont(Font.font("Bauhaus",20));
        label.setTextFill(Color.INDIGO);
        //field for username
        nameField=new TextField();
        nameField.setPromptText("Username");
        nameField.setMaxWidth(200);
        
        //filed for password
        passwordField=new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);
        
        Button ok =new Button("Log in");
        ok.setOnAction(e->{
            if (Database.getConnection(nameField.getText(), passwordField.getText())) {
                LoginPage.connected=true;
                mainStage.close();
            }
        });

        VBox components=new VBox();
        components.setSpacing(30);
        components.setAlignment(Pos.CENTER);
        
        //adding all components to be shown
        components.getChildren().addAll(label,nameField,passwordField,ok);

        Scene scene=new Scene(components,1000,600);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.showAndWait();
        return connected;
    }
}

package com.example.java_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.java_project.ProductPulse.addtable;

public class alert{

    public static Scene alert(Stage window){


        VBox addProducts = new VBox();
        addProducts.setSpacing(20);
        addProducts.setPadding(new Insets(10, 10, 10, 10));
        addProducts.getChildren().addAll(addtable);

        StackPane cards = new StackPane();
        cards.getChildren().addAll(addProducts);
        VBox layout =new VBox(10);
        layout.getChildren().addAll(cards);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout);

    }

}
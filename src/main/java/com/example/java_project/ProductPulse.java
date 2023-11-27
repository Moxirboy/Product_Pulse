package com.example.java_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductPulse extends Application {
    TableView<Dashboard> table;
    Stage window;
    Scene scene2;
    TextField NameInput, OutDate , quantity;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       window =stage;
       //
        TableColumn<Dashboard, String> nameColumn=new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        //
        TableColumn<Dashboard, String> quantityColumn=new TableColumn<>("quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        //
        TableColumn<Dashboard, String> OutDateColumn=new TableColumn<>("OutDateColumn");
        OutDateColumn.setMinWidth(200);
        OutDateColumn.setCellValueFactory(new PropertyValueFactory<>("OutDateColumn"));
        //
        NameInput=new TextField();
        NameInput.setPromptText("Name");
        NameInput.setMinWidth(100);
        //
        OutDate =new TextField();
        OutDate.setPromptText("OutDate");
        //
        quantity=new TextField();
        quantity.setPromptText("quantity");


        //
        Button addButton=new Button("add");
        addButton.setOnAction(e->actionAdd());
        Button deleteButton=new Button("delete");
        deleteButton.setOnAction(e->actionDelete());
        //
        HBox hbox=new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(NameInput,OutDate,quantity,addButton,deleteButton);
        //
        table =new TableView<>();
        table.setItems(getProducts());
        table.getColumns().addAll(nameColumn,quantityColumn,OutDateColumn);
        //
        Menu filMenu =new Menu("file");

       VBox vBox=new VBox();
       vBox.getChildren().addAll(hbox,table);
       //
       Scene scene =new Scene(vBox,300,340);
       //
       window.setScene(scene);
       window.setTitle("Product Pulse");
       window.show();

}

    public void actionAdd(){
        Dashboard dashboard =new Dashboard();
        dashboard.setName(NameInput.getText());
        dashboard.setQuantity(Integer.parseInt(quantity.getText()));
        dashboard.setOutDate(OutDate.getText());
        table.getItems().add(dashboard);
        NameInput.clear();
        quantity.clear();
        OutDate.clear();
    }
    public void actionDelete(){
       ObservableList<Dashboard> productSelected ,allproducts;
        allproducts=table.getItems();
        productSelected=table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allproducts::remove);
    }
    ObservableList<Dashboard > product = FXCollections.observableArrayList();
    public ObservableList<Dashboard> getProducts(){

        product.add(new Dashboard("laptop",12,"12.27","01.28"));
        product.add(new Dashboard("yogaList",12,"12.27","01.28"));
        product.add(new Dashboard("Mobile Phone",12,"12.27","01.28"));



        return product;
    }
//    public actionDelete(){
//
//    }


}


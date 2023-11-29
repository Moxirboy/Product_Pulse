package com.example.java_project;

import com.example.java_project.Product;
import com.example.java_project.alert;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ProductPulse extends Application {
    Stage mainStage;
    Scene mainScene;
    public static TableView<Product> addtable = new TableView<>();
    TextField newProductName, newProductAmount;
    DatePicker DateExpire;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        // Name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(180);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Amount column
        TableColumn<Product, String> amountColumn = new TableColumn<>("quantity");
        amountColumn.setMinWidth(180);
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Expire date column
        TableColumn<Product, String> expireColumn = new TableColumn<>("Expire");
        expireColumn.setMinWidth(180);
        expireColumn.setCellValueFactory(new PropertyValueFactory<>("expire"));

        // Registered date column
        TableColumn<Product, String > registeredColumn = new TableColumn<>("Registered");
        registeredColumn.setMinWidth(180);
        registeredColumn.setCellValueFactory(new PropertyValueFactory<>("registered"));

        // Adding table
        addtable.getColumns().addAll(nameColumn, amountColumn, expireColumn, registeredColumn);
        addtable.setItems(getProducts());

        // Components for adding new Product
        newProductName = new TextField();
        newProductName.setPromptText("Product name");
        DateExpire = new DatePicker();
        DateExpire.setPromptText("Expire date");
        newProductAmount = new TextField();
        newProductAmount.setPromptText("Amount");

        Button addProduct = new Button("Add");
        addProduct.setOnAction(e -> addProductToTable());

        Button deleteProduct = new Button("Delete");
        deleteProduct.setOnAction(e -> actionDelete());

        // Hbox for holding editing components
        HBox editingComp = new HBox();
        editingComp.setSpacing(20);
        editingComp.getChildren().addAll(newProductName, newProductAmount, DateExpire, addProduct, deleteProduct);

        // Layout for holding table and editing components
        VBox addProducts = new VBox();
        addProducts.setSpacing(20);
        addProducts.setPadding(new Insets(10, 10, 10, 10));
        addProducts.getChildren().addAll(addtable, editingComp);

        // Navigation buttons
        Button toProducts = new Button("Products");
        toProducts.setOnAction(e -> mainStage.setScene(mainScene));
        toProducts.setMinWidth(100);

        Button toAlert = new Button("Alert");
        toAlert.setOnAction(e -> mainStage.setScene(alert.alert(mainStage))); // Assuming alert is an instance of another class
        toAlert.setMinWidth(100);

        Button toSell = new Button("Sell");
        toSell.setOnAction(e -> {
            // Add your sell logic here
        });
        toSell.setMinWidth(100);

        // Layout for switching screens
        StackPane cards = new StackPane();
        cards.getChildren().addAll(addProducts);



        VBox navigationBar = new VBox();
        navigationBar.setSpacing(20);
        navigationBar.setPadding(new Insets(60, 10, 10, 10));
        navigationBar.getChildren().addAll(toProducts, toAlert, toSell);

        // Main layout for holding all components
        HBox mainLayout = new HBox();
        mainLayout.setSpacing(10);
        mainLayout.getChildren().addAll(navigationBar, cards);

        mainScene = new Scene(mainLayout);

        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void addProductToTable() {
        try {
            // If product name is not entered
            if (newProductName.getText().isEmpty())
                throw new Exception();

            String name = newProductName.getText();
            String quantity = newProductAmount.getText();
            LocalDate expireDate =DateExpire.getValue();
            addtable.getItems().add(new Product(name, quantity, expireDate));
            newProductName.clear();
            newProductAmount.clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionDelete() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = addtable.getItems();
        productSelected = addtable.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
    }

    public static ObservableList<Product> getProducts() {
        return FXCollections.observableArrayList();
    }
}

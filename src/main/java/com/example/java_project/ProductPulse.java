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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.print.CancelablePrintJob;

import org.controlsfx.control.PropertySheet.Item;

public class ProductPulse extends Application {
    Stage mainStage;
    Scene mainScene;
    public static TableView<Product> addtable = new TableView<>();
    TextField newProductName, newProductQuantity,newProductPrice;// Text Fields for getting data about product to be aded
    DatePicker dateExpire;// to get input for expire date
    ComboBox<String> quantityUnit;// Unit of measurment of a product (kilos, liters, peaces)
    Button addButton,deleteProduct,editButton;
    boolean editingMode;// true when when editing a existing product in the table
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

        //Price column
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(180);
        priceColumn.setMaxWidth(300);
        priceColumn.setPrefWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
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
        addtable.getColumns().addAll(nameColumn,priceColumn,amountColumn, expireColumn, registeredColumn);
        addtable.setItems(getProducts());
     
        addtable.setRowFactory(tv -> new TableRow<Product>() {
            final static private Background green=new Background(//color code for long expiration date
                    new BackgroundFill(
                            Color.rgb(0, 170, 0), null, null
                    ));
            final static private Background yellow=new Background(//color code for short expiration date
                    new BackgroundFill(
                            Color.rgb(255, 170, 51), null, null
                    ));
            final static private Background red =new Background(// color code for product near to expire or expired
                new BackgroundFill(
                        Color.rgb(255, 0, 0), null, null
                ));
            static private LocalDate today=LocalDate.now();

            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                if(empty)
                    return;
                    
                int daysLeft=(int)ChronoUnit.DAYS.between(today,item.getExpire());//calculating days to expire
                if(daysLeft<30) {
                    this.setBackground(red);
                    return;
                }else if(daysLeft<90&&daysLeft>30)
                {
                    this.setBackground(yellow);
                    return;
                }
                this.setBackground(green);
            }
        });

        // Components for adding new Product
        newProductName = new TextField();
        newProductName.setPromptText("Product name");
        
        newProductPrice=new TextField();
        newProductPrice.setPromptText("Price");

        dateExpire = new DatePicker();
        dateExpire.setPromptText("Expire date");
        dateExpire.setMaxWidth(100);
        
        newProductQuantity = new TextField();
        newProductQuantity.setPromptText("Amount");
        
        quantityUnit=new ComboBox<String>();
        quantityUnit.getItems().addAll("kg","ltr","pcs");// default values are kilo, liters,peaces
        quantityUnit.setValue(quantityUnit.getItems().get(0));
        quantityUnit.setEditable(true);// now user can define own quantity unit
        quantityUnit.setMaxWidth(100);

        addButton = new Button("Add");
        addButton.setOnAction(e -> addProductAction());

        editButton =new Button("Edit");
        editButton.setOnAction(e->editAction());
        deleteProduct = new Button("Delete");
        deleteProduct.setOnAction(e -> deleteAction());

        // Hbox for holding editing components
        GridPane editingComp = new GridPane();
        editingComp.setHgap(40);
        editingComp.setVgap(20);
        editingComp.add(newProductName, 0, 0);
        editingComp.add(newProductPrice, 1, 0);
        editingComp.add(newProductQuantity, 2, 0);
        editingComp.add(quantityUnit, 0, 1);
        editingComp.add(dateExpire, 1, 1);
        editingComp.add(addButton, 3, 0);
        editingComp.add(editButton,4,0);
        editingComp.add(deleteProduct, 5, 0);

        // Layout for holding table and editing components
        VBox addButtons = new VBox();
        addButtons.setSpacing(20);
        addButtons.setPadding(new Insets(10, 10, 10, 10));
        addButtons.getChildren().addAll(addtable, editingComp);

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
        cards.getChildren().addAll(addButtons);

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

    protected void editAction()// Acting when editButton is Pressed
    {
        if(editingMode)
        {
            addButton.setText("Add");
            editButton.setText("Edit"); 
            newProductName.clear();
            newProductQuantity.clear();
            newProductPrice.clear();
            dateExpire.setValue(null);;
            editingMode=false;
            return;
        }

        Product product=this.addtable.getSelectionModel().getSelectedItem();
        newProductName.setText(product.getName());
        newProductPrice.setText(product.getPrice()+"");
        newProductQuantity.setText(product.quantity+"");
        quantityUnit.setValue(product.getUnit());
        dateExpire.setValue(product.getExpire());
        addButton.setText("Apply");
        editButton.setText("Cancel");
        editingMode=true;
    }

    public void addProductAction() {
        try {
            // If product name is not entered
            if (newProductName.getText().isEmpty())
                throw new Exception();

            String name = newProductName.getText();
            float price=Float.parseFloat(newProductPrice.getText());// this is where exeption may appear
            int quantity = Integer.parseInt(newProductQuantity.getText());
            String unit=this.quantityUnit.getValue().toString();
            LocalDate expireDate =dateExpire.getValue();
            LocalDate registerDate=LocalDate.now();
            //if editing mode is true then reather adding new product to table it will edit existing one
            if(editingMode)
            {
                Product product=this.addtable.getSelectionModel().getSelectedItem();
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setUnit(unit);

                addButton.setText("Add");
                editButton.setText("Edit");
                editingMode=false;
            }
            else
                addtable.getItems().add(new Product(name,price,quantity,unit, expireDate,registerDate));
            newProductPrice.clear();
            newProductName.clear();
            newProductQuantity.clear();
            quantityUnit.setValue(null);
            dateExpire.setValue(null);
        } catch (Exception e) {
            new AlertBox("Invalid Input!");
        }
    }

    public void deleteAction() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = addtable.getItems();
        productSelected = addtable.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
        addButton.setText("Add");
        editButton.setText("Edit");
        editingMode=false;
    }

    public static ObservableList<Product> getProducts() {
        return FXCollections.observableArrayList();
    }
}

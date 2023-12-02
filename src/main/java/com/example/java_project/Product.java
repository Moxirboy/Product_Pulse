package com.example.java_project;

import java.time.LocalDate;

import javafx.util.converter.LocalDateStringConverter;

public class Product {


    String packageID;         // Package ID of the product, is unique,works as primary key
    String name ;            //name of the product
    int quantity;           // quantity of the product
    float price;           //Price of the product
    String unit;          // unit of quantity of the product
    LocalDate registered;// date of register of the product
    LocalDate expire;   //ddate of expire of the product
    Product(){
        this.name="";
        this.price=0.0F;
        this.quantity=0;
        this.unit="";
        this.expire=LocalDate.now();
        registered =LocalDate.now();

    }

    Product(String packageID,String name,float price,int quantity ,String unitOfQuantity,LocalDate expireDate,LocalDate registerDate){
        
        this.packageID=packageID;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.unit=unitOfQuantity;
        this.expire=expireDate;
        this.registered=registerDate;
    }

    public LocalDate getExpire() {
        return expire;
    }

    public void setExpire(LocalDate expireDate) {
        this.expire = expireDate;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registrationDate) {
        this.registered = registrationDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getQuantity() {
        return quantity+" "+unit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice()
    {
        return this.price;
    }

    public void setPrice(float price)
    {
        this.price=price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unitOfQuantity) {
        this.unit = unitOfQuantity;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }
}

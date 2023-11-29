package com.example.java_project;

import java.time.LocalDate;

public class Product {


    String name ;



    String quantity;
    LocalDate registered;


    public LocalDate getExpire() {
        return expire;
    }

    public void setExpire(LocalDate expire) {
        this.expire = expire;
    }

    LocalDate expire;
    String OutDate;
    Product(){
        this.name="";
        this.quantity="0";
        registered =LocalDate.now();

    }



    Product(String name, String quantity ,LocalDate expireDate){
        this.name=name;
        this.quantity=quantity;
        this.expire=expireDate;
        this.registered=LocalDate.now();
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
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getOutDate() {
        return OutDate;
    }

    public void setOutDate(String outDate) {
        OutDate = outDate;
    }
}

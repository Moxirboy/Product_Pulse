package com.example.java_project;

public class Dashboard {


    String name ;
    int quantity;
    String registrationDate;



    String OutDate;
    Dashboard(){
        this.name="";
        this.quantity=0;
        this.OutDate="";
        this.registrationDate="";
    }



    Dashboard(String name, int quantity, String Outdate, String registrationDate ){
        this.name=name;
        this.quantity=quantity;
        this.OutDate=Outdate;
        this.registrationDate=registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getOutDate() {
        return OutDate;
    }

    public void setOutDate(String outDate) {
        OutDate = outDate;
    }
}

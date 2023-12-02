package com.example.java_project;

import java.sql.*;
import java.time.LocalDate;
import java.util.jar.Attributes.Name;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Database class is singelton class
public class Database {
    
    static public Database database=new Database();
    static private Connection connection;
    static private Statement statement;
    static private PreparedStatement insert;// prepared statement to add new record
    static private PreparedStatement delete;//prepared statement to delete record
    static private PreparedStatement edit1;//prepared statemnet for editing record (edits single column)
    static private PreparedStatement edit2;//prepared statemnet for editing record (edits all columns)
    private Database(){};
    
    //initilazing connection
    static boolean getConnection(String user,String password)
    {

        String url="jdbc:mysql://localhost:3306/product_pulse";
        // user = "root";
        // password = "koba2004";

        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("<<<<< - Succesfully connected - >>>>>");
            statement = connection.createStatement();
            //preparing insert statement 
            insert=connection.prepareStatement("INSERT INTO products (package_id,name,price,quantity,unit,expire_date,register_date) values(?,?,?,?,?,?,?);");
            
            //preparing edit statement
            edit1=connection.prepareStatement("UPDATE products SET ? = ? WHERE package_id = ?;");
            edit2=connection.prepareStatement("UPDATE products SET package_id = ?, name = ?,price = ?, quantity = ?, unit=?, expire_date = ? WHERE package_id = ?;"); 
            
            //preparing delete statement
            delete=connection.prepareStatement("DELETE FROM products where package_id = ?;");

            return true;
        }
        catch(SQLException e)
        {
            new AlertBox("Incorrect Password or Username");
            return false;
        }
    }

    static ObservableList<Product> getAllRecords()
    {
        try{
        ObservableList<Product> products= FXCollections.observableArrayList();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM products;");
        while(resultSet.next())
        {
            String packageId=resultSet.getString("package_id");
            String name=resultSet.getString("name");
            float price=resultSet.getFloat("price");
            int quantity= resultSet.getInt("quantity");
            String unit=resultSet.getString("unit");
            LocalDate expireDate =resultSet.getDate("expire_date").toLocalDate();
            LocalDate registerDate=resultSet.getDate("register_date").toLocalDate();
            products.add(new Product(packageId, name, price, quantity, unit, expireDate, registerDate));
        }
            return products;
        }

        catch(Exception e){

            return null;
        }
    }

    static void deleteRecord(String packageID)
    {
        // delete from products where package_id=?
        
        try{
            delete.setString(1, packageID);
            delete.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Couldn't delete record");
        }
    }

    static void addRecord(Product product)
    {
        // "INSERT INTO products values(package_id,name,price,quantity,unit,expire_date,register_date)
        try{
            insert.setString(1, product.getPackageID());
            insert.setString(2, product.getName());
            insert.setFloat(3, product.getPrice());
            insert.setInt(4, product.quantity);
            insert.setString(5,product.getUnit());
            insert.setDate(6,Date.valueOf(product.getExpire()));
            insert.setDate(7, Date.valueOf(product.getRegistered()));
            insert.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Couldn't add Record"+e.getMessage());
        }
    }

    static void updateRecord(String packageID,Product newProduct)
    {
        //UPDATE products SET package_id = ?, name = ?,price = ?, quantity = ?, expire_date = ? WHERE package_id = ?
       try{ edit2.setString(1, newProduct.getPackageID());
        edit2.setString(2, newProduct.getName());
        edit2.setFloat(3, newProduct.getPrice());
        edit2.setInt(4, newProduct.quantity);
        edit2.setString(5, newProduct.getUnit());
        edit2.setDate(6, Date.valueOf(newProduct.getExpire()));
        edit2.setString(7, packageID);
        edit2.executeUpdate();}
        catch(SQLException e)
        {
            System.out.println("Coldn't edit record");
        }
    }

    static void editPackageID(String packageID,String newPackageID) throws SQLException
    {
        //UPDATE products SET ? = ? WHERE package_id = ?;
        edit1.setString(1, "package_id");
        edit1.setString(2, newPackageID);
        edit1.executeUpdate();
    }

    static void editName(String packageID,String newName) throws SQLException
    {
        //UPDATE products SET ? = ? WHERE package_id = ?;
        edit1.setString(1, "name");
        edit1.setString(2, newName);
        edit1.executeUpdate();
    }

    static void editQuantity(String packageID,int newQuantity) throws SQLException
    {
        //UPDATE products SET ? = ? WHERE package_id = ?;
        edit1.setString(1, "quantity");
        edit1.setInt(2, newQuantity);
        edit1.executeUpdate();
    }

    static void editPrice(String packageID,float newPrice) throws SQLException
    {
        //UPDATE products SET ? = ? WHERE package_id = ?;
        edit1.setString(1, "price");
        edit1.setFloat(2, newPrice);
        edit1.executeUpdate();
    }

    static void editUnit(String packageID,String newUnit) throws SQLException
    {
        //UPDATE products SET ? = ? WHERE package_id = ?;
        edit1.setString(1, "unit");
        edit1.setString(2, newUnit);
        edit1.executeUpdate();
    }

    static void editExpireDate(String packageID,LocalDate newExpireDate) throws SQLException
    {
        //UPDATE products SET ? = ? WHERE package_id = ?;
        edit1.setString(1, "expire_date");
        edit1.setDate(2, Date.valueOf(newExpireDate));
        edit1.executeUpdate();
    }

}
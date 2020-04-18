/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcrpp2.entity;

import vcrpp2.db.Db;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dzmitry
 */
public class Product implements Entity {

    private static String table = "products";
    
    protected int id;
            
    protected String title;
    
    protected int quantity;
    
    protected float price;
    
    public Product(int id) {
        
        ResultSet document = Product.getById(id);
        
        try {
            while(document.next()) {
                this.id = document.getInt("id");
                this.title = document.getString("title");
                this.quantity = document.getInt("quantity");
                this.price = document.getFloat("price");
                break;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Product(
            int id, 
            String title, 
            int quantity, 
            float price
    ) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Product(
            String title, 
            int quantity, 
            float price
    ) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getQantity() {
        return this.quantity;
    }
    
    public float getPrice() {
        return this.price;
    }
    
    public static ResultSet getById(int id) {
        
        Db db = new Db();
        ResultSet result = db.query(
                "select * from `" + Product.table + "` where `id` = \"" + id + "\""
        );
        
        return result;
    }
    
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Db db = new Db();
        ResultSet result = db.query("select * from " + Product.table);
        
        try {
            
            while(result.next()) {
                products.add(new Product(
                                Integer.parseInt(result.getString("id")),
                                result.getString("title"), 
                                Integer.parseInt(result.getString("quantity")), 
                                Float.parseFloat(result.getString("price"))
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return products;
    }
    
    @Override
    public boolean create() {
        boolean result = false;
        Db db = new Db();
        this.id = db.create("insert into " + Product.table + " (title, quantity, price) "
                    + "values("
                    + "\"" + this.title + "\","
                    + "\"" + this.quantity + "\","
                    + "\"" + this.price + "\""
                    + ")"
        );
        
        return result;
    }
    
    @Override
    public boolean save() {
        if (this.id > 0) {
            return this.update();
        }
        
        return this.create();
    }

    @Override
    public boolean update() {
        boolean result = false;
        Db db = new Db();
        result = db.execute("update " + Product.table
                    + " set "
                    + "title=\"" + this.title + "\", "
                    + "quantity=\"" + this.quantity + "\", "
                    + "price=\"" + this.price + "\""
                    + " where id=" + this.id
        );
        
        return result;
    }

    @Override
    public boolean delete() {
        boolean result = false;
        Db db = new Db();
        result = db.execute("delete from " + Product.table + " where id = " + this.id);
        
        return result;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcrpp2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dzmitry
 */
public class Db implements Query {

    protected String hostName = "localhost";
    
    protected String databaseName = "vcrpp2";
    
    protected String login = "root";
    
    protected String password = "123456";
    
    protected Statement statement;
    
    private Connection connection;
    
    public Db() {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://" + this.hostName + ":3306/" + this.databaseName, this.login, this.password
            );

            this.statement = this.connection.createStatement();
            
            Database db = new Database(this);
            db.createTables();
            
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void close() {
        
        try {
            this.connection.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    @Override
    public boolean create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet query(String query) {
        ResultSet result = null;
        
        try {
            result = this.statement.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return result;
    }
    
    public int create(String query) {
        int result = 0;
        
        
        try {
            
            PreparedStatement ps = this.connection.prepareStatement(
                    query,
                    Statement.RETURN_GENERATED_KEYS
            );
 
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return result;
    }
    
    public boolean execute(String query) {
        boolean result = false;
        
        try {
            result = this.statement.execute(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return result;
    }
    
}

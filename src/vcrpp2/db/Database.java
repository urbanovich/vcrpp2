/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcrpp2.db;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dzmitry
 */
public class Database {

    protected Db db;
    
    public Database(Db db) {
        this.db = db;
    }
    
    public void createTables() {
        try {
            this.createProductTable(db);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    protected void createProductTable(Db db) throws SQLException {
        db.statement.execute(
                "create table if not exists products( "
                    + "id int(10) primary key auto_increment, "
                    + "title varchar(100), "
                    + "quantity int(10), "
                    + "price decimal(10,2) "
                + ") engine=myisam character set=utf8;"
        );
    }
}

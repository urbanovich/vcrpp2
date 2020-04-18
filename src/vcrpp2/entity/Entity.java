/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcrpp2.entity;

/**
 *
 * @author dzmitry
 */
public interface Entity {
    
    public int id = 0;
    
    public String title = "";
    
    public boolean create();
    public boolean update();
    public boolean delete();
    public boolean save();
}


/**
 * Player.java  
 *
 * @author:
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Player
{
    private String name;
    private int x, y, size;
    private double velocity;
    private Color color;
    
    private final int ID;
    
    public Player(String name, int size, Color color, int x, int y, double velocity, int ID) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public double getVelocity() {
        return velocity;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
}

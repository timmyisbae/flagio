
/**
 * GamePanel.java  
 *
 * @author: Tim Spaeth
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
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{

    PrintWriter out;
    Player player;
    
    ArrayList<Player> players = new ArrayList<Player>();
    
    ArrayList<Direction> direction = new ArrayList<Direction>();
    
    public GamePanel(Dimension d) {
        super();
        this.setOpaque(true);
        this.setPreferredSize(d);
        this.setBackground(Color.white);
        this.addKeyListener(this);
        this.setFocusable(true);

        player = new Player("Tim", 50, Color.BLUE, 15, 42, 1, 1);

        Timer timer = new Timer(1, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(player.getColor());
        g.fillRect(player.getX(), player.getY(), player.getSize(), player.getSize());
    }

    public void actionPerformed(ActionEvent e) {
        repaint();

        if(direction.contains(Direction.UP)) {
            
            player.setY(player.getY() - (int)player.getVelocity());
        }
        if(direction.contains(Direction.DOWN)) {
            
            player.setY(player.getY() + (int)player.getVelocity());
        }
        if(direction.contains(Direction.LEFT)) {
            
            player.setX(player.getX() - (int)player.getVelocity());
        }
        if(direction.contains(Direction.RIGHT)) {
            
            player.setX(player.getX() + (int)player.getVelocity());                
        }
        
        if(player.getX() > this.getWidth() - 1) {
            player.setX(this.getWidth() - 1);
        }
        
        if(player.getY() > this.getHeight() - 1) {
            player.setY(this.getHeight() - 1);
        }
        
        if(player.getX() < 0) {
            player.setX(0);
        }
        
        if(player.getY() < 0) {
            player.setY(0);
        }
        
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (out != null) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("1");
                out.println("up");
                direction.add(Direction.UP);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("2");     
                out.println("down");
                direction.add(Direction.DOWN);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("3");       
                out.println("left");
                direction.add(Direction.LEFT);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("4");
                out.println("right");
                direction.add(Direction.RIGHT);             
            }
            if(e.getKeyCode() == KeyEvent.VK_W)
                out.println("up");
            if(e.getKeyCode() == KeyEvent.VK_S)
                out.println("down");
            if(e.getKeyCode() == KeyEvent.VK_A)
                out.println("left");
            if(e.getKeyCode() == KeyEvent.VK_D)
                out.println("right");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (out != null) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                direction.remove(Direction.UP); 
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction.remove(Direction.DOWN); 
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction.remove(Direction.LEFT); 
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction.remove(Direction.RIGHT);              
            }
            out.println("released");
        }       
    }

    public void keyTyped(KeyEvent e) {

    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}

enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

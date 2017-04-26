
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
    ArrayList<Direction> releasedKeys = new ArrayList<Direction>();

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
            player.moveUp();
        }
        if(direction.contains(Direction.DOWN)) {
            player.moveDown();
        }
        if(direction.contains(Direction.LEFT)) {
            player.moveLeft();
        }
        if(direction.contains(Direction.RIGHT)) {
            player.moveRight();
        }

        if(releasedKeys.contains(Direction.UP)) {
            if(player.resetAcceleration(Direction.UP))
                releasedKeys.remove(Direction.UP);
        }
        if(releasedKeys.contains(Direction.DOWN)) {
            if(player.resetAcceleration(Direction.DOWN))
                releasedKeys.remove(Direction.DOWN);
        }
        if(releasedKeys.contains(Direction.LEFT)) {
            if(player.resetAcceleration(Direction.LEFT))
                releasedKeys.remove(Direction.LEFT);
        }
        if(releasedKeys.contains(Direction.RIGHT)) {
            if(player.resetAcceleration(Direction.RIGHT))
                releasedKeys.remove(Direction.RIGHT);
        }

        if(player.getX() > this.getWidth() - player.getSize())
            player.setX(this.getWidth() - player.getSize());

        if(player.getX() < 0)
            player.setX(0);

        if(player.getY() > this.getHeight() - player.getSize())
            player.setY(this.getHeight() - player.getSize());

        if(player.getY() < 0)
            player.setY(0);

    }
    public void keyPressed(KeyEvent e) {
        if (out != null) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                System.out.println("1");
                out.println("up");
                if(!direction.contains(Direction.UP))direction.add(Direction.UP);
                if(releasedKeys.contains(Direction.UP))releasedKeys.remove(Direction.UP);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                System.out.println("2");     
                out.println("down");
                if(!direction.contains(Direction.DOWN))direction.add(Direction.DOWN);
                if(releasedKeys.contains(Direction.DOWN))releasedKeys.remove(Direction.DOWN);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                System.out.println("3");       
                out.println("left");
                if(!direction.contains(Direction.LEFT))direction.add(Direction.LEFT);
                if(releasedKeys.contains(Direction.LEFT))releasedKeys.remove(Direction.LEFT);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                System.out.println("4");
                out.println("right");
                if(!direction.contains(Direction.RIGHT))direction.add(Direction.RIGHT);
                if(releasedKeys.contains(Direction.RIGHT))releasedKeys.remove(Direction.RIGHT);
            }
            if(e.getKeyCode() == KeyEvent.VK_W)
                out.println("up");
            if(e.getKeyCode() == KeyEvent.VK_S)
                out.println("down");
            if(e.getKeyCode() == KeyEvent.VK_A)
                out.println("left");
            if(e.getKeyCode() == KeyEvent.VK_D)
                out.println("right");
            System.out.print("{");
            for(Direction i : direction)
                System.out.print(i + ",");
            System.out.print("}");
        }
    }

    public void keyReleased(KeyEvent e) {
        if (out != null) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                direction.remove(Direction.UP); 
                if(!releasedKeys.contains(Direction.UP))releasedKeys.add(Direction.UP);  
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction.remove(Direction.DOWN); 
                if(!releasedKeys.contains(Direction.DOWN))releasedKeys.add(Direction.DOWN); 
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction.remove(Direction.LEFT); 
                if(!releasedKeys.contains(Direction.LEFT))releasedKeys.add(Direction.LEFT); 
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction.remove(Direction.RIGHT);    
                if(!releasedKeys.contains(Direction.RIGHT))releasedKeys.add(Direction.RIGHT); 
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

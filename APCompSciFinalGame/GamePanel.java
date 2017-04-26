
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
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{

    PrintWriter out;
    Player player;

    BufferedImage background;

    ArrayList<Player> players = new ArrayList<Player>();

    ArrayList<Direction> direction = new ArrayList<Direction>();
    ArrayList<Direction> releasedKeys = new ArrayList<Direction>();

    private boolean start = true;

    private int offsetX, offsetY;

    public GamePanel(Dimension d) {
        super();

        player = new Player("Tim", 50, Color.BLUE, 225, 225, 1, 1);

        try {
            background = ImageIO.read(new File("background.jpg"));
        } catch(IOException e) {}

        this.setOpaque(true);
        this.setPreferredSize(d);
        this.addKeyListener(this);
        this.setFocusable(true);

        Timer timer = new Timer(1, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(player.getColor());

        g.drawImage(background, offsetX, offsetY, 4000, 4000, this);

        //g.translate(-player.getX(), -player.getY());
        g.fillRect((this.getWidth() / 2) - player.getSize() / 2, (this.getHeight() / 2) - player.getSize() / 2, player.getSize(), player.getSize());
        if(start) start = false;
    }

    public void actionPerformed(ActionEvent e) {
        repaint();

        if(!start) {

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

            if(player.getX() > 4000 - player.getSize() / 2 - this.getWidth() / 2)
                player.setX(4000 - player.getSize() / 2 - this.getWidth() / 2);

            if(player.getX() < (-this.getWidth() / 2) + player.getSize() / 2)
                player.setX((-this.getWidth() / 2) + player.getSize() / 2);

            if(player.getY() > 4000 - player.getSize() / 2 - this.getHeight() / 2)
                player.setY(4000 - player.getSize() / 2 - this.getHeight() / 2);

            if(player.getY() < (-this.getHeight() / 2) + player.getSize() / 2)
                player.setY((-this.getHeight() / 2) + player.getSize() / 2);

            offsetX = -player.getX();
            offsetY = -player.getY();
            
            if(out!=null)
            out.println("ID:" + player.getID() + "|X:" + player.getX() + "|Y:" + player.getY());
            
        }

    }

    public void keyPressed(KeyEvent e) {
        if (out != null) {
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                if(!direction.contains(Direction.UP))direction.add(Direction.UP);
                if(releasedKeys.contains(Direction.UP))releasedKeys.remove(Direction.UP);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {  
                if(!direction.contains(Direction.DOWN))direction.add(Direction.DOWN);
                if(releasedKeys.contains(Direction.DOWN))releasedKeys.remove(Direction.DOWN);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {    
                if(!direction.contains(Direction.LEFT))direction.add(Direction.LEFT);
                if(releasedKeys.contains(Direction.LEFT))releasedKeys.remove(Direction.LEFT);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
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

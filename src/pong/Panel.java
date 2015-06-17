/******************************************************************************
 * Program Name:         Pong
 * Program Description:  MY FIRST GAME!!!!
 *                        
 * Program Author:        Nathanael Paulemon
 * Date Created:          12/10/2014
 *
 * Change#        Change Date      Programmer Name        Description
 * -------        ------------     -------------------    ---------------------
 ****************************************************************************/
package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener,KeyListener{

    // Animation Clock
    Timer t = new Timer(5,this);
    
    int barOneX = 50;
    int barOneY = 250;
    int barOneYVel = 0;
    
    int barTwoX = 550;
    int barTwoY = 250;
    int barTwoYVel = 0;
    
    int x = 300;
    int y = 300;
    
    int velX ;
    int velY ;
    
    boolean up = false;
    boolean down = false;
    boolean up2 = false;
    boolean down2 = false;
    
    int p1Score = 0;
    int p2Score = 0;

    String player = null;
    
    boolean game = true;
    boolean win = false;
    
    
    // Panel constructor
    public Panel(){
        
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);
        
        // Start clock for animation timer
        t.start();
     
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        
        if(game){
            g.setColor(Color.RED);
            g.fillRect(barOneX,barOneY, 10, 100);

            g.setColor(Color.WHITE);
            g.fillOval(x, y, 20, 20);

            g.setColor(Color.BLUE);
            g.fillRect(barTwoX, barTwoY, 10, 100);

            g.setColor(Color.WHITE);

            Font font = new Font("serif",Font.BOLD,20);
            g.setFont(font);

            g.drawString( Integer.toString(p1Score), 25, 550);
            g.drawString( Integer.toString(p2Score), 565, 550);
            g.drawString("First one to ten WINS!", 200, 20);
        }

        else if(win){
            Font f = new Font("serif",Font.BOLD,75);
            
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString(player+" WINS!", 55, 300);
            
            Font font = new Font("serif",Font.BOLD,30);
            g.setFont(font);
            g.drawString("Press 'P' to play again!", 55, 350);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
        x += velX;
        y += velY;
        
        barOneY += barOneYVel;
        barTwoY += barTwoYVel;
        
        // BALL PHYSICS
        if(x + 20 > 600){
            p1Score+=1;
            velX = 0;
            velY = 0;
            x = 300;
            y = 300;
            barOneY = 250;
            barTwoY = 250;
            up = false;
            down = false;
            up2 = false;
            down2 = false;
        }
        else if(x < 0){
            
            p2Score+=1;
            velX = 0;
            velY = 0;
            x = 300;
            y = 300;
            barOneY = 250;
            barTwoY = 250;
            up = false;
            down = false;
            up2 = false;
            down2 = false;
        }
        else if(y < 0){
            
            if(velX == -4 && velY == -4){
                velX = -4;
                velY = 4;
            }else{
            
            velX = 4;
            velY = 4;
            
            }
        }
        else if(y + 50 > 600){
            if(velX == 4 && velY == 4){
                velX = 4;
                velY = -4;
            }else{
            
            velX = -4;
            velY = -4;
            }
        }
        
        // KEEP p1 Bar in screen
        if(barOneY < 0 || barOneY + 80 > 600){
            barOneYVel = 0;
        }
        
        // P1 COLLISION DETECTION
        if(x < barOneX && up){
            if(y >= barOneY && y < barOneY + 100){
                velX = 4;
                velY = -4;

            }
        }
        else if(x < barOneX && down){
            if(y >= barOneY && y < barOneY + 100){
                velX = 4;
                velY = 4;
              
            }
        }
        else if(x < barOneX && down == false && up == false){
            if(y >= barOneY && y <= barOneY + 100){
                velX = 4;
                velY = 0;
            }
        }
        
        // P2 COLLISION DETECTION
        if(x > barTwoX - 20 && up2){
            if(y >= barTwoY  && y < barTwoY + 100){
                velX = -4;
                velY = -4;
                
            }
        }
        else if(x > barTwoX - 20 && down2){
            if(y >= barTwoY  && y < barTwoY + 100){
                velX = -4;
                velY = 4;   
            }
        }
         else if(x > barTwoX - 20 && down2 == false && up2 == false){
            if(y >= barTwoY  && y <= barTwoY + 100){
                velX = -4;
                velY = 0;
              
            }
        }

        else if(p1Score == 10){
            win = true;
            game = false;
            player = "Player1";
        }else if(p2Score == 10){
            win = true;
            game = false;
            player = "Player2";
        }

        repaint();
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    int max = 9;
    int min = 1; 
    
    Random random = new Random();
    int randomNum = random.nextInt(max-min)+min;
    
    @Override
    public void keyPressed(KeyEvent ke) {
        
        int code = ke.getKeyCode();
        
        switch(code)
        {
            case KeyEvent.VK_P:
                win = false;
                game = true;
                p1Score = 0;
                p2Score = 0;
                up = false;
                down = false;
                up2 = false;
                down2 = false;
                break;
            
            // DEV CONTROLS
            case KeyEvent.VK_SPACE:
                
                int max = 9;
                int min = 1;
                
                Random random = new Random();
                
                int randomNum = random.nextInt(max-min)+min;
                
                if(randomNum % 2 == 0){
                    velX = 2;
                    velY = 0;
                }
                else if(randomNum % 3 ==0){
                        velX = 2;
                        velY = -2;
                 } else if(randomNum % 4 ==0){
                        velX = -2;
                        velY = 2;
                 }
                else{
                    velX = -4;
                }
                break;
                
            case KeyEvent.VK_O:
                velX = 5;
                velY = -5;
                break;
                
            case KeyEvent.VK_I:
                velX = -3;
                velY = 3;
                break;
                
            // P1 CONTROLS    
            case KeyEvent.VK_W:
                barOneYVel = -5;
                if(down){
                    down = false;
                }
                up = true;
                break;
                
            case KeyEvent.VK_S:
                barOneYVel = 5;
                if(up){
                    up = false;
                }
                down = true;
                break;
                
            // P2 CONTROLS   
            case KeyEvent.VK_UP:
                barTwoYVel = -5;
                if(down2){
                    down2 = false;
                }
                up2 = true;
                break;
                
            case KeyEvent.VK_DOWN:
                barTwoYVel = 5;
                if(up2){
                    up2 = false;
                }
                down2 = true;
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int c = ke.getKeyCode();
        
        if(c == KeyEvent.VK_W){
            barOneYVel = 0;
        }
        else if(c == KeyEvent.VK_S){
            barOneYVel = 0;
        }
        if(c == KeyEvent.VK_UP){
            barTwoYVel = 0;
        }
        else if(c == KeyEvent.VK_DOWN){
            barTwoYVel = 0;
        }
     }
        
}

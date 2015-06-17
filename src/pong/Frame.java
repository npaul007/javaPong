/******************************************************************************
 * Program Name:         Pong
 * Program Description:  MY FIRST GAME!!
 *                        
 * Program Author:        Nathanael Paulemon
 * Date Created:          12/10/2014
 *
 * Change#        Change Date      Programmer Name        Description
 * -------        ------------     -------------------    ---------------------
 ****************************************************************************/
package pong;

import javax.swing.JFrame;

public class Frame {
    public static void main(String[]args){
        
        JFrame frame = new JFrame("NATE'S Pong GAME!");
        
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
              
        Panel panel = new Panel();
        frame.add(panel);
        
        frame.setVisible(true);
    }
}

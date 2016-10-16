//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Main Class
//
//    May 30, 2016
//

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Main{
  
  static int WIDTH = 800;
  static int HEIGHT = 650;
  static JFrame frame;
  static boolean resetGame = false;
  
  public static void main(String[] args){
    //Main
    frame = new JFrame("Coin Getter");
    frame.pack();
    frame.setSize(WIDTH, HEIGHT);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new Game());
    Game.playBackgroundMusic();
    frame.setVisible(true);
  }
}

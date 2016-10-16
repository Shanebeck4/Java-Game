//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Key Input Class
//
//    May 30, 2016
//

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
  Player p;
  
  public KeyInput(Player p){
    this.p = p;
  }
  
  public void keyPressed(KeyEvent e){
    p.keyPressed(e);
  }
  
  public void keyReleased(KeyEvent e){
    p.keyReleased(e);
  }
}
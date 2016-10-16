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

public class KeyInputM extends KeyAdapter{
  Menu m;
  
  public KeyInputM(Menu m){
    this.m = m;
  }
  
  public void keyPressed(KeyEvent e){
    m.keyPressed(e);
  }
}
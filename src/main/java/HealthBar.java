//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Health Bar Class
//
//    May 30, 2016
//

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

public class HealthBar extends GlobalPosition{
  
  Rectangle healthBar;
  int healthBarLength;
  
  public HealthBar(int x, int y){
    super(x,y);
    
    healthBar = new Rectangle(x, y, 201, 51);
    healthBarLength = 200;
  }
  
  public void update(){
    healthBarLength = Player.playerHealth;
  }
  
  public void draw(Graphics2D g2d){
    g2d.setColor(Color.white);
    g2d.draw(healthBar);
    g2d.setColor(Color.red);
    g2d.fillRect(x+1,y+1, 200, 50);
    g2d.setColor(Color.green);
    g2d.fillRect(x+1,y+1, healthBarLength, 50);
  }
}
//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Power Up Class
//
//    June 16, 2016
//

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class PowerUp extends GlobalPosition{
  
  private String powerUpImage = "/images/pills.png";
  static Rectangle powerUpBounds;
  
  int velX;
  int velY;
  
  boolean collected;
  
  public PowerUp(int x, int y){
    super(x, y);
    velX = 1;
    velY = 1;
    powerUpBounds = new Rectangle(x, y, 50, 50);
    collected = false;
  }
  
  public void update(){
    
    x += velX;
    y += velY;
    
    powerUpBounds.x = x;
    powerUpBounds.y = y;
    
    //Collision
    if(x < 0){
      velX *= -1;
    } 
    if(x > 744){
      velX *= -1;
    }
    if(y < 0){
      velY *= -1;
    }
    if(y > 566){
      velY *= -1;
    }
  }
  
  public void draw(Graphics2D g2d){
    if(!collected){
      g2d.drawImage(getPowerUpImage(), x, y, null);
    }
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x, y, 50, 50);
  }
  
  public Image getPowerUpImage(){
    ImageIcon i = new ImageIcon(getClass().getResource(powerUpImage));
    Image newImage = Coin.resizeImage(i.getImage(), 50, 50);
    return newImage;
  }
}
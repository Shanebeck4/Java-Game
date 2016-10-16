//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Enemy Class
//
//    June 1, 2016
//

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GlobalPosition{
  
  int leftRect;
  int rightRect;
  int topRect;
  int bottomRect;
  
  int velX;
  int velY;
  
  Color enemyColor;
  ArrayList<Color> colorList = new ArrayList<Color>();
  Random randomGen = new Random();
  Rectangle enemyRect;
  
  public Enemy(int x, int y, int velX, int velY){
    super(x, y);
    enemyRect = new Rectangle(x, y, 31, 31);
    for(int i = 0; i < 100; i++){
      colorList.add(new Color(randomGen.nextInt(255), randomGen.nextInt(255), randomGen.nextInt(255)));
      enemyColor = colorList.get(randomGen.nextInt(colorList.size()));
    }
    
    this.velX = velX;
    this.velY = velY;
    
    leftRect = x;
    rightRect = x + 31;
    topRect = y;
    bottomRect = y + 31;
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x, y, 30, 30);
  }
  
  public void update(){
    x += velX;//Update rect x position 
    y += velY;//Update rect y position
    enemyRect.x = x;//Keep border with rect
    enemyRect.y = y;
    
    //if(x > 745 || x < -2)
    //{
      //velX *= -1;//Change direction
    //}
    //Collision
    if(x < 0){
      velX *= -1;
    } 
    if(x > 750){
      velX *= -1;
    }
    if(y < 0){
      velY *= -1;
    }
    if(y > 570){
      velY *= -1;
    }
  }
  
  public void draw(Graphics2D g2d){
	g2d.setColor(Color.black);
    g2d.draw(enemyRect);
    g2d.setColor(enemyColor);
    g2d.fillRect(x+1, y+1, 30, 30);
  }
}
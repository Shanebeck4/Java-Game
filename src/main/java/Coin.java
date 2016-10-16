//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Coin Class
//
//    May 30, 2016
//

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Coin extends GlobalPosition
{
  private String coinImage = "/images/coin.png";
  private LinkedList<Coin> c = Controller.getCoinBounds();
  
  int velX;
  int velY;
  
  public Coin(int x, int y, int velX, int velY)
  {
    super(x, y);
    this.velX = velX;
    this.velY = velY;
  }
  
  public void update()
  {
    x += velX;
    y += velY;
    
    //Collision
    if(x < 0){
      velX *= -1;
    } 
    if(x > 754){
      velX *= -1;
    }
    if(y < 0){
      velY *= -1;
    }
    if(y > 571){
      velY *= -1;
    }
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x, y, 40, 40);
  }
  
  public void draw(Graphics2D g2d)
  {
    g2d.drawImage(getCoinImage(), x, y, null);
  }
  
  public Image getCoinImage()
  {
    ImageIcon i = new ImageIcon(getClass().getResource(coinImage));
    Image newImage = resizeImage(i.getImage(), 40, 40);
    return newImage;
  }
  
  public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
 
        return bufferedImage;
  }
}
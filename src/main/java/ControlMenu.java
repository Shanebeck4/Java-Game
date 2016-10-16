//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Control Menu Class
//
//    July 7, 2016
//

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class ControlMenu implements MouseListener, MouseMotionListener, KeyListener{
  
  private String controlImage = "/images/arrowKeys.png";
  ImageIcon i = new ImageIcon(getClass().getResource(controlImage));
  Image newControlImage = Game.resizeImage(i.getImage(), 204, 140);
  private String playerImage = "/images/player.png";
  ImageIcon j = new ImageIcon(getClass().getResource(playerImage));
  Image newPlayerImage = Game.resizeImage(j.getImage(), 50, 50);
  private String backButtonImage = "/images/backButton.png";
  ImageIcon k = new ImageIcon(getClass().getResource(backButtonImage));
  Image newBackButtonImage = Game.resizeImage(k.getImage(), 50, 50);
  
  //Fonts
  Font font = new Font("Retro Computer", Font.PLAIN, 70);
  Font font1 = new Font("Retro Computer", Font.PLAIN, 20);
  
  int x;
  int y;
  
  //Mouse coordinates
  int mouseX;
  int mouseY;
  
  //Random number generator
  Random randomGen;
  
  public ControlMenu(){
    //No arg constructor
    x = 250;
    y = 175;
    
    //Initialize random number generator
    randomGen = new Random();
  }
  
  public void update(){
    
  }
  
  public void draw(Graphics2D g2d){
    g2d.setColor(Color.white);
    g2d.setFont(font);
    g2d.drawString("CONTROLS", 150, 100);
    g2d.drawImage(newControlImage, x, y, null);
    g2d.drawImage(newPlayerImage, x + 240, y + 83, null);
    g2d.setFont(font1);
    g2d.drawString("Use the arrow keys (or WASD) to move.", 130, 400);
    g2d.drawString("That's pretty much it. Also, 'E' will", 130, 420);//420 :p
    g2d.drawString("open the store. Now go get some coins.", 130, 440);
    g2d.drawImage(newBackButtonImage, 10, 555, null);
    g2d.setColor(new Color(randomGen.nextInt(255), randomGen.nextInt(255), randomGen.nextInt(255)));
    g2d.drawString("[MAIN MENU]", 70, 588);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
	  if(mouseX >= 10 && mouseX <= 60 && mouseY >= 555 && mouseY <= 605){
		  Game.backToMainMenu = true;
		  Game.controlMenuSelected = false;
		  Game.mainMenu = true;
	  }
  }
	
  @Override
  public void mousePressed(MouseEvent e) {
			
  }
	
  @Override
  public void mouseReleased(MouseEvent e) {
		
  }
	
  @Override
  public void mouseEntered(MouseEvent e) {
		
  }
	
  @Override
  public void mouseExited(MouseEvent e) {
		
  }
  
  @Override
  public void mouseDragged(MouseEvent e) {
	//Empty
  }

  @Override
  public void mouseMoved(MouseEvent e) {
	//Update current x and y positions of the mouse
	mouseX = e.getX();
	mouseY = e.getY();
  }

  @Override
  public void keyTyped(KeyEvent e) {
	
  }

  @Override
  public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
	if(key == KeyEvent.VK_BACK_SPACE){
		Game.backToMainMenu = true;
		Game.controlMenuSelected = false;
		Game.mainMenu = true;
	}
  }

  @Override
  public void keyReleased(KeyEvent e) {
	
  }
}
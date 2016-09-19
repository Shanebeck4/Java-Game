//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Menu Class
//
//    June 3, 2016
//

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GlobalPosition{
  
  Color startColor;
  Color quitColor;
  Color controlsColor;
  Color highscoreColor;
  
  ArrayList<String> optionList;
  boolean startSelected;
  boolean quitSelected;
  boolean controlsSelected;
  boolean highscoreSelected;
  int menuIndex = 0;
  
  //Player image string (path)
  private String playerImage = "/images/player.png";
  ImageIcon i = new ImageIcon(getClass().getResource(playerImage));
  Image newPlayerImage = Game.resizeImage(i.getImage(), 500, 500);
  
  public Menu(int x, int y){
    super(x, y);
    startSelected = false;
    quitSelected = true;
    controlsSelected = false;
    highscoreSelected = false;
    startColor = Color.white;
    quitColor = Color.white;
    optionList = new ArrayList<String>();
    optionList.add("Quit");
    optionList.add("Controls");
    optionList.add("Highscores");
    optionList.add("Start");
  }
  
  public void update(){
    if(startSelected){
      startColor = new Color(240,50,50);
      quitColor = Color.white;
      controlsColor = Color.white;
      highscoreColor = Color.white;
    }
    else if(quitSelected){
      startColor = Color.white;
      quitColor = new Color(240,50,50);
      controlsColor = Color.white;
      highscoreColor = Color.white;
    }
    else if(controlsSelected){
      startColor = Color.white;
      quitColor = Color.white;
      controlsColor = new Color(240,50,50);
      highscoreColor = Color.white;
    }
    else if(highscoreSelected){
      startColor = Color.white;
      quitColor = Color.white;
      controlsColor = Color.white;
      highscoreColor = new Color(240,50,50);
    }
  }
  
  public void keyPressed(KeyEvent event){
    //Menu controls
    int key = event.getKeyCode();
    if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !Game.play){
      if(optionList.get(menuIndex) == "Start"){//If start is selected
        startSelected = false;//Set startSelected to true so it will change color
        quitSelected = true;//set quit selected to false because it isn't selected
        controlsSelected = false;
        highscoreSelected = false;
        menuIndex -= 3;
      }
      else if(optionList.get(menuIndex) == "Quit"){
        startSelected = false;
        quitSelected = false;
        controlsSelected = true;
        highscoreSelected = false;
        menuIndex++;
      }
      else if(optionList.get(menuIndex) == "Controls"){
        startSelected = false;//Set startSelected to false so it will change color
        quitSelected = false;//set quit selected to false because it isn't selected
        controlsSelected = false;
        highscoreSelected = true;
        menuIndex++;//Add one to the index for the array to keep current position
      }
      else if(optionList.get(menuIndex) == "Highscores"){
        startSelected = true;//Set startSelected to false so it will change color
        quitSelected = false;//set quit selected to false because it isn't selected
        controlsSelected = false;
        highscoreSelected = false;
        menuIndex++;//Add one to the index for the array to keep current position
      }
    }
    if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN && !Game.play){
      if(optionList.get(menuIndex) == "Start"){//If start is selected
        startSelected = false;//Set startSelected to true so it will change color
        quitSelected = false;//set quit selected to false because it isn't selected
        controlsSelected = false;
        highscoreSelected = true;
        menuIndex --;//Add one to the index for the array to keep current position
      }
      else if(optionList.get(menuIndex) == "Quit"){
        startSelected = true;
        quitSelected = false;
        controlsSelected = false;
        highscoreSelected = false;
        menuIndex += 3;
      }
      else if(optionList.get(menuIndex) == "Controls"){
        startSelected = false;//Set startSelected to false so it will change color
        quitSelected = true;//set quit selected to false because it isn't selected
        controlsSelected = false;
        highscoreSelected = false;
        menuIndex--;//Add one to the index for the array to keep current position
      }
      else if(optionList.get(menuIndex) == "Highscores"){
        startSelected = false;//Set startSelected to false so it will change color
        quitSelected = false;//set quit selected to false because it isn't selected
        controlsSelected = true;
        highscoreSelected = false;
        menuIndex--;
      }
    }
    else if(key == KeyEvent.VK_ENTER && startSelected && !Game.play && Game.mainMenu){
      Game.play = true;
    }
    else if(key == KeyEvent.VK_ENTER && quitSelected && !Game.play && Game.mainMenu){
      Game.play = false;
      System.exit(0);
    }
    else if(key == KeyEvent.VK_ENTER && controlsSelected && !Game.play && Game.mainMenu){
      Game.play = false;
      Game.controlMenuSelected = true;
      Game.mainMenu = false;
      Game.backToMainMenu = false;
    }
  }
  
  public Color changeColorRandom(){
	  //Generate a random color and then return it
	  Random randomGen = new Random();
	  Color newColor = new Color(randomGen.nextInt(255), randomGen.nextInt(255), randomGen.nextInt(255), randomGen.nextInt(255));
	  return newColor;
  }
  
  public void draw(Graphics2D g2d){    
    //Menu with just text
    g2d.setColor(startColor);
    Font font = new Font("Retro Computer", Font.PLAIN, 48);
    Font font1 = new Font("Retro Computer", Font.PLAIN, 70);
    Font font2 = new Font("Retro Computer", Font.PLAIN, 12);
    g2d.setFont(font);
    g2d.drawString("START", x, y+65);
    g2d.setColor(highscoreColor);
    g2d.drawString("HIGHSCORES", x, y+115);
    g2d.setColor(controlsColor);
    g2d.drawString("CONTROLS", x, y+165);
    g2d.setColor(quitColor);
    g2d.drawString("QUIT", x, y+215);
    g2d.setColor(changeColorRandom());//Make start be a random color
    g2d.setFont(font1);
    g2d.drawString("[Coin Getter]", (Main.WIDTH/2) - 335, Main.HEIGHT/2);
    g2d.setColor(Color.white);
    g2d.setFont(font2);
    g2d.drawString("CREATED BY SHANE HARRINGTON", Main.WIDTH/2 + 82, Main.HEIGHT/2 + 20);
    //Draw player image
    g2d.drawImage(newPlayerImage, 450, 400, null);
  }
}
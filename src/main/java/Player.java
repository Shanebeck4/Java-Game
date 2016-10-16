//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Player Class
//
//    May 30, 2016
//

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;

public class Player extends GlobalPosition{
  
  private String playerImage = "/images/player.png";
  private LinkedList<Coin> c = Controller.getCoinBounds();
  private LinkedList<Enemy> enemyList = EnemyController.getEnemyBounds();
  static int s = Score.getScore();
  //private Rectangle powerUpBounds = PowerUp.powerUpBounds;
  Rectangle playerBounds;
  
  //Velocity variables for player
  int velX = 0;
  int velY = 0;
  
  int firstEnter = 0;//Counter that increases to 1 after enter is pressed for the first time
  static int playerHealth = 200;//Player's current health
  static boolean playerAlive = true;//Keeps track of whether the player is alive or not
  static boolean speedPowerUp = false;//Checks if user has speed power up or not
  static boolean multiplierPowerUp = false;//Checks if user has multiplier power up, which doubles score for the record
  static boolean healthPowerUp = false;
  static boolean slowerEnemyRespawn = false;
  
  //List of player's current power ups
  static ArrayList<Image> powerUpList = new ArrayList<>();
  
  public Player(int x, int y){
    super(x, y);
    
    //Initialize player health
    //playerAlive = true;
    
    //Player bounds
    playerBounds = getBounds();
  }
  
  public void update(){
	if(!speedPowerUp){
		x += velX;
    	y += velY;
	}
	else if(speedPowerUp){
		x += (velX * 2);
		y += (velY *2);
	}
	if(!multiplierPowerUp)
		s += 1;
	else
		s +=2;
    
    //Collision
    if(x < 0){
      x = 0;
    } 
    if(x > 744){
      x = 744;
    }
    if(y < 0){
      y = 0;
    }
    if(y > 566){
      y = 566;
    }
    
    collision();//Check collision with player object
    enemyCollision();//Check collision with player and enemy
    //powerUpCollision();//Check collision with player and power up
    
    //Check player health and if player should be dead
    if(playerHealth <= 0){
      playerAlive = false;
    }
    
    playerBounds = getBounds();
  }
  
  public void keyPressed(KeyEvent e){
    //Keyboard input
    if(playerAlive){
      int key = e.getKeyCode();
      
      if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
        velX = 5;
      } else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
        velX = -5;
      } else if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
        velY = -5;
      } else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
        velY = 5;
      }
    }
    //Pause the game if Enter is pressed
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_ENTER && !Game.pauseSelected && Game.play && firstEnter > 0){
    	//firstEnter += 1;
    	Game.pauseSelected = true;
    }
    else if(key == KeyEvent.VK_ENTER && firstEnter < 1 && Game.play){
    	firstEnter += 1;
    	Main.resetGame = true;
    }
    else if(key == KeyEvent.VK_ENTER){
    	Game.pauseSelected = false;
    }
    //Open the game store
    if(key == KeyEvent.VK_E && !Store.storeSelected){
    	Store.storeSelected = true;
    }
    else if(key == KeyEvent.VK_E && Store.storeSelected){
    	Store.storeSelected = false;
    }
  }
  
  public void keyReleased(KeyEvent e){
    //Stops movement when keys are no longer being pressed
    int key = e.getKeyCode();
    if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
      velX = 0;
    } else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
      velX = 0;
    } else if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
      velY = 0;
    } else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
      velY = 0;
    }
  }
  
  public void collision(){
    for(int i = 0; i < c.size(); i++){
      if(getBounds().intersects(c.get(i).getBounds())){
        c.remove(i);
        s += 100;
        Controller.coinCounter += 1;
      }
    }
  }
  
  public void enemyCollision(){
    for(int i = 0; i < enemyList.size(); i++){
      
      if(getBounds().intersects(enemyList.get(i).getBounds()) && getXPosition() < enemyList.get(i).rightRect && enemyList.get(i).velX > 0){
    	if(!healthPowerUp){
    		playerHealth -= 20;//Shorten health bar when player is hit by an enemy
    	}
    	else
    		playerHealth -= 10;
    		x += 100;
      }
      else if(getBounds().intersects(enemyList.get(i).getBounds()) && getXPosition() + 51 > enemyList.get(i).leftRect && enemyList.get(i).velX < 0){
    	if(!healthPowerUp){
    		playerHealth -= 20;//Shorten health bar when player is hit by an enemy
    	}
      	else
    		playerHealth -= 10;
        x -= 100;
      }
      if(getBounds().intersects(enemyList.get(i).getBounds()) && getYPosition() + 51 > enemyList.get(i).topRect && enemyList.get(i).velY < 0){
    	
    	if(!healthPowerUp){
    		playerHealth -= 20;//Shorten health bar when player is hit by an enemy
    	}
    	else
    		playerHealth -= 10;
    	y -= 100;
      }
      else if(getBounds().intersects(enemyList.get(i).getBounds()) && getYPosition() < enemyList.get(i).bottomRect && enemyList.get(i).velY > 0){
        
    	if(!healthPowerUp){
    	  playerHealth -= 20;//Shorten health bar when player is hit by an enemy
    	}
    	else
    		playerHealth -= 10;
    	y += 100;
      }
    }
  }
  
  public void powerUpCollision(){
    //if(getBounds().intersects(powerUpBounds)){
      //System.out.println("Yo");
    //}
  }
  
  public Rectangle getBounds(){
    return new Rectangle(x, y, 50, 50);
  }
  
  public void draw(Graphics2D g2d){
    if(playerAlive){
      g2d.drawImage(getPlayerImage(), x, y, null);
      /*g2d.setColor(Color.white);
      g2d.fillRect(x, y, 50, 50);
      g2d.setColor(Color.black);
      g2d.draw(playerBounds);*/
    }
  }
  
  public Image getPlayerImage(){
    ImageIcon i = new ImageIcon(getClass().getResource(playerImage));
    Image newImage = Game.resizeImage(i.getImage(), 50, 50);
    return newImage;
  }
  
  public int getXPosition(){
    return x;
  }
  
  public int getYPosition(){
    return y;
  }
  
  public void setVelX(int velX){
	  this.velX = velX;
  }
  
  public void setVelY(int velY){
	  this.velY = velY;
  }
}
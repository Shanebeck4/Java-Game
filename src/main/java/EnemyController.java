//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Enemy Controller Class
//
//    May 30, 2016
//

import java.util.Random;
import java.util.LinkedList;
import java.awt.Graphics2D;

public class EnemyController{
  
  static LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
  
  Enemy tempEnemy;
  Random randomGen;
  int numOfEnemies;
  int playerScore;
  int currentScore;
  
  public EnemyController(){
    
    randomGen = new Random();//Random number generator
    tempEnemy = new Enemy(10, 10, randomGen.nextInt(4)+4, randomGen.nextInt(4)+4);
    numOfEnemies = 0;
    addEnemy(new Enemy(300, 300, randomGen.nextInt(4)+4, randomGen.nextInt(4)+4));
    playerScore = Score.getScore();
    currentScore = 0;
  }
  
  public void update(){
    for(int i = 0; i < enemyList.size(); i++){
      tempEnemy = enemyList.get(i);
      
      tempEnemy.update();
    }
    
    if(!Player.slowerEnemyRespawn){
	    if(playerScore > currentScore + 5000){
	      currentScore = playerScore;
	      numOfEnemies += 1;
	      addEnemy(new Enemy(randomGen.nextInt(500) + 50, randomGen.nextInt(500) + 50, randomGen.nextInt(4)+4, randomGen.nextInt(4)+4));
	    }
    }
    else if(Player.slowerEnemyRespawn){
    	if(playerScore > currentScore + 10000){
  	      currentScore = playerScore;
  	      numOfEnemies += 1;
  	      addEnemy(new Enemy(randomGen.nextInt(500) + 50, randomGen.nextInt(500) + 50, randomGen.nextInt(4)+4, randomGen.nextInt(4)+4));
  	    }
    }
    
    //Update player score
    playerScore = Score.getScore();
  }
  
  public void draw(Graphics2D g2d){
    for(int i = 0; i < enemyList.size(); i++){
      tempEnemy = enemyList.get(i);
      
      tempEnemy.draw(g2d);
    }
  }
  
  public void addEnemy(Enemy newEnemy){
    enemyList.add(newEnemy);
  }
  
  public void removeEnemy(Enemy enemy){
    enemyList.remove(enemy);
  }
  
  public static LinkedList<Enemy> getEnemyBounds(){
    return enemyList;
  }
}
//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Controller Class
//
//    May 30, 2016
//

import java.util.LinkedList;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;

public class Controller{
  
  static LinkedList<Coin> coinList = new LinkedList<Coin>();
    
  Coin tempCoin;
  Random randomGen;
  private String coinImage = "/images/coin.png";
  ImageIcon i = new ImageIcon(getClass().getResource(coinImage));
  Image newCoinImage = Game.resizeImage(i.getImage(), 50, 50);
  static int coinCounter;//Keep track of the number of coins 
    
  public Controller(){

    randomGen = new Random();//Random number generator
    //Create random number of coins in random places with random velocities
    for(int i = 0; i < randomGen.nextInt(10); i++){
      addCoin(new Coin(randomGen.nextInt(630) + 50, randomGen.nextInt(480) + 50, randomGen.nextInt(6) + 1, randomGen.nextInt(6) + 1));
    }
    tempCoin = new Coin(10, 10, 1, 1);
  }
  
  public void update(){
    for(int i = 0; i < coinList.size(); i++){
      tempCoin = coinList.get(i);
      
      tempCoin.update();
    } 
    
    //If all coins are gone, make more coins
    if(coinList.isEmpty()){
      for(int i = 0; i < randomGen.nextInt(10); i++){
        addCoin(new Coin(randomGen.nextInt(630) + 50, randomGen.nextInt(480) + 50, randomGen.nextInt(6) + 1, randomGen.nextInt(5) + 1));
      }
      if(Player.playerHealth <= 180){
        Player.playerHealth += 20;
      }
    }
  }
  
  public void draw(Graphics2D g2d){
    for(int i = 0; i < coinList.size(); i++){
      tempCoin = coinList.get(i);
      
      tempCoin.draw(g2d);
    }
    if(Game.play){
      Font font = new Font("Retro Computer", Font.PLAIN, 40);
      g2d.setColor(Color.white);
      g2d.setFont(font);
      g2d.drawImage(newCoinImage, 250, 20, null);
      g2d.drawString(": " + Controller.coinCounter, 310, 60);
    }
  }
  
  public void addCoin(Coin coin){
    coinList.add(coin);
  }
  
  public void removeCoin(Coin coin){
    coinList.remove(coin);
  }      
  
  public static LinkedList<Coin> getCoinBounds(){
    
    return coinList;
  }
}
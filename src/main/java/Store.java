//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Coin Class
//
//    July 18, 2016
//

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

public class Store implements MouseMotionListener, MouseListener{
	
	//Instance variables
	Rectangle storeButton;
	Rectangle storeMenu;
	Color storeButtonColor;
	Color storeButtonFontColor;
	Font font;
	Font font1;
	String shoeImagePath = "/images/speedPowerUp.png";
    ImageIcon i = new ImageIcon(getClass().getResource(shoeImagePath));
    Image shoeImage = Game.resizeImage(i.getImage(), 75, 50);
    String coinImagePath = "/images/coin.png";
    ImageIcon j = new ImageIcon(getClass().getResource(coinImagePath));
    Image coinImage = Game.resizeImage(j.getImage(), 30, 30);
    String healthImagePath = "/images/healthPowerUp.png";
    ImageIcon k = new ImageIcon(getClass().getResource(healthImagePath));
    Image healthImage = Game.resizeImage(k.getImage(), 50, 50);
    String multiplierImagePath = "/images/multiplier.png";
    ImageIcon l = new ImageIcon(getClass().getResource(multiplierImagePath));
    Image multiplierImage = Game.resizeImage(l.getImage(), 50, 50);
    String removeEnemyImagePath = "/images/removeEnemy.png";
    ImageIcon m = new ImageIcon(getClass().getResource(removeEnemyImagePath));
    Image removeEnemyImage = Game.resizeImage(m.getImage(), 50, 50);
    String slowerRespawnImagePath = "/images/slow.png";
    ImageIcon n = new ImageIcon(getClass().getResource(slowerRespawnImagePath));
    Image slowerRespawnImage = Game.resizeImage(n.getImage(), 50, 50);
    
	//Mouse coordinates
	int mouseX;
	int mouseY;
	
	//Booleans
	static boolean storeSelected;
	boolean shoesSelected;
	boolean heartSelected;
	boolean removeSelected;
	boolean multiplierSelected;
	boolean slowerRespawnSelected;
	
	//Colors
	Color darkBlueTransparent;
	Color whiteTransparent;
	Color optionFontColor;

	public Store(){
		 whiteTransparent = new Color(255,255,255,200);
		 darkBlueTransparent = new Color(20,15,36);
		 storeButton = new Rectangle(610, 510, 160, 90);
		 storeMenu = new Rectangle(200, 90, 400, 510);
		 storeButtonColor = darkBlueTransparent;
		 storeButtonFontColor = whiteTransparent;
		 storeSelected = false;
		 font = new Font("Retro Computer", Font.PLAIN, 30);//Set font
		 font1 = new Font("Retro Computer", Font.PLAIN, 18);
		 shoesSelected = false;
	}
	
	public void update(){
		
		if(mouseX > storeButton.x && mouseX < storeButton.x + 160
				&& mouseY > storeButton.y && mouseY < storeButton.y + 90){
			storeButtonColor = whiteTransparent;
			storeButtonFontColor = darkBlueTransparent;
		}
		else{
			storeButtonColor = darkBlueTransparent;
			storeButtonFontColor = whiteTransparent;
		}
		if(mouseX > 210 && mouseX < 590 && mouseY > 100 && mouseY < 190){
			shoesSelected = true;
		}
		else{
			shoesSelected = false;
		}
		if(mouseX > 210 && mouseX < 590 && mouseY > 200 && mouseY < 290){
			multiplierSelected = true;
		}
		else{
			multiplierSelected = false;
		}
		if(mouseX > 210 && mouseX < 590 && mouseY > 300 && mouseY < 390){
			removeSelected = true;
		}
		else{
			removeSelected = false;
		}
		if(mouseX > 210 && mouseX < 590 && mouseY > 400 && mouseY < 490){
			heartSelected = true;
		}
		else{
			heartSelected = false;
		}
		if(mouseX > 210 && mouseX < 590 && mouseY > 500 && mouseY < 590){
			slowerRespawnSelected = true;
		}
		else{
			slowerRespawnSelected = false;
		}
	}
	
	public void drawStoreMenu(Graphics2D g2d){
		g2d.draw(storeMenu);
		g2d.setColor(new Color(50,50,50,200));
		g2d.fillRect(201, 91, 399, 499);
		storeButtonColor = whiteTransparent;
		storeButtonFontColor = darkBlueTransparent;
		drawOption(g2d, 100, 50, shoeImage, "Running Shoes", "-SPEED BOOST-", shoesSelected);
		drawOption(g2d, 200, 35, multiplierImage, "Score Multiplier", "-POINTS WORTH 2X-", multiplierSelected);
		drawOption(g2d, 300, 30, removeEnemyImage, "Remove Enemy", "-REMOVE ONE ENEMY-", removeSelected);
		drawOption(g2d, 400, 30, healthImage, "Extra Health", "-INCREASE HEALTH-", heartSelected);
		drawOption(g2d, 500, 50, slowerRespawnImage, "Slower Respawn", "-SLOW ENEMY RESPAWN-", slowerRespawnSelected);
	}
	
	public void drawOption(Graphics2D g2d, int yPosition, int priceOfItem, Image powerUpImage, String itemName, String itemDescription, boolean itemSelected){
		//Draw store option
		g2d.setColor(whiteTransparent);
		g2d.draw(new Rectangle(210,yPosition,380,90));
		if(!itemSelected){
			g2d.setColor(darkBlueTransparent);
			optionFontColor = whiteTransparent;
		}
		else if(itemSelected){
			g2d.setColor(whiteTransparent);
			optionFontColor = darkBlueTransparent;
		}
		g2d.fillRect(211, yPosition+1, 379, 89);
		g2d.drawImage(powerUpImage, 500, yPosition+20, null);
		g2d.setColor(optionFontColor);
		g2d.setFont(font1);
		g2d.drawString(itemName,220,yPosition+23);
		g2d.drawString(itemDescription,220,yPosition+38);
		g2d.drawImage(coinImage, 220, yPosition+48, null);
		g2d.drawString(": " + priceOfItem, 255, yPosition+70);
	}
	
	public void draw(Graphics2D g2d){
		//Make font
		if(!storeSelected){
			g2d.setColor(whiteTransparent);
			g2d.draw(storeButton);
			g2d.setFont(font);
			g2d.setColor(storeButtonColor);
			g2d.fillRect(611, 511, 159, 89);
			g2d.setColor(storeButtonFontColor);
			g2d.drawString("Store", storeButton.x + 18, storeButton.y + 58);
		}
		else{
			g2d.setColor(whiteTransparent);
			g2d.fillRect(611, 511, 159, 89);
			g2d.setColor(storeButtonFontColor);
			g2d.setFont(font);
			g2d.drawString("Store", storeButton.x + 18, storeButton.y + 58);
			g2d.setColor(whiteTransparent);
			g2d.draw(storeButton);
			drawStoreMenu(g2d);
		}
	}
	
	public boolean canBuy(int playerCoins, int priceOfItem){
		
		if(playerCoins >= priceOfItem)
			return true;
		return false;
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
	public void mouseClicked(MouseEvent e) {
		if(storeSelected){
			if(mouseX > 210 && mouseX < 590 && mouseY > 100 && mouseY < 190){
				//Check to see if mouse is over first store option
				if(canBuy(Controller.coinCounter, 50)){
					Player.speedPowerUp = true;
					Player.powerUpList.add(shoeImage);
					Controller.coinCounter -= 50;
				}
				else
					System.out.println("You cannot buy this");
			}
			else if(mouseX > 210 && mouseX < 590 && mouseY > 200 && mouseY < 290){
				//Check to see if mouse is over second store option
				if(canBuy(Controller.coinCounter, 35)){
					Player.multiplierPowerUp = true;
					Player.powerUpList.add(multiplierImage);
					Controller.coinCounter -= 35;
				}
				else
					System.out.println("You cannot buy this");
			}
			else if(mouseX > 210 && mouseX < 590 && mouseY > 300 && mouseY < 390){
				//Check to see if mouse is over third store option
				if(canBuy(Controller.coinCounter, 30)){
					EnemyController.enemyList.remove(0);
					Controller.coinCounter -= 30;
				}
				else
					System.out.println("You cannot buy this");
			}
			else if(mouseX > 210 && mouseX < 590 && mouseY > 400 && mouseY < 490){
				//Check to see if mouse is over fourth store option
				if(canBuy(Controller.coinCounter, 30)){
					Player.healthPowerUp = true;
					Player.powerUpList.add(healthImage);
					Controller.coinCounter -= 30;
				}					
				else
					System.out.println("You cannot buy this");
			}
			else if(mouseX > 210 && mouseX < 590 && mouseY > 500 && mouseY < 590){
				//Check to see if mouse is over fourth store option
				if(canBuy(Controller.coinCounter, 50)){
					Player.slowerEnemyRespawn = true;
					Player.powerUpList.add(slowerRespawnImage);
					Controller.coinCounter -= 50;
				}					
				else
					System.out.println("You cannot buy this");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mouseX > storeButton.x && mouseX < storeButton.x + 160
				&& mouseY > storeButton.y && mouseY < storeButton.y + 90){
			if(!storeSelected)
				storeSelected = true;
			else
				storeSelected = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

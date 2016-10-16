//
//    Shane Harrington
//
//    Ithaca College
//
//    Test Game - Game Class
//
//    May 30, 2016
//

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.TargetDataLine;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.util.Random;
import javax.swing.JTextField;

public class Game extends JPanel implements ActionListener
{
  private String background = "images/background.png";
  static String musicFile = "audio/RenaissanceEndo.wav";
  
  Timer gameLoopTimer;
  Player p;
  EnemyController ec;
  Controller c;
  Controller menuCoins;
  HealthBar h;
  Score s;
  Menu m;
  ControlMenu cm;
  Store st;
  //static PowerUp pu;
  
  static boolean play;
  static boolean playerAlive;
  static boolean controlMenuSelected;
  static boolean mainMenu;
  static boolean pauseSelected;
  static boolean storeSelected;
  static boolean backToMainMenu;
  
  //RGB values for background
  int r = 0;
  int gr = 0;
  int b = 0;
  
  //Random Generator
  Random randomGen = new Random();
  JTextField highscoreInput;
  
  public Game(){
    setFocusable(true);
    
    playerAlive = Player.playerAlive;
       
    gameLoopTimer = new Timer(10, this);
    gameLoopTimer.setInitialDelay(1000);
    gameLoopTimer.start();
    //gameLoopTimer.addActionListener(this);
    
    //Menu Object
    m = new Menu(10, 0);
    addKeyListener(new KeyInputM(m));
    
    //Create Player object
    p = new Player(500, 500);
    addKeyListener(new KeyInput(p));
    
    //Create enemy controller to create any number of enemies
    ec = new EnemyController();
    
    //Create coin controller to create any number of coins
    c = new Controller();
    
    //Create health bar
    h = new HealthBar(19, 19);
    
    //Create score counter
    s = new Score();
    
    //Create power up
    //pu = new PowerUp(300, 300);
    
    //Create control menu
    cm = new ControlMenu();
    
    //Create the store
    st = new Store();
    
    //Initialize play
    play = false;
    mainMenu = true;
    controlMenuSelected = false;
    pauseSelected = false;
    storeSelected = Store.storeSelected;
    backToMainMenu = true;
    
    //RGB values for background
    r = 20;
    gr = 15;
    b = 36;
    
    //Add stuff
    addMouseMotionListener(st);
    addMouseListener(st);
    addMouseMotionListener(cm);
    addMouseListener(cm);   
    addKeyListener(cm);
    
    highscoreInput = new JTextField();
    add(highscoreInput);

    //ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("/images/player.png"));
    //Image i = ic.getImage();
    //Main.frame.setIconImage(i);
  }
  
  public void paint(Graphics g){
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color(r, gr, b));
    g2d.fillRect(0, 0, 800, 650);
    //g2d.drawImage(getBackgroundImage(), 0, 0, this);//Draw background image
    if(!play && mainMenu && backToMainMenu){
      m.draw(g2d);
      c.draw(g2d);
    }
    else if(play && playerAlive){
      ec.draw(g2d);
      p.draw(g2d);//Draw player icon
      c.draw(g2d);//Draw coins
      h.draw(g2d);//Draw health bar
      s.draw(g2d);//Draw score counter
      //pu.draw(g2d);//Draw power up
      st.draw(g2d);//Draw the store
    }
    else if(play && !playerAlive){
      s.draw(g2d);//Draw score counter no matter what
    }
    else if(!play && controlMenuSelected && !backToMainMenu){
      cm.draw(g2d);
    }
    if(pauseSelected){
        Font font = new Font("Retro Computer", Font.PLAIN, 70);
        g2d.setFont(font);
        g2d.setColor(Color.white);
     g2d.drawString("PAUSE", 250, Main.HEIGHT/2);
     for(int i = 0; i < Player.powerUpList.size(); i++){
      Image currentImage = Player.powerUpList.get(i);
      g2d.drawImage(currentImage, 250, 400, null);
     }
    }
  }
  
  public Image cd(){
    ImageIcon i = new ImageIcon(getClass().getResource(background));
    Image image = i.getImage();
    Image newImage = resizeImage(image, 800, 650);
    return newImage;
  }
  
  public static void playBackgroundMusic(){
   new Thread(new Runnable(){
  public void run(){
   try{
    //Open up audio file
    Clip clip = AudioSystem.getClip();
    AudioInputStream inputStream =
            AudioSystem.getAudioInputStream(
                    ClassLoader.getSystemResource(musicFile));
    clip.open(inputStream);
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
   }
   catch(Exception ex){
    //Print error if file can't be opened for some reason
    System.out.println("Can't play sound, check file path");
   }
  }
   }).start();
  }
  public static BufferedImage resizeImage(final Image image, int width, int height){
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();

        return bufferedImage;
  }
  
  public void actionPerformed(ActionEvent e){
    //Game Loop executed every 10 milliseconds according to Timer
    playerAlive = Player.playerAlive;
    repaint();
    if(playerAlive && play && !pauseSelected && !storeSelected){
      p.update();
      ec.update();
      c.update();
      h.update();
      s.update();
      //pu.update();
    }
    else if(playerAlive && !play && backToMainMenu){
      c.update();
      m.update();
    }
    st.update();//Always update the store
    storeSelected = Store.storeSelected;
  }
}

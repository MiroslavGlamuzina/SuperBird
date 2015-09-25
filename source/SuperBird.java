import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SuperBird extends PApplet {




Minim minim;
AudioPlayer player;
AudioPlayer sfx;
AudioPlayer click;
boolean left = false;
boolean right = false;
boolean up = false;
boolean down = false;
Menu menu;
boolean startgameBoolean;
boolean startgame;
GameWorld game;
ArrayList<Coin> img = new ArrayList<Coin>();

public void setup() {
  startgameBoolean = false;
  startgame = false;
  size(1000, 800);  // Size must be the first statement
  stroke(255);     // Set line drawing color to white
  frameRate(30);
  minim = new Minim(this);
  sfx = minim.loadFile("jump.mp3");
  click = minim.loadFile("click.mp3");
  player = minim.loadFile("theme.mp3");
  player.loop();
  //player.play();
  menu = new Menu();
  setupImage();
}
public void setupImage() {
  for (int i =0; i <30; i++)
  {
    img.add(new Coin(new PVector(random(width), (random(20)*(-1)))));
  }
}

public void imagePaint() {
  for (int i =0; i <img.size (); i++)
  {
    img.get(i).paint();
  }
}

public void draw() {
  update();
  if (startgame)
  {
    game.paint();    
    imagePaint();
  } else
  {
    menu.paint();
    imagePaint();
  }
} 

public void update() {
  if (!startgameBoolean)
  {
    game = new GameWorld();
    startgameBoolean = true;
    //    startgame=true;
  }
}



public void keyPressed ()
{

  if (keyCode == LEFT)
  {
    left = true;
  }
  if (keyCode == RIGHT)
  {
    right = true;
  }
  if (keyCode == UP)
  {
    up = true;
  }
  if (keyCode == DOWN)
  {
    down = true;
  }
}
public void keyReleased ()
{
  if (keyCode == LEFT)
  {
    left = false;
  }
  if (keyCode == RIGHT)
  {
    right = false;
  }
  if (keyCode == UP)
  {
    up = false;
  }
  if (keyCode == DOWN)
  {
    down = false;
  }
}

public void mouseClicked() {
  if (mouseX > width/2-200 && mouseX < width/2+400 && mouseY > height/2 && mouseY < height/2 +50 && !startgame)
  {
    click.play();
    startgame=true;
    println("hit");
  }
  if (gameover) {
    startgame = false;
    startgameBoolean = false;
    gameover=false;
  }
}
boolean gameover;

class Coin{
PVector pos;
PImage img;
float speed;
Coin(PVector pos )
{
this.pos = pos; 
img = loadImage("coin.png");
speed = 2+random(10);
}

public void paint(){
  update();
  image(img, pos.x, pos.y,25,25);
}


public void update(){
  pos.y+= speed;;
  pos.x +=1-random(2);
if(pos.y > height)
{
pos.y = random(30)*(-1);
}
if(pos.x > width && pos.y >height)
{
pos.x = 0;
}
if(pos.x < 0 && pos.y >height)
{
pos.x = random(width);
}
}
}
class DiamondBird {
  int x ;
  int y ;
  int r;
  DiamondBird(int x, int y)
  {                    
    this.x = x;
    this.y = y;
    r=30;
  }

  public void paint() {
    fill(46, 252, 173);
    ellipse(x, y, r, r);
  }
}


class GameWorld {
  TargetBird  tBird;
  ArrayList<Portals> p ;

  GameWorld() {
    tBird = new TargetBird(new PVector(200, 50));
    p = new ArrayList<Portals>();
    portalSetup();
  }


  public void paint() {
    Background();
    update();
    for (int i =0; i < p.size (); i++)
    {
      p.get(i).paint();
    }

    tBird.drawMe();
  } 



  public void portalSetup() {
    //middle
    p.add(new Portals(200, height/2));
    p.add(new Portals(width-200, height/2));

    //bottom
    p.add(new Portals((width/2)-100, height -100));
    p.add(new Portals((width/2)+100, height-100));
  }

  public void update() {
    portalCollision();
    gameOver();
  }

  public void gameOver() {
    if (gameover) {
      textSize(32);
      fill(0, 0, 0);
      text("GAME OVER :( ", width/2-100, height/2 -50);
      textSize(22);
      text("click the screen to try again ", 350, height/2 +20);
    }
  }

  public void portalCollision() {
    if (tBird.pos.x >= p.get(0).x -20 && tBird.pos.x <= p.get(0).x + p.get(0).w &&
      tBird.pos.y >= p.get(0).y-20 && tBird.pos.y <= p.get(0).y + p.get(0).l)
    {
      background(255, 255, 255);
      tBird.pos.x = p.get(1).x+50;
      tBird.pos.y = p.get(1).y;
    }

    if (tBird.pos.x >= p.get(1).x -20 && tBird.pos.x <= p.get(1).x + p.get(1).w &&
      tBird.pos.y >= p.get(1).y-20 && tBird.pos.y <= p.get(1).y + p.get(1).l)
    {
      background(255, 255, 255);
      tBird.pos.x = p.get(2).x+50;
      tBird.pos.y = p.get(2).y;
    }

    if (tBird.pos.x >= p.get(2).x -20&& tBird.pos.x <= p.get(2).x + p.get(2).w &&
      tBird.pos.y >= p.get(2).y -20 &&  tBird.pos.y <= p.get(2).y + p.get(2).l)
    {
      //bottom left tele
      background(255, 255, 255);
      tBird.pos.x = p.get(1).x+50;
      tBird.pos.y = p.get(1).y;
    }

    if (tBird.pos.x >= p.get(3).x -20 && tBird.pos.x <= p.get(3).x + p.get(3).w &&
      tBird.pos.y >= p.get(3).y -20 && tBird.pos.y <= p.get(3).y + p.get(3).l)
    {
      background(255, 255, 255);
      tBird.pos.x = p.get(0).x+50;
      tBird.pos.y = p.get(0).y;
    }
  }
  public void Background() {
    //color
    background(51, 255, 255);
    //BACKGROUND
    //PYRAMIDS
    fill(255, 153, 51);
    stroke(0, 255, 0);
    strokeWeight(10);
    triangle(0, height, 100, height-25, 200, height);
    strokeWeight(8);
    triangle(50, height, 150, height-50, 250, height);
    strokeWeight(10);
    triangle(0, height, 100, height-50, 200, height);
    //pyramid 2
    strokeWeight(8);
    triangle(width-50, height, width, height-25, width, height);
    strokeWeight(12);
    triangle(width-250, height, width-300, height-50, width-350, height);
    strokeWeight(8);
    triangle(width-550, height, width-500, height-50, width-450, height);
    strokeWeight(8);
    triangle(width-750, height, width-700, height-100, width-650, height);
    strokeWeight(8);
    triangle(width-675, height, width-625, height-25, width-600, height);
    strokeWeight(8);
    triangle(width-400, height, width-375, height-50, width-350, height);
    strokeWeight(12);
    triangle(width-100, height, width-200, height-70, width-300, height);
    strokeWeight(12);
    triangle(width-50, height, width-100, height-32, width-150, height);
    //TOP
    strokeWeight(12);
    triangle(width-250, 0, width-300, 200, width-350, 0);
    strokeWeight(8);
    triangle(width-300, 0, width-350, 100, width-400, 0);
    strokeWeight(10);
    triangle(0, 0, 100, 100, 200, 0);
    //pyramid 2
    strokeWeight(8);
    triangle(50, 0, 150, 150, 250, 0);
    strokeWeight(8);
    triangle(350, 0, 450, 150, 550, 0);
    triangle(325, 0, 350, 200, 375, 0);

    strokeWeight(12);
    triangle(width-50, 0, width-100, 250, width-150, 0);


    //floors-cieling
    strokeWeight(2);
    rect(0, height-25, width, 25);
    rect(0, -1, width, 25);
  }
}

class Menu {
  Menu() {
  }

  public void paint() {
    background(0, 255, 255);
    fill(255, 255, 0);
    stroke(0);
    strokeWeight(10);
    ellipse(width/2, height/2, width*.5f, height*.5f);
    //text
    textSize(50);
    fill(255, 125, 125);
    text("SUPER BIRD", 100, 120); 
    text("SUPER BIRD", width-300, 135); 
    text("SUPER BIRD", width/2-100, 150); 

    fill(50, 120, 175);
    rect(width/2-200, height/2, 400, 50);
    fill(102, 178, 225);
    text("PLAY", width/2-55, height/2+45);
  }
}

class Portals {

  int x ;
  int y;
  int w;
  int l;

  Portals(int x, int y) {
    this.x=x ;
    this.y =y;
    w= 30;
    l = 75;
  }

  public void paint() {
    stroke(0, 0, 0);
    strokeWeight(3);
    fill(255, 255, 102);
    ellipse(x, y, w, l);
    fill(255, 255, 102);
    ellipse(x, y, w+2, l+2);
    fill(255, 255, 102);
    ellipse(x, y, w+10, l+10);
    fill(153, 255, 255);
    ellipse(x, y, w, l);
  }
}

class TargetBird {
  PVector pos;
  int speed =2;
  boolean dir=true; 
  int vel=-3; 
  boolean alt;
  TargetBird(PVector pos) {
    this.pos = pos;
  }
  TargetBird(PVector pos, boolean alt) {
    this.pos = pos;
    this.alt = alt;
  }
  public void drawMe() {

    pushMatrix();
    translate(pos.x, pos.y);
    // rotate(PI);
    //body
    stroke(2);
    strokeWeight(2);
    fill(255, 255, 8);
    drawCircle(0, 40, 6);
    //eye
    fill(200, 255, 200);
    ellipse(20, -20, 10, 10);
    //peak
    fill(255, 255, 8);
    triangle(45, -10, 55, 0, 45, 10);
    //wing
    rotate(PI/4);
    fill(255, 255, 8);
    ellipse(-5, 20, 20, 30);
    popMatrix();
  }

  public void drawCircle(int x, int radius, int level) {       
    update();    
    float tt = 126 * level/4.0f;
    fill(255, 255, 8);
    ellipse(x, 0, radius*2, radius*2);      
    if (level > 1) {
      level = level - 1;
      drawCircle(x, radius/2, level);
      //  drawCircle(x + radius/2, radius/2, level);
    }
  }

  public void update() {
    if (!gameover) {
      if (!alt) {
        bounds();
      }
      movement();
      accelerate();
    } else
    {
    }
  }
  public void bounds () {
    if (pos.x < -30 ) {
      pos.x = width +30;
    }
    if (pos.x > width)
    {
      pos.x = -30;
    }   
    if (pos.y <25 || pos.y > height-25)
    {
      gameover = true;
    }
  }

  public void accelerate() {
    if (dir)
    {
      pos.x +=speed;
    } else {
      pos.x -=speed;
    }
  }

  public void movement() {
    pos.y +=1.2f;
    if (up) {
      sfx.play();
      vel =5;
    }
    while (vel >0)
    {
      pos.y -=1;
      vel--;
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SuperBird" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}


import ddf.minim.*;

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

void setup() {
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
void setupImage() {
  for (int i =0; i <30; i++)
  {
    img.add(new Coin(new PVector(random(width), (random(20)*(-1)))));
  }
}

void imagePaint() {
  for (int i =0; i <img.size (); i++)
  {
    img.get(i).paint();
  }
}

void draw() {
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

void update() {
  if (!startgameBoolean)
  {
    game = new GameWorld();
    startgameBoolean = true;
    //    startgame=true;
  }
}



void keyPressed ()
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
void keyReleased ()
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

void mouseClicked() {
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

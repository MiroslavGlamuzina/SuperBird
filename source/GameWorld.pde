
class GameWorld {
  TargetBird  tBird;
  ArrayList<Portals> p ;

  GameWorld() {
    tBird = new TargetBird(new PVector(200, 50));
    p = new ArrayList<Portals>();
    portalSetup();
  }


  void paint() {
    Background();
    update();
    for (int i =0; i < p.size (); i++)
    {
      p.get(i).paint();
    }

    tBird.drawMe();
  } 



  void portalSetup() {
    //middle
    p.add(new Portals(200, height/2));
    p.add(new Portals(width-200, height/2));

    //bottom
    p.add(new Portals((width/2)-100, height -100));
    p.add(new Portals((width/2)+100, height-100));
  }

  void update() {
    portalCollision();
    gameOver();
  }

  void gameOver() {
    if (gameover) {
      textSize(32);
      fill(0, 0, 0);
      text("GAME OVER :( ", width/2-100, height/2 -50);
      textSize(22);
      text("click the screen to try again ", 350, height/2 +20);
    }
  }

  void portalCollision() {
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
  void Background() {
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


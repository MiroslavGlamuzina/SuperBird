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
  void drawMe() {

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

  void drawCircle(int x, int radius, int level) {       
    update();    
    float tt = 126 * level/4.0;
    fill(255, 255, 8);
    ellipse(x, 0, radius*2, radius*2);      
    if (level > 1) {
      level = level - 1;
      drawCircle(x, radius/2, level);
      //  drawCircle(x + radius/2, radius/2, level);
    }
  }

  void update() {
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
  void bounds () {
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

  void accelerate() {
    if (dir)
    {
      pos.x +=speed;
    } else {
      pos.x -=speed;
    }
  }

  void movement() {
    pos.y +=1.2;
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


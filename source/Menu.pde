class Menu {
  Menu() {
  }

  void paint() {
    background(0, 255, 255);
    fill(255, 255, 0);
    stroke(0);
    strokeWeight(10);
    ellipse(width/2, height/2, width*.5, height*.5);
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


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

  void paint() {
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


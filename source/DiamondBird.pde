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

  void paint() {
    fill(46, 252, 173);
    ellipse(x, y, r, r);
  }
}


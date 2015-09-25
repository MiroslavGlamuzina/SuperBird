
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

void paint(){
  update();
  image(img, pos.x, pos.y,25,25);
}


void update(){
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

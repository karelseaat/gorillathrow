import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import java.io.*;

class Stok{

private Vellocity stokvel;
private Posietie stokpos;
private Image stokimg;
private Sprite stokspr;
private int updatenodig;
private boolean updatetel;
private boolean alive;
private boolean moving;
private int minx,  miny, maxx , maxy , startx, starty;
private int tempx;
private int tempy;
private int frames = 2;

public boolean getMoving() {
return moving;
}

public Stok(int mix, int miy, int max,int may, int prox){

try {
		stokimg = Image.createImage("/stok.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}

minx = mix;
miny = miy;
maxx = max;
maxy = may;

startx = (mix + (prox * ((max - mix) / 100)));
starty = 100;

stokspr = new Sprite(stokimg,(stokimg.getWidth()/frames), stokimg.getHeight());
stokpos = new Posietie();
stokvel = new Vellocity();

Regenerate();

}

public boolean Gooi(int x, int y, int speedx, int speedy){
if ((!moving) && (alive)) {
stokpos.x = x;
stokpos.y = y;
stokvel.x	= (speedx - 7);
stokvel.y = speedy;
stokspr.setFrame(0);
return true;
}
return false;
}

public void Hit(){
updatetel = true;
alive = false;
stokvel.y = 0;
stokvel.x =0;
stokspr.setFrame(1);
}

public void Regenerate(){ 

//set bij object score het leven min 1
stokpos.x = startx; //get width kan allen in een canvas een paint etc.
stokpos.y = starty;
stokvel.x = 0;
stokvel.y = 0;

stokspr.defineCollisionRectangle(0,0,20,20);
stokspr.setPosition(stokpos.x, stokpos.y);
alive = true;

updatenodig = 0;

}


private void detectMovement(){
	
if (stokvel.x < 0) {tempx = stokvel.x * -1;}
else {tempx  = stokvel.x;}
if (stokvel.y < 0) {tempy = stokvel.y * -1;}
else {tempy = stokvel.y;}

stokvel.all = (tempx  + tempy);

if (stokvel.all > 0 )  {moving =  true;}
else {moving = false;}
}


public void Turn(){

//System.out.println(updatenodig);

if (updatetel) {updatenodig++;}

if (updatenodig >= 20) {
if (!alive) {Regenerate();}
	

updatenodig = 0;

}


if (alive) {detectMovement();
MoveIt();
}

}


public Sprite GetSprite(){
    return this.stokspr;
  }
	
public void MoveIt(){

//System.out.println(maxx);

stokpos.x = stokpos.x + stokvel.x;
stokpos.y = stokpos.y + stokvel.y;

if (stokpos.x < minx) {stokpos.x = stokpos.x - stokvel.x;alive = false; updatenodig = 30;}
if (stokpos.y < miny) {stokpos.y = stokpos.y - stokvel.y;alive =  false; updatenodig = 30;}
if (stokpos.x > maxx) {stokpos.x = stokpos.x - stokvel.x;alive= false; updatenodig = 30;}
if (stokpos.y > maxy) {stokpos.y = stokpos.y - stokvel.y;alive = false; updatenodig = 30;}

stokspr.setPosition(stokpos.x, stokpos.y);



}


}
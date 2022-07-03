import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import java.io.*;


class BadGuy {

private int[] stasequence = {1};
private int[] doodsequence = {3,3,4,4,4};
private int[] beweegsequence = {1,1,1,1,0,0,0,0};
private int[] zaagsequence = {1,1,1,2,2,2};

private RandomNumber willekeur;
private Vellocity guyvel;
private Posietie guypos;
private Image guyimg;
private Sprite guyspr;
private int updatenodig;
private boolean updatetel;
private int alive;
private boolean moving;
private int beginleven;
private boolean sawing;
public int i = 0;
private int tempx;
private int tempy;
private int minx,  miny, maxx , maxy , startx, starty;
private int ai, aitel;
private boolean aismart = true;
private int frames = 5;
private boolean anderesequence;

public BadGuy(int mix, int miy, int max,int may, int leven) {

if (leven <= 10) {
	try {
		guyimg = Image.createImage("/man.png");
		}catch (Exception e) {
  	System.out.println("error: "+e);
		}
}
else {
	if ((leven <= 20) && (leven >= 10)) {
		try {
			guyimg = Image.createImage("/man2.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}
	}
	else {	
		if (leven >= 30) {
		try {
			guyimg = Image.createImage("/man3.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}
	}
	
	}
}
	

guyspr = new Sprite(guyimg,(guyimg.getWidth()/frames), guyimg.getHeight());
guypos = new Posietie();
guyvel = new Vellocity();
willekeur = new RandomNumber();

minx = mix;
miny = miy;
maxx = max;
maxy = may;
startx = mix;
starty = willekeur.getRandom(maxy);
beginleven = leven;

Regenerate();
}

public void setZagen(){
	updatetel = true;
	sawing = true;
  anderesequence = true;
}

public int getAlive() {
return alive;
}

public void setUpdate() {
updatenodig = 30;
}


public void setHurt() {
updatetel = true;
alive -=10;
anderesequence = true;
ai = 5;
}

public boolean getHurt()
{
if (updatetel) {return false;}
else {return true;}
}


public void AiZero(){
if (aitel >= 3){
guyvel.x = 3;
aitel = 0;
}
if ((guypos.x + 5) > maxx) {ai = 4;}
}

public void AiOne(){
if (aitel >= 10){
guyvel.x = 4;
if (aismart) {guyvel.y = -4;aismart = false;}
else {guyvel.y = 4;aismart = true;}
aitel = 0;

if ((guypos.x + 5) > maxx) {ai = 4;}

}

}

public void AiTwo(){

if (aitel >= 10){
guyvel.x = 4;
guyvel.y = (willekeur.getRandom(11) - 5);
aitel = 0;
}
if ((guypos.x + 5) > maxx) {ai = 4;}
}

public void AiThree(){
 if (aitel >= 2){
guyvel.x = (willekeur.getRandom(7) - 2);
guyvel.y = (willekeur.getRandom(11) - 5);
aitel = 0;
}
if ((guypos.x + 5) > maxx) {ai = 4;}
}	

public void AiFour(){
 if (aitel >= 10){
guyvel.x = -2;
guyvel.y = ((willekeur.getRandom(3) - 1)*5);
aitel = 0;
}
}	

public void AiFive() {
if (aitel >= 10){
guyvel.x = 0;
guyvel.y = 0;
aitel = 0;
ai = 1;
}	
}

private void calcMovement() {
	
if (i > guyvel.all + 1 ) {
if (guyvel.x > 0) {guyvel.x--; }
if (guyvel.x < 0) {guyvel.x++; }
if (guyvel.y > 0) {guyvel.y--; }
if (guyvel.y < 0) {guyvel.y++; }
i = 0;
}



if (guyvel.x < 0) {tempx = guyvel.x * -1;}
else {tempx  = guyvel.x;}
if (guyvel.y < 0) {tempy = guyvel.y * -1;}
else {tempy = guyvel.y;}

guyvel.all = (tempx  + tempy);

if (guyvel.all > 0 )  {moving =  true;}
else {moving = false;}

guyvel.all = (guyvel.all/4);
i++;


}

public void Turn() {



if (alive >= 0) {
if (ai == 0) {AiZero();}
else {
if (ai == 1) {AiOne();}
else {
if (ai == 2) {AiTwo();}
else {
if (ai == 3) {AiThree();}
else {
if (ai == 4) {AiFour();}
else {
if (ai == 5) {AiFive();}
}
}
}
}
}
aitel++;
}


//System.out.println(updatetel);

if (updatetel) {updatenodig++;}

if (updatenodig >= 12) {
	if (alive <= 0) {Regenerate();}
	if (sawing) { sawing = false; }

updatetel = false;	
updatenodig = 0;

}

animatieKeuze();
if (alive >= 1) {calcMovement();}
MoveIt();
}

public void animatieKeuze (){

	
	if (anderesequence) {
	if((alive <= 0)&& (guyspr.getFrameSequenceLength() != doodsequence.length )) {
		guyspr.setFrameSequence(doodsequence);
	}
	else {
		if  (sawing)   {
					guyspr.setFrameSequence(zaagsequence);
				}
			
			  else {
			if ((!moving) && (alive >= 0)) {guyspr.setFrameSequence(stasequence);}
				
			}
	}
	anderesequence = false;
}

if ((alive >= 0) && (moving) && (!sawing) && (guyspr.getFrameSequenceLength() != beweegsequence.length)) {guyspr.setFrameSequence(beweegsequence);}
	


}

public Sprite GetSprite() {
    return this.guyspr;
}

public void Regenerate(){ 

 //set bij object score het leven min 1
guypos.x = startx; //get width kan alleen in een canvas een paint etc.
guypos.y = willekeur.getRandom(maxy);
guyvel.x = 0;
guyvel.y = 0;
guyspr.setFrameSequence(stasequence);
guyspr.setPosition(guypos.x, guypos.y);
alive = beginleven;
ai = willekeur.getRandom(4);
updatetel = false;

}

public void MoveIt() {


guypos.x = guypos.x + guyvel.x;
guypos.y = guypos.y + guyvel.y;

if (guypos.x < minx) {guypos.x = guypos.x - guyvel.x;guyvel.x=0;}
if (guypos.y < miny) {guypos.y = guypos.y - guyvel.y;guyvel.y=0;}
if (guypos.x > maxx) {guypos.x = guypos.x - guyvel.x;guyvel.x=0;}
if (guypos.y > maxy) {guypos.y = guypos.y - guyvel.y;guyvel.y=0;}

guyspr.setPosition(guypos.x, guypos.y);

guyspr.nextFrame();	

}
	


}

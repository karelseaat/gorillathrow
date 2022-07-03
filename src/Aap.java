import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import java.io.*;
import javax.microedition.media.*;
import java.io.InputStream;

class Aap {

private int[] stasequence = {0};
private int[] doodsequence = {3,3,3,4,4,4};
private int[] beweegsequence = {1,1,0,0};
private int[] gooisequence = {2,2,2,2,2,2,2};

private Vellocity aapvel;
private Posietie aappos;
private Image aapimg;
private Sprite aapspr;
private int updatenodig;
private boolean updatetel;
private boolean alive;
private boolean moving;
private boolean trowing;
private boolean anderesequence;


public int i = 0;
private int tempx;
private int tempy;
private int minx,  miny, maxx , maxy , startx, starty;
private int frames = 5;

public Aap(int mix, int miy, int max,int may, int prox, int proy) {


try {
			aapimg = Image.createImage("/aap.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}




	






//System.out.println(aapimg.getHeight() + " " + aapimg.getWidth());


aapspr = new Sprite(aapimg,(aapimg.getWidth()/frames), aapimg.getHeight());
aappos = new Posietie();
aapvel = new Vellocity();

minx = mix;
miny = miy;
maxx = max;
maxy = may;
startx = (mix + (prox * ((max - mix) / 100)));
starty = (mix + (proy * ((may - miy) / 100)));

Regenerate();
}


public int getXspeed(){
return aapvel.x;	
}

public int getYspeed(){
return aapvel.y;	
}

public boolean getAlive(){
return alive;
}

public void setUpdate(){
updatenodig = 30;
}

public void setGooi(){
updatetel = true;	
trowing = true;
anderesequence = true;
}

public boolean getGooi(){
return trowing;	
}

public void setDead(){
updatetel = true;
alive = false;
anderesequence = true;
}

public int getXpos(){
return aappos.x;
}

public int getYpos(){
return aappos.y;
}

public void Omhoog(){

aapvel.y--;	

     	
}

public void Rechts()
{

aapvel.x++;	


}

public void Omlaag()
{

aapvel.y++;

 
}	

public void Links()
{


aapvel.x--;	


}


private void calcMovement() {
	


if (i > aapvel.all + 1 ) {
if (aapvel.x > 0) {aapvel.x--; }
if (aapvel.x < 0) {aapvel.x++; }
if (aapvel.y > 0) {aapvel.y--; }
if (aapvel.y < 0) {aapvel.y++; }
i = 0;
}



if (aapvel.x < 0) {tempx = aapvel.x * -1;}
else {tempx  = aapvel.x;}
if (aapvel.y < 0) {tempy = aapvel.y * -1;}
else {tempy = aapvel.y;}

aapvel.all = (tempx  + tempy);

if (aapvel.all > 0 )  {moving =  true;}
else {moving = false;anderesequence = true;}

aapvel.all = (aapvel.all/4);
i++;


}


public void Turn(){



if (updatetel) {updatenodig++;}

if (updatenodig >= 15) {
	if (!alive) {Regenerate();}
	if (trowing) {trowing = false; }
	

updatenodig = 0;

}

animatieKeuze(); 
if (alive) {calcMovement();} 
MoveIt(); 
}



public void animatieKeuze (){

//System.out.println(alive + " " + moving);



	if (anderesequence) {
	if((!alive)&& (aapspr.getFrameSequenceLength() != doodsequence.length )) {
		aapspr.setFrameSequence(doodsequence);
	}
	else {
		if  (trowing)   {
					aapspr.setFrameSequence(gooisequence);
				}
			
			  else {
			if ((!moving) && (alive)) {aapspr.setFrameSequence(stasequence);}
				
			}
	}
	anderesequence = false;
}

if ((alive) && (moving) && (!trowing) && (aapspr.getFrameSequenceLength() != beweegsequence.length)) {aapspr.setFrameSequence(beweegsequence);}



}


	public Sprite GetSprite() {
    return this.aapspr;
}



public void MoveIt() {

//System.out.println(maxx);

aappos.x = aappos.x + aapvel.x;
aappos.y = aappos.y + aapvel.y;

if (aappos.x < minx) {aappos.x = aappos.x - aapvel.x;aapvel.x=0;}
if (aappos.y < miny) {aappos.y = aappos.y - aapvel.y;aapvel.y=0;}
if (aappos.x > maxx) {aappos.x = aappos.x - aapvel.x;aapvel.x=0;}
if (aappos.y > maxy) {aappos.y = aappos.y - aapvel.y;aapvel.y=0;}

aapspr.setPosition(aappos.x, aappos.y);

aapspr.nextFrame();	

}
	

public void Regenerate() { 


aappos.x = startx; 
aappos.y = starty;
aapvel.x = 0;
aapvel.y = 0;
aapspr.setFrameSequence(stasequence);
aapspr.setPosition(aappos.x, aappos.y);
alive = true;
updatetel = false;


}




}




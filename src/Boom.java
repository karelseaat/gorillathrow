import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import java.io.*;

class Boom{

private int[] stasequence = {0};
private int[] doodsequence = {0,0,0,1,1,1};
private int[] omsequence = {1,1};

private Posietie boompos;
private Image boomimg;
private Sprite boomspr;
private int updatenodig;
private boolean updatetel;
private int alive;
private boolean hurt;
private int frames = 3;
private boolean anderesequence;


private int minx,  miny, maxx , maxy , startx, starty;


public Boom(int mix, int miy, int max,int may, int prox, int proy){

try {
			boomimg = Image.createImage("/boom.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}

minx = mix;
miny = miy;
maxx = max;
maxy = may;

startx = (mix + (prox * ((max - mix) / 100)));
starty = (miy + (proy * ((may - miy) / 100)));

boomspr = new Sprite(boomimg,(boomimg.getWidth()/frames), boomimg.getHeight());
boompos = new Posietie();


Regenerate();

}

public void setHurt() {
if (!updatetel) {
updatetel = true;
hurt = true;
alive -=10;
anderesequence = true;
}
}

public boolean getAlive() {
if (alive > 0){
return true;
}
return false;
}

public boolean getHurt(){
return hurt;
}

public void Regenerate(){ 

//set bij object score het leven min 1
boompos.x = startx; //get width kan allen in een canvas een paint etc.
boompos.y = starty;

boomspr.setPosition(boompos.x, boompos.y);
alive = 30;

updatenodig = 0;
boomspr.setFrameSequence(stasequence);
}


public void animatieKeuze (){

	
	if (anderesequence) {
	if((alive >= 0)  && (boomspr.getFrameSequenceLength() != doodsequence.length )) {boomspr.setFrameSequence(doodsequence);
	}
	else {
		  if ((alive <= 0) && (!hurt) &&  (boomspr.getFrameSequenceLength() != omsequence.length )) {boomspr.setFrameSequence(omsequence);}
			else {
				if ((alive > 0) &&  (!hurt) && (boomspr.getFrameSequenceLength() != stasequence.length ))  {boomspr.setFrameSequence(stasequence);}
			}	
			
	}
	anderesequence = false;

}


	


}


public void Turn(){



if (updatetel) {updatenodig++;}

if (updatenodig >= 20) {
hurt = false;
updatetel = false;
updatenodig = 0;
anderesequence = true;
}


animatieKeuze();
boomspr.nextFrame();
}




public Sprite GetSprite(){
    return this.boomspr;
}
	
}
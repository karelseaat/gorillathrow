import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import java.io.*;
import javax.microedition.media.*;

class Score {

private int bomen;
private int score = 0;
private int leven = 3;
private Image levenimg;
private Image scoreimg;
private Image achtgimg;
private Sprite[] levenspr = {null,null,null};
private Sprite[] scorespr = {null,null,null,null,null,null};
private Sprite achtgspr;
private LayerManager lm;
private int frames = 10,frames2 = 2;
private int i,i2, ionthoud = 1, ruwegetallen;
private boolean gameover = false;

public Score(){

try {
			levenimg = Image.createImage("/levens.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}



try {
			scoreimg = Image.createImage("/score.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}
		
try {
			achtgimg = Image.createImage("/scorebord.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}

lm = new LayerManager();
achtgspr = new Sprite(achtgimg);

for (int i = 0; i < scorespr.length; i++) {
scorespr[i] = new Sprite(scoreimg,(scoreimg.getWidth()/frames), scoreimg.getHeight());
}

for (int i = 0; i < levenspr.length; i++) {
levenspr[i] = new Sprite(levenimg,(levenimg.getWidth()/frames2), levenimg.getHeight());
}

}

public boolean getgameover(){
return gameover;	
}

public void scoreTenplus(){
score+=10;
	
}

public void scoreFiveplus(){
score+=5;	
}

public void scoreTenminus(){
score-=10;	
}

public void scoreFiveminus() {
score -=5;	
}

public void liveOneminus(){
leven--;
if (leven == 0) {gameover = true;}
}

public void Turn(){

if (leven < 0) { leven =0;}
if (score < 0) {score = 0;}
ScoreSetter();
LevenSetter();
lm.append(achtgspr);	
}

public LayerManager getLayer(){
return lm;	
}

public void LevenSetter(){
	 for (int i = 0; i < levenspr.length; i++) {
	levenspr[i].setPosition(15 + (((i * (levenimg.getWidth() + 5)) / frames2)), 4);
	if (leven > i) {levenspr[i].setFrame(0);}
	else {levenspr[i].setFrame(1);}

lm.append(levenspr[i]);
}


}

public void ScoreSetter(){
	
for (int i = 100000; i > 0; i/=10) {	

ruwegetallen = ((score / i) - ((score / ionthoud) * 10));
if (ruwegetallen < 0) {ruwegetallen = (score / i);}
scorespr[i2].setFrame(ruwegetallen);
scorespr[i2].setPosition(5 + (i2 * (scoreimg.getWidth() / frames)), 15);
lm.append(scorespr[i2]);

ionthoud = i;
i2++;
}
i2 = 0;
}

}
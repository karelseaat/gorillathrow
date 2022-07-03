import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import java.io.*;
import javax.microedition.media.*;


class Achtergrond {


private int tiles = 4;
private Image achtgimg;
private TiledLayer achtglay;
public int i = 0;
private int minx,  miny, maxx , maxy, xtegels, ytegels;

private int[] animx = {1,2,3,4,5,6,7};
private int[] animy = {1,2,3,4,5,6,7}; 
private boolean animation;
private boolean updatetel;
private int updatenodig;
private RandomNumber willekeur;

public Achtergrond(int breedte,int hoogte) {


maxx = breedte;
maxy = hoogte;


try {
			achtgimg = Image.createImage("/bground.png");
		}catch (Exception e) {
  		System.out.println("error: "+e);
		}

System.out.println("plaatje BxH : " + achtgimg.getWidth()+ " " + achtgimg.getHeight());

System.out.println("tegel BxH : " + (achtgimg.getWidth() / tiles)+ " " + achtgimg.getHeight());

System.out.println("scherm BxH  : " + maxx + " " + maxy);

xtegels = ((maxx / (achtgimg.getWidth() / tiles))+ 1);  
ytegels = ((maxy / achtgimg.getHeight()) + 1);

System.out.println("tegels op x = " + xtegels + " tegels op de y = " + ytegels);


achtglay = new TiledLayer(xtegels,ytegels, achtgimg,(achtgimg.getWidth()/ tiles)  ,  achtgimg.getHeight());

willekeur = new RandomNumber();



while (i < animx.length) {


animx[i] = willekeur.getRandom(xtegels);
animy[i] = willekeur.getRandom(ytegels);




i++;
}
i = 0;
Regenerate();
}






public void Turn(){



if (updatetel) {updatenodig++;}

if (updatenodig >= 40) {

animation();
updatetel = false;
updatenodig = 0;

}
updatetel = true;

}






public TiledLayer GetTiles() {
    return this.achtglay;
}





public void Regenerate() { 


achtglay.fillCells(0, 0,xtegels , ytegels , 1);
updatenodig = 50;
while (i < 4)
{
achtglay.setCell(willekeur.getRandom(xtegels), willekeur.getRandom(ytegels), 2);
	i++;
}
i = 0;


}

public void animation(){
if (animation) {
	while (i < animx.length) {
	
	animation = false;achtglay.setCell(animx[i], animy[i], 4);
	i++;
	}
	i = 0;
	}
else {
	while (i < animx.length) {
	animation = true;achtglay.setCell(animx[i], animy[i], 3);
	i++;
  }
	}
i = 0;
}



}




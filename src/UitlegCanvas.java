//aanroepen van library's zoals gewoonlijk.
// let wel import javax.microedition.midlet.*; staat er niet in want dit is een ander object, geen mitlet object.
//wel is hier import javax.microedition.lcdui.game.*; erg balngrijk vanwege de sprites de layers ne het paintwerk.
//Dit kun je zien als enkel het doek en de kwast van dit spel, hier worden de apparte classes zoals aap, boomen, op het scherm gezet.
//Ook worden hier de toetsen uitgelezen, verder gebeurt hier zo min mogelijk.

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;

 
 //defineren van de class MyGameCanvas die ook de functies en mogelijkheden van class GameCanvas erbij heeft.
 
 class UitlegCanvas extends Canvas{

private Image[] uitleg = {null, null, null, null};
private int i;


UitlegCanvas(){


for (int i = 1; i <= 4; i++) {
try {
uitleg[(i - 1)] =  Image.createImage("/intro" + (i) + ".png");
	System.out.println("/intro" + (i) + ".png");
} catch (Exception e) {
  		System.out.println("error: "+e);
}
}
}

public void paint(Graphics g) {
	
	g.setColor(255, 100, 255); //kleurtje aan het eerstvolgende dat word getekend, licht paarse achtergrond met 255,200,255 (RGB)
	g.fillRect(0, 0, getWidth(), getHeight()); //teken een blak van (rechts,boven,maximale breedte, maximale hoogte) = exact het scherm.
	
	if (i < 0) {i = 3;}
	if (i > 3) {i = 0;}
	g.drawImage(uitleg[i], 0, 0, g.TOP| g.LEFT);

	
 
	repaint(); //Om weer terug te gaan naar : public void paint(Graphics g). dit maakt de de eerst lus.
	}
	
	public void keyPressed(int code) { //de functie getAndProcessInput
  int action = getGameAction(code);
  if (action == UP) i--;
  if (action == DOWN) i++;
  }
  	
  	



 


}
		

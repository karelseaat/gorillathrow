//aanroepen van library's zoals gewoonlijk.
// let wel import javax.microedition.midlet.*; staat er niet in want dit is een ander object, geen mitlet object.
//wel is hier import javax.microedition.lcdui.game.*; erg balngrijk vanwege de sprites de layers ne het paintwerk.
//Dit kun je zien als enkel het doek en de kwast van dit spel, hier worden de apparte classes zoals aap, boomen, op het scherm gezet.
//Ook worden hier de toetsen uitgelezen, verder gebeurt hier zo min mogelijk.

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
import java.lang.*;
import javax.microedition.midlet.*;

 
 //defineren van de class MyGameCanvas die ook de functies en mogelijkheden van class GameCanvas erbij heeft.
 
 class MyGameCanvas extends GameCanvas  {

private Score scorebord;
private BadGuy[] evil = {null,null};
private Aap held; //aanmaken van een object held van het type Aap: we hebben nu een object dat we kunnen manipuleren en held heet, hoe manipuleren, dat staat in de classe Aap().
private LayerManager lm;// object lm aanmaken van type LayerManager
private Stok[] wapen = {null,null,null};
private Boom[] jaap = {null,null,null,null,null,null,null,null,null,null,null,null,null,null,null};
private Achtergrond mooi;
private int i, i2, i3;
private RandomNumber willekeur;

//hier is weer de functie van een type MyGameCanvas dat dezelfde naam heeft MyGameCanvas en altijd wordt aangeroepen bij het creeren van een object van het type MyGameCanvas;

public  MyGameCanvas()
{
super(true);	//ik weet niet wat dit super(true); ding is, maar het is noodzakelijk.

willekeur = new RandomNumber();

for (int i = 0; i <= 2; i++) {
wapen[i] = new Stok(-40,-20,(getWidth()+20), (getWidth()+20), 0);
}

for (int i = 0; i <= 1; i++) { 
evil[i] = new BadGuy(-20,0,(getWidth()-27),(getHeight()-47), willekeur.getRandom(20)); //2e extra regel !!, ja ja  het wordt gewoon nog een aap.
}

scorebord = new Score();


held = new Aap(0,0,(getWidth()-35),(getHeight()-55), 130, 50); // het object held van type Aap moet nog eens appart worden aangeroepen, netzoals bij object mygamecanvas van type MyGameCanvas dat staat in Aats.java.

mooi = new Achtergrond(getWidth(),getHeight());


for (int i2 = 0; i2 <= 10; i2+=5) {
for (int i = 0; i <= 4; i++) {
jaap[(i + i2 )] = new Boom(0,0,getWidth(),getHeight(),(100 + (i2 * 5)), ((32 * i) + 10));
}
}

lm = new LayerManager();

}

//veel dingen worden gedaan in paint, omdat het de eerste lus is waar het spel door gaat, na een repaint komt het weer bij het begin van paint.
//paint is standaard functie die mag worden gebruikt in een Canvas en een GameCanvas, maag ook in MyGameCanvas want MyGameCanvas extends GameCanvas.
		

public void paint(Graphics g) {
	
	g.setColor(255, 150, 120); 
	g.fillRect(0, 0, getWidth(), getHeight()); //teken een blak van (rechts,boven,maximale breedte, maximale hoogte) = exact het scherm.
	
	
	
  		//System.out.println(getHeight()); // haal de eerste //'s weg om dit te gebruiken voor debugging

		 try{ //probeer
        Thread.sleep(25);	//40 milli seconden lang niets te doen, (lijkt met gemakkelijk genoeg)
      } catch (InterruptedException e){ //maar als het fout gaat doe dan dit
//hier staat niet veel, maar je kunt er altijd een //System.out.println(e); neerzetten om nog te kunnen lezen wat er ongevee fout ging.
      }
	for (int i = 0; i <= 2; i++) {       
	wapen[i].Turn();	
  }
  
  for (int i = 0; i <= 1; i++) {
	evil[i].Turn();
  }
	
	held.Turn(); //er staat dus een functie Turn in het object held, held was van het type Aap, een daar staat dus ook wat Turn(); doet
	for (int i2 = 0; i2 <= 10; i2+=5) {
  for (int i = 0; i <= 4; i++) {
	jaap[(i + i2)].Turn();
	}
  }
	scorebord.Turn();
	mooi.Turn();
	if (held.getAlive()) {getAndProcessInput();} //dit is het aanroepen van een functie die in dit object staat of in deze class (licht eraan hoe je het bekijkt), dit is de functie die de toetsen uitleest, iets dat dus niet nodig is bij een dode aap.
	collisionChecker();
	
	lm.setViewWindow(0,0,getWidth(),getHeight()); //in de classe LayerManager staat dus een functie setViewWindow, stelt in hoe groot de laag is, in dit geval heel scherm groot.
	for (int i = 0; i <= 1; i++) {
	lm.append(evil[i].GetSprite()); //3e extra regel !!, ja ja, denk eens in wat dit kan betekenen voor de badguys, 1 object to rule them all, muahahahahah.
  }
	lm.append(held.GetSprite());	//zet held.GetSprite() op de laag, deze laag is object lm. het object is zo geconstrueerd dat het zig als een laag gedraagd.
	for (int i = 0; i <= 2; i++) {
	lm.append(wapen[i].GetSprite());
  }
	for (int i2 = 10; i2 >= 0; i2-=5) {
  for (int i = 4; i >= 0; i--) {
	lm.append(jaap[(i + i2)].GetSprite());
  }
  }
	lm.append(mooi.GetTiles());

	lm.paint(g, 0, 0 ); 
	scorebord.getLayer().paint(g,0,0);
	if (!scorebord.getgameover())
	{repaint();} //Om weer terug te gaan naar : public void paint(Graphics g). dit maakt de de eerst lus.
	}
	
	public void getAndProcessInput() { //de functie getAndProcessInput
  int action = getKeyStates(); //maak een variabele aan van type int en sla getKeyStates() enr in op, getKeyStates is standaard functie van een MyGameCanvas omdat het een standaard functie is van GameCanvas.
  	
  	//als knop is ingedrukt doen dan held.richting. (Links(); Rechts(); etc, zijn functies van het object held van type Aap;
  	// nog 4 stukjes erbij voor de ongekeerde besturing van held2
  	if ((action & LEFT_PRESSED) != 0) {held.Links(); }
  	else {if ((action & RIGHT_PRESSED) != 0) {held.Rechts();}}
  	
  	if ((action & UP_PRESSED) != 0) {held.Omhoog();}
  	else {if ((action & DOWN_PRESSED) != 0) {held.Omlaag();}}
  	
  while (i3 <= 2) {
  	if (((action & FIRE_PRESSED) != 0) && (!wapen[i3].getMoving()) && (!held.getGooi()) ) {
  		wapen[i3].Gooi(held.getXpos(), held.getYpos(), held.getXspeed() , held.getYspeed());
  		held.setGooi();
  		}
  	i3++;
  	}
    i3=0;
  	
  	if ((action & GAME_A_PRESSED) != 0) {}
  	
  	}
  	
  	
  	



  public void collisionChecker() {
    for (int i = 0; i <= 1; i++) {
  if (evil[i].GetSprite().collidesWith(held.GetSprite(),true)) {if ((evil[i].getAlive() > 0)&& (held.getAlive())){held.setDead();evil[i].setZagen();scorebord.liveOneminus();}}
  }
  


  for (int i2 = 0; i2 <= 1; i2++) {
  for (int i = 0; i <= 2; i++) {
  if (wapen[i].GetSprite().collidesWith(evil[i2].GetSprite(),true)) {if ((evil[i2].getAlive() > 0) && (evil[i2].getHurt())) {evil[i2].setHurt();wapen[i].Hit();scorebord.scoreTenplus();}}
  }
  }
  
  for (int i3 = 0; i3 <= 1; i3++) {
  for (int i2 = 0; i2 <= 10; i2+=5) {
  for (int i = 0; i <= 4; i++) {
  if (evil[i3].GetSprite().collidesWith(jaap[(i + i2)].GetSprite(),true)) {if ((evil[i3].getAlive() > 0) && (jaap[(i + i2)].getAlive()) && (!jaap[(i + i2)].getHurt())){jaap[(i +i2)].setHurt();evil[i3].setZagen();scorebord.scoreFiveminus(); }}
}
}
}
}


}
		

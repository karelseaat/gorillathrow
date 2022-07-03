//importeren van de library's = ok

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
 
 //aanmaken van de midlet, display = ok. Kunnen behalfe mygamecanvas dingen bij voor het begin menu, zoals : private MenuList menutje(); ofzo.
 
public class Aats extends MIDlet implements CommandListener{
 	private Display theDisplay;
	private MyGameCanvas mygamecanvas; //Object mygamecanvas maken van het type MyGameCanvas, een MyGameCanvas maken met de naam: mygamecanvas
  private UitlegCanvas myuitlegcanvas;
  private List keuzemenu = new List("Jungle monkey MENU",List.IMPLICIT);
  
 //Bij het aanmaken van een Aats midlet zoals ^ hierboven zal altijd deze v functie worden aangeroepen, die ook heel handig de dezelfde naam heeft. classe Aats met functie Aats
 
 public Aats() {
  mygamecanvas = new MyGameCanvas(); // nieuw MyGameCanvas aanmaken. die in de variabele met de naam : mygamecanvas gaat. (dit deden we een stap eerder hierboven ook aal maar dit is gewoon nodig)
  myuitlegcanvas = new UitlegCanvas();
  theDisplay =  Display.getDisplay(this);

	keuzemenu.append("Start Game", null);
	keuzemenu.append("Help", null);
	keuzemenu.append("Exit", null);
	keuzemenu.setCommandListener(this);
}

 //bwa, theDisplay.setCurrent(bla bla bla moet erin staan maar das ook alles.
 
 public void startApp() { // het starten van de applicatie
	
  theDisplay.setCurrent(keuzemenu);
}
 
 public void commandAction(Command cmd, Displayable d){
 	if(d == keuzemenu){
 		if(keuzemenu.getSelectedIndex() == 0) {theDisplay.setCurrent(mygamecanvas);}
 		if(keuzemenu.getSelectedIndex() == 1) {theDisplay.setCurrent(myuitlegcanvas);}
		if(keuzemenu.getSelectedIndex() == 2) {exitMIDlet();}
		
		}
}
 
 
 //bla, hoort gewoon in een midlet
 
 public void pauseApp() {
 }
 
 //bla hoort ook in een midlet
 
 public void destroyApp(boolean unconditional) {
 
 }
 
 //bla, ~~~~~~~~~~~~~~~~~~~~~~
  
 public void exitMIDlet(){
    destroyApp(false);
    notifyDestroyed();
   }



}

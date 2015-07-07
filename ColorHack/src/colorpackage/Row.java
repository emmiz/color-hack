package colorpackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**Denna klass deklarerar en rad och dess funktioner i spelet. Den �rver ifr�n
 * klassens JPanel.*/
public class Row extends JPanel{
   
   //H�r deklareras ett serieversionsnummer f�r att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   //Klassens inkapslade instansvariabel.
   private ArrayList<Peg> pegArray;//Denna referensvariabel kommer att referera
                                   //till en array som inneh�ller radens kulor.
   
   
   /**Klassens konstruktor*/
   public Row(){
      setLayout(new FlowLayout());//En layout i form av en rad skapas. Inneh�ll
                                  //adderas kommer att fyllas p� till h� om
                                  //befintligt inneh�ll.
      setBackground(new Color(255,255,255));//F�nstret f�r vit som bakgrundsf�rg.
      
      pegArray=new ArrayList<Peg>();//H�r inintieras pegArray till en ny
                                    //ArrayList som kan lagra instanser av Peg.
                                    //Den ska h�lla koll p� kulornas inb�rdes
                                    //ordning i raden.
      
      //Denna for-loop itereras fyra g�nger och skapar f�r varje varv ett nytt
      //Peg-objekt som den placerar i PegArray och sedan ritar den dessutom ut
      //den i v�rt f�nster.
      for(int i=0; i<4; i++){
         Peg p=new Peg();
         pegArray.add(p);
         add(p);
      }
   }
   
   
   /**Denna metod anv�nds f�r att plocka ut information om en specifik kula i
    * raden. Den tar in ett heltal f�r att hitta kulan i respektive index i
    * arrayen och returnerar sedan kulan.*/
   public Peg getPeg(int index){
      if(index>=0 && index<pegArray.size()){//Om den parameterv�rdet �r st�rre
                                            //�n eller lika med 0 och mindre �n
                                            //arrayens antal element...
         return pegArray.get(index);//...s� returneras elementet p� denna plats.
      }
      return null;//I annat fall, �r argumentet ogiltigt och null returneras.
   }
}

package colorpackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**Denna klass deklarerar en rad och dess funktioner i spelet. Den ärver ifrån
 * klassens JPanel.*/
public class Row extends JPanel{
   
   //Här deklareras ett serieversionsnummer för att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   //Klassens inkapslade instansvariabel.
   private ArrayList<Peg> pegArray;//Denna referensvariabel kommer att referera
                                   //till en array som innehåller radens kulor.
   
   
   /**Klassens konstruktor*/
   public Row(){
      setLayout(new FlowLayout());//En layout i form av en rad skapas. Innehåll
                                  //adderas kommer att fyllas på till hö om
                                  //befintligt innehåll.
      setBackground(new Color(255,255,255));//Fönstret får vit som bakgrundsfärg.
      
      pegArray=new ArrayList<Peg>();//Här inintieras pegArray till en ny
                                    //ArrayList som kan lagra instanser av Peg.
                                    //Den ska hålla koll på kulornas inbördes
                                    //ordning i raden.
      
      //Denna for-loop itereras fyra gånger och skapar för varje varv ett nytt
      //Peg-objekt som den placerar i PegArray och sedan ritar den dessutom ut
      //den i vårt fönster.
      for(int i=0; i<4; i++){
         Peg p=new Peg();
         pegArray.add(p);
         add(p);
      }
   }
   
   
   /**Denna metod används för att plocka ut information om en specifik kula i
    * raden. Den tar in ett heltal för att hitta kulan i respektive index i
    * arrayen och returnerar sedan kulan.*/
   public Peg getPeg(int index){
      if(index>=0 && index<pegArray.size()){//Om den parametervärdet är större
                                            //än eller lika med 0 och mindre än
                                            //arrayens antal element...
         return pegArray.get(index);//...så returneras elementet på denna plats.
      }
      return null;//I annat fall, är argumentet ogiltigt och null returneras.
   }
}

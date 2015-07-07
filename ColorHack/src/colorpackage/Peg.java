package colorpackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**Denna klass deklarerar en kula/Peg och dess funktioner. Den ärver ifrån
 * JLabel och implementerar MouseListener.*/
public class Peg extends JLabel implements MouseListener{
   
   //Här deklareras ett serieversionsnummer för att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   //Klassens inkapslade instansvariabler.
   private ImageIcon image;//Denna referensvariabel kommer att referera till
                           //kulans bild, dvs. dess utseende.
   private int color=0;//Denna variabel håller koll på kulans färg.
   
   
   /**Klassens konstruktor.*/
   public Peg() {
      setIcon(color);//Peg får sin bild med hjälp av metoden setIcon.
      
      this.addMouseListener(this);//En mouselistener sätts på varje Peg-objekt
                                  //för att man skall kunna klicka på kulan och
                                  //byta färg.
   }
   
   
   /**Denna metod tar in ett heltal och utifrån det så sätter den en bild på
    * kulan.*/
   public void setIcon(int x){
      //Här skapas ett imageobjekt som får sitt utseende utifrån en fil där
      //heltalet x används för att får fram adressen till bilden.
      image=new ImageIcon(getClass().getResource("/images/"+x+".png"));
      
      this.setIcon(image);//image sätts som bild på Peg-objektet.
   }
   
   
   /**Denna metod överlagrar den ovanför om man skickar med en char istället för
    * ett heltal och bestämmer utifrån det en bild på kulan.*/
   public void setIcon(char bw){
      if(bw=='b'){
         //Här skapas ett imageobjekt som får sitt utseende utifrån en fil.
         image=new ImageIcon(getClass().getResource("/images/black.png"));
      }
      else if(bw=='w'){
         //Här skapas ett imageobjekt som får sitt utseende utifrån en fil.
         image=new ImageIcon(getClass().getResource("/images/white.png"));
      }
      else{
         //Här skapas ett imageobjekt som får sitt utseende utifrån en fil.
         image=new ImageIcon(getClass().getResource("/images/none.png"));
      }
      
      this.setIcon(image);
   }
   
   
   /**Denna metod används för att plocka fram heltalet som representerar den
    * färg som kulan har. (FeedBack använder denna).*/
   public int getPegColor(){
      return color;
   }
   
   
   /**Denna metod används för att ändra heltalet som representerar den färg som
    * kulan har och med andra ord ändra färgen på kulan. (Solution använder
    * denna).*/
   public void setPegColor(int color){
      this.color=color;
   }

   
   /**Denna metod talar om vad som skall hända om man klickar på en kula.*/
   public void clicked() {
      color++;//Varje gång du klickar på kulan så ökar instansvariabeln color
              //med 1.
      
      if(color>6) {//Om color har värdet 6 så sätts den till 1 igen för att man
                   //bara ska kunna växla mellan 6 olika färger.
         color=1;
      }
      setIcon(color);//Färgen på kulan ändras med hjälp av setIcon() som ärvs
                     //ifrån Shape och instansvariabeln color's värde som
                     //skickas med.
   }
   
   
   /**Denna metod används för att göra en kula till icke klickbar och med andra
    * ord inte kunna ändra på den.*/
   public void noClicks() {
      this.removeMouseListener(this);
   } 
   
   
   @Override
   /**Denna metod talar om att när musen klickat på kulan och släppt så ska den
    * räknas som klickad och då körs metoden clicked().*/
   public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub
      clicked();
   }
   
   @Override
   public void mouseClicked(MouseEvent arg0) {
      // TODO Auto-generated method stub 
   }

   @Override
   public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub  
   }

   @Override
   public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub  
   }

   @Override
   public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub  
   }
}

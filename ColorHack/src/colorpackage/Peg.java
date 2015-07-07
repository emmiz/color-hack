package colorpackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**Denna klass deklarerar en kula/Peg och dess funktioner. Den �rver ifr�n
 * JLabel och implementerar MouseListener.*/
public class Peg extends JLabel implements MouseListener{
   
   //H�r deklareras ett serieversionsnummer f�r att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   //Klassens inkapslade instansvariabler.
   private ImageIcon image;//Denna referensvariabel kommer att referera till
                           //kulans bild, dvs. dess utseende.
   private int color=0;//Denna variabel h�ller koll p� kulans f�rg.
   
   
   /**Klassens konstruktor.*/
   public Peg() {
      setIcon(color);//Peg f�r sin bild med hj�lp av metoden setIcon.
      
      this.addMouseListener(this);//En mouselistener s�tts p� varje Peg-objekt
                                  //f�r att man skall kunna klicka p� kulan och
                                  //byta f�rg.
   }
   
   
   /**Denna metod tar in ett heltal och utifr�n det s� s�tter den en bild p�
    * kulan.*/
   public void setIcon(int x){
      //H�r skapas ett imageobjekt som f�r sitt utseende utifr�n en fil d�r
      //heltalet x anv�nds f�r att f�r fram adressen till bilden.
      image=new ImageIcon(getClass().getResource("/images/"+x+".png"));
      
      this.setIcon(image);//image s�tts som bild p� Peg-objektet.
   }
   
   
   /**Denna metod �verlagrar den ovanf�r om man skickar med en char ist�llet f�r
    * ett heltal och best�mmer utifr�n det en bild p� kulan.*/
   public void setIcon(char bw){
      if(bw=='b'){
         //H�r skapas ett imageobjekt som f�r sitt utseende utifr�n en fil.
         image=new ImageIcon(getClass().getResource("/images/black.png"));
      }
      else if(bw=='w'){
         //H�r skapas ett imageobjekt som f�r sitt utseende utifr�n en fil.
         image=new ImageIcon(getClass().getResource("/images/white.png"));
      }
      else{
         //H�r skapas ett imageobjekt som f�r sitt utseende utifr�n en fil.
         image=new ImageIcon(getClass().getResource("/images/none.png"));
      }
      
      this.setIcon(image);
   }
   
   
   /**Denna metod anv�nds f�r att plocka fram heltalet som representerar den
    * f�rg som kulan har. (FeedBack anv�nder denna).*/
   public int getPegColor(){
      return color;
   }
   
   
   /**Denna metod anv�nds f�r att �ndra heltalet som representerar den f�rg som
    * kulan har och med andra ord �ndra f�rgen p� kulan. (Solution anv�nder
    * denna).*/
   public void setPegColor(int color){
      this.color=color;
   }

   
   /**Denna metod talar om vad som skall h�nda om man klickar p� en kula.*/
   public void clicked() {
      color++;//Varje g�ng du klickar p� kulan s� �kar instansvariabeln color
              //med 1.
      
      if(color>6) {//Om color har v�rdet 6 s� s�tts den till 1 igen f�r att man
                   //bara ska kunna v�xla mellan 6 olika f�rger.
         color=1;
      }
      setIcon(color);//F�rgen p� kulan �ndras med hj�lp av setIcon() som �rvs
                     //ifr�n Shape och instansvariabeln color's v�rde som
                     //skickas med.
   }
   
   
   /**Denna metod anv�nds f�r att g�ra en kula till icke klickbar och med andra
    * ord inte kunna �ndra p� den.*/
   public void noClicks() {
      this.removeMouseListener(this);
   } 
   
   
   @Override
   /**Denna metod talar om att n�r musen klickat p� kulan och sl�ppt s� ska den
    * r�knas som klickad och d� k�rs metoden clicked().*/
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

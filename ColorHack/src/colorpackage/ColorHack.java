package colorpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;

/**Denna klass deklarerar grunden till spelet ColorHack och inneh�ller bl.a.
 * spelets main-metod.*/
public class ColorHack {
   
   //Klassens inkapslade instansvariabel.
   private JFrame frame;//H�r deklareras en referens av typen JFrame som kommer
                        //kunna referera till JFrame-objekt.
   
   
   /**Spelet ColorHack's main-metod.*/
   public static void main(String[] args){
      
      EventQueue.invokeLater(
            new Runnable(){//Ett ny instans av typen Runnable skapas...
               
               public void run(){//...som i sin tur kallar p� metoden run.
                  
                  try{//Metoden i sin tur f�rs�ker...
                     ColorHack window = new ColorHack();//...skapa en ny instans
                                                //av typen ColorHack och k�r
                                                //d�rmed konstruktorn nedan.
                                                //Referensen d�ps till window.
                     
                     window.frame.setVisible(true);//Sedan s�tts synligheten p�
                                                //window till true med hj�lp av
                                                //metoden setVisible i klassen
                                                //JFrame.
                  }
                  catch (Exception e) {//Om inte skapandet  fungerar f�ngas ett
                                       //undantag upp med metoden printStackTrace
                                       //och skrivs ut i standardfelstr�mmen
                                       //(kommandotolken troligtvis).
                     e.printStackTrace();
                  }
               }
            }
      );
   }
   
   
   /**Klassens konstruktor som kallar p� metoden nedan.*/
   public ColorHack(){
      initialize();
   }
   
   
   /**Denna metod f�rbereder programmet ColorHacks f�nster f�r start.*/
   private void initialize(){
      frame = new JFrame("ColorHack 1.7");//Referensen frame initieras till ett nytt 
                           //JFrameobjekt som skapas och f�nstret f�r sin rubrik
                           //i baren initierad.
      
      frame.setBounds(500, 100, 300, 465);//Frame initieras till (f�nstrets plac
                                          // i x-led, y-led, dess strl i bredd,
                                          //och h�jd.
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ser till att
                                          //programmet avslutas n�r f�nstret
                                          //st�ngs med X-knappen.
      
      frame.getContentPane().setLayout(new BorderLayout());//F�nstrets layout
                                          //f�rbereds.
      frame.getContentPane().setBackground(new Color(255,255,255));//F�nstret
                                   //f�r vit som bakgrundsf�rg.
      
      GameBoard gb=new GameBoard();//En ny instans av GameBoard skapas och refe-
                                   //rensen deklareras till gb.
      
      frame.getContentPane().add(gb, BorderLayout.NORTH);//Spelplanen placeras
                                          //h�gst upp i f�nstrets layout.
      
      JButton button = new JButton("Gissa");//En ny knapp skapas med initierad
                                          //text och referensen f�r heta button.
 
      frame.getContentPane().add(button, BorderLayout.SOUTH);//Knappen placeras
                                          //l�ngst ner i f�nstrets layout.
      
      button.addActionListener(gb);//En actionListener l�ggs till p� knappen
                                   //och reaktionen deklareras i GameBoard som
                                   //skickas med (gb).
   }  
}
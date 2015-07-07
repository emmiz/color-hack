package colorpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;

/**Denna klass deklarerar grunden till spelet ColorHack och innehåller bl.a.
 * spelets main-metod.*/
public class ColorHack {
   
   //Klassens inkapslade instansvariabel.
   private JFrame frame;//Här deklareras en referens av typen JFrame som kommer
                        //kunna referera till JFrame-objekt.
   
   
   /**Spelet ColorHack's main-metod.*/
   public static void main(String[] args){
      
      EventQueue.invokeLater(
            new Runnable(){//Ett ny instans av typen Runnable skapas...
               
               public void run(){//...som i sin tur kallar på metoden run.
                  
                  try{//Metoden i sin tur försöker...
                     ColorHack window = new ColorHack();//...skapa en ny instans
                                                //av typen ColorHack och kör
                                                //därmed konstruktorn nedan.
                                                //Referensen döps till window.
                     
                     window.frame.setVisible(true);//Sedan sätts synligheten på
                                                //window till true med hjälp av
                                                //metoden setVisible i klassen
                                                //JFrame.
                  }
                  catch (Exception e) {//Om inte skapandet  fungerar fångas ett
                                       //undantag upp med metoden printStackTrace
                                       //och skrivs ut i standardfelströmmen
                                       //(kommandotolken troligtvis).
                     e.printStackTrace();
                  }
               }
            }
      );
   }
   
   
   /**Klassens konstruktor som kallar på metoden nedan.*/
   public ColorHack(){
      initialize();
   }
   
   
   /**Denna metod förbereder programmet ColorHacks fönster för start.*/
   private void initialize(){
      frame = new JFrame("ColorHack 1.7");//Referensen frame initieras till ett nytt 
                           //JFrameobjekt som skapas och fönstret får sin rubrik
                           //i baren initierad.
      
      frame.setBounds(500, 100, 300, 465);//Frame initieras till (fönstrets plac
                                          // i x-led, y-led, dess strl i bredd,
                                          //och höjd.
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ser till att
                                          //programmet avslutas när fönstret
                                          //stängs med X-knappen.
      
      frame.getContentPane().setLayout(new BorderLayout());//Fönstrets layout
                                          //förbereds.
      frame.getContentPane().setBackground(new Color(255,255,255));//Fönstret
                                   //får vit som bakgrundsfärg.
      
      GameBoard gb=new GameBoard();//En ny instans av GameBoard skapas och refe-
                                   //rensen deklareras till gb.
      
      frame.getContentPane().add(gb, BorderLayout.NORTH);//Spelplanen placeras
                                          //högst upp i fönstrets layout.
      
      JButton button = new JButton("Gissa");//En ny knapp skapas med initierad
                                          //text och referensen får heta button.
 
      frame.getContentPane().add(button, BorderLayout.SOUTH);//Knappen placeras
                                          //längst ner i fönstrets layout.
      
      button.addActionListener(gb);//En actionListener läggs till på knappen
                                   //och reaktionen deklareras i GameBoard som
                                   //skickas med (gb).
   }  
}
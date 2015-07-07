package colorpackage; //Denna klass deklareras att tillh�ra paketet colorpackage.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import static javax.swing.JOptionPane.*;

/**Denna klass deklarerar spelplanen och dess funktioner. Den �rver ifr�n klassen
 * JPanel och implementerar ActionListener.*/
public class GameBoard extends JPanel implements ActionListener{
   
   //H�r deklareras ett serieversionsnummer f�r att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   //Klassens inkapslade instansvariabler.
   private Solution mySolution;//Denna referensvariabel kommer att referera till
                               //l�sningsraden.
   private Guess  myGuess;//Denna referensvariabel kommer att referera till
                          //gissningsraden.
   private int guesses=0;//Denna instansvariabel h�ller ordning p� antalet f�rs�k.
   
   
   /**Klassens konstruktor.*/
   public GameBoard(){
      setLayout(new GridLayout(10, 1));//En layout i form av en tabell med
                                       //(rader,kolumner) skapas.
      setBackground(new Color(255,255,255));//F�nstret f�r vit som bakgrundsf�rg.
      reset();//En ny spelplan skapas.
      message(1);//Ett v�lkomstmeddelande visas.
   }
   
   
   /**Denna metod anv�nds f�r att skapa/nollst�lla spelplanen.*/
   private void reset(){
      this.removeAll();//Raderar allt eventuellt inneh�ll i f�nstret.
      
      guesses=0;//Nollst�ller f�rs�ksr�knaren.
      
      mySolution=new Solution();//Referensvariabeln mySolution initieras till
                                //att referera till ett nytt Solution-objekt.
    
      myGuess=new Guess();//Referensvariabeln myGuess initieras till att referera
                          //till ett nytt Guess-objekt.
      
      //add(mySolution);//Den l�sningsrad som nyss skapats placeras ut p� sk�rmen
                      //f�r att man skall kunna fels�ka och testa spelet under
                      //spelets produktion.
      
      add(myGuess);//Den gissningsrad som nyss skapats placeras ut p� spelplanen.
      
      validate();//Validerar/uppdaterar f�nstrets inneh�ll.
      repaint();//Ritar om f�nstret s� att allt nytt syns.
   }

   
   /**Denna metod anv�nds f�r att ett meddelande skall poppa upp p� sk�rmen.*/
   private void message(int i){
      if(i==1){
      showMessageDialog(null,//F�nstret placeras mitt �ver spelet.
            "V�lkommen till spelet ColorHack!\n\n\n" +
            "Detta spelet g�r ut p� att datorn slumpar fram en f�rgkod\n" +
            "och din uppgift �r sedan att kn�cka koden.\n\n" +
            "Du klickar p� de gr� kulorna f�r att v�xla f�rg p� dem. Det\n" +
            "finns sex olika f�rger att v�lja mellan och samma f�rg kan\n" +
            "f�rekomma flera g�nger. N�r du �r n�jd med din gissning\n" +
            "klickar du p� Gissa-knappen. Du f�r nu feedback i form av\n" +
            "svarta feedback-kulor som markerar hur m�nga kulor i din\n" +
            "gissning som var helt korrekta, b�de i f�rg och placering,\n" +
            "och vita feedback-kulor som markerar hur m�nga kulor\n" +
            "som enbart hade r�tt f�rg men inte r�tt placering.\n\n" +
            "Du har 10 f�rs�k p� dig att kn�cka koden. Lycka till!\n ",//Meddelande
            "V�lkommen",//Rubrik p� f�nstret.
            PLAIN_MESSAGE);//En ok-knapp.
      }
      else{
         showMessageDialog(null,//F�nstret placeras mitt �ver spelet.
               "Du m�ste v�lja en f�rg p� alla kulor innan" +
               " du kan gissa!",//Meddelande
               "Information",//Rubrik p� f�nstret.
               PLAIN_MESSAGE);//En ok-knapp.
      }
   }
   
   
   @Override
   /**Denna metod overridar actionPerformed() i ActionListener som klassen
    * implementerar. Den aktiveras d� gissa-knappen klickats.
    * Den j�mf�r l�sningen med gissningen med hj�lp av klassen FeedBack och
    * reagerar d�refter utifr�n resultatet.*/
   public void actionPerformed(ActionEvent e){
      //Inneh�llet i denna metod k�rs enbart om alla kulor i gissningen satts
      //till en f�rg vilket kontrolleras med metoden isSet() p� gissningen.
      if(myGuess.isSet()){
      FeedBack myF=new FeedBack();//Ett nytt FeedBack-objekt skapas.
      
      myF.setFeedBack(myGuess,mySolution);//Med hj�lp av metoden setFeedBack() i
                              //FeedBack-klassen s� initieras FeedBack-objektet.
      
      myGuess.addFeedback(myF);//Sedan adderas den till slutet p� gissningsraden,
      myGuess.noClicks();//och gissingsradens kulor s�tts till icke klickbara.
      validate();//Till sist uppdateras gissningsraden p� sk�rmen s� att
      repaint(); //feedbacken syns.
      
      //D�refter kontrolleras resultatet med metoden isCorrect() i FeedBack-klassen.
      if(myF.isCorrect()){//Om gissningen �r helt r�tt, 
         
         //hoppar ett dialogf�nster fram som talar om att man vunnit och fr�gar om
         //man vill spela igen. Utifr�n anv�ndarens svar utf�rs n�dv�ndiga �tg�rder.
         checkAnswer(confirmDialog(1));//Argumentet 1 kallar p� vinstmeddelandet.
      }
      
      //Om gissningen inte �r helt r�tt kontrolleras antalet f�rs�k.
      else{
         if(guesses<9){//Om f�rs�ken �r f�rre �n 10 st (9 eftersom en rad redan
                       //placerats ut vid start av spelomg�ngen) s�...
            
            guesses++;//�kar guesses med 1,
            myGuess=new Guess();//och en ny rad att gissa med skapas,
            this.add(myGuess);//och l�ggs till p� spelplanen.
            this.validate();//Till sist uppdateras f�nstret s� att den nya
                            //gissningsraden syns.
         }
         else{//Om 10 f�rs�k redan gjorts utan att man l�st f�rgkoden s�...
            //hoppar ett dialogf�nster fram som talar om att man f�rlorat och
            //fr�gar om man vill spela igen. Utifr�n anv�ndarens svar utf�rs
            //n�dv�ndiga �tg�rder.
            checkAnswer(confirmDialog(2));//Argumentet 2 kallar p� f�rlustmeddelandet.
         }
      }
      }
      else{
         message(2);
      }
   }
   
   
   /**Denna metod anv�nds f�r att ett dialogf�nster skall dyka upp som talar om
    * att anv�ndaren antingen har vunnit eller f�rlorat och som sedan fr�gar om
    * anv�ndaren vill spela en ny omg�ng.*/
   private int confirmDialog(int i){
      int svar=0;//H�r sparas svaret fr�n dialogf�nstret.
      if(i==1){
         svar=showConfirmDialog(null,//F�nstret placeras mitt �ver spelet.
               "Grattis! Du l�ste f�rgkoden. Vill du spela igen?",//Meddelande
               "Du vann!",//Rubrik p� f�nstret.
               YES_NO_OPTION);//En ja- och en nejknapp.
      }
      else if(i==2){
         svar=showConfirmDialog(null,//F�nstret placeras mitt �ver spelet.
               "Tyv�rr! Du l�ste inte koden p� 10 f�rs�k. Vill du spela igen?",//Meddelande
               "Du f�rlorade!",//Rubrik p� f�nstret.
               YES_NO_OPTION);//En ja- och en nejknapp.
      }
      return svar;
   }
   
   
   /**Denna metod anv�nds f�r att antingen starta en ny omg�ng eller avsluta
    * spelet beroende p� det argument den f�r.*/
   private void checkAnswer(int i){
      if(i==0){//Om anv�ndaren svarar ja,
         reset();//kallas metoden som skapar en ny omg�ng.
      }
      else if(i==1){//Om anv�ndaren svarar nej,
         System.exit(0);//avslutas programmet.
      }
   }
}
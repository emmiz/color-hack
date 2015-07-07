package colorpackage; //Denna klass deklareras att tillhöra paketet colorpackage.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import static javax.swing.JOptionPane.*;

/**Denna klass deklarerar spelplanen och dess funktioner. Den ärver ifrån klassen
 * JPanel och implementerar ActionListener.*/
public class GameBoard extends JPanel implements ActionListener{
   
   //Här deklareras ett serieversionsnummer för att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   //Klassens inkapslade instansvariabler.
   private Solution mySolution;//Denna referensvariabel kommer att referera till
                               //lösningsraden.
   private Guess  myGuess;//Denna referensvariabel kommer att referera till
                          //gissningsraden.
   private int guesses=0;//Denna instansvariabel håller ordning på antalet försök.
   
   
   /**Klassens konstruktor.*/
   public GameBoard(){
      setLayout(new GridLayout(10, 1));//En layout i form av en tabell med
                                       //(rader,kolumner) skapas.
      setBackground(new Color(255,255,255));//Fönstret får vit som bakgrundsfärg.
      reset();//En ny spelplan skapas.
      message(1);//Ett välkomstmeddelande visas.
   }
   
   
   /**Denna metod används för att skapa/nollställa spelplanen.*/
   private void reset(){
      this.removeAll();//Raderar allt eventuellt innehåll i fönstret.
      
      guesses=0;//Nollställer försöksräknaren.
      
      mySolution=new Solution();//Referensvariabeln mySolution initieras till
                                //att referera till ett nytt Solution-objekt.
    
      myGuess=new Guess();//Referensvariabeln myGuess initieras till att referera
                          //till ett nytt Guess-objekt.
      
      //add(mySolution);//Den lösningsrad som nyss skapats placeras ut på skärmen
                      //för att man skall kunna felsöka och testa spelet under
                      //spelets produktion.
      
      add(myGuess);//Den gissningsrad som nyss skapats placeras ut på spelplanen.
      
      validate();//Validerar/uppdaterar fönstrets innehåll.
      repaint();//Ritar om fönstret så att allt nytt syns.
   }

   
   /**Denna metod används för att ett meddelande skall poppa upp på skärmen.*/
   private void message(int i){
      if(i==1){
      showMessageDialog(null,//Fönstret placeras mitt över spelet.
            "Välkommen till spelet ColorHack!\n\n\n" +
            "Detta spelet går ut på att datorn slumpar fram en färgkod\n" +
            "och din uppgift är sedan att knäcka koden.\n\n" +
            "Du klickar på de grå kulorna för att växla färg på dem. Det\n" +
            "finns sex olika färger att välja mellan och samma färg kan\n" +
            "förekomma flera gånger. När du är nöjd med din gissning\n" +
            "klickar du på Gissa-knappen. Du får nu feedback i form av\n" +
            "svarta feedback-kulor som markerar hur många kulor i din\n" +
            "gissning som var helt korrekta, både i färg och placering,\n" +
            "och vita feedback-kulor som markerar hur många kulor\n" +
            "som enbart hade rätt färg men inte rätt placering.\n\n" +
            "Du har 10 försök på dig att knäcka koden. Lycka till!\n ",//Meddelande
            "Välkommen",//Rubrik på fönstret.
            PLAIN_MESSAGE);//En ok-knapp.
      }
      else{
         showMessageDialog(null,//Fönstret placeras mitt över spelet.
               "Du måste välja en färg på alla kulor innan" +
               " du kan gissa!",//Meddelande
               "Information",//Rubrik på fönstret.
               PLAIN_MESSAGE);//En ok-knapp.
      }
   }
   
   
   @Override
   /**Denna metod overridar actionPerformed() i ActionListener som klassen
    * implementerar. Den aktiveras då gissa-knappen klickats.
    * Den jämför lösningen med gissningen med hjälp av klassen FeedBack och
    * reagerar därefter utifrån resultatet.*/
   public void actionPerformed(ActionEvent e){
      //Innehållet i denna metod körs enbart om alla kulor i gissningen satts
      //till en färg vilket kontrolleras med metoden isSet() på gissningen.
      if(myGuess.isSet()){
      FeedBack myF=new FeedBack();//Ett nytt FeedBack-objekt skapas.
      
      myF.setFeedBack(myGuess,mySolution);//Med hjälp av metoden setFeedBack() i
                              //FeedBack-klassen så initieras FeedBack-objektet.
      
      myGuess.addFeedback(myF);//Sedan adderas den till slutet på gissningsraden,
      myGuess.noClicks();//och gissingsradens kulor sätts till icke klickbara.
      validate();//Till sist uppdateras gissningsraden på skärmen så att
      repaint(); //feedbacken syns.
      
      //Därefter kontrolleras resultatet med metoden isCorrect() i FeedBack-klassen.
      if(myF.isCorrect()){//Om gissningen är helt rätt, 
         
         //hoppar ett dialogfönster fram som talar om att man vunnit och frågar om
         //man vill spela igen. Utifrån användarens svar utförs nödvändiga åtgärder.
         checkAnswer(confirmDialog(1));//Argumentet 1 kallar på vinstmeddelandet.
      }
      
      //Om gissningen inte är helt rätt kontrolleras antalet försök.
      else{
         if(guesses<9){//Om försöken är färre än 10 st (9 eftersom en rad redan
                       //placerats ut vid start av spelomgången) så...
            
            guesses++;//Ökar guesses med 1,
            myGuess=new Guess();//och en ny rad att gissa med skapas,
            this.add(myGuess);//och läggs till på spelplanen.
            this.validate();//Till sist uppdateras fönstret så att den nya
                            //gissningsraden syns.
         }
         else{//Om 10 försök redan gjorts utan att man löst färgkoden så...
            //hoppar ett dialogfönster fram som talar om att man förlorat och
            //frågar om man vill spela igen. Utifrån användarens svar utförs
            //nödvändiga åtgärder.
            checkAnswer(confirmDialog(2));//Argumentet 2 kallar på förlustmeddelandet.
         }
      }
      }
      else{
         message(2);
      }
   }
   
   
   /**Denna metod används för att ett dialogfönster skall dyka upp som talar om
    * att användaren antingen har vunnit eller förlorat och som sedan frågar om
    * användaren vill spela en ny omgång.*/
   private int confirmDialog(int i){
      int svar=0;//Här sparas svaret från dialogfönstret.
      if(i==1){
         svar=showConfirmDialog(null,//Fönstret placeras mitt över spelet.
               "Grattis! Du löste färgkoden. Vill du spela igen?",//Meddelande
               "Du vann!",//Rubrik på fönstret.
               YES_NO_OPTION);//En ja- och en nejknapp.
      }
      else if(i==2){
         svar=showConfirmDialog(null,//Fönstret placeras mitt över spelet.
               "Tyvärr! Du löste inte koden på 10 försök. Vill du spela igen?",//Meddelande
               "Du förlorade!",//Rubrik på fönstret.
               YES_NO_OPTION);//En ja- och en nejknapp.
      }
      return svar;
   }
   
   
   /**Denna metod används för att antingen starta en ny omgång eller avsluta
    * spelet beroende på det argument den får.*/
   private void checkAnswer(int i){
      if(i==0){//Om användaren svarar ja,
         reset();//kallas metoden som skapar en ny omgång.
      }
      else if(i==1){//Om användaren svarar nej,
         System.exit(0);//avslutas programmet.
      }
   }
}
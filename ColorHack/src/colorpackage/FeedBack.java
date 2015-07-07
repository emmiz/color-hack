package colorpackage; //Denna klass deklareras att tillhöra paketet colorpackage.

import java.awt.GridLayout;

/**Detta är klassen för feedbacken i spelet. Den ärver ifrån klassen Row.*/
public class FeedBack extends Row{
   
   //Här deklareras ett serieversionsnummer för att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   //Klassens inkapslade instansvariabel.
   private int black=0;//Här sparas antalet rätt färg på rätt plats.
   private int white=0;//Här sparas antalet i övrigt enbart rätt färger.
   private boolean[] flagS={false,false,false,false};//Här sparas en boolean som håller
                                 //ordning på vilka kulor i lösningen som redan
                                 //parats ihop med en annan kula.
   private boolean[] flagG={false,false,false,false};//Här sparas en boolean som håller
                                 //ordning på vilka kulor i gissningen som redan
                                 //parats ihop med en annan kula.
   
   
   /**Klassens konstruktor.*/
   public FeedBack(){
      super();//Konstruktorn till superklassen som FeedBack  ärver ifrån, alltså
              //Row, kallas på för att instansiera FeedBack.
      
      setLayout(new GridLayout(2, 2));//Layouten ändras till att vara en tabell-
                                      //layout istället med (rader,kolumner).
      
      setIcon();//Utseendet på layoutens kulor sätts. 
   }
   
   
   /**Denna metod används för att sätta bilderna på feedback-kulorna.*/
   private void setIcon(){
      int i=0;//Denna variabel används för att hålla koll på hur många av
              //feedback-kulorna som fått sin bild.
      
      //Om värdet på black är större än 0 så används for-loopen för att initiera
      //svarta kulor i feedback-raden så många ggr.
      if(black>0){
         for(int b=0 ; b<black ; b++){
            getPeg(i).setIcon('b');//Elementet på respektive index i pegArray
                                   //hämtas och sedan körs metoden setIcon på
                                   //Peg-objektet vilket bestämmer dess bild.
            getPeg(i).noClicks();//Varje Peg-objekt som plockas fram i tur och
                                 //ordning sätts dessutom till icke klickbar 
                                 //eftersom man inte skall kunna ändra på dem.
            i++;//i ökar med ett för varje svart kula som placeras ut för man
                //till sist ska veta hur många tomma platser som skall placeras
                //ut.
         }
      }
      
      //Denna if-sats tillsammans med for-loop fungerar precis som ovan men
      //initierar istället vita kulor.
      if(white>0){
         for(int w=0 ; w<white ; w++){
            getPeg(i).setIcon('w');
            getPeg(i).noClicks();
            i++;
         }
      }
      
      //Denna if-sats kontrollerar till sist om det behöver fyllas på med några
      //tomma platser (dvs. se till så att det inte blir en standard grå klickbar
      //kula utplacerad som feedback). For-loopen fungerar precis som de
      //tidigare.
      if(i<4){
         for(int n=0 ; n<4-black-white ; n++){
            getPeg(i).setIcon('n');
            getPeg(i).noClicks();
            i++;
         }
      }
   }
   
   
   /**Denna metod används för att plocka fram rätt feedback. Den jämför den
    * lösning och gissning som skickas med som argument och initierar sedan
    * feedbacken till antalet helt korrekta och nästan korrekta placeringar.*/
   public void setFeedBack(Guess myGuess, Solution mySolution){
      //Denna for-loop itererar igenom alla kulor i gissningsraden.
      for(int i=0 ; i<4 ; i++){
         //Här jämförs kulan i gissningsraden med kulan på samma plats i lösningen.
         //Om de är lika sätts en flagga på detta indexvärde både i flagS-arrayen
         //och i flagG-arrayen.
         if (myGuess.getPeg(i).getPegColor()==mySolution.getPeg(i).getPegColor()) {
            flagS[i]=true;//En flagga sätts på att denna plats redan matchats.
            flagG[i]=true;//En flagga sätts på att denna plats redan matchats.
            black++;//black som håller ordning på helt korrekta gissningar ökar.
         }
      }
      
      //Denna for-loop går igenom flag-arrayerna för att se om några platser
      //inte är satta och går att matcha.
      for(int i=0 ; i<4 ; i++){
         if(flagS[i]!=true){//Om en flagga inte är satt på ordningsnr så går
            //nästa for-loop igenom de ordningnr i gissningsarrayen som inte är
            //samma för dessa skulle isf redan vara flaggade. Den går heller
            //inte igenom andra ordningsnr som redan är flaggade.
            for(int j=i+1,k=0 ; k<4 ; j++, k++){
               if(flagS[i]!=true){
                  if(j==4){//Om j har siffran 4 innebär det att varvet har gått
                     j=0;  //runt och j måste därför börja om på 0.
                  }
                  if(j!=i&&flagG[j]!=true&&
                     myGuess.getPeg(j).getPegColor()==mySolution.getPeg(i).getPegColor()){
                     flagS[i]=true;//Om en match hittas, flaggas denna
                     flagG[j]=true;
                     white++;//och white ökar med ett för att markera korrekta
                     //färger på fel plats.
                  }
               }
            }
         }
      }
      setIcon();//Tillsist initieras feedbackens bilder utifrån resultatet.
   }
   
   
   /**Denna metod används för att kontrollera om gissningen och lösningen är
    * densamma och isåfall returneras "true", i annat fall "false".*/
   public boolean isCorrect(){
      if(black==4){
         return true;
      }
      return false;
   } 
}

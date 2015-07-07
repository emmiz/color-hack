package colorpackage; //Denna klass deklareras att tillh�ra paketet colorpackage.

import java.awt.GridLayout;

/**Detta �r klassen f�r feedbacken i spelet. Den �rver ifr�n klassen Row.*/
public class FeedBack extends Row{
   
   //H�r deklareras ett serieversionsnummer f�r att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   //Klassens inkapslade instansvariabel.
   private int black=0;//H�r sparas antalet r�tt f�rg p� r�tt plats.
   private int white=0;//H�r sparas antalet i �vrigt enbart r�tt f�rger.
   private boolean[] flagS={false,false,false,false};//H�r sparas en boolean som h�ller
                                 //ordning p� vilka kulor i l�sningen som redan
                                 //parats ihop med en annan kula.
   private boolean[] flagG={false,false,false,false};//H�r sparas en boolean som h�ller
                                 //ordning p� vilka kulor i gissningen som redan
                                 //parats ihop med en annan kula.
   
   
   /**Klassens konstruktor.*/
   public FeedBack(){
      super();//Konstruktorn till superklassen som FeedBack  �rver ifr�n, allts�
              //Row, kallas p� f�r att instansiera FeedBack.
      
      setLayout(new GridLayout(2, 2));//Layouten �ndras till att vara en tabell-
                                      //layout ist�llet med (rader,kolumner).
      
      setIcon();//Utseendet p� layoutens kulor s�tts. 
   }
   
   
   /**Denna metod anv�nds f�r att s�tta bilderna p� feedback-kulorna.*/
   private void setIcon(){
      int i=0;//Denna variabel anv�nds f�r att h�lla koll p� hur m�nga av
              //feedback-kulorna som f�tt sin bild.
      
      //Om v�rdet p� black �r st�rre �n 0 s� anv�nds for-loopen f�r att initiera
      //svarta kulor i feedback-raden s� m�nga ggr.
      if(black>0){
         for(int b=0 ; b<black ; b++){
            getPeg(i).setIcon('b');//Elementet p� respektive index i pegArray
                                   //h�mtas och sedan k�rs metoden setIcon p�
                                   //Peg-objektet vilket best�mmer dess bild.
            getPeg(i).noClicks();//Varje Peg-objekt som plockas fram i tur och
                                 //ordning s�tts dessutom till icke klickbar 
                                 //eftersom man inte skall kunna �ndra p� dem.
            i++;//i �kar med ett f�r varje svart kula som placeras ut f�r man
                //till sist ska veta hur m�nga tomma platser som skall placeras
                //ut.
         }
      }
      
      //Denna if-sats tillsammans med for-loop fungerar precis som ovan men
      //initierar ist�llet vita kulor.
      if(white>0){
         for(int w=0 ; w<white ; w++){
            getPeg(i).setIcon('w');
            getPeg(i).noClicks();
            i++;
         }
      }
      
      //Denna if-sats kontrollerar till sist om det beh�ver fyllas p� med n�gra
      //tomma platser (dvs. se till s� att det inte blir en standard gr� klickbar
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
   
   
   /**Denna metod anv�nds f�r att plocka fram r�tt feedback. Den j�mf�r den
    * l�sning och gissning som skickas med som argument och initierar sedan
    * feedbacken till antalet helt korrekta och n�stan korrekta placeringar.*/
   public void setFeedBack(Guess myGuess, Solution mySolution){
      //Denna for-loop itererar igenom alla kulor i gissningsraden.
      for(int i=0 ; i<4 ; i++){
         //H�r j�mf�rs kulan i gissningsraden med kulan p� samma plats i l�sningen.
         //Om de �r lika s�tts en flagga p� detta indexv�rde b�de i flagS-arrayen
         //och i flagG-arrayen.
         if (myGuess.getPeg(i).getPegColor()==mySolution.getPeg(i).getPegColor()) {
            flagS[i]=true;//En flagga s�tts p� att denna plats redan matchats.
            flagG[i]=true;//En flagga s�tts p� att denna plats redan matchats.
            black++;//black som h�ller ordning p� helt korrekta gissningar �kar.
         }
      }
      
      //Denna for-loop g�r igenom flag-arrayerna f�r att se om n�gra platser
      //inte �r satta och g�r att matcha.
      for(int i=0 ; i<4 ; i++){
         if(flagS[i]!=true){//Om en flagga inte �r satt p� ordningsnr s� g�r
            //n�sta for-loop igenom de ordningnr i gissningsarrayen som inte �r
            //samma f�r dessa skulle isf redan vara flaggade. Den g�r heller
            //inte igenom andra ordningsnr som redan �r flaggade.
            for(int j=i+1,k=0 ; k<4 ; j++, k++){
               if(flagS[i]!=true){
                  if(j==4){//Om j har siffran 4 inneb�r det att varvet har g�tt
                     j=0;  //runt och j m�ste d�rf�r b�rja om p� 0.
                  }
                  if(j!=i&&flagG[j]!=true&&
                     myGuess.getPeg(j).getPegColor()==mySolution.getPeg(i).getPegColor()){
                     flagS[i]=true;//Om en match hittas, flaggas denna
                     flagG[j]=true;
                     white++;//och white �kar med ett f�r att markera korrekta
                     //f�rger p� fel plats.
                  }
               }
            }
         }
      }
      setIcon();//Tillsist initieras feedbackens bilder utifr�n resultatet.
   }
   
   
   /**Denna metod anv�nds f�r att kontrollera om gissningen och l�sningen �r
    * densamma och is�fall returneras "true", i annat fall "false".*/
   public boolean isCorrect(){
      if(black==4){
         return true;
      }
      return false;
   } 
}

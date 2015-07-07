package colorpackage; //Denna klass deklareras att tillhöra paketet colorpackage.

/**Denna klass deklarerar en gissning och dess funktion. Den ärver ifrån
 * klassen Row.*/
public class Guess extends Row{                                             

   //Här deklareras ett serieversionsnummer för att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;

   
   /**Klassens konstruktor.*/
   public Guess(){
      super();//Konstruktorn till superklassen som Guess  ärver ifrån, alltså
              //Row, kallas på för att instansiera Guess.
   }
   
   
   /**Denna metod används för att lägga till feedback i slutet på gissnings-
    * raden.*/
   public void addFeedback(FeedBack myF){
      add(myF);//FeedBack-objektet myF adderas till slutet av containern.
   }
   
   /**Denna metod används för att kontrollera att alla kulor i gissningen satts
    * till en färg.*/
   public boolean isSet(){
      int set=0;//I denna variabel sparas antalet satta kulor.
      
      //Denna for-loop itererar över alla kulor i gissningen och kontrollerar
      //att deras färgvalör är högre än 0 som är färgen på en orörd kula.
      for(int i=0;i<4;i++){
         if(getPeg(i).getPegColor()>0){
            set++;//Om kulan har fått en färg ökar set med ett.
         }  
      }
      return set==4;//Om set har värdet 4 efter for-loopen slutförts returneras true.
   }
   
   
   /**Denna metod används för att sätta kulorna i den gamla gissningsraden till
    * icke klickbara.*/
   public void noClicks(){
      for(int i=0;i<4;i++){//Med hjälp av en for-loop itererar vi igenom
         //elementen i pegArray (som deklareras i Row) och
         //utför följande:
         
         getPeg(i).noClicks();//Varje Peg-objekt som plockas fram i tur och
         //ordning sätts till icke klickbar eftersom man inte längre skall kunna
         //ändra på denna gissnings kulor.
      }
   }
}

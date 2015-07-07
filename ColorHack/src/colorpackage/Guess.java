package colorpackage; //Denna klass deklareras att tillh�ra paketet colorpackage.

/**Denna klass deklarerar en gissning och dess funktion. Den �rver ifr�n
 * klassen Row.*/
public class Guess extends Row{                                             

   //H�r deklareras ett serieversionsnummer f�r att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;

   
   /**Klassens konstruktor.*/
   public Guess(){
      super();//Konstruktorn till superklassen som Guess  �rver ifr�n, allts�
              //Row, kallas p� f�r att instansiera Guess.
   }
   
   
   /**Denna metod anv�nds f�r att l�gga till feedback i slutet p� gissnings-
    * raden.*/
   public void addFeedback(FeedBack myF){
      add(myF);//FeedBack-objektet myF adderas till slutet av containern.
   }
   
   /**Denna metod anv�nds f�r att kontrollera att alla kulor i gissningen satts
    * till en f�rg.*/
   public boolean isSet(){
      int set=0;//I denna variabel sparas antalet satta kulor.
      
      //Denna for-loop itererar �ver alla kulor i gissningen och kontrollerar
      //att deras f�rgval�r �r h�gre �n 0 som �r f�rgen p� en or�rd kula.
      for(int i=0;i<4;i++){
         if(getPeg(i).getPegColor()>0){
            set++;//Om kulan har f�tt en f�rg �kar set med ett.
         }  
      }
      return set==4;//Om set har v�rdet 4 efter for-loopen slutf�rts returneras true.
   }
   
   
   /**Denna metod anv�nds f�r att s�tta kulorna i den gamla gissningsraden till
    * icke klickbara.*/
   public void noClicks(){
      for(int i=0;i<4;i++){//Med hj�lp av en for-loop itererar vi igenom
         //elementen i pegArray (som deklareras i Row) och
         //utf�r f�ljande:
         
         getPeg(i).noClicks();//Varje Peg-objekt som plockas fram i tur och
         //ordning s�tts till icke klickbar eftersom man inte l�ngre skall kunna
         //�ndra p� denna gissnings kulor.
      }
   }
}

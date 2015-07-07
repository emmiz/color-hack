package colorpackage;

import java.util.Random;

/**Denna klassdeklarerar l�sningen och dess funktioner. Den �rver ifr�n klassen
 * Row.*/
public class Solution extends Row{

   //H�r deklareras ett serieversionsnummer f�r att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   /**H�r deklareras klassens konstruktor.*/
   public Solution(){
      super();//F�rst kallar man p� konstruktorn till superklassen som Solution
              //�rver ifr�n, allts� Row f�r att instansiera Solution.
      
      Random r=new Random();//D�refter skapas ett random-objekt f�r att kunna
                            //slumpa fram en kombination till just denna
                            //l�sningen. Referensen till objektet f�r heta r.
      
      for(int i=0;i<4;i++){//Med hj�lp av en for-loop itererar vi igenom
                           //elementen i pegArray (som deklareras i Row) och
                           //utf�r f�ljande:

         int color=r.nextInt(6)+1;//Ett v�rde mellan 1 och 6 slumpas fram och
                                  //sparas i variabeln color.
         
         //Elementet p� respektive index i pegArray h�mtas och sedan.. 
         getPeg(i).setPegColor(color);//k�rs metoden setPegColor...
         getPeg(i).setIcon(color);//och metoden Icon p� Peg-objektet f�r att
                                  //best�mma dess f�rg.
         
         getPeg(i).noClicks();//Varje Peg-objekt som plockas fram i tur och
                              //ordning s�tts dessutom till icke klickbar 
                              //eftersom man inte skall kunna �ndra p� l�sningen.
      }
   }
   
}

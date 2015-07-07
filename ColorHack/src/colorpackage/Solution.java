package colorpackage;

import java.util.Random;

/**Denna klassdeklarerar lösningen och dess funktioner. Den ärver ifrån klassen
 * Row.*/
public class Solution extends Row{

   //Här deklareras ett serieversionsnummer för att slippa ett felmeddelande
   //om att klassen inte deklarerar detta.
   private static final long serialVersionUID = 1L;
   
   
   /**Här deklareras klassens konstruktor.*/
   public Solution(){
      super();//Först kallar man på konstruktorn till superklassen som Solution
              //ärver ifrån, alltså Row för att instansiera Solution.
      
      Random r=new Random();//Därefter skapas ett random-objekt för att kunna
                            //slumpa fram en kombination till just denna
                            //lösningen. Referensen till objektet får heta r.
      
      for(int i=0;i<4;i++){//Med hjälp av en for-loop itererar vi igenom
                           //elementen i pegArray (som deklareras i Row) och
                           //utför följande:

         int color=r.nextInt(6)+1;//Ett värde mellan 1 och 6 slumpas fram och
                                  //sparas i variabeln color.
         
         //Elementet på respektive index i pegArray hämtas och sedan.. 
         getPeg(i).setPegColor(color);//körs metoden setPegColor...
         getPeg(i).setIcon(color);//och metoden Icon på Peg-objektet för att
                                  //bestämma dess färg.
         
         getPeg(i).noClicks();//Varje Peg-objekt som plockas fram i tur och
                              //ordning sätts dessutom till icke klickbar 
                              //eftersom man inte skall kunna ändra på lösningen.
      }
   }
   
}

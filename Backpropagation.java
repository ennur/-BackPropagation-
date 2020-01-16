
package backpropagation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Backpropagation {

    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        
        YSA ysa =new YSA(1000);
        Scanner in =new Scanner(System.in);
        int sec=0;
        do{
           System.out.println("1.Agı Egit");
           System.out.println("2.Agı Test Et");
           System.out.println("3.Minimum Hata Oranı");
           System.out.println("4.Elde Edilen Hatalar");
           System.out.println("5.Cikis");
           sec=in.nextInt();
           switch(sec){
               case 1:
                   ysa.Egit();
               case 2:
                   System.out.println("x:");
                   double x=in.nextDouble();
                   System.out.println("y:");
                   double y=in.nextDouble();
                   double[]z=ysa.test(x, y);
                   System.out.println("z:"+z[0]);
                   System.in.read();
                   break;
               case 3:
                   System.out.println("Hata:"+ysa.Hata());
                   System.in.read();
                   break;
               case 4:
                   double [] hatalar= ysa.Hatalar();
                   int epoch=1;
                   for(double h: hatalar) {
                   System.out.println(epoch+":"+h);
                    epoch++;                   
                   }
                   System.in.read();
                 break;          
           }
           
        }while (sec !=3);
    
    }
    
}

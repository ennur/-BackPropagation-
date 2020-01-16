
package backpropagation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.visrec.ml.data.DataSet;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

public class YSA {
    
    private static final File dosya =new File(YSA.class.getResource("Data.txt").getPath());
    BackPropagation bp ;
    double maksEpoch;
    double [] eldeEdilenHatalar;
    public YSA (double maksEpoch)
    {
       this.maksEpoch=maksEpoch;
       bp=new BackPropagation();
       eldeEdilenHatalar= new double[(int)maksEpoch];
    
    }
    public void Egit() throws FileNotFoundException{
       //çok katmanlı aglar
       //ikinci parametre ara katamndakı noron sayısı
       //ucuncu parametre çıktı sayısı
       //birinci parametre input sayısı
       MultiLayerPerceptron sinirselAg = new MultiLayerPerceptron(TransferFunctionType.TANH,2,3,1);
       
       bp.setLearningRate(0.2);
	     bp.setMaxError(0.0000001);
       sinirselAg.setLearningRule(bp);
        
       for(int i=0;i<maksEpoch;i++){
            sinirselAg.getLearningRule().doOneLearningIteration((org.neuroph.core.data.DataSet) EgitimVeriSeti());
            if(i==0)eldeEdilenHatalar[i]=1;
            else eldeEdilenHatalar[i]=sinirselAg.getLearningRule().getPreviousEpochError();
        }
        
        sinirselAg.save("ogrenenAg.nnet");
        System.out.println("Egitim Tamamlandi");      
    
    }
    
    public double [] Hatalar(){
    return eldeEdilenHatalar;
    }
    
    public double Hata(){
     return bp.getTotalNetworkError();
    }
    
    public DataSet EgitimVeriSeti() throws FileNotFoundException {
     Scanner oku = new Scanner(dosya);
     DataSet egitim = new org.neuroph.core.data.DataSet(2,1);
     while(oku.hasNextDouble())
     {
         DataSetRow satir =new DataSetRow(new double[]{oku.nextDouble(),oku.nextDouble()}, new double[]{oku.nextDouble()});
         egitim.add(satir);
         
     }
     oku.close();
     return egitim;
             
    }
    
    public double [] test (double x, double y){
    NeuralNetwork sinirselAg= NeuralNetwork.createFromFile("ogrenenAg.nnet");
    sinirselAg.setInput(x,y);
    sinirselAg.calculate();
    return sinirselAg.getOutput();
    }
    
}

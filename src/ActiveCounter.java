import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class ActiveCounter {

    int numberPart;
    int exponentPart;

    public ActiveCounter() {
        numberPart = 0;
        exponentPart = 0;
    }

    // int bitmapToInt(int[] bitmap){
    //     int value=0;
    //     for(int i=0;i<bitmap.length;i++){
    //         value += bitmap[i] * Math.pow(2, i);
    //     }
    //     return value;
    // }

    public void increment(){
        if(new Random().nextInt((int)Math.pow(2, (exponentPart&0xffff))) == 0){
            numberPart++;
            if(((numberPart & 0xffff)^0xffff) == 0){
                numberPart = numberPart >> 1;
                numberPart = numberPart & 0xffff;
                exponentPart ++;
                exponentPart = exponentPart & 0xffff;
            }
            
        }
    }

    public int printCount(){
        return (int) ((numberPart&0xffff) * Math.pow(2, (exponentPart&0xffff)));
    }

    public void Output(){
        String output = "Active Counter Val: " + printCount() + "\n";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("ActiveCounter.txt"));
            writer.write(output);
            writer.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}

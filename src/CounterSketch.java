import java.util.Arrays;

public class CounterSketch extends Counter{

    public CounterSketch(int numberOfCounterArrays, int numberOfCountersPerArray) {
        super(numberOfCounterArrays, numberOfCountersPerArray);
    }

    @Override
    void record(Flow flow){
        for(int i=0;i<numberOfCounterArrays;i++){
            int[] hash = hashFunctions.HashWithMSB(flow.flowId,i);
            if(hash[0]==0){
                bitmaps[i][hash[1]] -= flow.numberOfPackets;
            }else{
                bitmaps[i][hash[1]] += flow.numberOfPackets;
            }
        }
    }

    @Override
    int query(String flowId){
        int[] counts = new int[this.numberOfCounterArrays];
        for(int i=0;i<numberOfCounterArrays;i++){
            int[] hash = hashFunctions.HashWithMSB(flowId,i);
            if(hash[0]==0){
                counts[i] = (-1) * bitmaps[i][hash[1]];
            }else{
                counts[i] = bitmaps[i][hash[1]];
            }
        }

        Arrays.sort(counts);
        int median;
        if (counts.length % 2 == 0){
            median = (counts[counts.length/2] + counts[counts.length/2 - 1])/2;
        }
        else{
            median = counts[counts.length/2];
        }

        return median;
    }

    public void Output(Flow[] flows){
        super.Output(flows, "CounterSketch.txt");
    }
    
}

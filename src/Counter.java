import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public abstract class Counter{

    int numberOfCounterArrays;
    int numberOfCountersPerArray;
    HashFunctions hashFunctions;

    int[][] bitmaps;

    public Counter(int numberOfCounterArrays, int numberOfCountersPerArray){
        this.numberOfCounterArrays = numberOfCounterArrays;
        this.numberOfCountersPerArray = numberOfCountersPerArray;
        this.hashFunctions = new HashFunctions(numberOfCounterArrays, numberOfCountersPerArray);
        
        bitmaps = new int[numberOfCounterArrays][numberOfCountersPerArray];
    }

    abstract void record(Flow flow);

    abstract int query(String flowId);

    public int Error(Flow flow){
        flow.estimatedNumberOfPackets = query(flow.flowId);
        return Math.abs(flow.estimatedNumberOfPackets-flow.numberOfPackets);
    }

    public int AvgError(Flow[] flows){
        int totalError = 0;
        for(int i=0;i<flows.length;i++){
            totalError += Error(flows[i]);
        }
        return totalError/flows.length;
    }

    public void ConsumeFlows(Flow[] flows){
        for(int i=0;i<flows.length;i++){
            record(flows[i]);
        }
    }

    public void PrintCounterArray(int Counter){
        for(int i=0;i<bitmaps[Counter].length;i++){
            System.out.print(bitmaps[Counter][i]+" ");
        }
    }

    public String PrintTop100(Flow[] flows){
        Arrays.sort(flows,(a,b)->b.estimatedNumberOfPackets-a.estimatedNumberOfPackets);
        String top100="";
        for(int i=0;i<100;i++){
            top100+="Flow: " + flows[i].flowId + " Size: " + flows[i].numberOfPackets + " Estimated Size: " + flows[i].estimatedNumberOfPackets + "\n";
        }
        return top100;
    }

    public void Output(Flow[] flows, String outputFileName){
        String output = "Average Error: " + AvgError(flows) + "\n";
        output += PrintTop100(flows);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(output);
            writer.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}

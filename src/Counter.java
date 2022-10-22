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
        return Math.abs(query(flow.flowId)-flow.numberOfPackets);
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
    
}

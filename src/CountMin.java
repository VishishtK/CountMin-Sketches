public class CountMin {

    int numberOfCounterArrays;
    int numberOfCountersPerArray;
    HashFunctions hashFunctions;

    int[][] bitmaps;

    public CountMin(int numberOfCounterArrays, int numberOfCountersPerArray){
        this.numberOfCounterArrays = numberOfCounterArrays;
        this.numberOfCountersPerArray = numberOfCountersPerArray;
        this.hashFunctions = new HashFunctions(numberOfCounterArrays, numberOfCountersPerArray);
        
        bitmaps = new int[numberOfCounterArrays][numberOfCountersPerArray];
    }

    void record(Flow flow){
        for(int i=0;i<numberOfCounterArrays;i++){
            bitmaps[i][hashFunctions.Hash(flow.flowId,i)] += flow.numberOfPackets;
        }
    }

    int query(String flowId){
        int count = Integer.MAX_VALUE;
        for(int i=0;i<numberOfCounterArrays;i++){
            count = Math.min(count, bitmaps[i][hashFunctions.Hash(flowId,i)]);
        }
        return count;
    }

    int error(Flow flow){
        return Math.abs(query(flow.flowId)-flow.numberOfPackets);
    }

    public int AvgError(Flow[] flows){
        int totalError = 0;
        for(int i=0;i<flows.length;i++){
            totalError += error(flows[i]);
        }
        return totalError/flows.length;
    }

    public void ConsumeFlows(Flow[] flows){
        for(int i=0;i<flows.length;i++){
            record(flows[i]);
        }
    }


}

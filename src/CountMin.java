public class CountMin extends Counter{

    public CountMin(int numberOfCounterArrays, int numberOfCountersPerArray) {
        super(numberOfCounterArrays, numberOfCountersPerArray);
        //TODO Auto-generated constructor stub
    }

    @Override
    void record(Flow flow){
        for(int i=0;i<numberOfCounterArrays;i++){
            bitmaps[i][hashFunctions.Hash(flow.flowId,i)] += flow.numberOfPackets;
        }
    }

    @Override
    int query(String flowId){
        int count = Integer.MAX_VALUE;
        for(int i=0;i<numberOfCounterArrays;i++){
            count = Math.min(count, bitmaps[i][hashFunctions.Hash(flowId,i)]);
        }
        return count;
    }

    public void Output(Flow[] flows){
        super.Output(flows, "CountMin.txt");
    }

}

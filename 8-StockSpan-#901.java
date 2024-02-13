class StockSpanner {
    Stack<Pair<Integer, Integer>> st;
    int count = 1;

    public StockSpanner() {
        st = new Stack<>();
    }
    
    public int next(int price) {
        int ans = -1;
        if(st.empty() || st.peek().getKey() > price){
            ans = 1;
        }else if(st.peek().getKey() <= price){
            while(!st.empty() && st.peek().getKey() <= price) st.pop();
            if(st.empty()){
                ans = count;
            }else if(st.peek().getKey() > price){
                ans = count - st.peek().getValue();
            }
        }

        st.push(new Pair<Integer, Integer>(price, count++));
        return ans;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

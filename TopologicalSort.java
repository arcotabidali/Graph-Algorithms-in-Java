class TopologicalSort {
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        // add your code here
        ArrayList<Integer> list = new ArrayList<Integer>();
        boolean[] visited = new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i]){
                dfs(i,adj,visited,list);
            }
        }
        int j=0;
        int[] resultArr = new int[V];
        for(int i=V-1;i>=0;i--){
            resultArr[j++]=list.get(i);
        }
        return resultArr;
    }
    
    public static void dfs(int vertex,ArrayList<ArrayList<Integer>> adj,boolean[] visited,ArrayList<Integer> list){
        visited[vertex]=true;
        ArrayList<Integer> cl = adj.get(vertex);
        int len = cl.size();
        for(int i=0;i<len;i++){
            int nextNode = cl.get(i);
            if(!visited[nextNode]){
                dfs(nextNode,adj,visited,list);
            }
        }
        list.add(vertex);
    }
}


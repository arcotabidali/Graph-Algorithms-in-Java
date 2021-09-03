class FloydWarshall
{
    public void shortest_distance(int[][] graph)
    {
        // Code here
        int len = graph.length;
          for(int k=0;k<len;k++){
              //for each node acting as intermediate perfom the following
            for(int i=0;i<len;i++){
               for(int j=0;j<len;j++){
                   //for every source to destinataion
                   if(graph[i][k]==-1 || graph[k][j]==-1 || graph[i][j]==-1){ // there is not edge between , then continue
                       continue;
                   }
                   //check for the given source and destination does passing to current node gives a shorter path.
                    if(graph[i][k]+graph[k][j]<graph[i][j]){
                       graph[i][j]=graph[i][k]+graph[k][j];
                   }
               }
                
            }
        }
    }
    
    
}

class BridgesAndArticulationPoints {
   static int id=0;
    public static class Edge{
        int src;
        int dst;
        Edge(int src,int dst){
            this.src = src;
            this.dst = dst;
        }
    }
    public static void main(String args[] ) throws Exception {      

        //Scanner
        Scanner sc = new Scanner(System.in);
        int vertices  = sc.nextInt();
        int edges = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<vertices;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<edges;i++){
            int src = sc.nextInt();
            int dst = sc.nextInt();
            graph.get(src).add(dst);
        }
          //passing a graph to find brides and articulations
         findBridesAndAriculations(graph,vertices);

    }

    public static void findBridesAndAriculations(ArrayList<ArrayList<Integer>> graph,int V){
        int ids[] = new int[V];
        int lowValues[] = new int[V];       
        boolean []visited = new boolean[V];
        boolean[] arti = new boolean[V]; // to store articulations
        ArrayList<Edge> edges = new ArrayList<Edge>(); // to store edges
        for(int i=0;i<V;i++){
            if(!visited[i]){
        // children only need for root to verify wheather it is a articulation pointyou can't just get size of list and determine the size,it abou         
                int children = dfs(i,graph,-1,visited,ids,lowValues,arti,edges);
                // you can't just get size of list and determine the size,it about the traversing.            
                arti[i]=(children>1);
            }
        }
        ArrayList<Integer> articulates = new ArrayList<Integer>();
        for(int i=0;i<V;i++){
            if(arti[i]){
                articulates.add(i);
            }
        }
        Collections.sort(articulates);
        Collections.sort(edges,new Comparator<Edge>(){
            public int compare(Edge e1,Edge e2){
                if(e1.src!=e2.src){
                    return e1.src-e2.src;
                }
                return e1.dst-e2.dst;
            }
        });
        System.out.println(articulates.size());
        for(int node : articulates){
            System.out.print(node+" ");
        }
        System.out.println();
        System.out.println(edges.size());
        for(Edge edge : edges){
            System.out.println(edge.src + " " + edge.dst);
        }
    }
     
    public static int dfs(int vertex,ArrayList<ArrayList<Integer>> graph,int parent,boolean[] visited,int[] ids,int[] lowValues,boolean[] arti,ArrayList<Edge> edges){
     visited[vertex]=true;
     ids[vertex]=id;
     lowValues[vertex]=id++;
     int children=0;
     ArrayList<Integer> cl = graph.get(vertex);
     int len = cl.size();
     for(int i=0;i<len;i++){
         int nextNode = cl.get(i);
         if(!visited[nextNode]){
             children++; // we need these children only for root, though its waste of tracking for other nodse
             dfs(nextNode,graph,vertex,visited,ids,lowValues,arti,edges);
             //when recusion is returned, you wil check wheater it had got lowerValue
             lowValues[vertex]=Math.min(lowValues[vertex],lowValues[nextNode]);
         }
         //if encountered already visited node then min with its lowvalue
         lowValues[vertex]=Math.min(lowValues[vertex],ids[nextNode]);     
        
         if(lowValues[nextNode]>=ids[vertex]){
             //its articulaton through cycle "=" & brige ">" its sufficient check for                        intermediate
             //for root this condition would not be sufficient,but are again cheking for root at end
             arti[vertex]=true;
         }
          if(lowValues[nextNode]>ids[vertex]){
             //its a bride check because it couldn't reach to the ancestor on its own,it needs this bridge             
             if(nextNode>vertex){
             edges.add(new Edge(vertex,nextNode));
             }
             else{
              edges.add(new Edge(nextNode,vertex));
             }
         }
     }
     return children;
    }
}



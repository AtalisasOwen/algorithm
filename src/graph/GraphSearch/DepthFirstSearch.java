package graph.GraphSearch;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import graph.Graph;
import graph.SimpleUndirectedGraph;

/**
 * Created by 顾文涛 on 2017/2/28.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }
    private void dfs(Graph g,int v){
        marked[v] = true;
        count++;
        for (int w : g.adj(v))
            if (!marked[w]) dfs(g,w);
    }
    public boolean marked(int w){
        validateVertex(w);
        return marked[w];
    }
    public int count(){
        return  count;
    }
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /*
    *                   1
    *                 /  \
    *               0-----2
    *              |    /  \
    *             5---3----4
    *
    *       每个节点只会标注一次
    *       会沿着一个节点的邻近节点不断探索下去，
    *       直到检查完某节点的所有邻节点均被标注
    *       然后返回上一步
    *       此为深度优先搜索
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new SimpleUndirectedGraph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V()) StdOut.println("NOT connected");
        else                         StdOut.println("connected");
    }
}

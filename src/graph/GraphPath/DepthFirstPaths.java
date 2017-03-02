package graph.GraphPath;

import ADT.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import graph.Graph;
import graph.GraphSearch.DepthFirstSearch;
import graph.SimpleUndirectedGraph;

/**
 * Created by 顾文涛 on 2017/2/28.
 */
public class DepthFirstPaths {
     /*
    *                   1              0       edgeTo[]
    *                 /  \            |         0 |
    *               0-----2          2          1 | 2
    *              |    /  \       /  \         2 | 0
    *             5---3----4      1    3        3 | 2
    *                                /  \       4 | 3
    *                              4     5      5 | 3
    *   edgeTo的索引为节点，而edgeTo的值代表其前一个节点
    *
    *   x     路径
    * -------------------
    *   5     5
    *   3     3  5
    *   2     2  3  5
    *   0     0  2  3  5
    *   先沿一条路走走看，走到底，看能不能找到要找的节点（递归）
    */
    private boolean[] marked;   //这个顶点上是否dfs()
    private int[] edgeTo;       //从起点到一个顶点的已知路径上的最后一个节点
    private final int s;        //起点
    public DepthFirstPaths(Graph g,int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g,s);
    }

    private void dfs(Graph g,int v){
        marked[v] = true;
        for (int w : g.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v;x != s;x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new SimpleUndirectedGraph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}

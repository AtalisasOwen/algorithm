package graph.GraphPath;

import ADT.Queue;
import ADT.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import graph.Graph;
import graph.SimpleUndirectedGraph;

/**
 * Created by 顾文涛 on 2017/2/28.
 */
public class BreadthFirstPaths {
    /*
    *                   1              0            edgeTo[]     queue
    *                 /  \         /  |  \           0 |         0
    *               0-----2      2    1   5          1 | 0       2
    *              |    /  \    | \                  2 | 0       2 1
    *             5---3----4   3   4                 3 | 2       2 1 5
    *                                                4 | 2       1 5 3
    *                                                5 | 0       1 5 3 4
    *   广度优先，先发掘某节点的所有临近节点
    *   这样找到目标节点就返回，一定能找到最近路径
    *   采用一个队列的方式来实现
    */


    private boolean[] marked;   //到达该顶点的最短路径已知吗
    private int[] edgeTo;       //到达该顶点的已知路径上的最后一个顶点
    private final int s;        //起点
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path
    public BreadthFirstPaths(Graph g,int s){
        marked = new boolean[g.V()];
        distTo = new int[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g,s);
    }
    private void bfs(Graph g,int s){
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;       //标记起点
        queue.enqueue(s);        //将它加入队列
        while (!queue.isEmpty()){
            int v = queue.dequeue();//从队列中删除下一顶点
            for (int w : g.adj(v)){
                if (!marked[w]){    //对于每个未被标记的相邻节点
                    edgeTo[w] = v;  //保存最短路径的最后一条边
                    marked[w] = true;//标记它，因为最短路径已知
                    queue.enqueue(w);//并将它添加到队列中
                }
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

    // check optimality conditions for single source
    private boolean check(Graph G, int s) {

        // check that the distance of s = 0
        if (distTo[s] != 0) {
            StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("distTo[" + v + "] = " + distTo[v]);
                    StdOut.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }

        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        // provided v is reachable from s
        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                StdOut.println("shortest path edge " + v + "-" + w);
                StdOut.println("distTo[" + v + "] = " + distTo[v]);
                StdOut.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Unit tests the {@code BreadthFirstPaths} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new SimpleUndirectedGraph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }
}

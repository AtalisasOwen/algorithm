package graph;

/**
 * Created by 顾文涛 on 2017/2/27.
 */
public interface Graph {




    int V();
    int E();
    void addEdge(int v,int w);
    Iterable<Integer> adj(int v);

    String toString();
}

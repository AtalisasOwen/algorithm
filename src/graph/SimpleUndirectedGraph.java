package graph;

import ADT.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/2/27.
 */
public class SimpleUndirectedGraph implements Graph {
    private final int V;        //顶点个数
    private int E;              //边的个数
    private Bag<Integer>[] adj; //邻接表


    public SimpleUndirectedGraph(int V) {
        this.V = V;
        this.E=0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0;v<V;v++){
            adj[v] = new Bag<Integer>();
        }
    }

    public SimpleUndirectedGraph(In in){
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public int V() {
        return V;
    }


    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }




    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public String toString(){
        String s = V + " vertices,"+E+" edges\n";
        for (int v=0;v<V;v++){
            s+=v + ": ";
            for (int w : this.adj(v))
                s+=w+" ";
            s+="\n";
        }
        return s;
    };

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}

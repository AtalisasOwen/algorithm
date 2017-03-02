package graph.ConnectedComponents;

import graph.Graph;

/**
 * Created by 顾文涛 on 2017/2/28.
 */
public class SimpleCC {
    /*     1     2
    *       \  /
    *        0-------6           7---8
    *       |         \
    *      5-----------4          9---10
    *       \        /           | \
    *        \     /            11--12
    *          3
    *
    *
    *
    *
    *
    *
     */

    private boolean[] marked;
    private int[] id;
    private int count;
    public SimpleCC(Graph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0;s < g.V();s++){
            if (!marked[s]){
                //第一遍走连通，将能通的id 写0
                //第二遍走另一个连通，将能通的id 写1
                // ..........
                dfs(g,s);
                count++;
            }
        }
    }


    public void dfs(Graph g,int v){
        marked[v] = true;
        id[v] =count;
        for (int w : g.adj(v)){
            if (!marked[w]){
                dfs(g,w);
            }
        }
    }

    public boolean connected(int v,int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}

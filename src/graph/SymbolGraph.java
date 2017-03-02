package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * Created by 顾文涛 on 2017/3/1.
 */
public class SymbolGraph {

    private ST<String,Integer> st;
    private String[] keys;
    private Graph g;
    public SymbolGraph(String stream,String sp){
        st = new ST<String,Integer>();
        In in =new In(stream);
        while (in.hasNextLine()){
            String[] a = in.readLine().split(sp);
            for (int i = 0;i<a.length;i++){
                if (!st.contains(a[i])){
                    st.put(a[i],st.size());
                }
            }

        }
    }
}

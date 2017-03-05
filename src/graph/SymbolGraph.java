package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * Created by 顾文涛 on 2017/3/1.
 */
public class SymbolGraph {

    private ST<String,Integer> st;  //符号名 -> 索引
    private String[] keys;          //索引 -> 符号名
    private Graph g;                //图
    public SymbolGraph(String stream,String sp){
        st = new ST<String,Integer>();
        In in =new In(stream);      //第一遍
        while (in.hasNextLine()){   //构造索引
            String[] a = in.readLine().split(sp);//读取字符串
            for (int i = 0;i<a.length;i++){     //为每一个不同的字符串关联一个索引
                if (!st.contains(a[i])){
                    st.put(a[i],st.size());
                }
            }

            keys = new String[st.size()];   //用来获取顶点名的反向索引，是一个数组

            for(String name : st.keys()){
                keys[st.get(name)] = name;
            }
            g = new SimpleUndirectedGraph(st.size());
            in = new In(stream);
            while(in.hasNextLine()){
                String[] b =in.readLine().split(sp);
                int v = st.get(b[0]);
                for (int i=1;i<b.length;i++){
                    g.addEdge(v,st.get(b[i]));
                }
            }
        }
    }

    public boolean contains(String s){ return st.contains(s);}

    public int index(String s){return st.get(s);}

    public String name(int v){return keys[v];}

    public Graph G(){return g;}





}

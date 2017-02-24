package sort.PriorityQueue;

import sort.Sort;

/**
 * Created by 顾文涛 on 2017/2/24.
 */
public class MaxPQHeap<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N=0;
    public MaxPQHeap(int maxN){
        pq=(Key[]) new Comparable[maxN+1];
    }
    public boolean isEmpty(){
      return N==0;
    }
    public int size(){
        return N;
    }
    public void insert(Key k){
        pq[N++]=k;
        swim(N);
    }
    public Key delMax(){
        Key max=pq[1];
        exchange(1,N--);//将其与最后一个节点交换，然后N-1
        pq[N+1]=null;
        sink(1);
        return max;

    }
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }
    private void exchange(int i,int j){
        Key temp=pq[i];
        pq[i]=pq[j];
        pq[j]=temp;
    }

    //TODO:上浮算法
    private void swim(int k){
        while(k>1 && less(k/2,k)){
            exchange(k/2,k);
            k=k/2;
        }
    }

    //TODO:下沉算法
    private void sink(int k){
        while(2*k<N){
            int j=2*k;
            if (j<N && less(j,j+1)) j++;
            if (!less(k,j))break;
            exchange(k,j);
            k=j;
        }
    }



}

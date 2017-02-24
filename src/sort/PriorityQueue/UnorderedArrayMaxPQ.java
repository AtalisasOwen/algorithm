package sort.PriorityQueue;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac UnorderedArrayMaxPQ.java
 *  Execution:    java UnorderedArrayMaxPQ
 *  Dependencies: StdOut.java
 *
 *  Priority queue implementation with an unsorted array.
 *
 *  Limitations
 *  -----------
 *   - no array resizing
 *   - does not check for overflow or underflow.
 *
 ******************************************************************************/

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;      // elements
    private int n;         // number of elements

    // set inititial size of heap to hold size elements
    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty()   { return n == 0; }
    public int size()          { return n;      }
    //无序优先队列，加到数组的最后
    public void insert(Key x)  { pq[n++] = x;   }


    //选择排序的方式，把最大元素放到数组末尾并删除返回
    public Key delMax() {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i)) max = i;
        exch(max, n-1);

        return pq[--n];
    }


    /***************************************************************************
     * Helper functions.
     ***************************************************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }


    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<String>(10);
        pq.insert("Z");
        pq.insert("F");
        pq.insert("A");
        pq.insert("T");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }

}
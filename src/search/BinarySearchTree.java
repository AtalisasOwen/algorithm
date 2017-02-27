package search;

import ADT.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/2/26.
 * 由于只和根节点比较，所有最坏情况下，会出现所有节点都在根节点的一边
 * 所以有了二叉平衡树。
 */
public class BinarySearchTree<Key extends Comparable<Key>,Value> {
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node leftNode;
        private Node rightNode;
        private int N;
        public Node(Key key,Value value,int N){
            this.key=key;
            this.value=value;
            this.N=N;
        }
    }

    //一种很好的封装模式
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if(x==null){
            return 0;
        }else{
            return x.N;
        }
    }

    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x,Key key){
        //在根节点的子树中查找并返回Value，无则返回null
        if(x==null){
            return  null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return get(x.rightNode,key);
        else if (cmp < 0) return get(x.leftNode.key);
        else return x.value;
    }

    public void put(Key key,Value value){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        root = put(root, key, value);
        assert check();
    }
    private Node put(Node x,Key key,Value value){
        //如果key存在于以x为根节点的子树中，则更新他
        //否则则插入该键值对
        //
        if(x ==  null){
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(x.key);
        //下面这两句，会得到new Node(key,value,1);
        //并把Node放到相应的位置，更新value
        if (cmp > 0) x.rightNode=put(x.rightNode,key,value);
        else if (cmp < 0) x.leftNode=put(x.leftNode,key,value);
        else x.value=value;

        x.N=size(x.leftNode)+size(x.rightNode)+1;
        return x;
    }

    //找到排名为k 的键：即有k 个值比他小
    //如果给的键小于根节点，则返回在左子树中的排名（递归）
    //如果给的键大与根节点，则返回t+1加上他在右子树中的排名（递归）
    public Key select(int k){
        return select(root,k).key;
    }
    private Node select(Node x,int k){
        if(x == null) return null;
        int t=size(x.leftNode);
        if (t>k) return select(x.leftNode,k);
        else if(t < k) return select(x.rightNode,k-t-1);
        else return x;

    }

    //rank是select()的逆方法
    public int rank(Key key){
        return rank(key,root);
    }
    private int rank(Key key,Node x){
        if (x==null) return -1;
        int cmp=key.compareTo(root.key);
        if      (cmp < 0) return rank(key, x.leftNode);
        else if (cmp > 0) return 1 + size(x.leftNode) + rank(key, x.rightNode);
        else              return size(x.leftNode);

    }



    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key);
        assert check();
    }
    /*！！！！！！！！！！
    * 1.查找将要被删除的节点x
    * 2.将上述节点x 保存为 t
    * 3.将x 指针指向他的后继节点 min(t.right)
    * 4.将x 的右链接指向deleteMin（t.right);
    * 也就是删除后，所有节点仍然大于x.key 的子二叉树
    * 5.将x的左链接，设为t.left......
    *
     *       MMB,这是人能想出来的吗
     */
    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.leftNode  = delete(x.leftNode,  key);
        else if (cmp > 0) x.rightNode = delete(x.rightNode, key);
        else {
            if (x.rightNode == null) return x.leftNode;
            if (x.leftNode  == null) return x.rightNode;
            Node t = x;
            x = min(t.rightNode);
            x.rightNode = deleteMin(t.rightNode);
            x.leftNode = t.leftNode;
        }
        x.N = size(x.leftNode) + size(x.rightNode) + 1;
        return x;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }
    private Node deleteMin(Node x) {
        if (x.leftNode == null) return x.rightNode;
        x.leftNode = deleteMin(x.leftNode);//将指向左边子树的指针指向该子树的右边,左边的最小值无指针后被回收
        x.N = size(x.leftNode) + size(x.rightNode) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.rightNode == null) return x.leftNode;
        x.rightNode = deleteMax(x.rightNode);
        x.N = size(x.leftNode) + size(x.rightNode) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if(x.leftNode == null){
            return x;
        }
        return min(x.leftNode);
    }

    public Key max(){
        return max(root).key;
    }
    private Node max(Node x){
        if(x.rightNode==null) return x;
        return max(x.rightNode);
    }

    public Key floor(Key key){
        return floor(root,key).key;
    }
    private Node floor(Node x,Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor(x.leftNode,key);
        if (cmp == 0) return x;
        Node t = floor(x.rightNode,key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.leftNode, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.rightNode, key);
    }


    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.leftNode, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.rightNode, queue, lo, hi);
    }


    /**
    树的高度，决定树的查找效率
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.leftNode), height(x.rightNode));
    }

    /**
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.leftNode);
            queue.enqueue(x.rightNode);
        }
        return keys;
    }






    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.leftNode, min, x.key) && isBST(x.rightNode, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() { return isSizeConsistent(root); }
    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.N != size(x.leftNode) + size(x.rightNode) + 1) return false;
        return isSizeConsistent(x.leftNode) && isSizeConsistent(x.rightNode);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }



}

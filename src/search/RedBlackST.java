package search;

import java.util.NoSuchElementException;

/**
 * Created by 顾文涛 on 2017/2/26.
 * TwoThreeST的实现！
 *
 * TwoThreeST的红链接将两个2-节点连接起来，来表示一个2-3树的3-节点
 * 黑链接表示2-3树的2-节点
 * 1.红链接均为左链接
 * 2.没有任何一个节点同时和两条红链接相连
 * 3.该树是完美黑色平衡，即任意空链接到根节点的路径上的黑链接数量相同
 */
public class RedBlackST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BALCK = false;

    private Node root;


    private class Node{
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;

        Node(Key key,Value value,int N,boolean color){
            this.key=key;
            this.value=value;
            this.N=N;
            this.color=color;//父节点指向他的链接颜色
        }
    }

    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    //旋转操作：改变红链接的指向
    //将红色的右链改为红色的左链
    private Node rotateLeft(Node target){
        Node x = target.right;//目标节点右边为红链接（即节点左边为两树的中间数），将红链接指向的节点设为x
        target.right = x.left;//将红链接指向节点左边（两树的中间数）给黑链（左链）的右边
        x.left = target;//x.left原来为两树的中间数，现在指向目标（成为红链接）
        x.color = target.color;//x 获取目标节点的父节点的颜色
        target.color = RED;//target现在是 x的左链接指向的节点（为红）
        x.N = target.N;//复制目标节点的子节点数
        target.N = 1+size(target.left)+size(target.right);//重新计算目标节点的子节点数
        return x;//返回现在的父节点（现在为x ，原来为 target ）
    }

    //说的简单一点，就是将左边的（即红链指向的）节点的右子节点 赋给 原父节点的左边（原来左边是那个红链）
    //父节点要转移，
    private Node rotateRight(Node target){
        Node x = target.left;//P277
        target.left = x.right;
        x.right = target;
        x.color = target.color;
        target.color = RED;
        x.N = target.N;
        target.N = 1+size(target.left)+size(target.right);
        return x;
    }

    //颜色转换：用于将某节点的双红子链接变黑，且将其父节点由黑变红
    private void flipColors(Node target){
        target.color = RED;
        target.left.color = BALCK;
        target.right.color = BALCK;

    }

    /*红黑树插入情形
    1.向单个2-节点插入：如果小于根节点，直接插在左边，红链接在左。
    如果大于根节点，则会插在右边，这时红链接在右，
    调用root = rotateLeft(root);来更新根节点

    2.向一个3-节点（双键树）插入：
        1- .新键大于原树中的两个键：直接插在该双键数的右边（用红链接连接），将左右的红链接都变成黑色
        2- .新键小于原树中的两个键：新键会插在树最左边的空链接中（产生了2 条连续的红链），
        然后将上层的红链接右旋转，于是得到1- 的情况,将左右的红链接都变成黑色
        3- .新键介于两键之间，新键将会插入根节点的 左红链的 右边，于是又产生两条连续的红链，
        然后将下层的红链接左旋转，于是得到2- 的情况（然后将上层的红链右旋转，得到1- 情况，将两红链变黑）

     总结一下：
     一.右子节点为红，而左子节点为黑，进行左旋转
     二.左子节点为红，为它的左子节点还是红，则右旋转
     三.左右子节点都为红，则颜色转换
     */
    public void put(Key key,Value value){
        root = put(root,key,value);
        root.color = BALCK;
    }
    private  Node put(Node target,Key key,Value value){
        if(target == null){
            return new Node(key,value,1,RED);
        }
        int cmp = key.compareTo(target.key);
        if (cmp < 0){
            target.left = put(target.left,key,value);//往左边插入new Node(key,value,1,RED);
        }else if(cmp > 0){
            target.right = put(target.right,key,value);//往右边插入new Node(key,value,1,RED);
        }else  target.value=value;

        //完成平衡树的重要操作！
        if (isRed(target.right) && !isRed(target.left)) target=rotateLeft(target);//此为真，则下两步必为真
        if (isRed(target.left) && !isRed(target.left.left)) target=rotateRight(target);//此为真，则下步必为真
        if (isRed(target.left) && isRed(target.right)) flipColors(target);

        target.N = size(target.left) + size(target.right) + 1;
        return target;
    }

    /*删除最小值：要保证一下操作之一成立：
    * 1. 如果当前节点的左子节点不是2-节点
    * 2. 如果当前节点的左子节点是2-节点而他的兄弟节点不是2-节点，
    * 则将兄弟节点中的一个键给左子节点中（）
    * 3. 如果当前节点的左子节点和他的亲兄弟节点都是2-节点，
    * 将左子节点、父节点中的最小键和左子节点最近的兄弟节点合并，
    * 成为一个4-节点，使父节点有3-节点变成2-节点或者由4-节点变成3-节点
    */
    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) root.color = BALCK;
    }
    private Node deleteMin(Node target){
        if (target.left == null) return null;
        if (!isRed(target.left) && (!isRed(target.left.left)))
            target = moveRedLeft(target);
        target.left = deleteMin(target.left);
        return balance(target);

    }
    private Node moveRedLeft(Node target){
        //假设节点h 为红色，h.left和h.left.left都是黑色
        //将h.left 或 h.left的子节点之一变红
        flipColors(target);
        if (isRed(target.right.left)){
            target.right = rotateRight(target.right);
            target = rotateLeft(target);
        }
        return target;
    }

    /*删除最大值
     */
    public void deleteMax(){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BALCK;
    }
    private Node deleteMax(Node h){
        if (isRed(h.left))
            h=rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h.right);
        h.right = deleteMax(h.right);
        return balance(h);
    }
    private Node moveRedRight(Node h){
        //假设节点h为红色，h.right 和 h.right,left 都是黑色
        //将h.right 或者 h.right 的子节点之一设为红色
        flipColors(h);
        if (!isRed(h.left.left))
            h=rotateRight(h);
        return h;
    }

    /*  删除操作

     */
    public void delete(Key key){
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root,key);
        if (isEmpty()) root.color=BALCK;

    }
    private Node delete(Node h,Key key){
        if (key.compareTo(h.key) < 0) {
            if (! isRed(root.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left,key);

        }else{
            if (isRed(h.left))
                h=rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0){
                h.value = get(h.right,min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }else
                h.right = delete(h.right ,key);

        }
        return balance(h);
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.value;
        }
        return null;
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }
    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }
    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else                 return max(x.right);
    }



    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }



        public int size(){
            return size(root);
        }
        private int size(Node x) {
        if (x == null) return 0;
        return x.N;
        }
        public boolean isEmpty() {
            //return  n == 0;
            return root == null;
         }


}

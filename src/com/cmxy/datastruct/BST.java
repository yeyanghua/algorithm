package com.cmxy.datastruct;

//二分搜索树：
//条件：
//1、每个节点的键值大于左孩子
//2、每个节点的键值小于右孩子
//3、以左右孩子为根的子树仍为二分搜索树
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;//根节点
    private int count;//二分搜索树有多少节点

    public BST() {
        this.root = null;
        this.count = 0;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public void insert(Key key, Value value) {
        //传入root是因为需要从头开始比较
        insert(root, key, value);
    }

    //向以node为根的二叉搜索树中，插入节点(key,value)
    //返回插入新的节点的二叉搜索树的根节点
    private Node insert(Node node, Key key, Value value) {
        //递归的终止条件:root节点为空，即是空的二叉搜索树
        if (node == null) {
            count++;
            return new Node(key, value);
        }
        //如果要传入的value和根节点的key相同则更新，参考Map
        if (key.equals(node.key)) {
            node.value = value;
            //根据二叉搜索树的定义，如果大于节点的value肯定在右子树否则在左子树
        } else if (node.key.compareTo(key) > 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node;
    }

    //内部类：节点
    class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}

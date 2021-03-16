package com.gbgs.edu.application.programm.ds;

public class TreeNode {
    // Variable to keep the track
    // of visible nodes
    static int visibleNodeCount;
    Node root;

    // Function to perform the preorder traversal
    // for the given tree
    static void preOrder(Node node, int max) {
        if (node == null) {
            return;
        }
        if (node.data >= max) {
            visibleNodeCount++;
            max = Math.max(node.data, max);
        }
        preOrder(node.left, max);
        preOrder(node.right, max);
    }

    // Driver code
    public static void main(String[] args) {
        TreeNode tree = new TreeNode();

        /*
                5
               /  \
             3     10
            /  \   /
           20   21 1
*/

        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(10);

        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(21);

        tree.root.right.left = new Node(1);

        visibleNodeCount = 0;
        preOrder(tree.root, Integer.MIN_VALUE);

        System.out.println(visibleNodeCount);
    }
}

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}
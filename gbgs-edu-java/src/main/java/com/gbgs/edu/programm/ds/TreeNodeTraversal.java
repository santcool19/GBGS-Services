package com.gbgs.edu.programm.ds;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Requirement: Given a Binary Tree, one needs to traverse the tree such that:
 * Each node at a particular level is accessed before the nodes in the next level.
 * The nodes at a particular level is accessed in the reverse order, as in the previous level.
 * For example: One of the possible outcomes of such a traversal on the tree below could be
 * A C B D E F G K J I H
 */
public class TreeNodeTraversal {
    static int index;
    Node rootNode;

    public static void main(String[] args) throws InterruptedException {
        TreeNodeTraversal treeNodeTraversal = new TreeNodeTraversal();

        //treeNodeTraversal.addNode(treeNodeTraversal);
        //treeNodeTraversal.printNodeTraversal(treeNodeTraversal.rootNode);
        //treeNodeTraversal.printNodeSeqTraversal(treeNodeTraversal.rootNode);
        //System.out.println(treeNodeTraversal.maxDepth(treeNodeTraversal.rootNode));
        Node rootNode = null;
        rootNode = addNode(rootNode, 10, true);
        rootNode = addNode(rootNode, 5, true);
        rootNode = addNode(rootNode, 20, true);
        rootNode = addNode(rootNode, 3, true);
        rootNode = addNode(rootNode, 8, true);
        rootNode = addNode(rootNode, 7, true);

        System.out.println(treeNodeTraversal.maxDepth(rootNode));
    }

    private static Node addNode(TreeNodeTraversal treeNodeTraversal) {
        treeNodeTraversal.rootNode = new Node("A");
        treeNodeTraversal.rootNode.leftChild = new Node("B");
        treeNodeTraversal.rootNode.rightChild = new Node("C");
        treeNodeTraversal.rootNode.leftChild.leftChild = new Node("D");
        treeNodeTraversal.rootNode.leftChild.rightChild = new Node("E");
        treeNodeTraversal.rootNode.rightChild.leftChild = new Node("F");
        treeNodeTraversal.rootNode.rightChild.rightChild = new Node("G");
        treeNodeTraversal.rootNode.rightChild.rightChild.rightChild = new Node("K");
        treeNodeTraversal.rootNode.rightChild.rightChild.leftChild = new Node("J");
        treeNodeTraversal.rootNode.rightChild.leftChild.rightChild = new Node("I");
        treeNodeTraversal.rootNode.rightChild.leftChild.leftChild = new Node("H");
        return treeNodeTraversal.rootNode;
    }

    private static Node addNode(Node rootNode, int i, boolean isRootNode) {
        if (rootNode == null) {
            return new Node(i);
        } else {
            if (i > rootNode.getData()) {
                Node nodeToAdd = addNode(rootNode.getRightChild(), i, isRootNode);
                rootNode.setRightChild(nodeToAdd);

            } else {
                Node nodeToAdd = addNode(rootNode.getLeftChild(), i, isRootNode);
                rootNode.setLeftChild(nodeToAdd);
            }
        }
        return rootNode;
    }

    private static void printTreePreOrder(Node rootNode) {
        if (rootNode == null)
            return;

        System.out.print(rootNode.getData() + " ");
        printTreePreOrder(rootNode.getLeftChild());
        printTreePreOrder(rootNode.getRightChild());
    }

    private static void printTreePostOrder(Node rootNode) {
        if (rootNode == null)
            return;

        printTreePreOrder(rootNode.getLeftChild());
        printTreePreOrder(rootNode.getRightChild());
        System.out.print(rootNode.getData() + " ");
    }

    //Serialize Binary Tree
    private static String serializeBinaryTree(Node rootNode) {
        if (rootNode == null) {
            return "null,";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(rootNode.getData());
        sb.append(",");

        sb.append(serializeBinaryTree(rootNode.getLeftChild()));
        sb.append(serializeBinaryTree(rootNode.getRightChild()));
        return sb.toString();
    }

    //Deserialize Binary Tree
    public static Node deserializeBinaryTree(String data) {
        String[] temp = data.split(",");
        return deserializeUsingStaticCounter(temp);
    }

    private static Node deserializeUsingStaticCounter(String[] data) {
        if (index > data.length || data[index].equals("null")) {
            index++;
            return null;
        }

        Node node = new Node(Integer.parseInt(data[index++]));
        node.setLeftChild(deserializeUsingStaticCounter(data));
        node.setRightChild(deserializeUsingStaticCounter(data));

        return node;
    }

    int maxDepth(Node node) {
        int lDepth = 0;
        int rDepth = 0;

        if (node == null)
            return 0;

        lDepth = maxDepth(node.leftChild);
        rDepth = maxDepth(node.rightChild);

        if (lDepth > rDepth)
            return (lDepth + 1);
        else
            return (rDepth + 1);
    }

    //Inorder Printing.
    private void printTreeInOrder(Node rootNode) {
        if (rootNode == null)
            return;

        printTreeInOrder(rootNode.getLeftChild());
        System.out.print(rootNode.getData() + " ");
        printTreeInOrder(rootNode.getRightChild());
    }

    void printNodeSeqTraversal(Node rootNode) throws InterruptedException {
        {
            if (rootNode == null) {
                return;
            } else {
                this.rootNode = rootNode;
            }

            BlockingQueue<Node> currentLevel = new ArrayBlockingQueue<>(20);
            BlockingQueue<Node> nextLevel = new ArrayBlockingQueue<>(20);

            currentLevel.put(rootNode);

            while (!currentLevel.isEmpty()) {
                Node node = currentLevel.take();

                System.out.print(node.nodeData + " ");

                if (node.leftChild != null) {
                    currentLevel.put(node.leftChild);
                }

                if (node.rightChild != null) {
                    currentLevel.put(node.rightChild);
                }
            }
        }
    }

    void printNodeTraversal(Node rootNode) {
        if (rootNode == null) {
            return;
        } else {
            this.rootNode = rootNode;
        }
        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();

        currentLevel.push(rootNode);
        boolean traversalFlag = true;

        while (!currentLevel.isEmpty()) {
            Node node = currentLevel.pop();

            System.out.print(node.nodeData + " ");

            if (traversalFlag) {
                if (node.leftChild != null) {
                    nextLevel.push(node.leftChild);
                }

                if (node.rightChild != null) {
                    nextLevel.push(node.rightChild);
                }
            } else {
                if (node.rightChild != null) {
                    nextLevel.push(node.rightChild);
                }

                if (node.leftChild != null) {
                    nextLevel.push(node.leftChild);
                }
            }


            if (currentLevel.isEmpty()) {
                traversalFlag = !traversalFlag;
                Stack<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }
}

class Node {
    String nodeData;
    int data;
    Node leftChild;
    Node rightChild;

    public Node(int data) {
        this.data = data;
    }

    Node(String nodeData) {
        this.nodeData = nodeData;
    }

    static int minCntFoun(List<Integer> locations) {
        int gardenLength = locations.size();
        int[] dp = new int[gardenLength];
        for (int i = 0; i < gardenLength; i++) {
            dp[i] = -1;
        }
        int idxLeft;
        int idxRight;
        for (int i = 0; i < gardenLength; i++) {
            idxLeft = Math.max(i - locations.indexOf(i), 0);
            idxRight = Math.min(i + (locations.indexOf(i) + 1), gardenLength);
            dp[idxLeft] = Math.max(dp[idxLeft],
                    idxRight);
        }
        int cntfount = 1;
        int idxNext = 0;
        idxRight = dp[0];

        for (int i = 0; i < gardenLength; i++) {
            idxNext = Math.max(idxNext, dp[i]);

            if (i == idxRight) {
                cntfount++;
                idxRight = idxNext;
            }
        }
        return cntfount;
    }

    public String getNodeData() {
        return nodeData;
    }

    public void setNodeData(String nodeData) {
        this.nodeData = nodeData;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

}

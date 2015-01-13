package base;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * This class contains code implementing binary trees
 *
 * Created by wireed on 9/20/2014.
 */
public class Trees {
    private boolean verbose = true;
    private int size = 0;
    private Random rnd = new Random();

    Trees(boolean vbose) {
        verbose = vbose;
    }

    private class Node {
        int value;
        Node left;
        Node right;
    }

    private Node root = null;

    /**
     * This method inserts a value in the correct location of the tree
     * @param value
     */
    public void insertSorted(int value) {
        root = insertSorted(root, value);
    }

    private Node insertSorted(Node rt, int value) {
        if (verbose) {
            if (size == 0)
                System.out.print("     insert: ");
        }
        size++;
        if (rt == null) {
            rt = new Node();
            rt.value = value;
            rt.left = null;
            rt.right = null;
            if (verbose)
                System.out.print(value + ", ");
            return rt;
        }
        if (value < rt.value) {
            if (rt.left == null) {
                Node leftChild = new Node();
                leftChild.value = value;
                rt.left = leftChild;
                if (verbose)
                    System.out.print(value + ", ");
            } else
                insertSorted(rt.left, value);
        } else {
            if (rt.right == null) {
                Node rightChild = new Node();
                rightChild.value = value;
                rt.right = rightChild;
                if (verbose)
                    System.out.print(value + ", ");
            } else
                insertSorted(rt.right, value);
        }
        return rt;
    }

    /**
     * This method deletes a value from the tree
     * @param value
     */
    public void delete(int value) {
        // TODO: need to implement this method
    }

    /**
     * This method returns true if a value exists in a tree, false if it doesn't
     *
     * @param value
     * @return
     */
    public boolean find(int value) {
        return find(root, value);
    }

    private boolean find(Node rt, int value) {
        boolean found = false;
        if (rt == null)
            found = false;
        else if (rt.value == value)
            found = true;
        else if (value < rt.value)
            if (rt.left != null)
                found = find(rt.left, value);
            else
                found = false;
        else if (value > rt.value)
            if (rt.right != null)
                found = find(rt.right, value);
            else
                found = false;
        return found;
    }

    public void printInOrder() {
        if (verbose)
            System.out.print("     print inorder: ");
        printInOrder(root);
        if (verbose)
            System.out.println(" ");
    }

    private void printInOrder(Node rt) {
        if (rt != null) {
            printInOrder(rt.left);
            if (verbose)
                System.out.print(rt.value + ", ");
            printInOrder(rt.right);
        }
    }

    public void printPreOrder() {
        if (verbose)
            System.out.print("     print preorder: ");
        printPreOrder(root);
        if (verbose)
            System.out.println(" ");
    }

    private void printPreOrder(Node rt) {
        if (rt != null) {
            if (verbose)
                System.out.print(rt.value + ", ");
            printPreOrder(rt.left);
            printPreOrder(rt.right);
        }
    }

    public void printPostOrder() {
        if (verbose)
            System.out.print("     print postorder: ");
        printPostOrder(root);
        if (verbose)
            System.out.println(" ");
    }

    private void printPostOrder(Node rt) {
        if (rt != null) {
            printPostOrder(rt.left);
            printPostOrder(rt.right);
            if (verbose)
                System.out.print(rt.value + ", ");
        }
    }

    public void printLevelOrder() {
        if (verbose)
            System.out.print("     print levelorder: ");
        printLevelOrder(root);
        if (verbose)
            System.out.println(" ");
    }

    private void printLevelOrder(Node rt) {
        Queue<Node> level  = new LinkedList<Node>();
        level.add(rt);
        int currentLevel = 1;
        int nextLevel = 0;
        System.out.println(" ");
        while(!level.isEmpty()){
            Node node = level.poll();
            if (verbose) {
                System.out.print(node.value + ", ");
                currentLevel--;
                if (currentLevel == 0) {
                    System.out.println(" ");
                }
            }
            if (node.left!= null) {
                level.add(node.left);
                nextLevel++;
            }
            if (node.right!= null) {
                level.add(node.right);
                nextLevel++;
            }
            if (currentLevel == 0) {
                currentLevel = nextLevel;
                nextLevel = 0;
            }
        }
    }

    /**
     * This method sets up the data to test all the other methods
     */
    public void testTrees() {
        if (verbose)
            System.out.println("Trees:");
        int count = rnd.nextInt(10) + 5;
        int value = 0;
        //for (int i = 0; i < count; i++) {
            //value = rnd.nextInt(100);
        //    insertSorted(value);
        //}
        insertSorted(20);
        insertSorted(10);
        insertSorted(30);
        insertSorted(5);
        insertSorted(100);
        insertSorted(50);
        insertSorted(35);
        boolean found = find(value);
        if (verbose) {
            System.out.println(" ");
            System.out.println("     find: " + value + "  result: " + found);
        }
        printInOrder();
        printPreOrder();
        printPostOrder();
        printLevelOrder();
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
package base;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * This class contains code implementing Stacks
 *
 * Created by wireed on 10/4/2014.
 */
public class Stacks {
    private boolean verbose = true;
    private Random rnd = new Random();

    Stacks(boolean vbose) {
        verbose = vbose;
    }

    private class Node {
        int value;
        Node next;
    }

    private Node stack;

    /**
     * This method adds a value to the top of the stack
     *
     * @param value
     */
    public void push(int value) {
        if (verbose) {
            System.out.print("     push: " + value);
        }
        Node n = new Node();
        n.value = value;
        n.next = stack;
        stack = n;
    }

    /**
     * This method returns the value on the top of the stack and removes it
     *
     * @return
     * @throws EmptyStackException
     */
    public int pop() throws EmptyStackException {
        int value = 0;
        if (stack == null)
            throw new EmptyStackException();
        else
            value = stack.value;

        stack = stack.next;
        if (verbose)
            System.out.print("     pop: " + value);
        return value;
    }

    /**
     * This method removes all values from the stack
     */
    public void popTheWholeStack() {
        int popped = 0;
        while (stack != null) {
            popped = pop();
        }
    }

    /**
     * This method sets up the data to call all the other methods
     */
    public void testStacks() {
        if (verbose)
            System.out.println("Stacks: ");
        for (int i = 0; i < 5; i++)
            push(rnd.nextInt(100));
        popTheWholeStack();
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
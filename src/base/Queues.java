package base;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * This class contains code implementing Queues
 *
 * Created by wireed on 9/27/2014.
 */
public class Queues {
    private boolean verbose = true;
    private Random rnd = new Random();

    Queues(boolean vbose) {
        verbose = vbose;
    }

    private class Node {
        int value;
        Node next;
        Node previous;
    }

    private Node front = null;
    private Node rear = null;
    private int size = 0;

    /**
     * This method adds a value to the end of the queue
     *
     * @param value
     */
    public void enqueue(int value) {
        if (rear == null) {
            rear = new Node();
            rear.value = value;
            rear.next = null;
            rear.previous = null;
            front = rear;
        }
        else {
            Node r = new Node();
            r.value = value;
            r.next = rear;
            r.previous = null;
            rear.previous = r;
            rear = r;
        }
        if (verbose) {
            if (size == 0)
                if (verbose)
                System.out.print("     enqueue: ");
            if (verbose)
                System.out.print(value + ", ");
        }
        size++;
    }

    /**
     * This method returns the value at the front of the queue and removes it
     *
     * @return
     * @throws EmptyStackException
     */
    public int dequeue() throws EmptyStackException {
        if (front == null)
            throw new EmptyStackException();
        int value = front.value;
        size--;
        front = front.previous;
        if (front != null)
            front.next = null;
        if (verbose)
            System.out.print(value + ", ");
        return value;
    }

    /**
     * This method returns the value at the front of the queue without removing it
     *
     * @return
     * @throws EmptyStackException
     */
    public int front() throws EmptyStackException {
        if (front == null)
            throw new EmptyStackException();
        return front.value;
    }

    /**
     * This method returns the value at the rear of the queue without removing it
     *
     * @return
     * @throws EmptyStackException
     */
    public int rear() throws EmptyStackException {
        if (rear == null)
            throw new EmptyStackException();
        return rear.value;
    }

    /**
     * This method returns the size of the queue
     *
     * @return
     */
    public int queueSize() {
        return size;
    }

    /**
     * This method returns 1 if queue has elements, 0 if it does not
     *
     * @return
     */
    public int isEmptyQueue() {
        if (size == 0)
            return 1;
        else
            return 0;
    }

    /**
     * This method removes all elements from the queue
     *
     */
    public void dequeueAll() {
        int d = 0;
        if (verbose)
            System.out.print("     dequeue: ");
        while (size > 0)
            dequeue();
    }

    /**
     * This method returns the larges value from windowSize consecutive elements
     *
     * @param windowSize
     * @return
     * @throws EmptyStackException
     */
    public int largestWindow(int windowSize) throws EmptyStackException {
        if (windowSize > size && size == 0)
            throw new EmptyStackException();
        int sumSize = size - windowSize + 1;
        int[] sums = new int[sumSize];
        Node pos = rear;
        for (int i = 0; i < sumSize; i++) {
            Node extract = pos;
            for (int j = 0; j < windowSize; j++) {
                sums[i] += extract.value;
                extract = extract.next;
            }
            pos = pos.next;
        }
        int largest = -32767;
        for (int i = 0; i < sumSize; i++)
            if (sums[i] > largest)
                largest = sums[i];
        return largest;
    }

    /**
     * This method sets up the data to call test the other methods
     */
    public void testQueues() {
        if (verbose)
            System.out.println("Queues:");
        int count = rnd.nextInt(10) + 1;
        for (int i = 0; i < count; i++)
            enqueue(rnd.nextInt(100));
        if (verbose) {
            System.out.println(" ");
            System.out.println("     front: " + front() + ", rear: " + rear() + ", size: " + queueSize() + ", isempty: " + isEmptyQueue());
        }
        int window = rnd.nextInt(count) + 1;
        if (verbose)
            System.out.println("     find largest sum for a window of " + window + ": " + largestWindow(window));
        dequeueAll();
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
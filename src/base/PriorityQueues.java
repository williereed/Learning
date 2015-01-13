package base;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * This class contains code implementing Priority Queues
 *
 * Created by wireed on 9/13/2014.
 */
public class PriorityQueues {
    private boolean verbose = true;
    private Random rnd = new Random();

    PriorityQueues(boolean vbose) {
        verbose = vbose;
    }

    private class Node {
        int priority;
        int value;
        Node next;
        Node previous;
    }

    private Node front = null;
    private Node rear = null;
    private int size = 0;

    /**
     * This method adds a value and its priority to the queue
     *
     * @param priority
     * @param value
     */
    public void enqueue(int priority, int value) {
        if (rear == null) {
            rear = new Node();
            rear.priority = priority;
            rear.value = value;
            rear.next = null;
            rear.previous = null;
            front = rear;
        }
        else {
            int position = 0;
            Node nn = new Node();
            nn.priority = priority;
            nn.value = value;
            Node current = rear;

            // get current to the correct location for insert
            while (priority < current.priority && current.next != null) {
                current = current.next;
                position++;
            }
            // determine if insert is in front of, or behind current
            if (priority < current.priority) {
                // insert is in front of current
                current.next = nn;
                nn.previous = current;
                front = nn;
            }
            else {
                // insert after
                if (current.previous != null) {
                    current.previous.next = nn;
                    nn.previous = current.previous;
                }
                current.previous = nn;
                nn.next = current;
                if (position == 0)
                    rear = nn;
            }
        }
        if (verbose) {
            if (size == 0)
                if (verbose)
                    System.out.print("     enqueue: ");
            if (verbose)
                System.out.print("(p" + priority + ",v" + value + "), ");
        }
        size++;
    }

    /**
     * This method returns a value from the front of the queue and removes it
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
     * This method prints all elements in the queue from rear to front
     */
    public void printQueueRearToFront() {
        if (verbose)
            System.out.print("     print rear to front: ");
        Node current = rear;
        while (current != null) {
            if (verbose)
                System.out.print("(p" + current.priority + ",v" + current.value + "), ");
            current = current.next;
        }
        if (verbose)
            System.out.println(" ");
    }

    public int front() throws EmptyStackException {
        if (front == null)
            throw new EmptyStackException();
        return front.value;
    }

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
     * This method returns 1 if the queue is empty, 0 if it is not
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
     * This method removes all the elements from the queue
     */
    public void dequeueAll() {
        int d = 0;
        if (verbose)
            System.out.print("     dequeue: ");
        while (size > 0)
            dequeue();
    }

    /**
     * This method returns the largest sum of windowSize number of consecutive elements
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
     * This method sets up the data to test all other methods
     */
    public void testPriorityQueues() {
        if (verbose)
            System.out.println("Priority Queues:");
        int priority = 2;
        int count = rnd.nextInt(7) + 3;
        //for (int i = 0; i < count; i++) {
        //    enqueue(priority++, rnd.nextInt(100));
        //    if (priority > 3)
        //        priority = 1;
        //}
        enqueue(2, 50);
        enqueue(3, 90);
        enqueue(1, 31);
        enqueue(4, 0);
        enqueue(0, 4);

        int front = front();
        int rear = rear();
        int qsize = queueSize();
        int empty = isEmptyQueue();
        if (verbose) {
            System.out.println(" ");
            System.out.println("     front: " + front + ", rear: " + rear + ", size: " + qsize + ", isempty: " + empty);
        }
        int window = rnd.nextInt(count) + 1;
        int largestWindow = largestWindow(window);
        if (verbose)
            System.out.println("     find largest sum for a window of " + window + ": " + largestWindow);
        printQueueRearToFront();
        dequeueAll();
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
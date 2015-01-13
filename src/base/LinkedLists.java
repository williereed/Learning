package base;

import java.util.EmptyStackException;

/**
 * This class contains code implementing Linked Lists
 *
 * Created by wireed on 10/11/2014.
 */
public class LinkedLists {
    private boolean verbose = true;

    LinkedLists(boolean vbose) {
        verbose = vbose;
    }

    /**
     * This method reverses a linked link
     */
    public void reverseList() {
        Node current = head;
        Node reversed = null;
        reversed = addHead(reversed, head.value);
        while (current.next != null) {
            reversed = addHead(reversed, current.next.value);
            current = current.next;
        }
        head = reversed;
    }

    /**
     * This method adds a value to a sorted list in the correct sorted position
     *
     * @param value int - the value to add to the linked list
     */
    public void addSorted(int value) {
        Node n = new Node();
        n.value = value;
        n.next = null;
        if (head == null)
            head = n;
        else {
            Node current = head;
            if (current.value > value) {
                n.next = current;
                head = n;
            } else {
                while (current.next != null && current.next.value < value) {
                    current = current.next;
                }
                n.next = current.next;
                current.next = n;
            }
        }
    }

    /**
     * This method deletes a value from a linked list
     *
     * @param value int - the value delete from the linked list
     * @return int - 1 if successful in deleting the value, 0 if not successful
     * @throws EmptyStackException
     */
    public int deleteValue(int value) throws EmptyStackException {
        if (head == null)
            throw new EmptyStackException();
        if (head.value == value) {
            head = head.next;
            return 1;
        }
        else {
            Node current = head;
            if (current.next == null)
                return 0;
            while (current.next != null)
                if (current.next.value != value) {
                    current.next = current.next.next;
                    return 1;
                }
        }
        return 0;
    }

    /**
     * This method removes the element at the head of the linked list
     *
     * @throws EmptyStackException
     */
    public void deleteHead() throws EmptyStackException {
        if (head == null)
            throw new EmptyStackException();
        head = head.next;
    }

    /**
     * This method removes the element at the end of the linked list
     *
     * @throws EmptyStackException
     */
    public void deleteTail() throws EmptyStackException {
        if (head == null)
            throw new EmptyStackException();
        if (head.next == null)
            head = null;
        else {
            Node current = head;
            while (current.next.next != null)
                current = current.next;
            current.next = null;
        }
    }

    /**
     * This method adds a linked list called Tail to a linked list called Head
     *
     * @throws EmptyStackException
     */
    public void addTailToHead() throws EmptyStackException {
        if (tail == null)
            throw new EmptyStackException();
        if (head == null)
            throw new EmptyStackException();

        Node current = head;
        while (current.next != null)
            current = current.next;
        current.next = tail;
    }

    /**
     * This method adds a value to a linked list called Tail
     *
     * @param value
     */
    public void growTail(int value) {
        tail = addTail(tail, value);
    }

    /**
     * This method adds a value to a linked list called Head
     *
     * @param value
     */
    public void addTail(int value) {
        head = addTail(head, value);
    }

    /**
     * This method adds a value to linked list
     *
     * @param h
     * @param value
     * @return
     */
    private Node addTail(Node h, int value) {
        Node n = new Node();
        n.value = value;
        if (h == null)
            h = n;
        else {
            Node current = h;
            while (current.next != null)
                current = current.next;
            current.next = n;
        }
        return h;
    }

    private class Node {
        int value;
        Node next;
    }

    private Node head = null;
    private Node tail = null;

    public void addHead(int value) {
        head = addHead(head, value);
    }

    private Node addHead(Node h, int value) {
        Node n = new Node();
        n.value = value;
        n.next = h;
        return n;
    }

    /**
     * This method prints the elements in order
     */
    public void printList() {
        printList(true);
    }

    /**
     * This method prints the elements in either order
     * @param inOrder
     */
    public void printList(boolean inOrder) {
        printList(head, inOrder);
    }

    /**
     * This method prints the elements, front to rear, or rear to front
     *
     * @param h
     * @param inOrder
     */
    private void printList(Node h, boolean inOrder) {
        if (inOrder)
            if (verbose)
                System.out.print(h.value + ", ");
        if (h.next != null)
            printList(h.next, inOrder);
        if (!inOrder)
            if (verbose)
                System.out.print(h.value + ", ");
    }

    /**
     * This method sets up the data to test all the other methods
     */
    public void testLinkedLists() {
        if (verbose)
            System.out.println("Linked Lists:");
        addHead(4);
        addTail(6);
        addHead(2);
        growTail(8);
        growTail(10);
        growTail(12);
        addTailToHead();
        if (verbose)
            System.out.print("     two linked lists evens: ");
        printList();
        if (verbose)
            System.out.println(" ");
        addSorted(5);
        addSorted(1);
        addSorted(13);
        if (verbose)
            System.out.print("     with odds insert sortd: ");
        printList();
        if (verbose)
            System.out.println(" ");
        deleteHead();
        deleteTail();
        deleteValue(10);
        if (verbose)
            System.out.print("     head, tail, 10 deleted: ");
        printList();
        if (verbose) {
            System.out.println(" ");
            System.out.print("     list after reversed   : ");
        }
        reverseList();
        printList(true);
        if (verbose) {
            System.out.println("");
            System.out.print("     print in reversed     : ");
        }
        printList(false);
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}

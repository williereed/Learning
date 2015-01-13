package base;

public class Main {
    private static boolean verbose = true;

    public static void exercisesDemo() {
        Exercises e = new Exercises(verbose);
        e.testExercises();
    }

    public static void gameDemo() {
        Game g = new Game(verbose);
        g.testGame();
    }

    public static void recusionDemo() {
        Recursion r = new Recursion(verbose);
        r.testRecursion();
    }

    public static void linkedListsDemo() {
        LinkedLists ll = new LinkedLists(verbose);
        ll.testLinkedLists();
    }

    public static void stacksDemo() {
        Stacks s = new Stacks(verbose);
        s.testStacks();
    }

    public static void queuesDemo() {
        Queues q = new Queues(verbose);
        q.testQueues();
    }

    public static void treesDemo() {
        Trees t = new Trees(verbose);
        t.testTrees();
    }

    public static void priorityQueueDemo() {
        PriorityQueues pq = new PriorityQueues(verbose);
        pq.testPriorityQueues();
    }

    public static void sortDemo() {
        Sorts s = new Sorts(verbose);
        s.testSorts();
    }

    public static void main(String[] args) {
        exercisesDemo();
        gameDemo();
        recusionDemo();
        linkedListsDemo();
        stacksDemo();
        queuesDemo();
        treesDemo();
        priorityQueueDemo();
        sortDemo();
    }
}

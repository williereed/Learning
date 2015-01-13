package base;

/**
 * This class contains code implementing Recursion
 *
 * Created by wireed on 9/18/2014.
 */
public class Recursion {
    private boolean verbose = true;

    Recursion(boolean vbose) {
        verbose = vbose;
    }

    /**
     * This method returns the Fibonacci value for number
     *
     * @param number
     * @return int - the Fibonacci of number
     */
    public int fibonacciNumbers(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }

        return fibonacciNumbers(number - 1) + fibonacciNumbers(number - 2); //tail recursion
    }

    /**
     * This method uses recursion to print the numbers 0 to value
     *
     * @param value
     */
    public void printRecursively(int value) {
        if (verbose)
            System.out.print("     recurse 0 to " + value + ": ");
        printRecursively(0, value, true);
        if (verbose)
            System.out.println(" ");
    }

    /**
     * This method is the entry point for the recurse method to print a list of numbers
     *
     * @param value - the terminating number
     * @param increasing - boolean true will print 0 to value, false will print value to 0
     */
    public void printRecursively(int value, boolean increasing) {
        if (verbose)
            if (increasing)
                System.out.print("     recurse 0 to " + value + ": ");
            else
                System.out.print("     recurse " + value + " to 0: ");
        printRecursively(0, value, increasing);
        if (verbose)
            System.out.println(" ");
    }

    /**
     * This method performs the recursion to print a list of numbers
     *
     * @param iteration
     * @param value
     * @param increasing
     */
    private void printRecursively(int iteration, int value, boolean increasing) {
        if (increasing)
            if (iteration > value)
                return;
            else {
                if (verbose)
                    System.out.print(iteration + ", ");
            }
        else {
            if (value < 0)
                return;
            else {
                if (verbose)
                    System.out.print(value + ", ");
                value--;
            }
        }
        iteration++;
        printRecursively(iteration, value, increasing);
    }

    /**
     * This method initializes the data for printing recursive methods
     *
     */
    public void testRecursion() {
        if (verbose) {
            System.out.println("Recursion:");
        }
        printRecursively(10);
        printRecursively(10, false);

        int number = 10;
        if (verbose)
            System.out.print("     fibonacci " + number + " numbers: ");
        for (int i = 1; i <= number; i++)
            if (verbose)
                System.out.print(fibonacciNumbers(i) + " ");
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }

    }
}
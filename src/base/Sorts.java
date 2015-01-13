package base;

import java.util.Arrays;

/**
 * This class contains code implementing Sort algorithms
 *
 * Created by wireed on 9/6/2014.
 */
public class Sorts {
    private boolean verbose = true;

    Sorts(boolean vbose) {
        verbose = vbose;
    }

    /**
     * This method implements the Bubble sort
     *
     * @param A
     * @return
     */
    public int[] bubbleSort(int[] A) {
        int temp, swapped = 1;
        for (int pass = A.length; pass >= 0 && swapped == 1; pass--) {
            swapped = 0;
            for (int i = 0; i < pass - 1; i++)
                if (A[i] > A[i + 1]) {
                    temp = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = temp;
                    swapped = 1;
                }
        }
        return A;
    }

    /**
     * This method implements the Selection sort
     *
     * @param A
     * @return
     */
    public int[] selectionSort(int[] A) {
        int min, temp;
        for (int i = 0; i < A.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < A.length; j++)
                if (A[j] < A[min])
                    min = j;
            temp = A[min];
            A[min] = A[i];
            A[i] = temp;
        }
        return A;
    }

    /**
     * This method implements the Insertion sort
     *
     * @param A
     * @return
     */
    public int[] insertionSort(int[] A) {
        int i, j, v;
        for (i = 1; i <= A.length - 1; i++) {
            v = A[i];
            j = i;
            while ((j >= 1) && (A[j - 1] > v)) {
                A[j] = A[j - 1];
                j--;
            }
            A[j] = v;
        }
        return A;
    }

    /**
     * This method implements the Shell sort
     *
     * @param A
     * @return
     */
    public int[] shellSort(int[] A) {
        int inner, outer, len, temp;
        len = A.length;
        //find initial value of h
        int h = 1;
        while (h <= len / 3)
            h = h * 3 + 1; // (1, 4, 13, 40, 121, ...)

        while (h > 0) // decreasing h, until h=1
        {
            // h-sort the file
            for (outer = h; outer < len; outer++) {
                temp = A[outer];
                inner = outer;
                // one subpass (eg 0, 4, 8)
                while (inner > h - 1 && A[inner - h] >= temp) {
                    A[inner] = A[inner - h];
                    inner -= h;
                }
                A[inner] = temp;
            }
            h = (h - 1) / 3; // decrease h
        }
        return A;
    }

    /**
     * This method is the entry point for the recursive Merge sort
     *
     * @param A
     * @return
     */
    public int[] mergeSort(int[] A) {
        if (A.length > 1) {
            int q = A.length/2;

            int[] leftArray = Arrays.copyOfRange(A, 0, q);
            int[] rightArray = Arrays.copyOfRange(A, q, A.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(A,leftArray,rightArray);
        }
        return A;
    }

    /**
     * This method implements recursion to perform Merge sort
     *
     * @param a
     * @param l
     * @param r
     */
    public void merge(int[] a, int[] l, int[] r) {
        int totElem = l.length + r.length;
        //int[] a = new int[totElem];
        int i,li,ri;
        i = li = ri = 0;
        while ( i < totElem) {
            if ((li < l.length) && (ri<r.length)) {
                if (l[li] < r[ri]) {
                    a[i] = l[li];
                    i++;
                    li++;
                }
                else {
                    a[i] = r[ri];
                    i++;
                    ri++;
                }
            }
            else {
                if (li >= l.length) {
                    while (ri < r.length) {
                        a[i] = r[ri];
                        i++;
                        ri++;
                    }
                }
                if (ri >= r.length) {
                    while (li < l.length) {
                        a[i] = l[li];
                        li++;
                        i++;
                    }
                }
            }
        }
        //return a;
    }

    /**
     * This method implements the Quick sort
     *
     * @param A
     * @param low
     * @param n
     * @return
     */
    public int[] quickSort(int[] A, int low, int n) {
        int lo = low;
        int hi = n;
        if (lo >= n) {
            return A;
        }
        int mid = A[(lo + hi) / 2];
        while (lo < hi) {
            while (lo<hi && A[lo] < mid) {
                lo++;
            }
            while (lo<hi && A[hi] > mid) {
                hi--;
            }
            if (lo < hi) {
                int T = A[lo];
                A[lo] = A[hi];
                A[hi] = T;
            }
        }
        if (hi < lo) {
            int T = hi;
            hi = lo;
            lo = T;
        }
        quickSort(A, low, lo);
        quickSort(A, lo == low ? lo+1 : lo, n);
        return A;
    }

    /**
     * This method sets up the data to test all the other methods
     */
    public void testSorts() {
        int[] original = {8, 7, 6, 5, 4, 9, 3, 2, 1, 0};
        int[] original2 = {5, 1, 7, 9, 2};
        if (verbose) {
            System.out.println("Sorting");
            System.out.print("     original: ");
            for (int i = 0; i < original.length; i++)
                System.out.print(original[i] + ", ");
            System.out.println(" ");
        }
        int[] bubble = new int[original.length];
        bubble = bubbleSort(original.clone());
        int[] selection = new int[original.length];
        selection = selectionSort(original.clone());
        int[] insertion = new int[original.length];
        insertion = insertionSort(original.clone());
        int[] shell = new int[original.length];
        shell = shellSort(original.clone());
        int[] merge = new int[original.length];
        merge = mergeSort(original.clone());
        int[] quick = new int[original2.length];
        quick = quickSort(original2.clone(), 0, quick.length - 1);

        if (verbose) {
            System.out.print("     bubble  : ");
            for (int i = 0; i < bubble.length; i++)
                System.out.print(bubble[i] + ", ");
            System.out.println(" ");

            System.out.print("     select  : ");
            for (int i = 0; i < selection.length; i++)
                System.out.print(selection[i] + ", ");
            System.out.println(" ");

            System.out.print("     insert  : ");
            for (int i = 0; i < insertion.length; i++)
                System.out.print(insertion[i] + ", ");
            System.out.println(" ");

            System.out.print("     shell   : ");
            for (int i = 0; i < shell.length; i++)
                System.out.print(shell[i] + ", ");
            System.out.println(" ");

            System.out.print("     merge   : ");
            for (int i = 0; i < merge.length; i++)
                System.out.print(merge[i] + ", ");
            System.out.println(" ");

            System.out.print("     quick   : ");
            for (int i = 0; i < quick.length; i++)
                System.out.print(quick[i] + ", ");
            System.out.println(" ");

            System.out.println(" ");
        }
    }
}

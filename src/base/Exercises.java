package base;

import java.util.*;

/**
 * The purpose of this class is to demonstrate solutions to coding exercises
 *
 * Created by wireed on 10/01/2014.
 */
public class Exercises {
    private boolean verbose = true;

    public Exercises(boolean vbose) {
        verbose = vbose;
    }

    /**
     * This method determines if 2 characters are within distance value of each other
     * @param s
     * @param ch1
     * @param ch2
     * @param distance
     * @return
     */
    public boolean charsWithinDistance(String s, char ch1, char ch2, int distance) {
        if (s == null || s.length()== 0 || distance < 0)
            return false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch1) {
                for (int j = 0; j <= distance; j++) {
                    if (j + i < s.length()) {
                        if (s.charAt(j + i) == ch2) {
                            return true;
                        }
                    }
                    else
                        break;
                }
            }
        }
        return false;
    }

    /**
     * This method stores values in a HashMap, not adding duplicates
     *
     * @param myArray
     * @return
     */
    public int[] removeDuplicatesHash(int[] myArray) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        // find unique values, place in HashMap
        for (int i = 0; i < myArray.length; i++) {
            if (!hm.containsKey(myArray[i]))
                hm.put(myArray[i], 1);
        }
        // transfer values to array
        int[] duplicatesRemoved = new int[hm.size()];
        int i = 0;
        for (Integer k : hm.keySet()) {
            duplicatesRemoved[i++] = k;
        }
        return duplicatesRemoved;
    }

    /**
     * This method adds unique numbers to an int array
     *
     * @param myArray
     * @return
     */
    public int[] removeDuplicatesArray(int[] myArray) {
        int[] temp = new int[myArray.length];
        int countUnique = 0;
        for (int i = 0; i < myArray.length; i++) {

            if (countUnique == 0) {
                temp[i] = myArray[i];
                countUnique++;
            }
            else {
                int j = 0;
                boolean isUnique = true;
                while (j < countUnique && isUnique == true) {
                    if (myArray[i] == temp[j])
                        isUnique = false;
                    j++;
                }
                if (isUnique) {
                    temp[countUnique] = myArray[i];
                    countUnique++;
                }
            }
        }
        int[] uniqueNumbers = new int[countUnique];
        for (int i = 0; i < countUnique; i++)
            uniqueNumbers[i] = temp[i];
        return uniqueNumbers;
    }

    public String fizzBuzz(int num) {
        if (num % 15 == 0)
            return "FizzBuzz ";
        if (num % 5 == 0)
            return "Buzz ";
        if (num % 3 == 0)
            return "Fizz ";
        return num + " ";
    }


    /**
     * An Armstrong number is one equal to the sum of its digits cubed
     * 371 = (3*3*3) + (7*7*7) + (1*1*1)
     *
     * @param number
     * @return true if number is an Armstrong, false if not
     */
    private boolean isArmStrong(int number) {
        int result = 0;
        int orig = number;
        while (number != 0) {
            int remainder = number % 10;
            result = result + remainder * remainder * remainder;
            number = number / 10;
        }
        //number is Armstrong return true
        if (orig == result) {
            return true;
        }
        return false;
    }

    /**
     * A Palandrom is a string that is the same forward or backward
     * radar when reversed equals radar
     *
     * @param isPal
     * @return true if str is a Palandrom
     */
    public boolean isPalandrom(String isPal) {
        if (isPal == null || isPal.length() == 0)
            return false;
        if (isPal.length() == 1)
            return true;

        int len = isPal.length();
        int mid = len / 2;
        len--;
        for (int i = 0; i < mid; i++)
            if (isPal.charAt(i) != isPal.charAt(len - i))
                return false;
        return true;
    }

    /**
     * This method uses recursion to return the string reversed
     *
     * @param str
     * @return string which is the reverse of str
     */
    public String reverseRecursively(String str) {
        if (str.length() < 2)
            return str;
        return reverseRecursively(str.substring(1)) + str.charAt(0);
    }

    /**
     * This method uses iteration to loop backwards in building a new string reversed
     *
     * @param str
     * @return string which is the reverse of str
     */
    public String reverseIteratively1(String str) {
        if (str == null)
            return null;
        if (str.length() < 2)
            return str;
        StringBuilder strBuilder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--)
            strBuilder.append(str.charAt(i));
        return strBuilder.toString();
    }

    /**
     * This method uses iteration to loop half the count of the string swapping characters in the copy of original
     *
     * @param str
     * @return string which is the reverse of str
     */
    public String reverseIteratively2(String str) {
        if (str == null)
            return null;
        if (str.length() < 2)
            return str;
        char[] strChars = str.toCharArray();
        char temp;
        for (int i = 0; i < str.length() / 2; i++) {
            temp = strChars[i];
            strChars[i] = strChars[(str.length() - 1) - i];
            strChars[(str.length() - 1) - i] = temp;
        }
        return new String(strChars);
    }

    /**
     * This method wraps the value in print statements to reduce code redundancy
     *
     * @param str
     */
    public void testPalandrom(String str) {
        boolean isP = isPalandrom(str);
        if (verbose)
            System.out.println("     IsPalandrom '" + str + "': " + isP);
    }

    /**
     * This method splits a string str based on occurrences of string regex
     *
     * @param str
     * @param regex
     * @return
     */
    public static String[] myStringSplit(String str, String regex)
    {
        if (str == null || regex == null)
            return new String[0];

        List<String> result = new ArrayList<String>();
        int start = 0;
        int pos = str.indexOf(regex);
        while (pos>=start) {
            if (pos>start) {
                result.add(str.substring(start,pos));
            }
            start = pos + regex.length();
            result.add(regex);
            pos = str.indexOf(regex,start);
        }
        if (start<str.length()) {
            result.add(str.substring(start));
        }
        String[] array = result.toArray(new String[0]);
        return array;
    }

    /**
     * This method searches string full, finding string locate, and replacing with string replace
     *
     * @param full
     * @param locate
     * @param replace
     * @return
     */
    public String myStringReplace(String full, String locate, String replace) {
        if (full == null || locate == null)
            return null;
        if (replace == null)
            replace = "";
        if (full == locate)
            return replace;

        String[] splitString = myStringSplit(full, locate);
        String result = new String();
        int splitCount = splitString.length;
        int position = 0;

        if (splitCount == 1)
            return full;

        while (position < splitCount) {
            // see if we lead with the replace
            if (splitString[position] == locate) {
                // yes, lead with the replace
                result += replace;
                if (position < splitCount) {
                    position++;
                    if (position < splitCount)
                        result += splitString[position];
                }
            }
            else {
                // no, lead with keep
                result += splitString[position++];
                if (position < splitCount)
                    result += replace;
            }
            position++;
        }
        return result;
    }

    /**
     * This method wraps the input values in print statements to reduce code redundancy
     *
     * @param full
     * @param locate
     */
    public void testMyStringSplit(String full, String locate) {
        String[] result = myStringSplit(full, locate);
        int splitCount = result.length;
        int printCount = 0;
        if (verbose) {
            System.out.print("     MyStringSplit(" + full + ", " + locate + ") = ");
            while (printCount < splitCount)
                System.out.print(result[printCount++] + " ");
            System.out.println(" ");
        }
    }

    /**
     * This method wraps the input values in print statements to reduce code redundancy
     *
     * @param full
     * @param locate
     * @param replace
     */
    public void testMyStringReplace(String full, String locate, String replace) {
        String result = myStringReplace(full, locate, replace);
        if (verbose)
            System.out.println("     MyStringReplace(" + full + ", " + locate + ", " + replace + ") = " + result);
    }

    /**
     * This method sets up the data values for the primary methods in this class
     */
    public void testExercises() {
        if (verbose) {
            System.out.println("Exercises:");
            System.out.println("     charsWithinDistance: abcde c e 2 = " + charsWithinDistance("abcde", 'c', 'e', 2));
            System.out.println("     charsWithinDistance: a a a 0 = " + charsWithinDistance("a", 'a', 'a', 0));
            System.out.println("     charsWithinDistance: abcde e a 10 = " + charsWithinDistance("abcde", 'e', 'a', 10));
            System.out.print("     fizzBuzz: ");
        }
        for (int i = 1; i < 50; i++)
            if (verbose)
                System.out.print(fizzBuzz(i));
        boolean isarmstrong153 = isArmStrong(153);
        boolean isarmstrong371 = isArmStrong(371);
        boolean isarmstrong400 = isArmStrong(400);
        String r = "12345";
        String rr = reverseRecursively(r);
        String ri1 = reverseIteratively1(r);
        String ri2 = reverseIteratively2(r);
        if (verbose) {
            System.out.println(" ");

            System.out.println("     isArmstrong: 153-" + isarmstrong153 + ", 371-" + isarmstrong371 + ", 400-" + isarmstrong400);
            System.out.println("     reverseRecursively: " + r + " - " + rr);
            System.out.println("     reverseIteratively1: " + r + " - " + ri1);
            System.out.println("     reverseIteratively2: " + r + " - " + ri2);
        }

        testPalandrom("abcdedcba");
        testPalandrom("abcdeedcba");
        testPalandrom("abcde1dcba");

        testMyStringSplit("abcde", "c");
        testMyStringSplit("abcde", "z");
        testMyStringReplace("abcde", "c", "XYZ");
        testMyStringReplace("a", "a", "ZYX");
        testMyStringReplace("abcde", "abcde", "X");
        testMyStringReplace("abcde", "abcdef", "X");
        testMyStringReplace("abcde", "abcd", null);
        testMyStringReplace("abacada", "a", "XY");

        int [] containsDups = new int[] {4,5,4,5,4};
        int [] dupsRemoved1 = removeDuplicatesHash(containsDups.clone());
        int [] dupsRemoved2 = removeDuplicatesArray(containsDups.clone());
        System.out.print("     RemoveDups: ");
        for (int i = 0; i < containsDups.length; i++)
            System.out.print(containsDups[i] + ", ");
        System.out.print(" dupsRemoved1: ");
        for (int i = 0; i < dupsRemoved1.length; i++)
            System.out.print(dupsRemoved1[i] + ", ");
        System.out.print(" dupsRemoved2: ");
        for (int i = 0; i < dupsRemoved2.length; i++)
            System.out.print(dupsRemoved2[i] + ", ");
        if (verbose) {
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}

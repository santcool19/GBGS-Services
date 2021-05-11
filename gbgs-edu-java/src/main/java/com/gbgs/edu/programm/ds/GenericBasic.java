package com.gbgs.edu.programm.ds;

import com.gbgs.edu.application.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class GenericBasic {
    public static void main(String args[]) {
        reverseCollection();
    }

    private static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
        return longest;
    }

    private static String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    //ABC
    static void printAllPossibleString(String str, String prefix) {
        if (str.length() == 0) {
            System.out.print(prefix + " ");
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String str1 = str.substring(0, i) + str.substring(i + 1);
            printAllPossibleString(str1, prefix + ch);
        }
    }

    static void printIntersection() {
        int arr1[] = {1, 2, 4, 5, 6};
        int arr2[] = {2, 3, 5, 7};
        int m = arr1.length;
        int n = arr2.length;
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j])
                i++;
            else if (arr2[j] < arr1[i])
                j++;
            else {
                System.out.print(arr2[j++] + " ");
                i++;
            }
        }
    }

    private static void removeDuplicateElement() {
        int arr[] = {-2, 3, 4, 3, 9, 1, 1, -3};
        int a[] = {1, 2, 2, 2, 3, 4, 4, 4, 5, 5};
        int i = 1, j = 1;
        while (i < a.length) {
            if (a[i] != a[i - 1]) {
                a[j] = a[i];
                i++;
                j++;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(a, 0, j)));
    }

    private static void maxSubArraySum() {
        int a[] = {-2, -3, 4, -1, 9, 1, 5, -3};
        int start, end = 0;
        int arrMaxSum = a[0];
        int arrCurrMax = a[0];
        int size = a.length;
        for (int i = 1; i < size; i++) {
            arrCurrMax = Math.max(a[i], arrCurrMax + a[i]);
            arrMaxSum = Math.max(arrMaxSum, arrCurrMax);
        }
        System.out.println(arrMaxSum);
    }

    private static void isPalindrom() {
        String str = String.valueOf(12321);
        boolean isPalindrom = true;
        int start = 0;
        int end = str.length() - 1;
        while (end > start && isPalindrom) {
            if (str.charAt(start) != str.charAt(end)) {
                isPalindrom = false;
            }
            start++;
            end--;
        }
        System.out.println(isPalindrom);
    }

    private static void reverseCollection() {
        Student student1 = new Student(372, "Venkat", 1);
        Student student2 = new Student(2, "Sachin", 4);
        Student student3 = new Student(2345, "Ganguly", 6);
        Student student4 = new Student(72, "Karthik", 2);
        List<Student> studlist = new ArrayList<>();
        studlist.add(student1);
        studlist.add(student2);
        studlist.add(student3);
        studlist.add(student4);
        studlist.sort(Comparator.comparing(Student::getId).thenComparing(Comparator.comparing(Student::getRank)));
        //studlist.sort((Student s1, Student s2) -> s2.getId() - s1.getId());
        studlist.forEach(s -> System.out.println(s));
    }


}



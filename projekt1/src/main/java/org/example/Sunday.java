package org.example;

import java.util.Arrays;

public class Sunday {
    public static int search(String txt, String pat) {
        int counter = 0;
        int l1 = pat.length();
        int l2 = txt.length();
        int[] shiftTable = buildShiftTable(pat);

        int i = 0;
        while (i <= l2 - l1) {
            int j;
            for (j = 0; j < l1; j++) {
                counter++;
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == l1) {
                System.out.println("Pattern found at index: " + i);
            }
            if (i + l1 < l2) {
                i += l1 - shiftTable[txt.charAt(i + l1)];
            } else {
                i++;
            }
        }
        return counter;
    }

    private static int[] buildShiftTable(String pattern) {
        int[] shiftTable = new int[256]; // ASCII character set
        Arrays.fill(shiftTable, -1); // Initialize all values with -1

        for (int i = 0; i < pattern.length(); i++) {
            shiftTable[pattern.charAt(i)] = i;
        }
        return shiftTable;
    }
}

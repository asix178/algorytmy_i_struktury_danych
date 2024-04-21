package org.example;

public class Naive {
    static int search(String txt, String pat) {
        int counter = 0;
        int l1 = pat.length();
        int l2 = txt.length();

        for (int i = 0; i <= l2 - l1; i++) {
            int j;

            for (j = 0; j < l1; j++) {
                counter++;
                if (txt.charAt(i + j) != pat.charAt(j)) {

                    break;
                }
            }
            if (j == l1)
                System.out.println("Pattern found at index: " + i);
        }
        return counter;
    }

}

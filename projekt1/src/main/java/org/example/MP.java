package org.example;

class MP {
    public static int search(String txt, String pat) {
        int counter=0;
        int l1 = pat.length();
        int l2 = txt.length();

        int[] lps = new int[l1]; //longest prefix suffix
        int j = 0; // index for pat[]

        Array(pat, l1, lps);

        int i = 0;
        while ((l2 - i) >= (l1 - j)) {
            counter++;
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == l1) {
                System.out.println("Pattern found at index: " + (i - j));
                j = lps[j - 1];
            }
            else if (i < l2 && pat.charAt(j) != txt.charAt(i)) {

                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return counter;
    }

    public static void Array(String pat, int l1, int[] lps) {
        // previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < l1) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

}


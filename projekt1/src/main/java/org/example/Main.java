package org.example;

import static org.example.CsvGenerator.*;


public class Main {

        public static void main(String[] args) {
//        String txt = "AABAACAADAABAAABAA";
//        String pat = "AABA";
//
//        System.out.println("Naive: \n");
//        Naive.search(txt, pat);
//
//        System.out.println("\nSunday: \n");
//        Sunday.search(txt, pat);
//
//        System.out.println("\nMP: \n");
//        MP.search(txt, pat);
            final int textLength = 10000;
            generateVariousLengthTextChecksAmount(textLength,"Naive");
            generateVariousLengthTextChecksAmount(textLength, "Sunday");
            generateVariousLengthTextChecksAmount(textLength,"MP");

            final int patternLength = 40;
            generateVariousLengthPatternChecksAmount(patternLength,"Naive");
            generateVariousLengthPatternChecksAmount(patternLength, "Sunday");
            generateVariousLengthPatternChecksAmount(patternLength,"MP");

            final int alphabetSize = 20;
            generateVariousAlphabetSizeChecksAmount(alphabetSize,"Naive");
            generateVariousAlphabetSizeChecksAmount(alphabetSize,"Sunday");
            generateVariousAlphabetSizeChecksAmount(alphabetSize,"MP");
    }
}
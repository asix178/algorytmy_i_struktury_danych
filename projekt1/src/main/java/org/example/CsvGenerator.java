package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CsvGenerator {
    public static String getChar(int option){
        return switch (option) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            default -> "";
        };
    }

    public static void generateVariousLengthTextChecksAmount(int amount, String algorithm){
        Random random = new Random();
        StringBuilder pattern = new StringBuilder();
        for(int i=0;i<5;i++){
            int option = Math.abs(random.nextInt()%3);
            pattern.append(getChar(option));
        }

        String patternBuilt = pattern.toString();
        String text = getChar( Math.abs(random.nextInt()%3));
        Map<Integer, Integer> toCsv = new HashMap<>();

        String fileName = "DlugoscTekstu/"+algorithm+".csv";

        amount = amount+1;
        switch(algorithm){
            case "Naive":
                for(int i=1;i<=amount;i++){
                    Integer result = Naive.search(text, patternBuilt);
                    text += getChar( Math.abs(random.nextInt()%3));
                    toCsv.put(i, result);
                }
                break;
            case "Sunday":
                for(int i=1;i<=amount;i++){
                    Integer result = Sunday.search(text, patternBuilt);
                    text += getChar( Math.abs(random.nextInt()%3));
                    toCsv.put(i, result);
                }
                break;
            case "MP":
                for(int i=1;i<=amount;i++){
                    Integer result = MP.search(text, patternBuilt);
                    text += getChar( Math.abs(random.nextInt()%3));
                    toCsv.put(i, result);
                }
                break;
        }

        try (FileWriter csvWriter = new FileWriter(fileName)) {
            csvWriter.append("Długość tekstu,Powtórzenia\n");

            for(int i=1;i<amount-1;i++){
                csvWriter.append(String.valueOf(toCsv.keySet().toArray()[i])).append(",");
                csvWriter.append(String.valueOf(toCsv.get(i))).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateVariousLengthPatternChecksAmount(int amount, String algorithm){
        Random random = new Random();
        StringBuilder text = new StringBuilder();

        for(int i=0;i<10000;i++){
            int option = Math.abs(random.nextInt()%3);
            text.append(getChar(option));
        }
        amount = amount + 1;
        String textBuilt = text.toString();
        String pattern = getChar( Math.abs(random.nextInt()%3));
        Map<Integer, Integer> toCsv = new HashMap<>();
        amount = amount+1;
        String fileName = "DlugoscWzorca/"+algorithm+".csv";

        switch(algorithm){
            case "Naive":
                for(int i=1;i<=amount;i++){
                    Integer result = Naive.search(textBuilt, pattern);
                    pattern += getChar( Math.abs(random.nextInt()%3));
                    toCsv.put(i, result);
                }
                break;
            case "Sunday":
                for(int i=1;i<=amount;i++){
                    Integer result = Sunday.search(textBuilt, pattern);
                    pattern += getChar( Math.abs(random.nextInt()%3));
                    toCsv.put(i, result);
                }
                break;
            case "MP":
                for(int i=1;i<=amount;i++){
                    Integer result = MP.search(textBuilt, pattern);
                    pattern += getChar( Math.abs(random.nextInt()%3));
                    toCsv.put(i, result);
                }
                break;
        }

        try (FileWriter csvWriter = new FileWriter(fileName)) {
            csvWriter.append("Długość wzorca,Powtórzenia\n");

            for(int i=1;i<amount-2;i++){
                csvWriter.append(String.valueOf(toCsv.keySet().toArray()[i])).append(",");
                csvWriter.append(String.valueOf(toCsv.get(i))).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateVariousAlphabetSizeChecksAmount(int alphabetSize, String algorithm) {
        Random random = new Random();
        alphabetSize += 1;
        char[] alphabet = new char[alphabetSize];
        Map<Integer, Integer> toCsv = new HashMap<>();
        for (int j = 1; j <= alphabetSize; j++) {
            StringBuilder pattern = new StringBuilder();
            StringBuilder text = new StringBuilder();
            for (int i = 0; i < j; i++) {
                char c = (char) (random.nextInt(26) + 'a');
                alphabet[i] = c;
            }

            for (int i = 0; i < 10000; i++) {
                text.append(alphabet[Math.abs(random.nextInt() % j)]);
            }

            for (int i = 0; i < 10; i++) {
                pattern.append(alphabet[Math.abs(random.nextInt() % j)]);
            }

            String textBuilt = text.toString();
            String patternBuilt = pattern.toString();


            int result;
            switch (algorithm) {
                case "Naive":
                    result = Naive.search(textBuilt, patternBuilt);
                    toCsv.put(j, result);
                    break;
                case "Sunday":
                    result = Sunday.search(textBuilt, patternBuilt);
                    toCsv.put(j, result);
                    break;
                case "MP":
                    result = MP.search(textBuilt, patternBuilt);
                    toCsv.put(j, result);

                    break;
            }


        }
        String fileName = "RozmiarAlfabetu/" + algorithm + ".csv";
        try (FileWriter csvWriter = new FileWriter(fileName)) {
            csvWriter.append("Rozmiar alfabetu,Powtórzenia\n");
            for (int i = 1; i < alphabetSize - 1; i++) {
                csvWriter.append(String.valueOf(toCsv.keySet().toArray()[i])).append(",");
                csvWriter.append(String.valueOf(toCsv.get(i))).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

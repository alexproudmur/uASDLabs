package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TextProcessor - the main utility class
 */

public class TextProcessor implements ITextProcessor {

    private StringBuilder text;
    private int[] array;

    /**
     * Constructor
     * @param file - path to the file
     * @throws IOException - exception can be thrown
     */

    public TextProcessor(String file) throws IOException {
        fillTextField(file);
    }

    /**
     * Find first equal sequence in text
     * @param file - path to the file
     * @param findingText - sequence
     */

    @Override
    public int findFirst(String file, String findingText) {
        createArray(findingText);
        return findNext(findingText, 0);
    }

    /**
     * Find all equal sequences
     * @param file - path to the file
     * @param findingText - text to find
     */

    @Override
    public Integer[] findAll(String file, String findingText) {
        createArray(findingText);
        List<Integer> result = new ArrayList<>();
        int point = findNext(findingText, 0);
        while (point != -1) {
            result.add(point);
            point = findNext(findingText, point + 1);
        }
        return result.toArray(new Integer[0]);
    }

    /**
     * Change first equal sequence
     * @param file - path to the file
     * @param findingText - text to find and change
     * @param changedText - text to change for
     */

    @Override
    public void changeFirst(String file, String findingText, String changedText) {
        createArray(findingText);
        StringBuilder inMemoryText = text;
        int changePoint = findNext(findingText, 0);

        inMemoryText.delete(changePoint, changePoint + findingText.length());
        inMemoryText.insert(changePoint, changedText);
    }

    /**
     * Change all equal sequences
     * @param file - path to the file
     * @param findingText - text to find
     * @param changedText - text to change for
     */
    @Override
    public void changeAll(String file, String findingText, String changedText) {
        createArray(findingText);
        Integer[] allPoints = findAll(file, findingText);
        StringBuilder inMemoryText = text;
        for (int i = allPoints.length - 1; i >= 0; i--) {
            inMemoryText.delete(allPoints[i], allPoints[i] + findingText.length());
            inMemoryText.insert(allPoints[i], changedText);
        }
    }

    /**
     * Load text to private var
     * @param file - path to the file
     * @throws IOException - exception can be thrown
     */

    private void fillTextField(String file) throws IOException {
        StringBuilder inMemoryText = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            Scanner scan = new Scanner(fileReader);
            while (scan.hasNextLine()) {
                inMemoryText.append(scan.nextLine()).append("\n");
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        text = inMemoryText;
    }

    /**
     * Fill the piArray with indexes
     * @param findingText - text to find
     */

    private void createArray(String findingText) {
        int[] piArray = new int[findingText.length()];
        int j = 0;
        int i = 1;
        while (i < findingText.length()) {

            if (findingText.charAt(i) != findingText.charAt(j)) {
                if (j != 0) {
                    j = piArray[j - 1];
                } else {
                    piArray[i] = 0;
                    i++;
                }
            } else {
                piArray[i] = j + 1;
                i++;
                j++;

            }
        }
        array = piArray;
    }

    /**
     * Private method to find next char sequence
     * @param findingText - text to find
     * @param lastPoint - the last point to stop
     * @return - returning point
     */

    private int findNext(String findingText, int lastPoint){
        int k = lastPoint;
        int l = 0;
        while (k < text.length()) {
            boolean isSame = text.charAt(k) == findingText.charAt(l);
            k++;
            if (isSame) {
                l++;
                if (l == findingText.length()) {
                    return k - findingText.length();
                }
            } else {
                if (l != 0) {
                    l = array[l - 1];
                }
            }
        }
        return -1;
    }

}

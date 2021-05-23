package com.company;

import java.io.IOException;

public interface ITextProcessor {

    int findFirst(String file, String findingText) throws IOException;

    Integer[] findAll(String file, String findingText) throws IOException;

    void changeFirst(String file, String findingText, String changedText) throws IOException;

    void changeAll(String file, String findingText, String changedText) throws IOException;
}
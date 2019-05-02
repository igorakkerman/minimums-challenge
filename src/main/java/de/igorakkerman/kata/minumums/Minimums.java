package de.igorakkerman.kata.minumums;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class Minimums {
    public static List<Integer> minimums(List<Integer> items, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count == " + count + " < 0");

        return emptyList();
    }
}

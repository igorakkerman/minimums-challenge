package de.igorakkerman.kata.minumums;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Minimums {
    public static List<Integer> minimums(List<Integer> input, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count == " + count + " < 0");

        if (count > input.size())
            throw new IllegalArgumentException("count == " + count + " > " + input.size() + " == input.size()");

        if (input.isEmpty() || count <= 0) {
            return emptyList();
        } else {
            return input;
        }
    }
}

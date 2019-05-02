package de.igorakkerman.kata.minumums;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.emptyList;

public class Minimums {
    public static List<Integer> minimums(List<Integer> input, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count == " + count + " < 0");

        if (count > input.size())
            throw new IllegalArgumentException("count == " + count + " > " + input.size() + " == input.size()");

        return !input.isEmpty()
                ? sort(input, count)
                : emptyList();
    }

    private static List<Integer> sort(List<Integer> input, int count) {
        var sorted = new ArrayList<>(input);
        sorted.sort(Comparator.comparingInt(i -> i));
        return sorted.subList(0, count);
    }
}

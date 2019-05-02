package de.igorakkerman.kata.minumums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

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
        var pivot = input.get(0);
        List<Integer> low = new ArrayList<>();
        List<Integer> high = new ArrayList<>();

        input
                .stream()
                .skip(1)
                .forEach(item -> {
                    if (item <= pivot)
                        low.add(item);
                    else
                        high.add(item);
                });

        List<Integer> sortedLow = low.isEmpty() ? emptyList() : sort(low, count);
        List<Integer> sortedHigh = high.isEmpty() ? emptyList() : sort(high, count);
//        List<Integer> sortedHigh = high;

        var sorted = new ArrayList<>(sortedLow);
        sorted.add(pivot);
        sorted.addAll(sortedHigh);

        return sorted.stream().limit(count).collect(toList());
    }
}

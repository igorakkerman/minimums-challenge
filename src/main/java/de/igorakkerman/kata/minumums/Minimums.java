package de.igorakkerman.kata.minumums;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (input.isEmpty())
            return input;

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

        List<Integer> sortedLow = sort(low, count);
        List<Integer> sortedHigh = sortedLow.size() < count - 1 ? sort(high, count - sortedLow.size() - 1) : high;

        return Stream.concat(Stream.concat(sortedLow.stream(), Stream.of(pivot)), sortedHigh.stream()).limit(count).collect(toList());
    }
}

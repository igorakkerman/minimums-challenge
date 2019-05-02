package de.igorakkerman.kata.minumums;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Minimums {
    public static List<Integer> minimums(int[] items, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count == " + count + " < 0");

        if (count > items.length)
            throw new IllegalArgumentException("count == " + count + " > " + items.length + " == items.length");

        sort(items, 0, items.length - 1, count);

        return Arrays.stream(items).limit(count).boxed().collect(toList());
    }

    private static void sort(int[] items, int minIndex, int maxIndex, int count) {
        if (count == 0 || minIndex >= maxIndex)
            return;

        @SuppressWarnings("UnnecessaryLocalVariable")  //
        final int pivotIndex = maxIndex;

        int firstHighIndex = minIndex - 1;
        for (int lowIndex = minIndex; lowIndex < maxIndex; lowIndex++)
            if (items[lowIndex] <= items[pivotIndex])
                swap(items, ++firstHighIndex, lowIndex);

        final int newPivotIndex = ++firstHighIndex;
        swap(items, pivotIndex, newPivotIndex);

        sort(items, minIndex, newPivotIndex - 1, count);
        sort(items, newPivotIndex + 1, maxIndex, count);

//        List<Integer> sortedLow = sort(low, count);
//        List<Integer> sortedHigh = sortedLow.size() < count - 1 ? sort(high, count - sortedLow.size() - 1) : high;

//        return Stream.of(sortedLow.stream(), Stream.of(pivot), sortedHigh.stream()).flatMap(Function.identity()).limit(count).collect(toList());
    }

    private static void swap(int[] items, int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }
}

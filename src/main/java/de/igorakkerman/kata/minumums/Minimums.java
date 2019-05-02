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

        sortUpToCount(items, 0, items.length - 1, count);

        return Arrays.stream(items).limit(count).boxed().collect(toList());
    }

    private static void sortUpToCount(int[] items, int minIndex, int maxIndex, int count) {
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

        sortUpToCount(items, minIndex, newPivotIndex - 1, count);
        final int missingCount = count - 1 - (newPivotIndex - minIndex);
        if (missingCount > 0)
            sortUpToCount(items, newPivotIndex + 1, maxIndex, missingCount);
    }

    private static void swap(int[] items, int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }
}

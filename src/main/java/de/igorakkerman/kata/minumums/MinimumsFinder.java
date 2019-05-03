package de.igorakkerman.kata.minumums;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MinimumsFinder {

    public static List<Integer> minimums(int[] items, int count) {
        return new MinimumsFinder(items, count)
                .findMinimums()
                .getMinimums();
    }

    private final int[] items;
    private final int count;

    private int swapCount = 0;

    public MinimumsFinder(int[] items, int count) {
        if (count < 0)
            throw new IllegalArgumentException("count == " + count + " < 0");

        if (count > items.length)
            throw new IllegalArgumentException("count == " + count + " > " + items.length + " == items.length");

        this.items = items;
        this.count = count;
    }

    public MinimumsFinder findMinimums() {
        sortUpToCount(items, 0, items.length - 1, count);
        return this;
    }

    private void sortUpToCount(int[] items, int minIndex, int maxIndex, int count) {
        if (count == 0 || minIndex >= maxIndex)
            return;

        final int newPivotIndex =
                partition(items, minIndex, maxIndex);

        final int missingCount = count - 1 - (newPivotIndex - minIndex);

        sortUpToCount(items, minIndex, newPivotIndex - 1, count);
        sortUpToCount(items, newPivotIndex + 1, maxIndex, missingCount);
    }

    private int partition(int[] items, int minIndex, int pivotIndex) {
        int firstHighIndex = minIndex - 1;
        for (int lowIndex = minIndex; lowIndex < pivotIndex; lowIndex++)
            if (items[lowIndex] <= items[pivotIndex])
                swap(items, ++firstHighIndex, lowIndex);

        final int newPivotIndex = ++firstHighIndex;
        swap(items, pivotIndex, newPivotIndex);
        return newPivotIndex;
    }

    private void swap(int[] items, int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;

        swapCount++;
    }

    public List<Integer> getMinimums() {
        return Arrays.stream(items).limit(count).boxed().collect(toList());
    }

    public int getSwapCount() {
        return swapCount;
    }
}

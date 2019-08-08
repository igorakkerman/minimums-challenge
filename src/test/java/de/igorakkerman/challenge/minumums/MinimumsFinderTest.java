package de.igorakkerman.challenge.minumums;

import org.junit.jupiter.api.Test;

import java.util.List;

import static de.igorakkerman.challenge.minumums.MinimumsFinder.minimums;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsFinderTest {

    @Test
    void negativeCount_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(new int[0], -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == -1 < 0");
    }

    @Test
    void countHigherThanInputSize_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(new int[0], 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == 1 > 0 == items.length");
    }

    @Test
    void inputEmpty_count0() {
        assertThat(minimums(new int[0], 0))
                .isEmpty();
    }

    @Test
    void input1_count0() {
        assertThat(minimums(new int[]{1}, 0))
                .isEmpty();
    }

    @Test
    void input1_count1() {
        assertThat(minimums(new int[]{1}, 1))
                .containsExactly(1);
    }

    @Test
    void input2Sorted_count1() {
        assertThat(minimums(new int[]{1, 2}, 1))
                .containsExactly(1);
    }

    @Test
    void input2Unsorted_count1() {
        assertThat(minimums(new int[]{2, 1}, 1))
                .containsExactly(1);
    }

    @Test
    void input2Sorted_count2() {
        assertThat(minimums(new int[]{1, 2}, 2))
                .containsExactly(1, 2);
    }

    @Test
    void input2Unsorted_count2() {
        assertThat(minimums(new int[]{2, 1}, 2))
                .containsExactly(1, 2);
    }

    @Test
    void input3Unsorted_count2() {
        assertThat(minimums(new int[]{2, 3, 1}, 2))
                .containsExactly(1, 2);
    }

    @Test
    void input5Unsorted_count3() {
        assertThat(minimums(new int[]{5, 2, 4, 3, 1}, 3))
                .containsExactly(1, 2, 3);
    }

    @Test
    void input10_000IntsReverted_count100() {
        int[] input = firstIntsReverted(10_000);

        final MinimumsFinder minimumsFinder =
                new MinimumsFinder(input, 100)
                        .findMinimums();

        assertThat(minimumsFinder.getMinimums())
                .containsExactlyElementsOf(firstInts(100));
        assertThat(minimumsFinder.getSwapCount())
                .isEqualTo(980_299);

        // unoptimized: 25_004_999 swaps
    }

    @Test
    void input10_000_000IntsReverted_count100() {
        int[] input = firstIntsReverted(10_000_000);

        final MinimumsFinder minimumsFinder =
                new MinimumsFinder(input, 100)
                        .findMinimums();

        assertThat(minimumsFinder.getMinimums())
                .containsExactlyElementsOf(firstInts(100));
        assertThat(minimumsFinder.getSwapCount())
                .isEqualTo(989_990_299);
    }

    // optimized: 3.5 seconds
    // unoptimized: StackOverflow after 4.5 minutes

    private List<Integer> firstInts(int count) {
        return rangeClosed(1, count).boxed().collect(toList());
    }

    private int[] firstIntsReverted(int count) {
        int[] items = new int[count];

        for (int i = 0; i < count; i++)
            items[i] = count - i;

        return items;
    }
}

package de.igorakkerman.kata.minumums;

import org.junit.jupiter.api.Test;

import static de.igorakkerman.kata.minumums.Minimums.minimums;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsTest {

    private static final int VALUE_1 = 42;
    private static final int VALUE_2 = 666;
    private static final int VALUE_3 = 4711;
    private static final int VALUE_4 = 10000;
    private static final int VALUE_5 = 999999999;

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
        assertThat(minimums(new int[]{VALUE_1}, 0))
                .isEmpty();
    }

    @Test
    void input1_count1() {
        assertThat(minimums(new int[]{VALUE_1}, 1))
                .containsExactly(VALUE_1);
    }

    @Test
    void input2Sorted_count1() {
        assertThat(minimums(new int[]{VALUE_1, VALUE_2}, 1))
                .containsExactly(VALUE_1);
    }

    @Test
    void input2Unsorted_count1() {
        assertThat(minimums(new int[]{VALUE_2, VALUE_1}, 1))
                .containsExactly(VALUE_1);
    }

    @Test
    void input2Sorted_count2() {
        assertThat(minimums(new int[]{VALUE_1, VALUE_2}, 2))
                .containsExactly(VALUE_1, VALUE_2);
    }

    @Test
    void input2Unsorted_count2() {
        assertThat(minimums(new int[]{VALUE_2, VALUE_1}, 2))
                .containsExactly(VALUE_1, VALUE_2);
    }

    @Test
    void input3Unsorted_count2() {
        assertThat(minimums(new int[]{VALUE_2, VALUE_3, VALUE_1}, 2))
                .containsExactly(VALUE_1, VALUE_2);
    }

    @Test
    void input5Unsorted_count3() {
        assertThat(minimums(new int[] {VALUE_5, VALUE_2, VALUE_4, VALUE_3, VALUE_1}, 3))
                .containsExactly(VALUE_1, VALUE_2, VALUE_3
                );
    }

    @Test
    void inputAllIntsReverted_count100() {
        int[] input = rangeClosed(1, VALUE_4).boxed().sorted(reverseOrder()).mapToInt(i->i).toArray();
        assertThat(minimums(input, 100))
                .containsExactlyElementsOf(rangeClosed(1, 100).boxed().collect(toList()));
    }
}

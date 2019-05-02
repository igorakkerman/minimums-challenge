package de.igorakkerman.kata.minumums;

import org.junit.jupiter.api.Test;

import static de.igorakkerman.kata.minumums.Minimums.minimums;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsTest {

    public static final int VALUE_1 = 42;
    public static final int VALUE_2 = 4711;

    @Test
    void negativeCount_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(emptyList(), -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == -1 < 0");
    }

    @Test
    void countHigherThanInputSize_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(emptyList(), 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == 1 > 0 == input.size()");
    }

    @Test
    void inputEmpty_count0_outputEmpty() {
        assertThat(minimums(emptyList(), 0))
                .isEmpty();
    }

    @Test
    void input1_count0_outputEmpty() {
        assertThat(minimums(singletonList(VALUE_1), 0))
                .isEmpty();
    }

    @Test
    void input1_count1_outputValue1() {
        assertThat(minimums(singletonList(VALUE_1), 1))
                .containsExactly(VALUE_1);
    }

    @Test
    void input2_count1_outputValue1() {
        assertThat(minimums(asList(VALUE_1, VALUE_2), 1))
                .containsExactly(VALUE_1);
    }

    @Test
    void input2Unsorted_count1_outputValue2() {
        assertThat(minimums(asList(VALUE_2, VALUE_1), 1))
                .containsExactly(VALUE_1);
    }

    @Test
    void input2Sorted_count2_outputValue1() {
        assertThat(minimums(asList(VALUE_1, VALUE_2), 2))
                .containsExactly(VALUE_1, VALUE_2);
    }
}

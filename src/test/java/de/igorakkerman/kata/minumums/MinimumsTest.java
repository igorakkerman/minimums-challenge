package de.igorakkerman.kata.minumums;

import org.junit.jupiter.api.Test;

import static de.igorakkerman.kata.minumums.Minimums.minimums;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsTest {

    public static final int VALUE_1 = 42;

    @Test
    void emptyList_negativeCount_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(emptyList(), -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == -1 < 0");
    }

    @Test
    void emptyList_countHigherThanInputSize_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(emptyList(), 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == 1 > 0 == input.size()");
    }

    @Test
    void emptyInput_0Count_emptyMinimums() {
        assertThat(minimums(emptyList(), 0))
                .isEmpty();
    }

    @Test
    void singletonInput_0Count_emptyMinimums() {
        assertThat(minimums(singletonList(VALUE_1), 0))
                .isEmpty();
    }

    @Test
    void singletonInput_1Count_value1Minimums() {
        assertThat(minimums(singletonList(VALUE_1), 1))
                .containsExactly(VALUE_1);
    }
}

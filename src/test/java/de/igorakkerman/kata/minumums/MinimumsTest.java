package de.igorakkerman.kata.minumums;

import org.junit.jupiter.api.Test;

import static de.igorakkerman.kata.minumums.Minimums.minimums;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsTest {

    @Test
    void negativeCount_IllegalArgumentThrown() {
        assertThatThrownBy(() -> minimums(emptyList(), -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == -1 < 0");
    }

    @Test
    void emptyInput_0Count_emptyMinimums() {
        assertThat(minimums(emptyList(), 0))
                .isEmpty();
    }

    @Test
    void emptyInput_1Count_emptyMinimums() {
        assertThat(minimums(emptyList(), 1))
                .isEmpty();
    }

    @Test
    void emptyInput_2Count_emptyMinimums() {
        assertThat(minimums(emptyList(), 2))
                .isEmpty();
    }

    @Test
    void singletonInput_0Count_emptyMinimums() {
        assertThat(minimums(emptyList(), 0))
                .isEmpty();
    }
}

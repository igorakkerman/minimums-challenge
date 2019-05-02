package de.igorakkerman.kata.minumums;

import org.junit.jupiter.api.Test;

import static de.igorakkerman.kata.minumums.Minimums.minimums;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsTest {

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
        assertThat(minimums(emptyList(), 0))
                .isEmpty();
    }

}
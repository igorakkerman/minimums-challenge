package de.igorakkerman.kata.minumums;

import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MinimumsTest {

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void negativeCount_IllegalArgumentThrown() {
        assertThatThrownBy(() -> Minimums.minimums(emptyList(), -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("count == -1 < 0");
    }
}

package io.github.isguliyev.random.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolTest {
    @Test
    void next_returnsO_whenSymbolIsX() {
        assertEquals(Symbol.O, Symbol.X.next());
    }

    @Test
    void next_returnsX_whenSymbolIsO() {
        assertEquals(Symbol.X, Symbol.O.next());
    }
}
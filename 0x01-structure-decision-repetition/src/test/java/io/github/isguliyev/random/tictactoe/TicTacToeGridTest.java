package io.github.isguliyev.random.tictactoe;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;

public class TicTacToeGridTest {
    private static final int GRID_HEIGHT = 3;
    private static final int GRID_WIDTH = 3;

    private TicTacToeGrid ticTacToeGrid;

    @BeforeEach
    void setUp() {
        this.ticTacToeGrid = new TicTacToeGrid(GRID_HEIGHT, GRID_WIDTH);
    }

    @Nested
    class ConstructorTest {
        @Test
        void constructor_throwsIllegalArgumentException_whenHeightIsLessThan3() {
            assertThrows(IllegalArgumentException.class, () -> new TicTacToeGrid(2, 3));
        }

        @Test
        void constructor_throwsIllegalArgumentException_whenWidthIsLessThan3() {
            assertThrows(IllegalArgumentException.class, () -> new TicTacToeGrid(3, 2));
        }
    }

    @Test
    void getState_returnsDefensiveCopyOfCurrentState() {
        this.ticTacToeGrid.getState()[0][0] = Symbol.X;

        assertEquals(Symbol.EMPTY, this.ticTacToeGrid.getState()[0][0]);
    }

    @Nested
    class PlayTest {
        @Test
        void play_putsNextSymbolOnGrid_whenRowAndColumnAreValid() {
            ticTacToeGrid.play(0, 0);

            assertEquals(Symbol.X, ticTacToeGrid.getState()[0][0]);
        }

        @Test
        void play_decrementsEmptySymbolCount_whenRowAndColumnAreValid() {
            assertEquals(GRID_HEIGHT * GRID_WIDTH, ticTacToeGrid.getEmptySymbolCount());

            ticTacToeGrid.play(0, 0);

            assertEquals(GRID_HEIGHT * GRID_WIDTH - 1, ticTacToeGrid.getEmptySymbolCount());
        }

        @Test
        void play_changesTurn_whenRowAndColumnAreValid() {
            assertEquals(Symbol.X, ticTacToeGrid.getTurn());

            ticTacToeGrid.play(0, 0);

            assertEquals(Symbol.O, ticTacToeGrid.getTurn());
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, GRID_HEIGHT})
        void play_throwsIllegalArgumentException_whenRowIsOutOfBounds(int row) {
            assertThrows(IllegalArgumentException.class, () -> ticTacToeGrid.play(row, 0));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, GRID_WIDTH})
        void play_throwsIllegalArgumentException_whenColumnIsOutOfBounds(int column) {
            assertThrows(IllegalArgumentException.class, () -> ticTacToeGrid.play(0, column));
        }

        @Test
        void play_throwsIllegalStateException_whenSymbolInGivenRowAndColumnIsNotEmpty() {
            ticTacToeGrid.play(0, 0);

            assertThrows(IllegalStateException.class, () -> ticTacToeGrid.play(0, 0));
        }

        @Test
        void play_throwsIllegalStateException_whenEmptySymbolCountIs0() {
            for (int row = 0; row < GRID_HEIGHT; row++) {
                for (int column = 0; column < GRID_WIDTH; column++) {
                    ticTacToeGrid.play(row, column);
                }
            }

            assertThrows(IllegalStateException.class, () -> ticTacToeGrid.play(0, 0));
        }
    }

    @Nested
    class IsSymbolEmptyTest {
        @ParameterizedTest
        @ValueSource(ints = {-1, GRID_HEIGHT})
        void isSymbolEmpty_throwsIllegalArgumentException_whenRowIsOutOfBounds(int row) {
            assertThrows(IllegalArgumentException.class, () -> ticTacToeGrid.isSymbolEmpty(row, 0));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, GRID_WIDTH})
        void isSymbolEmpty_throwsIllegalArgumentException_whenColumnIsOutOfBounds(int column) {
            assertThrows(IllegalArgumentException.class, () -> ticTacToeGrid.isSymbolEmpty(0, column));
        }

        @Test
        void isSymbolEmpty_returnsTrue_whenSymbolInGivenRowAndColumnIsEmpty() {
            assertTrue(ticTacToeGrid.isSymbolEmpty(0, 0));
        }

        @Test
        void isSymbolEmpty_returnsFalse_whenSymbolInGivenRowAndColumnIsNotEmpty() {
            ticTacToeGrid.play(0, 0);

            assertFalse(ticTacToeGrid.isSymbolEmpty(0, 0));
        }
    }
}
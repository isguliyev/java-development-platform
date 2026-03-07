package io.github.isguliyev.random.tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomTicTacToePlayerTest {
    private static final int GRID_HEIGHT = 3;
    private static final int GRID_WIDTH = 3;

    private TicTacToeGrid ticTacToeGrid;

    @BeforeEach
    void setUp() {
        this.ticTacToeGrid = new TicTacToeGrid(GRID_HEIGHT, GRID_WIDTH);
    }

    @Nested
    class PlayTest {
        @Test
        void play_putsNextSymbolOnRandomEmptyGridCell_whenTicTacToeGridIsNotNull() {
            RandomTicTacToePlayer.play(ticTacToeGrid);

            assertEquals(GRID_HEIGHT * GRID_WIDTH - 1, ticTacToeGrid.getEmptySymbolCount());
        }

        @Test
        void play_throwsNullPointerException_whenTicTacToeGridIsNull() {
            assertThrows(NullPointerException.class, () -> RandomTicTacToePlayer.play(null));
        }

        @Test
        void play_throwsIllegalStateException_whenEmptySymbolCountOfTicTacToeGridIs0() {
            for (int row = 0; row < GRID_HEIGHT; row++) {
                for (int column = 0; column < GRID_WIDTH; column++) {
                    ticTacToeGrid.play(row, column);
                }
            }

            assertThrows(
                IllegalStateException.class,
                () -> RandomTicTacToePlayer.play(ticTacToeGrid)
            );
        }
    }

    @Nested
    class PlayUntilEndTest {
        @Test
        void playUntilEnd_playsTicTacToeRandomlyUntilEnd_whenTicTacToeGridIsNotNull() {
            RandomTicTacToePlayer.playUntilEnd(ticTacToeGrid);

            assertEquals(0, ticTacToeGrid.getEmptySymbolCount());
        }

        @Test
        void playUntilEnd_throwsNullPointerException_whenTicTacToeGridIsNull() {
            assertThrows(NullPointerException.class, () -> RandomTicTacToePlayer.playUntilEnd(null));
        }

        @Test
        void playUntilEnd_throwsIllegalStateException_whenEmptySymbolCountOfTicTacToeGridIs0() {
            for (int row = 0; row < GRID_HEIGHT; row++) {
                for (int column = 0; column < GRID_WIDTH; column++) {
                    ticTacToeGrid.play(row, column);
                }
            }

            assertThrows(
                IllegalStateException.class,
                () -> RandomTicTacToePlayer.playUntilEnd(ticTacToeGrid)
            );
        }
    }
}
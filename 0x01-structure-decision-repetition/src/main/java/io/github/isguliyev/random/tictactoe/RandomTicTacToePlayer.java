package io.github.isguliyev.random.tictactoe;

import java.util.Random;

public class RandomTicTacToePlayer {
    public static void play(TicTacToeGrid ticTacToeGrid) {
        if (ticTacToeGrid == null) {
            throw new NullPointerException("TicTacToeGrid must not be null");
        }

        final int emptySymbolCount = ticTacToeGrid.getEmptySymbolCount();

        if (emptySymbolCount == 0) {
            throw new IllegalStateException("All cells are already occupied");
        }

        final int height = ticTacToeGrid.getHeight();
        final int width  = ticTacToeGrid.getWidth();

        Random random = new Random();
        final int randomEmptySymbolOrder = random.nextInt(emptySymbolCount) + 1;
        int emptySymbolOrder = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (ticTacToeGrid.isSymbolEmpty(i, j)) {
                    emptySymbolOrder++;
                }

                if (randomEmptySymbolOrder == emptySymbolOrder) {
                    ticTacToeGrid.play(i, j);

                    return;
                }
            }
        }
    }

    public static void playUntilEnd(TicTacToeGrid ticTacToeGrid) {
        if (ticTacToeGrid == null) {
            throw new NullPointerException("TicTacToeGrid must not be null");
        }

        final int emptySymbolCount = ticTacToeGrid.getEmptySymbolCount();

        if (emptySymbolCount == 0) {
            throw new IllegalStateException("All cells are already occupied");
        }

        while (ticTacToeGrid.getEmptySymbolCount() > 0) {
            play(ticTacToeGrid);
        }
    }
}
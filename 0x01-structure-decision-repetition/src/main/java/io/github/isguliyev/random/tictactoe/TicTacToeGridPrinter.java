package io.github.isguliyev.random.tictactoe;

public class TicTacToeGridPrinter {
    public static void printWithASCIICharacters(TicTacToeGrid ticTacToeGrid) {
        if (ticTacToeGrid == null) {
            throw new NullPointerException("TicTacToeGrid must not be null");
        }

        final String X = " X ";
        final String O = " O ";
        final String EMPTY = "   ";

        final int height = ticTacToeGrid.getHeight();
        final int width = ticTacToeGrid.getWidth();
        Symbol[][] gridState = ticTacToeGrid.getState();

        for (int row = 0; row < height; row++) {
            printHorizontalASCIIGridLine(width);

            for (int column = 0; column < width; column++) {
                System.out.print("|");

                switch (gridState[row][column]) {
                    case X:
                    System.out.print(X);
                    break;
                    case O:
                    System.out.print(O);
                    break;
                    case EMPTY:
                    System.out.print(EMPTY);
                }
            }

            System.out.println("|");
        }

        printHorizontalASCIIGridLine(width);
    }

    private static void printHorizontalASCIIGridLine(int width) {
        for (int i = 0; i < width; i++) {
            System.out.print("+---");
        }

        System.out.println("+");
    }
}
package io.github.isguliyev.random.tictactoe;

public class TicTacToeGrid {
    private final int height;
    private final int width;
    private final Symbol[][] state;
    private int emptySymbolCount;
    private Symbol turn;

    public TicTacToeGrid(int height, int width) {
        if (height < 3) {
            throw new IllegalArgumentException("Failed to initialize tic tac toe grid: height is less than 3");
        }

        if (width < 3) {
            throw new IllegalArgumentException("Failed to initialize tic tac toe grid: width is less than 3");
        }

        this.height = height;
        this.width = width;
        this.state = new Symbol[height][width];

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                this.state[row][column] = Symbol.EMPTY;
            }
        }

        this.emptySymbolCount = height * width;
        this.turn = Symbol.X;
    }

    public void play(int row, int column) {
        this.validateBounds(row, column);

        if (this.emptySymbolCount == 0) {
            throw new IllegalStateException("Failed to play: all cells are already occupied");
        }

        if (this.state[row][column] != Symbol.EMPTY) {
            throw new IllegalStateException("Failed to play: cell is already occupied");
        }

        this.state[row][column] = this.turn;
        this.turn = this.turn.next();

        this.emptySymbolCount--;
    }

    public boolean isSymbolEmpty(int row, int column) {
        this.validateBounds(row, column);

        return this.state[row][column] == Symbol.EMPTY;
    }

    private void validateBounds(int row, int column) {
        if (row < 0 || row >= this.height) {
            throw new IllegalArgumentException("Failed to validate bounds: row index out of bounds");
        }

        if (column < 0 || column >= this.width) {
            throw new IllegalArgumentException("Failed to validate bounds: column index out of bounds");
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Symbol[][] getState() {
        Symbol[][] stateCopy = new Symbol[this.height][this.width];

        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                stateCopy[row][column] = this.state[row][column];
            }
        }

        return stateCopy;
    }

    public int getEmptySymbolCount() {
        return this.emptySymbolCount;
    }

    public Symbol getTurn() {
        return this.turn;
    }
}
package io.github.isguliyev.random.tictactoe;

public enum Symbol {
    X,
    O,
    EMPTY;

    public Symbol next() {
        return this == X ? O : X;
    }
}
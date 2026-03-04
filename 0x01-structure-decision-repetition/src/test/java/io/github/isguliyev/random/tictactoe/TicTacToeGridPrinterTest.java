package io.github.isguliyev.random.tictactoe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class TicTacToeGridPrinterTest {
    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream standardOutputStream;

    @BeforeAll
    public void setUpAll() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.standardOutputStream = System.out;

        System.setOut(new PrintStream(this.byteArrayOutputStream, true));
    }

    @AfterAll
    public void tearDownAll() {
        System.setOut(this.standardOutputStream);
    }

    @AfterEach
    public void tearDown() {
        this.byteArrayOutputStream.reset();
    }

    @ParameterizedTest
    @CsvSource(
        value = {
            "'+---+---+---+\n"
            + "| X | O | X |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n', 3, 3",
            "'+---+---+---+---+\n"
            + "| X | O | X | O |\n"
            + "+---+---+---+---+\n"
            + "|   |   |   |   |\n"
            + "+---+---+---+---+\n"
            + "|   |   |   |   |\n"
            + "+---+---+---+---+\n', 3, 4",
            "'+---+---+---+\n"
            + "| X | O | X |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n"
            + "|   |   |   |\n"
            + "+---+---+---+\n', 4, 3"
        }
    )
    public void printWithASCIICharacters_printsGridWithASCIICharacters_whenTicTacToeGridIsNotNull(
        String expectedOutput,
        int height,
        int width
    ) {
        TicTacToeGrid ticTacToeGrid = new TicTacToeGrid(height, width);

        for (int column = 0; column < width; column++) {
            ticTacToeGrid.play(0, column);
        }

        TicTacToeGridPrinter.printWithASCIICharacters(ticTacToeGrid);

        assertEquals(expectedOutput, this.byteArrayOutputStream.toString());
    }

    @Test
    public void printWithASCIICharacters_throwsNullPointerException_whenTicTacToeGridIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> TicTacToeGridPrinter.printWithASCIICharacters(null)
        );
    }
}
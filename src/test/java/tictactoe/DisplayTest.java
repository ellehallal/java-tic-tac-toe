package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayTest {

    @Test
    void displaysTheGrid() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        var squaresOutput =
                "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n\n";
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        display.showGrid(grid);

        assertEquals(squaresOutput, output.toString());
    }

    @Test
    void displaysTieMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.outcomeMessage(GameOutcome.tie.toString());

        assertEquals("It's a tie!\n\n", output.toString());
    }

    @Test
    void displaysWinnerMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var winnersMark = "x";

        display.outcomeMessage(winnersMark);

        assertEquals("x is the winner!\n\n", output.toString());
    }


    @Test
    void displaysPlayerSelectionMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.playerSelectionMessage(1);

        assertEquals("Please select player 1\n(human, computer or unbeatable):\n", output.toString());
    }

    @Test
    void displaysInvalidPlayerSelectionMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.invalidPlayerSelectionMessage();

        assertEquals("Invalid player selection. Please try again.\n", output.toString());
    }

    @Test
    void displaysPlayerMarkMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.playerMarkMessage(1);

        assertEquals("Please select player 1's mark:\n", output.toString());
    }

    @Test
    void displaysInvalidPlayerMarkMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);

        display.invalidPlayerMarkMessage();

        assertEquals("Invalid move selection. Please try again.\n", output.toString());
    }

    @Test
    void displaysComputerPlayerMessages() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        display.computerPlayerMessages(grid, "x");

        var outputString = output.toString();

        assertThat(outputString).contains("1 | 2 | 3\n");
        assertThat(outputString).contains("x, it's your turn.\n");
        assertThat(outputString).contains("Choose a position from 1 - 9:\n");
        assertThat(outputString).contains("x, is thinking...\n\n");
    }

    @Test
    void displaysPlayerTurnMessageWhenTrue() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        display.humanPlayerMessages(grid, "o", true);

        var outputString = output.toString();

        assertThat(outputString).contains("o, it's your turn.\n");
        assertThat(outputString).contains("Choose a position from 1 - 9:\n");
    }

    @Test
    void displaysInvalidMoveMessageWhenFalse() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        display.humanPlayerMessages(grid, "o", false);

        var outputString = output.toString();

        assertThat(outputString).contains("Invalid move. Please try again, o.\n");
        assertThat(outputString).contains("Choose a position from 1 - 9:\n");
    }

    @Test
    void displaysInputErrorMessage() {

        var output = new ByteArrayOutputStream();
        var consoleWriter = new ConsoleWriter(new PrintStream(output));
        var display = new Display(consoleWriter);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);

        display.inputErrorMessage();

        var outputString = output.toString();

        assertThat(outputString).contains("Trouble reading input \n");
    }
}
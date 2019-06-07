package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameFactoryTest {
    @Test
    void createsANewInstanceOfGame() {
        var simulatedInput = "human" + System.getProperty("line.separator")
                + "x" + System.getProperty("line.separator")
                + "computer" + System.getProperty("line.separator")
                + "o" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        var display = new Display(new ConsoleWriter());
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var squares = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        var grid = new Grid(squares);
        var board = new Board(grid);
        var gameFactory = new GameFactory(playerFactory, board);

        var newGame = gameFactory.createNewGame();

        assertThat(newGame).isInstanceOf(Game.class);
        assertEquals("x", newGame.currentPlayersMark());
        assertEquals(PlayerTypes.human.toString(), newGame.currentPlayersType());
        assertEquals("o", newGame.opponentsMark());
        assertEquals(PlayerTypes.computer.toString(), newGame.opponentsType());
    }

}
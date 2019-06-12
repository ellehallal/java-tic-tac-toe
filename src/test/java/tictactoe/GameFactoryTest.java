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
        var gameFactory = new GameFactory(playerFactory);

        var newGame = gameFactory.newGame();

        assertThat(newGame).isInstanceOf(Game.class);
        assertEquals("x", newGame.currentPlayersMark());
        assertEquals(PlayerType.human.toString(), newGame.currentPlayersType());
        assertEquals("o", newGame.opponentsMark());
        assertEquals(PlayerType.computer.toString(), newGame.opponentsType());
    }

    @Test
    void createsANewInstanceOfGameWithExistingInformation() {
        var display = new Display(new ConsoleWriter());
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        var inputValidator = new InputValidator(bufferedReader, display);
        var minimax = new Minimax();
        var playerFactory = new PlayerFactory(display, inputValidator, minimax);
        var gameFactory = new GameFactory(playerFactory);
        var squares = Arrays.asList("x", "o", "x", "x", "o", "o", "o", "x", "9");

        var existingGame = gameFactory.existingGame
                ("x", "human", "o", "computer", squares);

        assertThat(existingGame).isInstanceOf(Game.class);
        assertEquals("x", existingGame.currentPlayersMark());
        assertEquals(PlayerType.human.toString(), existingGame.currentPlayersType());
        assertEquals("o", existingGame.opponentsMark());
        assertEquals(PlayerType.computer.toString(), existingGame.opponentsType());
        assertEquals(squares, existingGame.board.getGrid().getSquares());
    }

}
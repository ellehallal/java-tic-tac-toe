package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTypesTest {

    @Test
    void returnsHFromEnum() {
        var playerType = PlayerTypes.HUMAN.type;

        assertEquals("h", playerType);
    }

    @Test
    void returnsCFromEnum() {
        var playerType = PlayerTypes.COMPUTER.type;

        assertEquals("c", playerType);
    }

}
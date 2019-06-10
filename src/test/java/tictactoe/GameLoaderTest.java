package tictactoe;

import org.junit.jupiter.api.Test;

class GameLoaderTest {

    @Test
    void selectEntryFromDatabase() {
        var database = new Database();

        var gameLoader = new GameLoader(database.connect());
        gameLoader.selectEntryFromDatabase("testgame");
    }
}
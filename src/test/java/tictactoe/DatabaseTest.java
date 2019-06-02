package tictactoe;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseTest {

    @Test
    void displaysConnectionMessageWhenDatabaseConnectionIsSuccessful() {
        var output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        var expectedOutput = "Connected to the database successfully.\n";

        Database app = new Database();
        app.connect();

        assertEquals(expectedOutput, output.toString());
    }
}
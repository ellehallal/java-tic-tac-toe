package tictactoe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GameLoader {

    private final Connection connection;
    private List playerInformation;
    private List squares;

    public GameLoader(Connection connection) {
        this.connection = connection;
    }

    public void selectEntryFromDatabase(String gameName) {
        var query = "SELECT * FROM saved_games WHERE game_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameName);
            preparedStatement.executeQuery();
            var results = preparedStatement.getResultSet();
            System.out.println(results.getClass());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

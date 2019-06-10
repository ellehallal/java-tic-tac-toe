package tictactoe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLoader {

    private final Connection connection;
    private List playerInformation = new ArrayList<String>();
    private List squares = new ArrayList<String>();

    public GameLoader(Connection connection) {
        this.connection = connection;
    }

    public void getGame(String gameName) throws SQLException {
        var gameData = selectEntryFromDatabase(gameName);
        extractGameData(gameData);
    }

    private ResultSet selectEntryFromDatabase(String gameName) {
        ResultSet gameData = null;

        try {
            gameData = getGameDataFromDatabase(gameName);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gameData;
    }

    private ResultSet getGameDataFromDatabase(String gameName) throws SQLException {
        var query = "SELECT * FROM saved_games WHERE game_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, gameName);
        preparedStatement.executeQuery();
        return preparedStatement.getResultSet();
    }

    private void extractGameData(ResultSet gameData) throws SQLException {
        while (gameData.next()) {
            playerInformation.add(gameData.getString(3));
            playerInformation.add(gameData.getString(4));
            playerInformation.add(gameData.getString(5));
            playerInformation.add(gameData.getString(6));
            squares = (squaresArrayToArrayList(gameData));
        }
    }

    private List squaresArrayToArrayList(ResultSet gameData) throws SQLException {
        var squaresData = gameData.getArray(7);
        var squaresArray = (String[]) squaresData.getArray();
        return new ArrayList<>(Arrays.asList(squaresArray));
    }

    public Object getPlayerInformation(int index) {
        return playerInformation.get(index);
    }

    public List getSquares() {
        return squares;
    }
}

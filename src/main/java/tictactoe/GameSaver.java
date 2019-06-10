package tictactoe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSaver {

    private final Connection connection;

    public GameSaver(Connection connection) {
        this.connection = connection;
    }

    public void saveGame(String gameName, Game game) {
        var gameState = getGameState(gameName, game);
        var squares = getSquares(game);
        addEntryToDatabase(gameState, squares);
    }

    private void addEntryToDatabase
            (ArrayList<String> gameState, String[] squares) {
        var query = "INSERT INTO saved_games ("
                + " game_name,"
                + " current_player_mark,"
                + " current_player_type,"
                + " opponent_mark,"
                + " opponent_type,"
                + " squares ) VALUES ("
                + "?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            var squaresArray = connection.createArrayOf("text", squares);

            preparedStatement.setString(1, gameState.get(0));
            preparedStatement.setString(2, gameState.get(1));
            preparedStatement.setString(3, gameState.get(2));
            preparedStatement.setString(4, gameState.get(3));
            preparedStatement.setString(5, gameState.get(4));
            preparedStatement.setArray(6, squaresArray);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Database updated successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isGameNameInDatabase(String gameName) {
        var query = "SELECT * FROM saved_games WHERE game_name = ?";

        try {
            var retrievedGameName = "";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameName);
            preparedStatement.executeQuery();
            var results = preparedStatement.getResultSet();
            while (results.next()) {
                retrievedGameName = results.getString("game_name");
            }
            return gameName.equals(retrievedGameName);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<String> getGameState(String gameName, Game game) {
        var currentPlayersMark = getPlayersMark(GamePlayers.currentplayer.toString(), game);
        var currentPlayersType = getPlayerType(GamePlayers.currentplayer.toString(), game);
        var opponentsMark = getPlayersMark(GamePlayers.opponent.toString(), game);
        var opponentsType = getPlayerType(GamePlayers.opponent.toString(), game);

        return new ArrayList<>
                (Arrays.asList(gameName, currentPlayersMark, currentPlayersType, opponentsMark, opponentsType));
    }

    public String[] getSquares(Game game) {
        var grid = game.board.getGrid();
        var squares = grid.getSquares();
        return squares.toArray(new String[squares.size()]);
    }

    private String getPlayersMark(String player, Game game) {
        return player.matches(GamePlayers.currentplayer.toString()) ?
                game.currentPlayersMark() : game.opponentsMark();
    }

    private String getPlayerType(String player, Game game) {
        return player.matches(GamePlayers.currentplayer.toString()) ?
                game.currentPlayersType() : game.opponentsType();
    }
}

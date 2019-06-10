package tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Minimax {

    public int chooseMove
            (Board board, String currentPlayersMark, String opponentsMark) {

        var copyBoard = board.copyBoard();
        return find_best_move(copyBoard, 0, currentPlayersMark, opponentsMark);
    }

    private int find_best_move
            (Board board, int depth, String currentPlayersMark, String opponentsMark) {

        if (board.isComplete(currentPlayersMark, opponentsMark)) {
            return scoreMove(board, depth, currentPlayersMark, opponentsMark);
        }

        var movesAndScores = new HashMap<Integer, Integer>();
        var availableSquares = board.availableSquares(currentPlayersMark, opponentsMark);

        for (String square : availableSquares) {
            var squareNum = Integer.parseInt(square);
            var copyBoard = board.copyBoard();

            copyBoard.takeSquare(squareNum, currentPlayersMark);

            movesAndScores.put(squareNum,
                    -1 * find_best_move(copyBoard, depth + 1, opponentsMark, currentPlayersMark));
        }
        return evaluateMove(depth, movesAndScores);
    }


    private int scoreMove
            (Board board, int depth, String currentPlayersMark, String opponentsMark) {

        if (board.isWinningPlayer(currentPlayersMark)) {
            return 10 - depth;
        } else if (board.isWinningPlayer(opponentsMark)) {
            return depth - 10;
        } else {
            return 0;
        }
    }

    private int evaluateMove(int depth, Map scores) {
        return depth == 0 ? maximisingPlayerBestMove(scores) : maximisingPlayerBestScore(scores);
    }


    private int maximisingPlayerBestMove(Map<Integer, Integer> scores) {
        var entry = scores.entrySet().iterator().next();
        var bestMove = entry.getKey();
        var bestScore = 0;

        for (Map.Entry<Integer, Integer> moveScore : scores.entrySet()) {
            var score = moveScore.getValue();

            if (score > bestScore) {
                bestScore = score;
                bestMove = moveScore.getKey();
            }
        }

        return bestMove;
    }

    private int maximisingPlayerBestScore(Map<Integer, Integer> scores) {
        var entry = scores.entrySet().iterator().next();
        var bestScore = entry.getValue();

        for (Map.Entry<Integer, Integer> moveScore : scores.entrySet()) {
            var score = moveScore.getValue();

            if (score > bestScore) {
                bestScore = moveScore.getValue();
            }
        }
        return bestScore;
    }


}

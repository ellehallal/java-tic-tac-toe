package tictactoe;

import java.util.ArrayList;
import java.util.List;

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

        var movesAndScores = new ArrayList<MoveAndScoreResult>();
        var availableSquares = board.availableSquares(currentPlayersMark, opponentsMark);

        for (String square : availableSquares) {
            var squareNum = Integer.parseInt(square);
            var copyBoard = board.copyBoard();

            copyBoard.takeSquare(squareNum, currentPlayersMark);

            var score = - 1 * find_best_move(copyBoard, depth + 1, opponentsMark, currentPlayersMark);

            movesAndScores.add(new MoveAndScoreResult(squareNum, score));
        }
        return evaluateMove(depth, movesAndScores);
    }


    private int scoreMove
            (Board board, int depth, String currentPlayersMark, String opponentsMark) {

        if (board.winningLineExists(currentPlayersMark, opponentsMark)) {
            return depth - 10;
        } else {
            return 0;
        }
    }

    private int evaluateMove(int depth, List<MoveAndScoreResult> movesAndScores) {
        return depth == 0 ? maximisingPlayerBestMove(movesAndScores) : maximisingPlayerBestScore(movesAndScores);
    }


    private int maximisingPlayerBestMove(List<MoveAndScoreResult> movesAndScores) {
        var bestMove = 0;
        var bestScore = -10;

        for (MoveAndScoreResult result : movesAndScores) {
            var score = result.getScore();

            if(score > bestScore) {
                bestScore = score;
                bestMove = result.getMove();
            }
        }

        return bestMove;
    }

    private int maximisingPlayerBestScore(List<MoveAndScoreResult> movesAndScores) {
        var bestScore = -10;

        for (MoveAndScoreResult result : movesAndScores) {
            var score = result.getScore();

            if(score > bestScore) {
                bestScore = score;
            }
        }
        return bestScore;
    }

    class MoveAndScoreResult {
        private final int move;
        private final int score;

        public MoveAndScoreResult(int move, int score) {
            this.move = move;
            this.score = score;
        }

        public int getMove() {
            return move;
        }

        public int getScore() {
            return score;
        }
    }
}

package chess;

import chess.observers.Observer;
import chess.observers.GameLogger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Chess Game.
 */
public class ChessGame {
    private ChessBoard board;
    private String turn; // Current player's turn ("White" or "Black")
    private final List<Observer> observers; // List of observers for logging moves

    /**
     * Constructor for ChessGame.
     * Initializes the chessboard using the ChessBoardBuilder.
     */
    public ChessGame() {
        this.board = new ChessBoardBuilder().setupStandardBoard().build();
        this.turn = "White"; // White moves first
        this.observers = new ArrayList<>();
    }

    /**
     * Adds an observer (e.g., GameLogger) to track game moves.
     *
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Prints the current state of the chessboard.
     */
    public void printBoard() {
        board.printBoard();
    }

    /**
     * Attempts to make a move on the chessboard.
     *
     * @param x1 Start X-coordinate.
     * @param y1 Start Y-coordinate.
     * @param x2 Destination X-coordinate.
     * @param y2 Destination Y-coordinate.
     * @return True if the move is successful, false otherwise.
     */
    public boolean makeMove(int x1, int y1, int x2, int y2) {
        if (board.movePiece(x1, y1, x2, y2)) {
            turn = turn.equals("White") ? "Black" : "White"; // Switch turn

            // Notify if the King is in check
            if (board.isKingInCheck(turn)) {
                System.out.println(turn + " King is in check!");
            }

            // Notify all observers of the move
            for (Observer observer : observers) {
                observer.update(String.format("Move: %d%d -> %d%d", x1, y1, x2, y2));
            }
            return true;
        }
        return false;
    }
}

import chess.ChessGame;
import chess.observers.GameLogger;

/**
 * Main entry point for the console-based chess game.
 */
public class ConsoleChess {
    public static void main(String[] args) {
        // Initialize a new ChessGame
        ChessGame game = new ChessGame();

        // Add a console logger to track moves
        game.addObserver(new GameLogger());

        // Print the initial chessboard
        game.printBoard();

        // Sample moves
        game.makeMove(1, 1, 1, 2); // White Pawn moves
        game.makeMove(1, 2, 1, 3); // Black Pawn moves
        game.makeMove(1, 3, 1, 4); // White Pawn moves
        game.makeMove(1, 4, 1, 5); // Black Pawn moves

        // Print the board after moves
        game.printBoard();
    }
}

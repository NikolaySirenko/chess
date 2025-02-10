package chess;

import chess.pieces.PieceFactory;

// CREATIONAL: Builder

/**
 * Builder class for creating and initializing a ChessBoard.
 */
public class ChessBoardBuilder {
    private final ChessBoard chessBoard;

    /**
     * Constructor for ChessBoardBuilder.
     */
    public ChessBoardBuilder() {
        chessBoard = new ChessBoard();
    }

    /**
     * Adds a piece to the board at the specified coordinates.
     *
     * @param type  The type of the chess piece (e.g., "Pawn", "Rook", "Knight", etc.).
     * @param color The color of the piece ("White" or "Black").
     * @param x     The x-coordinate (column index).
     * @param y     The y-coordinate (row index).
     * @return The updated builder instance.
     */
    public ChessBoardBuilder addPiece(String type, String color, int x, int y) {
        chessBoard.board[x][y] = PieceFactory.createPiece(type, color);
        return this;
    }

    /**
     * Sets up a standard chessboard layout.
     *
     * @return The updated builder instance.
     */
    public ChessBoardBuilder setupStandardBoard() {
        String[] backRow = {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"};

        // Place pawns
        for (int i = 0; i < 8; i++) {
            this.addPiece("Pawn", "White", i, 1);
            this.addPiece("Pawn", "Black", i, 6);
        }

        // Place back-row pieces for both White and Black
        for (int i = 0; i < 8; i++) {
            this.addPiece(backRow[i], "White", i, 0);
            this.addPiece(backRow[i], "Black", i, 7);
        }

        return this;
    }

    /**
     * Builds and returns the ChessBoard instance.
     *
     * @return A fully constructed ChessBoard instance.
     */
    public ChessBoard build() {
        return chessBoard;
    }
}

package chess.adapter;

import chess.pieces.Piece;

// STRUCTURAL: Adapter

/**
 * Adapter class for move validation.
 * This allows for an abstraction between different move validation mechanisms.
 */
public class MoveValidatorAdapter {

    /**
     * Validates whether a move is legal for the given chess piece.
     *
     * @param piece The chess piece attempting to move.
     * @param x1    Start X-coordinate.
     * @param y1    Start Y-coordinate.
     * @param x2    Destination X-coordinate.
     * @param y2    Destination Y-coordinate.
     * @return True if the move is valid, false otherwise.
     */
    public boolean validateMove(Piece piece, int x1, int y1, int x2, int y2) {
        if (piece == null) {
            return false; // No piece to move
        }
        return piece.isValidMove(x1, y1, x2, y2);
    }
}

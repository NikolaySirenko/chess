package chess.pieces;

/**
 * Class representing a Rook chess piece.
 */
public class Rook extends Piece {

    /**
     * Constructor for a Rook piece.
     *
     * @param color The color of the rook ("White" or "Black").
     */
    public Rook(String color) {
        super(color);
    }

    /**
     * Determines if a move is valid for a Rook.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move is in a straight line (horizontal or vertical), false otherwise.
     */
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2) {
        // The Rook can only move horizontally (same row) or vertically (same column)
        return x1 == x2 || y1 == y2;
    }
}

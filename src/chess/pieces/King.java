package chess.pieces;

/**
 * Class representing a King chess piece.
 */
public class King extends Piece {

    /**
     * Constructor for a King piece.
     *
     * @param color The color of the king ("White" or "Black").
     */
    public King(String color) {
        super(color);
    }

    /**
     * Determines if a move is valid for a King.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move is valid (one square in any direction), false otherwise.
     */
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2) {
        // The King moves exactly one square in any direction
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }
}

package chess.pieces;

/**
 * Class representing a Bishop chess piece.
 */
public class Bishop extends Piece {

    /**
     * Constructor for a Bishop piece.
     *
     * @param color The color of the bishop ("White" or "Black").
     */
    public Bishop(String color) {
        super(color);
    }

    /**
     * Determines if a move is valid for a Bishop.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move is diagonal, false otherwise.
     */
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2) {
        // A bishop moves diagonally, meaning the absolute difference between x and y must be the same
        return Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }
}

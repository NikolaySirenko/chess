package chess.pieces;

/**
 * Class representing a Queen chess piece.
 */
public class Queen extends Piece {

    /**
     * Constructor for a Queen piece.
     *
     * @param color The color of the queen ("White" or "Black").
     */
    public Queen(String color) {
        super(color);
    }

    /**
     * Determines if a move is valid for a Queen.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move is valid (horizontal, vertical, or diagonal), false otherwise.
     */
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2) {
        // The Queen can move like a Rook (straight line) or a Bishop (diagonal)
        return (x1 == x2 || y1 == y2) || (Math.abs(x1 - x2) == Math.abs(y1 - y2));
    }
}

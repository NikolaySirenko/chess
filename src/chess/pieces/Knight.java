package chess.pieces;

/**
 * Class representing a Knight chess piece.
 */
public class Knight extends Piece {

    /**
     * Constructor for a Knight piece.
     *
     * @param color The color of the knight ("White" or "Black").
     */
    public Knight(String color) {
        super(color);
    }

    /**
     * Determines if a move is valid for a Knight.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move follows an "L" shape, false otherwise.
     */
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        // A Knight moves in an "L" shape: 2 squares in one direction and 1 in the other
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}

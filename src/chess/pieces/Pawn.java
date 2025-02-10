package chess.pieces;

/**
 * Class representing a Pawn chess piece.
 */
public class Pawn extends Piece {

    /**
     * Constructor for a Pawn piece.
     *
     * @param color The color of the pawn ("White" or "Black").
     */
    public Pawn(String color) {
        super(color);
    }

    /**
     * Determines if a move is valid for a Pawn.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move is valid, false otherwise.
     */
    @Override
    public boolean isValidMove(int x1, int y1, int x2, int y2) {
        // White pawns move up (y increases), Black pawns move down (y decreases)
        int direction = color.equals("White") ? 1 : -1;

        // Standard move: Forward by one square (without capturing)
        if (x1 == x2 && y2 == y1 + direction) {
            return true;
        }

        // Initial double move: Only from the starting row (y=1 for White, y=6 for Black)
        if (x1 == x2 && y2 == y1 + 2 * direction && (y1 == (color.equals("White") ? 1 : 6))) {
            return true;
        }

        // Pawn captures diagonally: One square diagonally forward
        if (Math.abs(x1 - x2) == 1 && y2 == y1 + direction) {
            return true;
        }

        return false;
    }
}

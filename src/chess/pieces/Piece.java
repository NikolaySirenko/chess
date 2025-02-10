package chess.pieces;

/**
 * Abstract class representing a chess piece.
 */
public abstract class Piece {
    // Piece color (either "White" or "Black")
    protected String color;

    /**
     * Constructor for a chess piece.
     *
     * @param color The color of the piece ("White" or "Black").
     */
    public Piece(String color) {
        this.color = color;
    }

    /**
     * Abstract method to check if a move is valid for a specific piece.
     *
     * @param x1 Initial x-coordinate.
     * @param y1 Initial y-coordinate.
     * @param x2 Destination x-coordinate.
     * @param y2 Destination y-coordinate.
     * @return True if the move is valid, false otherwise.
     */
    public abstract boolean isValidMove(int x1, int y1, int x2, int y2);

    /**
     * Returns a short notation for the piece.
     *
     * @return Short representation of the piece.
     */
    public String shortName() {
        return (color.equals("White") ? "W" : "B") +
                (this instanceof Knight ? "N" : this.getClass().getSimpleName().charAt(0));
    }

    /**
     * Gets the color of the piece.
     *
     * @return "White" or "Black".
     */
    public String getColor() {
        return color;
    }
}

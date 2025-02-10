package chess.pieces;

// CREATIONAL: Factory Method

/**
 * Factory class for creating chess pieces.
 */
public class PieceFactory {

    /**
     * Creates a new chess piece based on the given type and color.
     *
     * @param type  The type of the chess piece (e.g., "Pawn", "Rook", "Knight", etc.).
     * @param color The color of the piece ("White" or "Black").
     * @return A new instance of the requested chess piece.
     * @throws IllegalArgumentException if the piece type is invalid.
     */
    public static Piece createPiece(String type, String color) {
        return switch (type) {
            case "Pawn" -> new Pawn(color);
            case "Rook" -> new Rook(color);
            case "Knight" -> new Knight(color);
            case "Bishop" -> new Bishop(color);
            case "Queen" -> new Queen(color);
            case "King" -> new King(color);
            default -> throw new IllegalArgumentException("Invalid piece type: " + type);
        };
    }
}

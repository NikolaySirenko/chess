package chess.composite;

import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

// STRUCTURAL: Composite

/**
 * Composite class representing a team of chess pieces.
 * Implements the Composite Pattern to manage multiple pieces as a group.
 */
public class ChessTeam {
    private final List<Piece> pieces; // List of pieces belonging to the team

    /**
     * Constructor for ChessTeam.
     * Initializes an empty list of pieces.
     */
    public ChessTeam() {
        this.pieces = new ArrayList<>();
    }

    /**
     * Adds a piece to the team.
     *
     * @param piece The chess piece to be added.
     */
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    /**
     * Removes a piece from the team.
     *
     * @param piece The chess piece to be removed.
     */
    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Checks whether a piece belongs to the team.
     *
     * @param piece The piece to check.
     * @return True if the piece is part of the team, false otherwise.
     */
    public boolean contains(Piece piece) {
        return pieces.contains(piece);
    }

    /**
     * Returns the list of all pieces in the team.
     *
     * @return List of team pieces.
     */
    public List<Piece> getPieces() {
        return new ArrayList<>(pieces); // Returns a copy to prevent modification
    }
}

package chess.observers;

// BEHAVIORAL: Observer

/**
 * Observer interface for tracking chess game moves.
 */
public interface Observer {
    /**
     * Updates the observer with the latest move.
     *
     * @param move The move in string format (e.g., "Move: 1,1 -> 1,2").
     */
    void update(String move);
}

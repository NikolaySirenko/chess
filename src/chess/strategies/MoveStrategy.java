package chess.strategies;

// BEHAVIORAL: Strategy

/**
 * Strategy interface for determining the next move in a chess game.
 */
public interface MoveStrategy {
    /**
     * Determines the next move based on the strategy's logic.
     *
     * @return An array of four integers representing the move:
     *         [startX, startY, destinationX, destinationY].
     */
    int[] nextMove();
}

package chess.strategies;

import java.util.Random;

/**
 * A simple move strategy that generates random moves.
 */
public class RandomMoveStrategy implements MoveStrategy {
    private final Random random = new Random();

    @Override
    public int[] nextMove() {
        return new int[]{
                random.nextInt(8), random.nextInt(8), // Start position
                random.nextInt(8), random.nextInt(8)  // Destination position
        };
    }
}

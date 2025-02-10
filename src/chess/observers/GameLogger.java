package chess.observers;

/**
 * Concrete observer that logs game moves to the console.
 */
public class GameLogger implements Observer {
    @Override
    public void update(String move) {
        System.out.println("Move recorded: " + move);
    }
}

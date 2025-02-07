import java.util.*;

// Class of a piece
abstract class Piece {
    // piece color
    String color;
    // piece constructor
    public Piece(String color) {
        this.color = color;
    }
    // abstract declaration of method checking validity of a move for the piece
    abstract boolean isValidMove(int x1, int y1, int x2, int y2);
}

// Class of a pawn - extends piece
class Pawn extends Piece {
    public Pawn(String color) { super(color); }
    // override of method checking validity of a move for the pawn
    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        return (color.equals("White") ? y2 == y1 + 1 : y2 == y1 - 1) && x1 == x2;
    }
}

// CREATIONAL: Factory Method
// Factory class for creating new pieces
class PieceFactory {
    public static Piece createPiece(String type, String color) {
        return switch (type) {
            case "Pawn" -> new Pawn(color);
            default -> throw new IllegalArgumentException("Invalid piece type");
        };
    }
}

// CREATIONAL: Builder Pattern for ChessBoard setup
// Class of a chessboard
class ChessBoard {
    // Two dimensional array of pieces
    private Piece[][] board;
    // chessboard constructor
    private ChessBoard() {
        board = new Piece[8][8];
    }
    // Builder class of a chessboard builder for a new chessboard initialisation
    public static class Builder {
        private ChessBoard chessBoard;
        // Builder constructor
        public Builder() {
            chessBoard = new ChessBoard();
        }
        // method for adding a new piece to the board with specified type, color and coordinates
        public Builder addPiece(String type, String color, int x, int y) {
            chessBoard.board[x][y] = PieceFactory.createPiece(type, color);
            return this;
        }

        public ChessBoard build() {
            return chessBoard;
        }
    }
}

// STRUCTURAL: Adapter Pattern for Move Validation
class MoveValidatorAdapter {
    public boolean validateMove(Piece piece, int x1, int y1, int x2, int y2) {
        return piece.isValidMove(x1, y1, x2, y2);
    }
}

// STRUCTURAL: Composite Pattern for grouping pieces
// Composite class for referring to a pieces of a team
class ChessTeam {
    // initialisation of a list of pieces
    private List<Piece> pieces = new ArrayList<>();
    // method for adding a piece to the list
    public void addPiece(Piece piece) {
        pieces.add(piece);
    }
    // method for checking whether a piece belongs to the team
    public boolean contains(Piece piece) {
        return pieces.contains(piece);
    }
}

// BEHAVIORAL: Strategy Pattern for AI Moves
// Strategy interface for the next move
interface MoveStrategy {
    int[] nextMove();
}
// Strategy class for making a random move
class RandomMoveStrategy implements MoveStrategy {
    @Override
    public int[] nextMove() {
        return new int[]{(int) (Math.random() * 8), (int) (Math.random() * 8)};
    }
}

// BEHAVIORAL: Observer Pattern for move tracking
// Observer interface for logging moves made
interface Observer {
    void update(String move);
}
// Observer class logs the move to the console
class GameLogger implements Observer {
    @Override
    public void update(String move) {
        System.out.println("Move recorded: " + move);
    }
}

// Class of a chessgame
class ChessGame {
    // List of observers for logging
    private List<Observer> observers = new ArrayList<>();
    // Adding console logging to the list of observers
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    // Notify all observers in the list with the made move
    public void makeMove(String move) {
        for (Observer observer : observers) {
            observer.update(move);
        }
    }
}

public class ConsoleChess {
    public static void main(String[] args) {
        // Building a new chessboard
        ChessBoard chessBoard = new ChessBoard.Builder()
                .addPiece("Pawn", "White", 1, 1)
                .addPiece("Pawn", "Black", 6, 6)
                .build();

        // Initialising a new chessgame
        ChessGame game = new ChessGame();
        // adding a console logger to the chessgame
        game.addObserver(new GameLogger());
        // making a move with white
        game.makeMove("Pawn from (1,1) to (1,2)");
    }
}

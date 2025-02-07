import java.util.*;

// CREATIONAL: Factory Method
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

    public String shortName() {
        return ((color.equals("White") ? "W" : "B") +
                (this instanceof Knight ? "N" : this.getClass().getSimpleName().charAt(0))
        );
    }
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

// Class of a rook - extends piece
class Rook extends Piece {
    public Rook(String color) { super(color); }
    // override of method checking validity of a move for the rook
    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2;
    }
}

// Class of a knight - extends piece
class Knight extends Piece {
    public Knight(String color) { super(color); }
    // override of method checking validity of a move for the knight
    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2);
    }
}

// Class of a bishop - extends piece
class Bishop extends Piece {
    public Bishop(String color) { super(color); }
    // override of method checking validity of a move for the bishop
    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }
}

// Class of a queen - extends piece
class Queen extends Piece {
    public Queen(String color) { super(color); }
    // override of method checking validity of a move for the queen
    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }
}

// Class of a king - extends piece
class King extends Piece {
    public King(String color) { super(color); }
    // override of method checking validity of a move for the king
    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1;
    }
}

// CREATIONAL: Factory Method
// Factory class for creating new pieces
class PieceFactory {
    public static Piece createPiece(String type, String color) {
        return switch (type) {
            case "Pawn" -> new Pawn(color);
            case "Rook" -> new Rook(color);
            case "Knight" -> new Knight(color);
            case "Bishop" -> new Bishop(color);
            case "Queen" -> new Queen(color);
            case "King" -> new King(color);
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

    // Method to check if square is not off board
    private boolean isValidBoardPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    // Method to check if path is blocked
    private boolean isPathBlocked(int x1, int y1, int x2, int y2) {
        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);
        int x = x1 + dx;
        int y = y1 + dy;

        while (x != x2 || y != y2) {
            if (board[x][y] != null) {
                return true; // Found a piece blocking the path
            }
            x += dx;
            y += dy;
        }
        return false;
    }

    // Method for moving a piece over the board
    public boolean movePiece(int x1, int y1, int x2, int y2) {
        // Prevent moving off the board
        if (!isValidBoardPosition(x1, y1) || !isValidBoardPosition(x2, y2)) {
            return false;
        }
        // Prevent invalid move
        if (board[x1][y1] == null || !board[x1][y1].isValidMove(x1, y1, x2, y2)) {
            return false;
        }
        // Prevent moving onto an allied piece
        if (board[x2][y2] != null && board[x2][y2].color.equals(board[x1][y1].color)) {
            return false;
        }
        // Prevent moving through other pieces (except for Pawn, Knight, King)
        if (!(board[x1][y1] instanceof Pawn || board[x1][y1] instanceof Knight || board[x1][y1] instanceof King)) {
            if (isPathBlocked(x1, y1, x2, y2)) {
                return false;
            }
        }

        // Store the target piece temporarily
        Piece temp = board[x2][y2];
        board[x2][y2] = board[x1][y1];
        board[x1][y1] = null;

        // Revert move if it puts the king in check
        if (isKingInCheck(board[x2][y2].color)) {
            board[x1][y1] = board[x2][y2];
            board[x2][y2] = temp;
            return false;
        }
        return true;
    }

    // Method for printing the chessboard to console
    public void printBoard() {
        System.out.println("     a      b      c      d      e      f      g      h ");
        System.out.println("  +------+------+------+------+------+------+------+------+");
        for (int y = 7; y >= 0; y--) {

            System.out.print("  |");
            for (int x = 0; x < 8; x++) {
                System.out.print(((x + y) % 2 == 0 ? "      " : "░░░░░░") + "|");
            }
            System.out.println();

            System.out.print((y + 1) + " |");
            for (int x = 0; x < 8; x++) {
                if((x + y) % 2 == 0){
                    System.out.print((board[x][y] == null ? "      " : "  " + board[x][y].shortName() + "  ") + "|");
                }
                else{
                    System.out.print((board[x][y] == null ? "░░░░░░" : "░░" + board[x][y].shortName() + "░░") + "|");
                }
            }
            System.out.println(" " + (y + 1));

            System.out.print("  |");
            for (int x = 0; x < 8; x++) {
                System.out.print(((x + y) % 2 == 0 ? "      " : "░░░░░░") + "|");
            }
            System.out.println();

            System.out.println("  +------+------+------+------+------+------+------+------+");
        }
        System.out.println("     a      b      c      d      e      f      g      h ");
        System.out.println();
        System.out.println();
    }

    // method for checking if a players king is in check
    public boolean isKingInCheck(String color) {
        // Algorithm for finding player's king coordinates
        int kingX = -1, kingY = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].color.equals(color)) {
                    kingX = i;
                    kingY = j;
                }
            }
        }
        // return false if king is absent
        if (kingX == -1) return false;
        // iterate over the chessboard
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // return true if there is an enemy piece attacking king's coordinates
                if (board[i][j] != null && !board[i][j].color.equals(color) && board[i][j].isValidMove(i, j, kingX, kingY)) {
                    return true;
                }
            }
        }
        // return false by default
        return false;
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

        public Builder setupStandardBoard() {
            // Place pawns
            for (int i = 0; i < 8; i++) {
                this.addPiece("Pawn", "White", i, 1);
                this.addPiece("Pawn", "Black", i, 6);
            }
            // Place rooks
            this.addPiece("Rook", "White", 0, 0);
            this.addPiece("Rook", "White", 7, 0);
            this.addPiece("Rook", "Black", 0, 7);
            this.addPiece("Rook", "Black", 7, 7);
            // Place knights
            this.addPiece("Knight", "White", 1, 0);
            this.addPiece("Knight", "White", 6, 0);
            this.addPiece("Knight", "Black", 1, 7);
            this.addPiece("Knight", "Black", 6, 7);
            // Place bishops
            this.addPiece("Bishop", "White", 2, 0);
            this.addPiece("Bishop", "White", 5, 0);
            this.addPiece("Bishop", "Black", 2, 7);
            this.addPiece("Bishop", "Black", 5, 7);
            // Place queens
            this.addPiece("Queen", "White", 3, 0);
            this.addPiece("Queen", "Black", 3, 7);
            // Place kings
            this.addPiece("King", "White", 4, 0);
            this.addPiece("King", "Black", 4, 7);
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
    private ChessBoard board;
    // Current turn
    private String turn;

    // ChessGame constructor
    public ChessGame() {
        // create a new chessboard with starting pieces using the builder
        board = new ChessBoard.Builder().setupStandardBoard().build();
        // set current turn to white
        turn = "White";
    }

    // List of observers for logging
    private List<Observer> observers = new ArrayList<>();
    // Adding console logging to the list of observers
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void printBoard() {
        board.printBoard();
    }

    // Notify all observers in the list with the made move
    public boolean makeMove(int x1, int y1, int x2, int y2) {
        if (board.movePiece(x1, y1, x2, y2)) {
            turn = turn.equals("White") ? "Black" : "White";
            if (board.isKingInCheck(turn)) {
                System.out.println(turn + " King is in check!");
            }
            for (Observer observer : observers) {
                observer.update(String.format("Move: %d%d %d%d", x1, y1, x2, y2));
            }
            return true;
        }
        return false;
    }
}

public class ConsoleChess {
    public static void main(String[] args) {

        // Initialising a new chessgame
        ChessGame game = new ChessGame();
        // adding a console logger to the chessgame
        game.addObserver(new GameLogger());

        game.printBoard();
        game.makeMove(1, 1, 1, 2);
        game.makeMove(1, 2, 1, 3);
        game.makeMove(1, 3, 1, 4);
        game.makeMove(1, 4, 1, 5);

        game.printBoard();
    }
}

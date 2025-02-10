package chess;

import chess.pieces.King;
import chess.pieces.Piece;

/**
 * Class representing a Chessboard.
 */
public class ChessBoard {
    protected final Piece[][] board; // 8x8 board grid

    /**
     * Constructor for ChessBoard (only accessible by ChessBoardBuilder).
     */
    protected ChessBoard() {
        board = new Piece[8][8];
    }

    /**
     * Checks if the given position is within the board limits.
     *
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return True if within valid board limits, false otherwise.
     */
    private boolean isValidBoardPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    /**
     * Checks if the path between two positions is blocked (except for Knight, King, and Pawn).
     *
     * @param x1 Start X-coordinate.
     * @param y1 Start Y-coordinate.
     * @param x2 Destination X-coordinate.
     * @param y2 Destination Y-coordinate.
     * @return True if the path is blocked, false otherwise.
     */
    private boolean isPathBlocked(int x1, int y1, int x2, int y2) {
        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);
        int x = x1 + dx;
        int y = y1 + dy;

        while (x != x2 || y != y2) {
            if (board[x][y] != null) {
                return true; // A piece is blocking the path
            }
            x += dx;
            y += dy;
        }
        return false;
    }

    /**
     * Moves a piece on the chessboard if the move is valid.
     *
     * @param x1 Start X-coordinate.
     * @param y1 Start Y-coordinate.
     * @param x2 Destination X-coordinate.
     * @param y2 Destination Y-coordinate.
     * @return True if the move is successful, false otherwise.
     */
    public boolean movePiece(int x1, int y1, int x2, int y2) {
        if (!isValidBoardPosition(x1, y1) || !isValidBoardPosition(x2, y2)) {
            return false; // Move out of board bounds
        }

        Piece piece = board[x1][y1];
        if (piece == null || !piece.isValidMove(x1, y1, x2, y2)) {
            return false; // No piece to move or invalid move
        }

        if (board[x2][y2] != null && board[x2][y2].getColor().equals(piece.getColor())) {
            return false; // Cannot capture own piece
        }

        if (!(piece instanceof King) && isPathBlocked(x1, y1, x2, y2)) {
            return false; // Path-blocking rule applies to most pieces
        }

        // Store captured piece temporarily
        Piece temp = board[x2][y2];
        board[x2][y2] = piece;
        board[x1][y1] = null;

        // Prevent moves that put the player's king in check
        if (isKingInCheck(piece.getColor())) {
            board[x1][y1] = piece;
            board[x2][y2] = temp;
            return false; // Illegal move (would leave the King in check)
        }
        return true;
    }

    /**
     * Prints the chessboard to the console.
     */
    public void printBoard() {
        System.out.println("       A       B       C       D       E       F       G       H ");
        System.out.println("   ╔═══╩═══════╩═══════╩═══════╩═══════╩═══════╩═══════╩═══════╩════╗");
        for (int y = 7; y >= 0; y--) {
            System.out.print("   ║");
            for (int x = 0; x < 8; x++) {
                System.out.print(((x + y) % 2 == 0 ? "░░░░░░░░" : "████████"));
            }
            System.out.print("║\n");

            System.out.print((y + 1) + " ╞╣");
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0) {
                    System.out.print((board[x][y] == null ? "░░░░░░░░" : "░░|" + board[x][y].shortName() + "|░░"));
                } else {
                    System.out.print((board[x][y] == null ? "████████" : "██|" + board[x][y].shortName() + "|██"));
                }
            }
            System.out.println("╠╡ " + (y + 1));

            System.out.print("   ║");
            for (int x = 0; x < 8; x++) {
                System.out.print(((x + y) % 2 == 0 ? "░░░░░░░░" : "████████"));
            }
            System.out.print("║\n");
        }
        System.out.println("   ╚═══╦═══════╦═══════╦═══════╦═══════╦═══════╦═══════╦═══════╦════╝");
        System.out.println("       A       B       C       D       E       F       G       H \n");
    }

    /**
     * Checks if a player's King is in check.
     *
     * @param color The player's color ("White" or "Black").
     * @return True if the King is in check, false otherwise.
     */
    public boolean isKingInCheck(String color) {
        int kingX = -1, kingY = -1;

        // Locate the king on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].getColor().equals(color)) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }

        if (kingX == -1) return false; // King not found (should never happen)

        // Check if any opponent piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && !board[i][j].getColor().equals(color) &&
                        board[i][j].isValidMove(i, j, kingX, kingY)) {
                    return true; // King is in check
                }
            }
        }
        return false;
    }
}

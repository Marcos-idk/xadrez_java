package xadrez;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean[][] possibleMoves(Board board) {
        boolean[][] moves = new boolean[8][8];

        int row = position.getRow();
        int col = position.getColumn();

        if (color == Color.WHITE) {

            if (board.positionExists(row - 1, col) && !board.hasPiece(row - 1, col)) {
                moves[row - 1][col] = true;
            }

            if (row == 6 && !board.hasPiece(row - 1, col) && !board.hasPiece(row - 2, col)) {
                moves[row - 2][col] = true;
            }

            if (board.positionExists(row - 1, col - 1) &&
                board.hasPiece(row - 1, col - 1) &&
                board.getPiece(row - 1, col - 1).getColor() != color) {

                moves[row - 1][col - 1] = true;
            }

            if (board.positionExists(row - 1, col + 1) &&
                board.hasPiece(row - 1, col + 1) &&
                board.getPiece(row - 1, col + 1).getColor() != color) {

                moves[row - 1][col + 1] = true;
            }
        }

        if (color == Color.BLACK) {

            if (board.positionExists(row + 1, col) && !board.hasPiece(row + 1, col)) {
                moves[row + 1][col] = true;
            }

            if (row == 1 && !board.hasPiece(row + 1, col) && !board.hasPiece(row + 2, col)) {
                moves[row + 2][col] = true;
            }

            if (board.positionExists(row + 1, col - 1) &&
                board.hasPiece(row + 1, col - 1) &&
                board.getPiece(row + 1, col - 1).getColor() != color) {

                moves[row + 1][col - 1] = true;
            }

            if (board.positionExists(row + 1, col + 1) &&
                board.hasPiece(row + 1, col + 1) &&
                board.getPiece(row + 1, col + 1).getColor() != color) {

                moves[row + 1][col + 1] = true;
            }
        }

        return moves;
    }
}
package xadrez;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean[][] possibleMoves(Board board) {
        boolean[][] moves = new boolean[8][8];

        int row = position.getRow();
        int col = position.getColumn();

        // CIMA
        for (int i = row - 1; i >= 0; i--) {
            if (!board.hasPiece(i, col)) {
                moves[i][col] = true;
            } else {
                if (board.getPiece(i, col).getColor() != this.color) {
                    moves[i][col] = true;
                }
                break;
            }
        }

        // BAIXO
        for (int i = row + 1; i < 8; i++) {
            if (!board.hasPiece(i, col)) {
                moves[i][col] = true;
            } else {
                if (board.getPiece(i, col).getColor() != this.color) {
                    moves[i][col] = true;
                }
                break;
            }
        }

        // ESQUERDA
        for (int j = col - 1; j >= 0; j--) {
            if (!board.hasPiece(row, j)) {
                moves[row][j] = true;
            } else {
                if (board.getPiece(row, j).getColor() != this.color) {
                    moves[row][j] = true;
                }
                break;
            }
        }

        // DIREITA
        for (int j = col + 1; j < 8; j++) {
            if (!board.hasPiece(row, j)) {
                moves[row][j] = true;
            } else {
                if (board.getPiece(row, j).getColor() != this.color) {
                    moves[row][j] = true;
                }
                break;
            }
        }

        return moves;
    }
}
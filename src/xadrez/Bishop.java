package xadrez;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean[][] possibleMoves(Board board) {
        boolean[][] moves = new boolean[8][8];

        int row = position.getRow();
        int col = position.getColumn();

        // CIMA ESQUERDA
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0) {
            if (!board.hasPiece(i, j)) {
                moves[i][j] = true;
            } else {
                if (board.getPiece(i, j).getColor() != this.color) {
                    moves[i][j] = true;
                }
                break;
            }
            i--;
            j--;
        }

        // CIMA DIREITA
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < 8) {
            if (!board.hasPiece(i, j)) {
                moves[i][j] = true;
            } else {
                if (board.getPiece(i, j).getColor() != this.color) {
                    moves[i][j] = true;
                }
                break;
            }
            i--;
            j++;
        }

        // BAIXO ESQUERDA
        i = row + 1;
        j = col - 1;
        while (i < 8 && j >= 0) {
            if (!board.hasPiece(i, j)) {
                moves[i][j] = true;
            } else {
                if (board.getPiece(i, j).getColor() != this.color) {
                    moves[i][j] = true;
                }
                break;
            }
            i++;
            j--;
        }

        // BAIXO DIREITA
        i = row + 1;
        j = col + 1;
        while (i < 8 && j < 8) {
            if (!board.hasPiece(i, j)) {
                moves[i][j] = true;
            } else {
                if (board.getPiece(i, j).getColor() != this.color) {
                    moves[i][j] = true;
                }
                break;
            }
            i++;
            j++;
        }

        return moves;
    }
}
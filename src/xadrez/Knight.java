package xadrez;

public class Knight extends Piece {

    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean[][] possibleMoves(Board board) {
        boolean[][] moves = new boolean[8][8];

        int row = position.getRow();
        int col = position.getColumn();

        int[][] directions = {
            {-2, -1}, {-2, +1},
            {-1, -2}, {-1, +2},
            {+1, -2}, {+1, +2},
            {+2, -1}, {+2, +1}
        };

        for (int[] d : directions) {
            int newRow = row + d[0];
            int newCol = col + d[1];

            if (board.positionExists(newRow, newCol)) {
                if (!board.hasPiece(newRow, newCol)) {
                    moves[newRow][newCol] = true;
                } else {
                    if (board.getPiece(newRow, newCol).getColor() != this.color) {
                        moves[newRow][newCol] = true; // captura
                    }
                }
            }
        }

        return moves;
    }
}
package xadrez;

public class Board {
    private Piece[][] pieces;

    public Board() {
        pieces = new Piece[8][8];
    }

    public Piece getPiece(int row, int column) {
        return pieces[row][column];
    }

    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()][position.getColumn()] = piece;
    }

    public boolean positionExists(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean hasPiece(int row, int col) {
        return getPiece(row, col) != null;
    }
}
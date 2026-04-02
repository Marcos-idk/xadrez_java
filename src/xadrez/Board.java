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
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        Piece aux = getPiece(position.getRow(), position.getColumn());
        if (aux != null) {
            pieces[position.getRow()][position.getColumn()] = null;
        }
        return aux;
    }

    public void movePiece(Position source, Position target) {
        Piece piece = removePiece(source);
        removePiece(target); // captura
        placePiece(piece, target);
    }

    public boolean positionExists(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean hasPiece(int row, int col) {
        return getPiece(row, col) != null;
    }

    // 🔥 POSIÇÃO INICIAL
    public void setupInitialPosition() {

        // BRANCAS
        placePiece(new Rook(Color.WHITE, new Position(7, 0)), new Position(7, 0));
        placePiece(new Knight(Color.WHITE, new Position(7, 1)), new Position(7, 1));
        placePiece(new Bishop(Color.WHITE, new Position(7, 2)), new Position(7, 2));
        placePiece(new Queen(Color.WHITE, new Position(7, 3)), new Position(7, 3));
        placePiece(new King(Color.WHITE, new Position(7, 4)), new Position(7, 4));
        placePiece(new Bishop(Color.WHITE, new Position(7, 5)), new Position(7, 5));
        placePiece(new Knight(Color.WHITE, new Position(7, 6)), new Position(7, 6));
        placePiece(new Rook(Color.WHITE, new Position(7, 7)), new Position(7, 7));

        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn(Color.WHITE, new Position(6, i)), new Position(6, i));
        }

        // PRETAS
        placePiece(new Rook(Color.BLACK, new Position(0, 0)), new Position(0, 0));
        placePiece(new Knight(Color.BLACK, new Position(0, 1)), new Position(0, 1));
        placePiece(new Bishop(Color.BLACK, new Position(0, 2)), new Position(0, 2));
        placePiece(new Queen(Color.BLACK, new Position(0, 3)), new Position(0, 3));
        placePiece(new King(Color.BLACK, new Position(0, 4)), new Position(0, 4));
        placePiece(new Bishop(Color.BLACK, new Position(0, 5)), new Position(0, 5));
        placePiece(new Knight(Color.BLACK, new Position(0, 6)), new Position(0, 6));
        placePiece(new Rook(Color.BLACK, new Position(0, 7)), new Position(0, 7));

        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn(Color.BLACK, new Position(1, i)), new Position(1, i));
        }
    }

    // 🔥 ENCONTRAR REI
    public Position findKing(Color color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = getPiece(i, j);

                if (p instanceof King && p.getColor() == color) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    // 🔥 XEQUE
    public boolean isCheck(Color color) {

        Position kingPos = findKing(color);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece p = getPiece(i, j);

                if (p != null && p.getColor() != color) {
                    boolean[][] moves = p.possibleMoves(this);

                    if (moves[kingPos.getRow()][kingPos.getColumn()]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 🔥 XEQUE-MATE
    public boolean isCheckmate(Color color) {

        if (!isCheck(color)) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece p = getPiece(i, j);

                if (p != null && p.getColor() == color) {

                    boolean[][] moves = p.possibleMoves(this);

                    for (int r = 0; r < 8; r++) {
                        for (int c = 0; c < 8; c++) {

                            if (moves[r][c]) {

                                Position source = new Position(i, j);
                                Position target = new Position(r, c);

                                Piece captured = getPiece(r, c);

                                movePiece(source, target);

                                boolean stillInCheck = isCheck(color);

                                // desfaz movimento
                                movePiece(target, source);
                                placePiece(captured, target);

                                if (!stillInCheck) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
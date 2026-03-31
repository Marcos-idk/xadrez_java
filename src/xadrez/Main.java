package xadrez;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();

        Position rookPosition = new Position(3, 3);
        Piece rook = new Rook(Color.WHITE, rookPosition);

        board.placePiece(rook, rookPosition);

        // peça inimiga (para testar captura)
        Piece enemy = new Rook(Color.BLACK, new Position(1, 3));
        board.placePiece(enemy, new Position(1, 3));

        boolean[][] moves = rook.possibleMoves(board);

        System.out.println("Movimentos possíveis da Torre:\n");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(moves[i][j] ? "X " : "- ");
            }
            System.out.println();
        }
    }
}
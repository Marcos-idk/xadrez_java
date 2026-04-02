package xadrez;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Board board = new Board();
        board.setupInitialPosition();

        Color currentPlayer = Color.WHITE;

        while (true) {

            printBoard(board);
            System.out.println("\nTurno: " + currentPlayer);

            // 🔥 INPUT ORIGEM VALIDADO
            String input;
            while (true) {
                System.out.print("Origem (ex: A2): ");
                input = sc.next().toUpperCase();

                if (input.length() == 2 &&
                    input.charAt(0) >= 'A' && input.charAt(0) <= 'H' &&
                    input.charAt(1) >= '1' && input.charAt(1) <= '8') {
                    break;
                }

                System.out.println("Entrada inválida! Use formato A1 até H8.");
            }

            int row = 8 - Character.getNumericValue(input.charAt(1));
            int col = input.charAt(0) - 'A';

            if (!board.positionExists(row, col)) {
                System.out.println("Posição inválida!");
                continue;
            }

            Piece piece = board.getPiece(row, col);

            if (piece == null) {
                System.out.println("Não há peça nessa posição!");
                continue;
            }

            if (piece.getColor() != currentPlayer) {
                System.out.println("Essa peça não é sua!");
                continue;
            }

            boolean[][] moves = piece.possibleMoves(board);

            // 🔥 VERIFICA SE TEM ALGUM MOVIMENTO POSSÍVEL
            boolean hasMove = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (moves[i][j]) {
                        hasMove = true;
                    }
                }
            }

            if (!hasMove) {
                System.out.println("Essa peça não possui movimentos possíveis!");
                continue;
            }

            printMoves(moves);

            // 🔥 ESCOLHER OUTRA PEÇA (VALIDADO)
            String opcao;
            while (true) {
                System.out.print("Deseja escolher outra peça? (s/n): ");
                opcao = sc.next().toLowerCase();

                if (opcao.equals("s") || opcao.equals("n")) {
                    break;
                }

                System.out.println("Digite apenas 's' ou 'n'!");
            }

            if (opcao.equals("s")) {
                continue;
            }

            // 🔥 INPUT DESTINO VALIDADO
            String target;
            while (true) {
                System.out.print("Destino (ex: A4): ");
                target = sc.next().toUpperCase();

                if (target.length() == 2 &&
                    target.charAt(0) >= 'A' && target.charAt(0) <= 'H' &&
                    target.charAt(1) >= '1' && target.charAt(1) <= '8') {
                    break;
                }

                System.out.println("Entrada inválida! Use formato A1 até H8.");
            }

            int newRow = 8 - Character.getNumericValue(target.charAt(1));
            int newCol = target.charAt(0) - 'A';

            if (!board.positionExists(newRow, newCol)) {
                System.out.println("Posição inválida!");
                continue;
            }

            if (!moves[newRow][newCol]) {
                System.out.println("Movimento inválido!");
                continue;
            }

            // 🔥 MOVIMENTO + CAPTURA
            Piece captured = board.getPiece(newRow, newCol);

            board.movePiece(new Position(row, col), new Position(newRow, newCol));

            if (captured != null) {
                System.out.println("Você capturou uma peça!");
            }

            // 🔄 troca turno
            currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
    }

    // 🔥 TABULEIRO
    public static void printBoard(Board board) {
        System.out.println("\n  A B C D E F G H");

        for (int i = 0; i < 8; i++) {
            int displayRow = 8 - i;
            System.out.print(displayRow + " ");

            for (int j = 0; j < 8; j++) {
                Piece p = board.getPiece(i, j);

                if (p == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(getSymbol(p) + " ");
                }
            }
            System.out.println();
        }
    }

    // 🔥 MOVIMENTOS
    public static void printMoves(boolean[][] moves) {
        System.out.println("\nMovimentos possíveis:");

        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(moves[i][j] ? "X " : "- ");
            }
            System.out.println();
        }

        System.out.println("  A B C D E F G H");
    }

    // 🔥 SÍMBOLOS
    public static String getSymbol(Piece p) {
        if (p instanceof King) return "K";
        if (p instanceof Queen) return "Q";
        if (p instanceof Rook) return "R";
        if (p instanceof Bishop) return "B";
        if (p instanceof Knight) return "N";
        if (p instanceof Pawn) return "P";
        return "?";
    }
}
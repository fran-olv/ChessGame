package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;


public class UI {
	//é preciso que o terminal seja preto e colorido como o do GITBash (IR na pasta Bin do Projeto e abrir o GitBash apartir dali)
	
	//codigos retirados de https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	//cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
//cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
		String s = sc.nextLine();
		char column = s.charAt(0);
		int row = Integer.parseInt(s.substring(1));
		return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are form a1 to h8");
		}
	}
	
	// printar a partida com o jogador da vez e o turno, e as pecas que foram capturadas
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println("");
		System.out.println("Turn : " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
		
		//se estivermos em xeeque
		if (!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
				
			}
		}
		else {
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
		
	}
	
	
	//Printa na tela o tabuleiro de xadrez onde as colunas sao mapeadas letras e as linhas por numeros
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false); //pecas nao tem o fundo colorido
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	//printa durante a moviemntacao de uma peca os possiveis destinos que essa peca pode ter.
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]); 
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	//COLORE AS PEÇAS E O TABULEIRO
	private static void printPiece(ChessPiece piece, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);  //ANSI_YELLOW  //trocar para branca ser amarela e preta ser preta
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);  //ANSI_BLACK
            }
        }
        System.out.print(" ");
	}
	
	//imprimir as pecas capturadas
	private static void printCapturedPieces(List<ChessPiece> captured) { // utilizacando filtro de lista para implementar 
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList()); //filtrando brancas
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList()); //filtrando pretas
		
		System.out.println("Captured Pieces: ");
		
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.print("Black");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
		
	}
	
	
}

